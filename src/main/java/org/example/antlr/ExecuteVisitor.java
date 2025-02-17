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
        visit(ctx.classBlock());
        classes.put(className, currentClass);
        System.out.println("Class: " + className + ", details: " + currentClass);
        currentClass = null;
        return null;
    }

    @Override
    public Object visitVisibility(delphiParser.VisibilityContext ctx) {
        currentVisibility = ctx.getChild(0).getText().toLowerCase();
        System.out.println("Current visibility: " + currentVisibility);
        return super.visitVisibility(ctx);
    }

    @Override
    public Void visitClassBlock(delphiParser.ClassBlockContext ctx) {
//        if (ctx.classVariableDeclarationPart() != null) {
//            visit(ctx.classVariableDeclarationPart());
//        }
        if (ctx.methodImplementation().getFirst().constructorImplementation() != null) {
            visit(ctx.methodImplementation().getFirst().constructorImplementation());
        }
        if (ctx.methodImplementation().getFirst().destructorImplementation() != null) {
            visit(ctx.methodImplementation().getFirst().destructorImplementation());
        }

        return null;
    }

    @Override
    public Void visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx) {
        if (currentClass != null) {
            currentClass.hasConstructor = true;
            System.out.println("Constructor name: " + currentClass.name);
        }
        return null;
    }

    @Override
    public Void visitDestructorImplementation(delphiParser.DestructorImplementationContext ctx) {
        if (currentClass != null) {
            currentClass.hasDestructor = true;
            System.out.println("Destructor name: " + currentClass.name);
        }
        return null;
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
