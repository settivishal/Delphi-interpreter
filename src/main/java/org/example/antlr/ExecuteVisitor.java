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
        System.out.println("Class: " + className + ", details: " + currentClass);
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

        return null;
    }

    @Override
    public Object visitClassVariableDeclarationPart(delphiParser.ClassVariableDeclarationPartContext ctx) {
//        visit(ctx.visibility());

        String input = ctx.getChild(2).getText();
        String[] parts = input.split(":");

        String name = parts[0]; // "size"
        String type = parts[1]; // "integer"

        System.out.println("class variable name "+name);
        System.out.println("class variable type "+type);

        currentVariable = new VariableImplementation(name, type);
        currentVisibility = ctx.getChild(0).getText();
        currentVariable.setVisibility(currentVisibility);

        // add to the hashmap

        variables.put(currentVariable.name, currentVariable);

        return super.visitClassVariableDeclarationPart(ctx);
    }

    public Object visitVariableDeclarationPart(delphiParser.VariableDeclarationPartContext ctx) {


        String input = ctx.getChild(1).getText();
        String[] parts = input.split(":");

        String name = parts[0];
        String type = parts[1];

        System.out.println("variable name "+name);
        System.out.println("variable type "+type);

        currentVariable = new VariableImplementation(name, type);

        variables.put(currentVariable.name, currentVariable);

        return super.visitVariableDeclarationPart(ctx);
    }



    @Override
    public Void visitVisibility(delphiParser.VisibilityContext ctx) {
        currentVisibility = ctx.getChild(0).getText().toLowerCase();
        System.out.println("Current visibility: " + currentVisibility);
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
    public Object visitCompoundStatement(delphiParser.CompoundStatementContext ctx) {
        if (ctx.getText().contains("writeln")) {
            String pattern = "writeln\\((\\d+)\\)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(ctx.getText());
            while (matcher.find()) {
                String value = matcher.group(1);
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
}
