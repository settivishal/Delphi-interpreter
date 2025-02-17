[1mdiff --git a/src/main/java/org/example/Interpreter.java b/src/main/java/org/example/Interpreter.java[m
[1mindex 297514b..fa95eba 100644[m
[1m--- a/src/main/java/org/example/Interpreter.java[m
[1m+++ b/src/main/java/org/example/Interpreter.java[m
[36m@@ -5,6 +5,7 @@[m [mimport org.antlr.v4.runtime.CommonTokenStream;[m
 import org.antlr.v4.runtime.tree.ParseTree;[m
 import org.example.antlr.*;[m
 import java.io.*;[m
[32m+[m[32mimport org.example.antlr.ExecuteVisitor;[m
 [m
 public class Interpreter {[m
     public static void main(String[] args) {[m
[36m@@ -33,6 +34,9 @@[m [mpublic class Interpreter {[m
             System.out.println("Processing file: " + testFile);[m
             System.out.println(tree.toStringTree(parser));[m
             System.out.println();[m
[32m+[m
[32m+[m[32m            ExecuteVisitor visitor = new ExecuteVisitor();[m
[32m+[m[32m            visitor.visit(tree);[m
         }[m
     }[m
 [m
[1mdiff --git a/src/main/java/org/example/antlr/ExecuteVisitor.java b/src/main/java/org/example/antlr/ExecuteVisitor.java[m
[1mindex 57ef704..d07c00a 100644[m
[1m--- a/src/main/java/org/example/antlr/ExecuteVisitor.java[m
[1m+++ b/src/main/java/org/example/antlr/ExecuteVisitor.java[m
[36m@@ -1,8 +1,108 @@[m
 package org.example.antlr;[m
 [m
[31m-public class Visitor extends delphiBaseVisitor<Object>{[m
[32m+[m[32mimport java.util.regex.*;[m
[32m+[m[32mimport java.util.*;[m
[32m+[m
[32m+[m[32m// Class[m
[32m+[m[32mclass ClassImplementation {[m
[32m+[m[32m    String name;[m
[32m+[m
[32m+[m[32m    ClassImplementation(String name) {[m
[32m+[m[32m        this.name = name;[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mclass ObjectImplementation {[m
[32m+[m[32m    ClassImplementation classInfo;[m
[32m+[m
[32m+[m[32m    ObjectImplementation(ClassImplementation classInfo) {[m
[32m+[m[32m        this.classInfo = classInfo;[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32mpublic class ExecuteVisitor extends delphiBaseVisitor<Object>{[m
[32m+[m[32m    private ClassImplementation currentClass = null;[m
[32m+[m
[32m+[m[32m    // Default visibility to "PUBLIC"[m
[32m+[m[32m    private String currentVisibility = "public";[m
[32m+[m
[32m+[m[32m    private final Map<String, ClassImplementation> classes = new HashMap<>();[m
[32m+[m[32m    private final Map<String, ObjectImplementation> objects = new HashMap<>();[m
[32m+[m
[32m+[m[32m    @Override[m
     public Object visitProgram(delphiParser.ProgramContext ctx) {[m
[31m-        System.out.println("Program: " + ctx.programHeading().toString());[m
[32m+[m[32m        System.out.println("Program " + ctx.programHeading().identifier().getText());[m
[32m+[m
[32m+[m[32m        for (String className: classes.keySet()) {[m
[32m+[m[32m            System.out.println(" " + className + " => " + classes.get(className));[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        for (String objectName: objects.keySet()) {[m
[32m+[m[32m            System.out.println(" " + objectName + " => " + objects.get(objectName));[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        return visitChildren(ctx);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public Void visitClassDeclarationPart(delphiParser.ClassDeclarationPartContext ctx) {[m
[32m+[m[32m        String className = ctx.identifier().getText();[m
[32m+[m[32m        currentClass = new ClassImplementation(className);[m
[32m+[m[32m        visit(ctx.classBlock());[m
[32m+[m[32m        classes.put(className, currentClass);[m
[32m+[m[32m        System.out.println("Class: " + className + ", details: " + currentClass);[m
[32m+[m[32m        currentClass = null;[m
[32m+[m[32m        return null;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public Object visitVisibility(delphiParser.VisibilityContext ctx) {[m
[32m+[m[32m        System.out.println( " ::::::::::::"+ctx.getText());[m
[32m+[m[32m        currentVisibility = ctx.getChild(0).getText().toLowerCase();[m
[32m+[m[32m        System.out.println("Current visibility: " + currentVisibility);[m
[32m+[m[32m        return super.visitVisibility(ctx);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public Void visitClassBlock(delphiParser.ClassBlockContext ctx) {[m
[32m+[m[32m        if (ctx.classVariableDeclarationPart() != null) {[m
[32m+[m[32m            visit(ctx.classVariableDeclarationPart());[m
[32m+[m[32m        }[m
[32m+[m[32m        if (ctx.methodImplementation().getFirst().constructorImplementation() != null) {[m
[32m+[m[32m            visit(ctx.methodImplementation().getFirst().constructorImplementation());[m
[32m+[m[32m        }[m
[32m+[m[32m        if (ctx.methodImplementation().getFirst().destructorImplementation() != null) {[m
[32m+[m[32m            visit(ctx.methodImplementation().getFirst().destructorImplementation());[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        return null;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public Object visitCompoundStatement(delphiParser.CompoundStatementContext ctx) {[m
[32m+[m[32m        if (ctx.getText().contains("writeln")) {[m
[32m+[m[32m            String pattern = "writeln\\((\\d+)\\)";[m
[32m+[m[32m            Pattern regex = Pattern.compile(pattern);[m
[32m+[m[32m            Matcher matcher = regex.matcher(ctx.getText());[m
[32m+[m[32m            while (matcher.find()) {[m
[32m+[m[32m                String value = matcher.group(1);[m
[32m+[m[32m                System.out.println("Value in writeln: " + value);[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m        return visitChildren(ctx);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public Object visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx) {[m
[32m+[m[32m        if (ctx.getText().contains(":=")) {[m
[32m+[m[32m            String str = ctx.getText();[m
[32m+[m[32m            String variable = str.split(":=")[0];[m
[32m+[m[32m            String value = str.split(":=")[1];[m
[32m+[m
[32m+[m[32m//            System.out.println("variable: " + variable);[m
[32m+[m[32m//            System.out.println("value: " + value);[m
[32m+[m[32m        }[m
[32m+[m
         return visitChildren(ctx);[m
     }[m
 }[m
[1mdiff --git a/src/main/java/org/example/antlr/delphiParser.java b/src/main/java/org/example/antlr/delphiParser.java[m
[1mindex ed2a299..4908358 100644[m
[1m--- a/src/main/java/org/example/antlr/delphiParser.java[m
[1m+++ b/src/main/java/org/example/antlr/delphiParser.java[m
[36m@@ -5,11 +5,8 @@[m [mpackage org.example.antlr;[m
 import org.antlr.v4.runtime.atn.*;[m
 import org.antlr.v4.runtime.dfa.DFA;[m
 import org.antlr.v4.runtime.*;[m
[31m-import org.antlr.v4.runtime.misc.*;[m
 import org.antlr.v4.runtime.tree.*;[m
 import java.util.List;[m
[31m-import java.util.Iterator;[m
[31m-import java.util.ArrayList;[m
 [m
 @SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})[m
 public class delphiParser extends Parser {[m
[36m@@ -598,7 +595,7 @@[m [mpublic class delphiParser extends Parser {[m
 		public TypeDefinitionPartContext typeDefinitionPart(int i) {[m
 			return getRuleContext(TypeDefinitionPartContext.class,i);[m
 		}[m
[31m-		public List<ClassVariableDeclarationPartContext> classVariableDeclarationPart() {[m
[32m+[m		[32mpublic ParseTree classVariableDeclarationPart() {[m
 			return getRuleContexts(ClassVariableDeclarationPartContext.class);[m
 		}[m
 		public ClassVariableDeclarationPartContext classVariableDeclarationPart(int i) {[m
[1mdiff --git a/src/main/tests/test1.pas b/src/main/tests/test1.pas[m
[1mindex 16d27e7..7cdd4e4 100644[m
[1m--- a/src/main/tests/test1.pas[m
[1m+++ b/src/main/tests/test1.pas[m
[36m@@ -1,4 +1,6 @@[m
 PROGRAM Test;[m
 VAR x: INTEGER;[m
[31m-BEGIN x := 1;[m
[32m+[m[32mBEGIN[m
[32m+[m[32m    x := 1;[m
[32m+[m[32m    writeln(2);[m
 END.[m
\ No newline at end of file[m
