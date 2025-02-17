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

public class ExecuteVisitor extends delphiBaseVisitor<Object>{
    private ClassImplementation currentClass = null;

    // Default visibility to "PUBLIC"
    private String currentVisibility = "public";

    private final Map<String, ClassImplementation> classes = new HashMap<>();
    private final Map<String, ObjectImplementation> objects = new HashMap<>();

    @Override
    public Object visitProgram(delphiParser.ProgramContext ctx) {
        System.out.println("Program " + ctx.programHeading().identifier().getText());

        for (String className: classes.keySet()) {
            System.out.println(" " + className + " => " + classes.get(className));
        }

        for (String objectName: objects.keySet()) {
            System.out.println(" " + objectName + " => " + objects.get(objectName));
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
        return super.visitClassVariableDeclarationPart(ctx);
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

            System.out.println("variable " + variable + " is assigned with value " + value);
        }

        return visitChildren(ctx);
    }
}
