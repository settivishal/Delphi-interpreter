package org.example.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;
import java.util.regex.*;
import java.io.IOException;
import java.nio.file.*;


public class LLVMCodeGenerator extends delphiBaseVisitor<Object> {
    // IR Generation State
    private List<String> irCode = new ArrayList<>();
    private int tempCounter = 0;
    private int labelCounter = 0;
    private Map<String, String> symbolTable = new HashMap<>();
    private String currentFunction = null;

    // Class Support
    private Map<String, LLVMClass> classInfo = new HashMap<>();
    private String currentClassName = null;

    // Helper Classes
    private static class LLVMClass {
        String name;
        Map<String, String> fields = new HashMap<>();
        Map<String, LLVMMethod> methods = new HashMap<>();
        boolean hasConstructor = false;

        LLVMClass(String name) {
            this.name = name;
        }
    }

    private static class LLVMMethod {
        String returnType;
        List<String> paramTypes = new ArrayList<>();

        LLVMMethod(String returnType) {
            this.returnType = returnType;
        }
    }

    // Main Generation Entry Point
    public String generateIR() {
        emitHeader();

        // Emit string literals
        for (Map.Entry<String, String> entry : stringLiterals.entrySet()) {
            String escaped = escapeString(entry.getValue());
            emit(entry.getKey() + " = private unnamed_addr constant [" +
                    (entry.getValue().length()+1) + " x i8] c\"" + escaped + "\\00\"");
        }
        emit("");

        // Emit the rest of the code
        return String.join("\n", irCode);
    }

    private String escapeString(String str) {
        // Simple escape for special characters
        return str.replace("\\", "\\5C")
                .replace("\n", "\\0A")
                .replace("\t", "\\09")
                .replace("\"", "\\22")
                .replace("'", "\\27");
    }

    private void emit(String code) {
        irCode.add(code);
    }

    private String newTemp() {
        return "%" + (++tempCounter);
    }

    private String newLabel() {
        return "label" + (++labelCounter);
    }

    private Map<String, String> stringLiterals = new HashMap<>();
    private int stringLiteralCounter = 0;

    private void emitHeader() {
        emit("; LLVM IR for Extended Pascal/Delphi");
        emit("target datalayout = \"e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128\"");
        emit("target triple = \"x86_64-pc-linux-gnu\"");
        emit("declare noalias i8* @malloc(i64)");
        emit("declare void @free(i8*)");
        emit("declare void @writeln_i32(i32)");  // For integers
        emit("declare void @writeln_str(i8*)");  // For strings
        emit("");
    }

    // Type Mapping
    private String mapType(String pascalType) {
        switch (pascalType.toLowerCase()) {
            case "integer": return "i32";
            case "real":    return "double";
            case "boolean": return "i1";
            case "char":    return "i8";
            case "string":  return "i8*";
            default:
                if (classInfo.containsKey(pascalType)) {
                    return "%struct." + pascalType + "*";
                }
                return "i32";
        }
    }

    // Program Structure
    @Override
    public Object visitProgram(delphiParser.ProgramContext ctx) {
        emit("; Program: " + ctx.programHeading().identifier().getText());
        visitChildren(ctx);

        // Only add main if not already present
        if (!irCode.stream().anyMatch(s -> s.contains("define i32 @main"))) {
            emitMainFunction();
        }
        return null;
    }

    private void emitMainFunction() {
        emit("\ndefine i32 @main() {");
        emit("entry:");

        // Visit all statements in the program block
        for (String varName : symbolTable.keySet()) {
            if (!varName.startsWith("%")) {
                emit("%" + varName + " = alloca " + symbolTable.get(varName));
                emit("store " + symbolTable.get(varName) + " " +
                        getDefaultValue(symbolTable.get(varName)) + ", " +
                        symbolTable.get(varName) + "* %" + varName);
            }
        }

        // Move all global statements into main
        List<String> newIrCode = new ArrayList<>();
        List<String> mainBody = new ArrayList<>();

        for (String line : irCode) {
            if (line.startsWith("store") || line.startsWith("%") &&
                    !line.contains("@")) {
                mainBody.add(line);
            } else {
                newIrCode.add(line);
            }
        }

        mainBody.add("ret i32 0");
        newIrCode.add("}");

        irCode = newIrCode;
        irCode.addAll(irCode.size() - 1, mainBody);
    }

    // Class Support
    @Override
    public Object visitClassDeclarationPart(delphiParser.ClassDeclarationPartContext ctx) {
        currentClassName = ctx.identifier().getText();
        LLVMClass classDef = new LLVMClass(currentClassName);
        classInfo.put(currentClassName, classDef);

        // Start class definition
        emit("%struct." + currentClassName + " = type {");

        // Visit class contents
        visit(ctx.classBlock());

        // Close class definition
        if (classDef.fields.isEmpty()) {
            emit("i8"); // Empty structs need at least one field
        }
        emit("}\n");

        // Generate constructor if needed
        if (classDef.hasConstructor) {
            emitConstructor(classDef);
        }

        currentClassName = null;
        return null;
    }

    private void emitConstructor(LLVMClass classDef) {
        String className = classDef.name;
        emit("define %struct." + className + "* @" + className + "_create() {");
        emit("entry:");

        // Calculate size
        int size = classDef.fields.values().stream()
                .mapToInt(t -> t.equals("double") ? 8 : 4)
                .sum();

        // Allocate memory
        String mallocResult = newTemp();
        emit(mallocResult + " = call noalias i8* @malloc(i64 " + size + ")");

        // Cast to class type
        String objectPtr = newTemp();
        emit(objectPtr + " = bitcast i8* " + mallocResult + " to %struct." + className + "*");

        // Initialize fields
        int index = 0;
        for (Map.Entry<String, String> field : classDef.fields.entrySet()) {
            String fieldPtr = newTemp();
            emit(fieldPtr + " = getelementptr %struct." + className +
                    ", %struct." + className + "* " + objectPtr + ", i32 0, i32 " + index++);
            emit("store " + field.getValue() + " " + getDefaultValue(field.getValue()) +
                    ", " + field.getValue() + "* " + fieldPtr);
        }

        emit("ret %struct." + className + "* " + objectPtr);
        emit("}\n");
    }

    private String getDefaultValue(String llvmType) {
        switch (llvmType) {
            case "i32": return "0";
            case "double": return "0.0";
            case "i1": return "false";
            default: return "null";
        }
    }

    @Override
    public Object visitClassVariableDeclarationPart(delphiParser.ClassVariableDeclarationPartContext ctx) {
        if (currentClassName == null) return null;

        String[] parts = ctx.getChild(2).getText().split(":");
        String fieldName = parts[0].trim();
        String pascalType = parts[1].trim();
        String llvmType = mapType(pascalType);

        // Add to class definition
        LLVMClass classDef = classInfo.get(currentClassName);
        classDef.fields.put(fieldName, llvmType);

        // Add to IR struct definition
        if (classDef.fields.size() > 1) {
            irCode.set(irCode.size() - 1, irCode.get(irCode.size() - 1) + ",");
        }
        emit(llvmType + " ; " + fieldName);

        return null;
    }

    // Methods and Functions
    @Override
    public Object visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx) {
        if (currentClassName != null) {
            classInfo.get(currentClassName).hasConstructor = true;
        }
        return null;
    }

    @Override
    public Object visitClassFunctionDeclaration(delphiParser.ClassFunctionDeclarationContext ctx) {
        if (currentClassName == null) return null;

        String input = ctx.getText();
        Pattern pattern = Pattern.compile("function(\\w+):(\\w+);");
        Matcher matcher = pattern.matcher(input);
        String funcName;
        String returnType;

        if (matcher.find()) {
            // Extract function name and return type
            funcName = matcher.group(1); // Matches the function name (e.g., getPrice)
            returnType = matcher.group(2);  // Matches the return type (e.g., integer)

            // Create method info
            LLVMMethod method = new LLVMMethod(returnType);
            classInfo.get(currentClassName).methods.put(funcName, method);

            // Generate method
            emit("define " + returnType + " @" + currentClassName + "_" + funcName +
                    "(%struct." + currentClassName + "* %this) {");
            emit("entry:");

            // TODO: Implement method body

            if (!returnType.equals("void")) {
                emit("ret " + returnType + " " + getDefaultValue(returnType));
            } else {
                emit("ret void");
            }
            emit("}\n");
            return null;

        } else {
            System.out.println("No matching function found in the input string.");
            return null;
        }

    }

    // Variables and Memory
    @Override
    public Object visitVariableDeclarationPart(delphiParser.VariableDeclarationPartContext ctx) {
        String[] parts = ctx.getChild(1).getText().split(":");
        String varName = parts[0].trim();
        String pascalType = parts[1].trim();
        String llvmType = mapType(pascalType);

        // Add to symbol table
        symbolTable.put(varName, llvmType);

        // Emit allocation
        if (currentFunction != null) {
            emit("%" + varName + " = alloca " + llvmType);
        } else {
            emit("@" + varName + " = global " + llvmType + " " + getDefaultValue(llvmType));
        }

        return null;
    }

    // Statements
    @Override
    public Object visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx) {
        String[] parts = ctx.getText().split(":=");
        String lhs = parts[0].trim();
        String rhs = parts[1].trim();

        if (!symbolTable.containsKey(lhs)) return null;

        String llvmType = symbolTable.get(lhs);
        String value;

        if (isArithmeticExpression(rhs)) {
            value = emitArithmeticExpression(rhs, llvmType);
        } else {
            value = evaluateRHS(rhs, llvmType);
        }

        if (currentFunction != null) {
            emit("store " + llvmType + " " + value + ", " + llvmType + "* %" + lhs);
        } else {
            emit("store " + llvmType + " " + value + ", " + llvmType + "* @" + lhs);
        }

        return null;
    }

    private boolean isArithmeticExpression(String expr) {
        return expr.matches(".*[+\\-*/].*");
    }

    private String emitArithmeticExpression(String expr, String llvmType) {
        Matcher m = Pattern.compile("([^+\\-*/]+)([+\\-*/])(.+)").matcher(expr);
        if (!m.matches()) return evaluateRHS(expr, llvmType);

        String left = evaluateRHS(m.group(1).trim(), llvmType);
        String right = evaluateRHS(m.group(3).trim(), llvmType);
        String op = m.group(2);

        String result = newTemp();
        switch (op) {
            case "+": emit(result + " = add " + llvmType + " " + left + ", " + right); break;
            case "-": emit(result + " = sub " + llvmType + " " + left + ", " + right); break;
            case "*": emit(result + " = mul " + llvmType + " " + left + ", " + right); break;
            case "/":
                if (llvmType.equals("double")) {
                    emit(result + " = fdiv " + llvmType + " " + left + ", " + right);
                } else {
                    emit(result + " = sdiv " + llvmType + " " + left + ", " + right);
                }
                break;
        }
        return result;
    }

    private String evaluateRHS(String expr, String expectedType) {
        // Literal numbers
        if (expr.matches("\\d+")) return expr;
        if (expr.matches("\\d+\\.\\d+")) return expr;

        // Variables
        if (symbolTable.containsKey(expr)) {
            String temp = newTemp();
            if (currentFunction != null) {
                emit(temp + " = load " + expectedType + ", " + expectedType + "* %" + expr);
            } else {
                emit(temp + " = load " + expectedType + ", " + expectedType + "* @" + expr);
            }
            return temp;
        }

        // Default value
        return getDefaultValue(expectedType);
    }

    // Control Flow
    @Override
    public Object visitIfStatement(delphiParser.IfStatementContext ctx) {
        String cond = visitExpression(ctx.expression(), "i1");
        String trueLabel = newLabel();
        String falseLabel = newLabel();
        String endLabel = newLabel();

        emit("br i1 " + cond + ", label %" + trueLabel + ", label %" + falseLabel);

        emit("\n" + trueLabel + ":");
        visit(ctx.statement(0));
        emit("br label %" + endLabel);

        emit("\n" + falseLabel + ":");
        if (ctx.ELSE() != null) {
            visit(ctx.statement(1));
        }
        emit("br label %" + endLabel);

        emit("\n" + endLabel + ":");
        return null;
    }

    @Override
    public Object visitWhileStatement(delphiParser.WhileStatementContext ctx) {
        String startLabel = newLabel();
        String condLabel = newLabel();
        String bodyLabel = newLabel();
        String endLabel = newLabel();

        emit("br label %" + condLabel);

        emit("\n" + condLabel + ":");
        String cond = visitExpression(ctx.expression(), "i1");
        emit("br i1 " + cond + ", label %" + bodyLabel + ", label %" + endLabel);

        emit("\n" + bodyLabel + ":");
        visit(ctx.statement());
        emit("br label %" + condLabel);

        emit("\n" + endLabel + ":");
        return null;
    }

    private String visitExpression(delphiParser.ExpressionContext ctx, String expectedType) {
        // Simplified - would need full expression handling
        String text = ctx.getText();

        if (text.contains("=")) {
            String[] parts = text.split("=");
            String left = evaluateRHS(parts[0].trim(), expectedType);
            String right = evaluateRHS(parts[1].trim(), expectedType);
            String result = newTemp();
            emit(result + " = icmp eq " + expectedType + " " + left + ", " + right);
            return result;
        }

        return evaluateRHS(text, expectedType);
    }

    // I/O Operations
    @Override
    public Object visitProcedureStatement(delphiParser.ProcedureStatementContext ctx) {
        if (ctx.getText().toUpperCase().startsWith("WRITELN")) {
            // Extract the content inside parentheses
            String content = ctx.getText().substring(ctx.getText().indexOf('(') + 1, ctx.getText().lastIndexOf(')')).trim();

            if (content.startsWith("'") && content.endsWith("'")) {
                // Handle string literal (Pascal-style single quotes)
                String strContent = content.substring(1, content.length()-1);
                String literalName = registerStringLiteral(strContent);
                String temp = newTemp();
                emit(temp + " = getelementptr inbounds [" + (strContent.length()+1) +
                        " x i8], [" + (strContent.length()+1) + " x i8]* " + literalName + ", i64 0, i64 0");
                emit("call void @writeln_str(i8* " + temp + ")");
            } else {
                // Handle integer/expression case
                String val = evaluateRHS(content, "i32");
                emit("call void @writeln_i32(i32 " + val + ")");
            }
        }
        return null;
    }

    private String registerStringLiteral(String content) {
        String literalName = "@.str." + (++stringLiteralCounter);
        stringLiterals.put(literalName, content);
        return literalName;
    }

    // Main Function
    public static void main(String[] args) {
        try {
            // Read input file
            String inputFile = args.length > 0 ? args[0] : "input.pas";
            String pascalCode = new String(Files.readAllBytes(Paths.get(inputFile)));

            // Parse
            delphiLexer lexer = new delphiLexer(CharStreams.fromString(pascalCode));
            delphiParser parser = new delphiParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.program();

            // Generate LLVM IR
            LLVMCodeGenerator generator = new LLVMCodeGenerator();
            generator.visit(tree);
            String llvmIR = generator.generateIR();

            // Write output
            String outputFile = args.length > 1 ? args[1] : "output.ll";
            Files.write(Paths.get(outputFile), llvmIR.getBytes());

            System.out.println("Successfully generated LLVM IR: " + outputFile);
        } catch (Exception e) {
            System.err.println("Error during compilation:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}