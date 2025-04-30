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

    private static class LoopContext {
        String condLabel;
        String bodyLabel;
        String endLabel;

        LoopContext(String cond, String body, String end) {
            this.condLabel = cond;
            this.bodyLabel = body;
            this.endLabel = end;
        }
    }

    private Stack<LoopContext> loopStack = new Stack<>();

    // Main Generation Entry Point
    public String generateIR() {
        // Create a new list to hold the final IR
        List<String> finalIR = new ArrayList<>();

        // 1. Add header first
        finalIR.add("; LLVM IR for Extended Pascal/Delphi");
        finalIR.add("target datalayout = \"e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128\"");
        finalIR.add("target triple = \"wasm32-unknown-unknown\"");
        finalIR.add("");

        // Function declarations with proper attributes
        finalIR.add("declare void @writeln_i32(i32) #1");
        finalIR.add("declare void @writeln_str(i8*) #1");
        finalIR.add("");

        // Attribute sections
        finalIR.add("attributes #0 = { \"wasm-export-name\"=\"memory\" }");
        finalIR.add("attributes #1 = { \"wasm-import-module\"=\"env\" }");
        finalIR.add("");

        // 2. Add string literals next
        for (Map.Entry<String, String> entry : stringLiterals.entrySet()) {
            String escaped = escapeString(entry.getValue());
            finalIR.add(entry.getKey() + " = private unnamed_addr constant [" +
                    (entry.getValue().length()+1) + " x i8] c\"" + escaped + "\\00\"");
        }
        finalIR.add("");

        // 3. Add the rest of the generated code
        finalIR.addAll(irCode);

        return String.join("\n", finalIR);
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
        // Function declarations with proper attributes
        emit("declare void @writeln_i32(i32) #1");
        emit("declare void @writeln_str(i8*) #1");
        emit("");

        // Attribute sections
        emit("attributes #0 = { \"wasm-export-name\"=\"memory\" }");
        emit("attributes #1 = { \"wasm-import-module\"=\"env\" }");
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

    private String getCurrentLoopEndLabel() {
        return loopStack.isEmpty() ? null : loopStack.peek().endLabel;
    }

    private String getCurrentLoopCondLabel() {
        return loopStack.isEmpty() ? null : loopStack.peek().condLabel;
    }

    // Program Structure
    @Override
    public Object visitProgram(delphiParser.ProgramContext ctx) {
//        emitHeader();
        emit("; Program: " + ctx.programHeading().identifier().getText());

        // Process all declarations first
        visitChildren(ctx);

        // Generate main function wrapping all statements
        emitMainFunction();
        return null;
    }

    private void emitMainFunction() {
        List<String> mainCode = new ArrayList<>();
        mainCode.add("define i32 @main() {");
        mainCode.add("  entry:");

        // Initialize global variables
        for (String varName : symbolTable.keySet()) {
            if (!varName.startsWith("%")) {
                mainCode.add("  store " + symbolTable.get(varName) + " " +
                        getDefaultValue(symbolTable.get(varName)) + ", " +
                        symbolTable.get(varName) + "* @" + varName);
            }
        }

        // Separate executable code from declarations
        List<String> executableCode = new ArrayList<>();
        List<String> declarations = new ArrayList<>();

        for (String line : irCode) {
            if (line.startsWith("@") && line.contains("global")) {
                declarations.add(line);
            } else if (isExecutableInstruction(line)) {
                executableCode.add("  " + line);
            } else if (!line.trim().isEmpty()) {
                if (line.endsWith(":")) {
                    executableCode.add(line); // Labels shouldn't be indented
                } else {
                    declarations.add(line);
                }
            }
        }

        // Combine everything
        mainCode.addAll(executableCode);

        // Ensure we have a return
        if (mainCode.stream().noneMatch(s -> s.contains("ret"))) {
            mainCode.add("  ret i32 0");
        }

        mainCode.add("}");

        // Rebuild IR
        irCode = declarations;
        irCode.addAll(mainCode);
    }

    private boolean isExecutableInstruction(String line) {
        return line.startsWith("%") ||          // Instructions
                line.startsWith("store") ||      // Stores
                line.startsWith("call") ||       // Function calls
                line.startsWith("br") ||         // Branches
                line.startsWith("ret");          // Returns
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
        emit("  entry:");

        // Calculate size
        int size = classDef.fields.values().stream()
                .mapToInt(t -> t.equals("double") ? 8 : 4)
                .sum();

        // Allocate memory
        String mallocResult = newTemp();
        emit("  " + mallocResult + " = call noalias i8* @malloc(i64 " + size + ")");

        // Cast to class type
        String objectPtr = newTemp();
        emit("  " + objectPtr + " = bitcast i8* " + mallocResult + " to %struct." + className + "*");

        // Initialize fields
        int index = 0;
        for (Map.Entry<String, String> field : classDef.fields.entrySet()) {
            String fieldPtr = newTemp();
            emit("  " + fieldPtr + " = getelementptr %struct." + className +
                    ", %struct." + className + "* " + objectPtr + ", i32 0, i32 " + index++);
            emit("  store " + field.getValue() + " " + getDefaultValue(field.getValue()) +
                    ", " + field.getValue() + "* " + fieldPtr);
        }

        emit("  ret %struct." + className + "* " + objectPtr);
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
            emit("  entry:");

            // TODO: Implement method body

            if (!returnType.equals("void")) {
                emit("  ret " + returnType + " " + getDefaultValue(returnType));
            } else {
                emit("  ret void");
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

        symbolTable.put(varName, llvmType);
        emit("@" + varName + " = global " + llvmType + " " + getDefaultValue(llvmType));
        return null;
    }

    // Statements
    @Override
    public Object visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx) {
        String[] parts = ctx.getText().split(":=");
        String lhs = parts[0].trim();
        String rhs = parts[1].trim().replace(";", "");  // Remove trailing semicolon

        if (!symbolTable.containsKey(lhs)) return null;

        String llvmType = symbolTable.get(lhs);
        String value;

        if (isArithmeticExpression(rhs)) {
            value = emitArithmeticExpression(rhs, llvmType);
        } else {
            value = evaluateRHS(rhs, llvmType);
        }

        // Load from stack address if in function
        String ptrTemp = newTemp();
        if (currentFunction != null) {
            emit(ptrTemp + " = load " + llvmType + "*, " + llvmType + "** %" + lhs + ".addr");
            emit("store " + llvmType + " " + value + ", " + llvmType + "* " + ptrTemp);
        } else {
            // In main function, use the address directly from symbol table
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
                String addrTemp = newTemp();
                emit(addrTemp + " = load " + expectedType + "*, " + expectedType + "** %" + expr + ".addr");
                emit(temp + " = load " + expectedType + ", " + expectedType + "* " + addrTemp);
            } else {
                emit(temp + " = load " + expectedType + ", " + expectedType + "* @" + expr);
            }
            return temp;
        }

        return getDefaultValue(expectedType);
    }

    // Control Flow
    @Override
    public Object visitIfStatement(delphiParser.IfStatementContext ctx) {
        String condValue = visitExpression(ctx.expression(), "i1");
        String thenLabel = "if.then." + labelCounter++;
        String elseLabel = "if.else." + labelCounter++;
        String endLabel = "if.end." + labelCounter++;

        // Branch based on condition
        emit("br i1 " + condValue + ", label %" + thenLabel + ", label %" +
                (ctx.ELSE() != null ? elseLabel : endLabel));

        // Then block
        emit(thenLabel + ":");
        visit(ctx.statement(0));  // Visit the compound statement
        emit("br label %" + endLabel);

        // Else block (if present)
        if (ctx.ELSE() != null) {
            emit(elseLabel + ":");
            visit(ctx.statement(1));
        }
        emit("br label %" + endLabel);

        // End of if
        emit(endLabel + ":");
        return null;
    }

    @Override
    public Object visitForStatement(delphiParser.ForStatementContext ctx) {
        // Get the loop variable and bounds from the grammar structure
        String varName = ctx.identifier().getText();
        delphiParser.ForListContext forList = ctx.forList();

        // Get initial and final values
        String startVal = forList.initialValue().getText();
        String endVal = forList.finalValue().getText();
        boolean isDownto = forList.DOWNTO() != null;

        // Generate labels
        String condLabel = "for.cond." + labelCounter++;
        String bodyLabel = "for.body." + labelCounter++;
        String incLabel = "for.inc." + labelCounter++;
        String endLabel = "for.end." + labelCounter++;

        // Initialize loop variable
        emit("store i32 " + startVal + ", i32* @" + varName);
        emit("br label %" + condLabel);

        // Condition check
        emit(condLabel + ":");
        String currentVal = newTemp();
        emit(currentVal + " = load i32, i32* @" + varName);
        String cmpResult = newTemp();

        // Use appropriate comparison based on TO/DOWNTO
        if (isDownto) {
            emit(cmpResult + " = icmp sge i32 " + currentVal + ", " + endVal);
        } else {
            emit(cmpResult + " = icmp sle i32 " + currentVal + ", " + endVal);
        }

        emit("br i1 " + cmpResult + ", label %" + bodyLabel + ", label %" + endLabel);

        // Loop body
        emit(bodyLabel + ":");
        visit(ctx.statement());
        emit("br label %" + incLabel);

        // Increment/Decrement
        emit(incLabel + ":");
        String nextVal = newTemp();
        if (isDownto) {
            emit(nextVal + " = sub i32 " + currentVal + ", 1");
        } else {
            emit(nextVal + " = add i32 " + currentVal + ", 1");
        }
        emit("store i32 " + nextVal + ", i32* @" + varName);
        emit("br label %" + condLabel);

        // End of loop
        emit(endLabel + ":");

        return null;
    }

    @Override
    public Object visitWhileStatement(delphiParser.WhileStatementContext ctx) {
        String condLabel = "while.cond." + labelCounter++;
        String bodyLabel = "while.body." + labelCounter++;
        String endLabel = "while.end." + labelCounter++;

        // Push loop context
        loopStack.push(new LoopContext(condLabel, bodyLabel, endLabel));

        // Initial branch to condition
        emit("br label %" + condLabel);

        // Condition block
        emit(condLabel + ":");
        String condValue = visitExpression(ctx.expression(), "i1");
        emit("br i1 " + condValue + ", label %" + bodyLabel + ", label %" + endLabel);

        // Body block
        emit(bodyLabel + ":");
        visit(ctx.statement());
        emit("br label %" + condLabel); // Loop back

        // End block
        emit(endLabel + ":");

        // Pop loop context
        loopStack.pop();
        return null;
    }

    private String visitExpression(delphiParser.ExpressionContext ctx, String expectedType) {
        // Simplified expression handling
        String text = ctx.getText();

        // Handle comparisons
        if (text.contains("<")) {
            String[] parts = text.split("<");
            String left = evaluateRHS(parts[0].trim(), "i32");
            String right = evaluateRHS(parts[1].trim(), "i32");
            String result = newTemp();
            emit(result + " = icmp slt i32 " + left + ", " + right);
            return result;
        } else if (text.contains(">")) {
            String[] parts = text.split(">");
            String left = evaluateRHS(parts[0].trim(), "i32");
            String right = evaluateRHS(parts[1].trim(), "i32");
            String result = newTemp();
            emit(result + " = icmp sgt i32 " + left + ", " + right);
            return result;
        } else if (text.contains("=")) {
            String[] parts = text.split("=");
            String left = evaluateRHS(parts[0].trim(), "i32");
            String right = evaluateRHS(parts[1].trim(), "i32");
            String result = newTemp();
            emit(result + " = icmp eq i32 " + left + ", " + right);
            return result;
        }

        // Default case - return the value
        return evaluateRHS(text, expectedType);
    }

    // I/O Operations
    @Override
    public Object visitProcedureStatement(delphiParser.ProcedureStatementContext ctx) {
        if (ctx.getText().toUpperCase().startsWith("WRITELN")) {
            String content = ctx.getText().substring(
                    ctx.getText().indexOf('(') + 1,
                    ctx.getText().lastIndexOf(')')).trim();

            if (content.startsWith("'") && content.endsWith("'")) {
                // Handle string output
            } else {
                // Handle variable/number output
                if (symbolTable.containsKey(content)) {
                    String temp = newTemp();
                    emit(temp + " = load i32, i32* @" + content);
                    emit("call void @writeln_i32(i32 " + temp + ")");
                } else {
                    // Default to 0 if variable not found
                    emit("call void @writeln_i32(i32 0)");
                }
            }
        }
        return null;
    }

    private String registerStringLiteral(String content) {
        String literalName = "@.str." + (++stringLiteralCounter);
        stringLiterals.put(literalName, content);
        return literalName;
    }

    @Override
    public Object visitBreakStatement(delphiParser.BreakStatementContext ctx) {
        String endLabel = getCurrentLoopEndLabel();
        if (endLabel != null) {
            emit("br label %" + endLabel);
        } else {
            emit("; ERROR: break outside loop");
        }
        return null;
    }

    @Override
    public Object visitContinueStatement(delphiParser.ContinueStatementContext ctx) {
        String condLabel = getCurrentLoopCondLabel();
        if (condLabel != null) {
            emit("br label %" + condLabel);
        } else {
            emit("; ERROR: continue outside loop");
        }
        return null;
    }
}