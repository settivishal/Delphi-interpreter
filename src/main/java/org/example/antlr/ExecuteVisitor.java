package org.example.antlr;

import java.util.regex.*;
import java.util.*;

class ClassImplementation {
    String name;

    boolean hasConstructor = false;
    boolean hasDestructor = false;

    ClassImplementation(String name) {
        this.name = name;
    }
}

class ObjectImplementation {
    ClassImplementation classInfo;

    ObjectImplementation(ClassImplementation classInfo) {
        this.classInfo = classInfo;
    }
}

class VariableImplementation {
    String name;
    String type;
    String value;

    String visibility = "public";

    VariableImplementation(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}

public class ExecuteVisitor extends delphiBaseVisitor<Object>{
    private ClassImplementation currentClass = null;

    private ObjectImplementation currentObject = null;

    private VariableImplementation currentVariable = null;

    // Default visibility to "PUBLIC"
    private String currentVisibility = "public";

    private final Map<String, ClassImplementation> classes = new HashMap<>();
    private final Map<String, ObjectImplementation> objects = new HashMap<>();
    private final Map<String, VariableImplementation> variables = new HashMap<>();

    @Override
    public Object visitProgram(delphiParser.ProgramContext ctx) {
        System.out.println("Program " + ctx.programHeading().identifier().getText());

        for (String className: classes.keySet()) {
            System.out.println(" " + className + " => " + classes.get(className));
        }

        for (String objectName: objects.keySet()) {
            System.out.println(" " + objectName + " => " + objects.get(objectName));
        }

        for (String variableName: variables.keySet()) {
            System.out.println(" " + variableName + " => " + variables.get(variableName));
        }

        return visitChildren(ctx);
    }

    @Override
    public Void visitClassDeclarationPart(delphiParser.ClassDeclarationPartContext ctx) {
        String className = ctx.identifier().getText();
        currentClass = new ClassImplementation(className);
        classes.put(className, currentClass);
        System.out.println("Class: " + className);
        visit(ctx.classBlock());
        currentClass = null;
        return null;
    }

    @Override
    public Void visitClassBlock(delphiParser.ClassBlockContext ctx) {
        if (ctx.classVariableDeclarationPart() != null) {
            for (delphiParser.ClassVariableDeclarationPartContext varCtx : ctx.classVariableDeclarationPart()) {
                visit(varCtx);
            }
        }
        if (ctx.methodImplementation() != null) {
            for (delphiParser.MethodImplementationContext methodCtx: ctx.methodImplementation()) {
                if (methodCtx != null) {
                    visit(methodCtx);
                }
            }
        }

        if (ctx.classProcedureAndFunctionDeclarationPart() != null) {
            for (delphiParser.ClassProcedureAndFunctionDeclarationPartContext procCtx: ctx.classProcedureAndFunctionDeclarationPart()) {
                if (procCtx != null) {
                    visit(procCtx);
                }
            }
        }

        return null;
    }

    @Override
    public Object visitClassVariableDeclarationPart(delphiParser.ClassVariableDeclarationPartContext ctx) {
//        visit(ctx.visibility());

        String input = ctx.getChild(2).getText();

        String[] parts = input.split(":");

        String name = parts[0]; // "size"
        String type = parts[1]; // "integer"
        String visibility = ctx.getChild(0).getText();

        System.out.println("class variable name: " + name + " type: "+type + " visibility: " + visibility);

        currentVariable = new VariableImplementation(name, type);
        currentVisibility = ctx.getChild(0).getText();
        currentVariable.setVisibility(currentVisibility);

        // add to the hashmap
        variables.put(currentVariable.name, currentVariable);

        String newName = currentClass.name + "." + currentVariable.name;

        if (visibility.equals("public")) {
            variables.put(newName, new VariableImplementation(newName, type));
        }

        return super.visitClassVariableDeclarationPart(ctx);
    }

    public Object visitVariableDeclarationPart(delphiParser.VariableDeclarationPartContext ctx) {

        String input = ctx.getChild(1).getText();
        String[] parts = input.split(":");

        String name = parts[0];
        String type = parts[1];

        System.out.println("variable name: " + name + " type: " + type);

        currentVariable = new VariableImplementation(name, type);

        variables.put(currentVariable.name, currentVariable);

        // if type is class then this is an object
        if (classes.containsKey(type)) {
            currentObject = new ObjectImplementation(classes.get(type));
            objects.put(currentVariable.name, currentObject);
            System.out.println("Object: " + currentVariable.name);
        }

        return super.visitVariableDeclarationPart(ctx);
    }



    @Override
    public Void visitVisibility(delphiParser.VisibilityContext ctx) {
        currentVisibility = ctx.getChild(0).getText().toLowerCase();
//        System.out.println("Current visibility: " + currentVisibility);
        return null;
    }

    @Override
    public Object visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx) {
        if (currentClass != null) {
            currentClass.hasConstructor = true;
            System.out.println("Constructor name: " + currentClass.name);
        }
        return super.visitConstructorImplementation(ctx);
    }

    @Override
    public Object visitDestructorImplementation(delphiParser.DestructorImplementationContext ctx) {
        if (currentClass != null) {
            currentClass.hasDestructor = true;
            System.out.println("Destructor name: " + currentClass.name);
        }
        return super.visitDestructorImplementation(ctx);
    }

    @Override
    public Object visitClassFunctionDeclaration(delphiParser.ClassFunctionDeclarationContext ctx) {
        String input = ctx.getText();
        Pattern pattern = Pattern.compile("function(\\w+):(\\w+);");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            // Extract function name and return type
            String functionName = matcher.group(1); // Matches the function name (e.g., getPrice)
            String returnType = matcher.group(2);  // Matches the return type (e.g., integer)

            // Print extracted values
            System.out.println("Function Name: " + functionName + " Return Type: " + returnType);
        } else {
            System.out.println("No matching function found in the input string.");
        }



        return super.visitClassFunctionDeclaration(ctx);
    }

    @Override
    public Object visitProcedureStatement(delphiParser.ProcedureStatementContext ctx) {
        if (ctx.getText().contains("writeln")) {
            String input = ctx.getText();
            Pattern pattern = Pattern.compile("\\((.*?)\\)");
            Matcher matcher = pattern.matcher(input);

            String value = "";

            if (matcher.find()) {
                value = matcher.group(1); // Extracts the content inside "()"
            } else {
                System.out.println("No brackets or value found in the input string");
            }

            // Case 1: If the value is enclosed in single quotes '...'
            if (value.startsWith("'") && value.endsWith("'")) {
                value =  value.substring(1, value.length() - 1); // Extract string inside quotes
            }

            // Case 2: If the value is an integer
            else if (value.matches("\\d+")) { // Check if the value is all digits
                value = value;
            }

            // Case 3: For any other string, check if it exists in the variables map
            else if (variables.containsKey(value)) {
                value = variables.get(value).value;
            }

            else {
                System.out.println("Error: variable " + value + " does not exist");
            }

            if (value != null) {
                System.out.println("value in writeln: " + value);

            }
        }

        return visitChildren(ctx);
    }

    @Override
    public Object visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx) {
        if (ctx.getText().contains(":=")) {
            String str = ctx.getText();
            String variable = str.split(":=")[0].trim();
            String value = str.split(":=")[1].trim();

            if (variables.containsKey(variable)) {
                currentVariable = variables.get(variable);
            }else {
                System.out.println(ctx.getParent().getText());
                currentVariable = new VariableImplementation(variable, "String");
                variables.put(variable, currentVariable);
            }

            // If the assignment is to a function call inside the Class or Constructor
            if (objects.containsKey(variable)) {
                currentObject = objects.get(variable);

                String[] parts = value.split("\\.");

                // Extract the constructor name and argument
                String constructorPart = parts[1]; // Example: "Car(1)"
                String constructorName = constructorPart.substring(0, constructorPart.indexOf("(")); // Extracting "Car"
                String passedValue = constructorPart.substring(constructorPart.indexOf("(") + 1, constructorPart.indexOf(")"));

                System.out.println("Object: " + currentObject.classInfo.name + " Constructor: " + constructorName + " Passed value: " + passedValue);

                return visitChildren(ctx);

            }

            // Addition, Sub, Multi, Div

            // Regular expression to match the format: LHS operator RHS
            String regex = "([a-zA-Z0-9]+)\\s*([+\\-*/])\\s*([a-zA-Z0-9]+)";

            // Compile the pattern
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);

            // Check if the pattern matches
            if (matcher.find()) {
                // Extract the components
                String leftSide = matcher.group(1);  // LHS (e.g., "x")
                String operator = matcher.group(2); // Operator (e.g., "+")
                String rightSide = matcher.group(3); // RHS (e.g., "1")

                int leftSideValue;
                if (variables.containsKey(leftSide)) {
                    leftSideValue = Integer.parseInt(variables.get(leftSide).value);
                }
                else {
                    leftSideValue = Integer.parseInt(leftSide);
                }

                int rightSideValue;
                if (variables.containsKey(rightSide)) {
                    rightSideValue = Integer.parseInt(variables.get(rightSide).value);
                }
                else {
                    rightSideValue = Integer.parseInt(rightSide);
                }

                if (operator.equals("+")) {
                    currentVariable.setValue(String.valueOf(leftSideValue + rightSideValue));
                }
                else if (operator.equals("-")) {
                    currentVariable.setValue(String.valueOf(leftSideValue - rightSideValue));
                }
                else if (operator.equals("*")) {
                    currentVariable.setValue(String.valueOf(leftSideValue * rightSideValue));
                }
                else if (operator.equals("/")) {
                    currentVariable.setValue(String.valueOf(leftSideValue / rightSideValue));
                }

//                System.out.println(currentVariable.value);

                // Print the results
                System.out.println("Left Side: " + leftSide);
                System.out.println("Operator: " + operator);
                System.out.println("Right Side: " + rightSide);

                return visitChildren(ctx);
            }


            // Assignment to the value or string

            // Case 1: If the value is enclosed in single quotes '...'
            if (value.startsWith("'") && value.endsWith("'")) {
                value =  value.substring(1, value.length() - 1); // Extract string inside quotes
            }

            // Case 2: If the value is an integer
            else if (value.matches("\\d+")) { // Check if the value is all digits
                value = value;
            }

            // Case 3: For any other string, check if it exists in the variables map
            else if (variables.containsKey(value)) {
                value = variables.get(value).value; // Return the value of the variable
            }

            else {
                System.out.println("Error: variable " + value + " does not exist");
            }

            currentVariable.setValue(value);

            System.out.println("variable " + variable + " is assigned with value " + value);
        }

        return visitChildren(ctx);
    }

    @Override
    public Object visitForStatement(delphiParser.ForStatementContext ctx) {
//        split the for and then variable should be the next and the start value and end value
        // store the variable value and print out writeln everytime - implement for loop here in java

        System.out.println("Block Scope Created - For Loop");
        String input = ctx.getText();

        // regex
        String regex = "(for)([a-zA-Z])\\s*:=\\s*(\\d+)\\s*to\\s*(\\d+)";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Check if the pattern matches
        if (matcher.find()) {
            // Extract the components
            String identifier = matcher.group(1);  // "for"
            String variableName = matcher.group(2);  // "y"
            int startValue = Integer.parseInt(matcher.group(3));  // 1
            int endValue = Integer.parseInt(matcher.group(4));  // 10

            // set currentVariable to y
            if (variables.containsKey(variableName)) {
                currentVariable = variables.get(variableName);
            }

//            System.out.println(currentVariable.name);

            for (int i = startValue; i < endValue; i++) {
                currentVariable.setValue(String.valueOf(i));
                System.out.println("value in writeln: " + currentVariable.value);
            }

            currentVariable.setValue(null);
            // Print the results
            System.out.println("Identifier: " + identifier);
            System.out.println("Variable Name: " + variableName);
            System.out.println("Start Value: " + startValue);
            System.out.println("End Value: " + endValue);
        } else {
            System.out.println("Pattern not found in the input string.");
        }

        return visitChildren(ctx);
    }

    @Override
    public Object visitWhileStatement(delphiParser.WhileStatementContext ctx) {
        System.out.println("Block Scope Created - While Loop");

        String input = ctx.getText();

        // Regular expression to extract 'while' components
        String regexWhile = "(while)([a-zA-Z]+)([<>])(\\d+)";

        // Regular expression to find where the iterator is incremented
        String regexIncrement = "([a-zA-Z]+)\\s*:=\\s*\\1\\s*([+\\-])\\s*(\\d+)";

        // Compile patterns
        Pattern patternWhile = Pattern.compile(regexWhile);
        Pattern patternIncrement = Pattern.compile(regexIncrement);

        // Match 'while' components
        Matcher matcherWhile = patternWhile.matcher(input);

        if (matcherWhile.find()) {
            // Extract 'while' components
            String identifier = matcherWhile.group(1);  // "while"
            String iterator = matcherWhile.group(2);   // "x"
            String comparator = matcherWhile.group(3); // "<"
            int endValue = Integer.parseInt(matcherWhile.group(4)); // 5

            if (variables.containsKey(iterator)) {
                currentVariable = variables.get(iterator);
            }

            // Print 'while' results
            System.out.println("Identifier: " + identifier);
            System.out.println("Iterator: " + iterator);
            System.out.println("Comparator: " + comparator);
            System.out.println("End Value: " + endValue);

            // Match increment details
            Matcher matcherIncrement = patternIncrement.matcher(input);
            if (matcherIncrement.find()) {
                String incrementVariable = matcherIncrement.group(1);  // "x"
                String operator = matcherIncrement.group(2);           // "+"
                int incrementValue = Integer.parseInt(matcherIncrement.group(3)); // 1

                int i = Integer.parseInt(currentVariable.value);

                while (i < endValue) {
                    i = i + incrementValue;
                    currentVariable.setValue(String.valueOf(i));
                    System.out.println("value in writeln " + i);
                }

                // Print increment details
                System.out.println("Increment Variable: " + incrementVariable);
                System.out.println("Increment Operator: " + operator);
                System.out.println("Increment Value: " + incrementValue);
            } else {
                System.out.println("No increment operation found.");
            }
        } else {
            System.out.println("Pattern not found in the input string.");
        }


        return visitChildren(ctx);
    }
}

