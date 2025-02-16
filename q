[1mdiff --git a/src/main/delphi.g4 b/src/main/delphi.g4[m
[1mindex 1d944e1..ab4ac4a 100644[m
[1m--- a/src/main/delphi.g4[m
[1m+++ b/src/main/delphi.g4[m
[36m@@ -5,7 +5,7 @@[m [moptions {[m
 }[m
 [m
 program[m
[31m-    : programHeading (INTERFACE)? block DOT EOF[m
[32m+[m[32m    : programHeading (INTERFACE)? classDeclarationPart* block* DOT EOF[m
     ;[m
 [m
 programHeading[m
[36m@@ -17,7 +17,6 @@[m [midentifier[m
     : IDENT[m
     ;[m
 [m
[31m-// added methodImplementation[m
 block[m
     : ([m
         labelDeclarationPart[m
[36m@@ -26,125 +25,13 @@[m [mblock[m
         | variableDeclarationPart[m
         | procedureAndFunctionDeclarationPart[m
         | usesUnitsPart[m
[31m-        | methodImplementation  // new methodImplementation syntax[m
         | IMPLEMENTATION[m
[31m-[m
     )* compoundStatement[m
     ;[m
 [m
[31m-// Add these rules to the existing pascal.g4 grammar[m
[31m-[m
[31m-// Type definition including class[m
[31m-[m
[31m-classType[m
[31m-    : 'class' (classHeritage)?[m
[31m-      classVisibility*[m
[31m-      'end'[m
[31m-    ;[m
[31m-[m
[31m-classHeritage[m
[31m-    : '(' identifier ')'    // Inheritance from parent class[m
[31m-    ;[m
[31m-[m
[31m-classVisibility[m
[31m-    : visibilityDirective memberList[m
[31m-    ;[m
[31m-[m
[31m-visibilityDirective[m
[31m-    : 'private'[m
[31m-    | 'protected'[m
[31m-    | 'public'[m
[31m-    | 'published'[m
[31m-    ;[m
[31m-[m
[31m-memberList[m
[31m-    : (fieldDeclaration[m
[31m-    | methodDeclaration[m
[31m-    | constructorDeclaration[m
[31m-    | destructorDeclaration)*[m
[31m-    ;[m
[31m-[m
[31m-fieldDeclaration[m
[31m-    : identifierList ':' type_ ';'[m
[31m-    ;[m
[31m-[m
[31m-methodDeclaration[m
[31m-    : methodHeading ';'[m
[31m-    ;[m
[31m-[m
[31m-methodHeading[m
[31m-    : procedureHeading[m
[31m-    | functionHeading[m
[31m-    ;[m
[31m-[m
[31m-procedureHeading[m
[31m-    : 'procedure' identifier formalParameters?[m
[31m-    ;[m
[31m-[m
[31m-functionHeading[m
[31m-    : 'function' identifier formalParameters? ':' returnType[m
[31m-    ;[m
[31m-[m
[31m-constructorDeclaration[m
[31m-    : 'constructor' identifier formalParameters? ';'[m
[31m-    ;[m
[31m-[m
[31m-destructorDeclaration[m
[31m-    : 'destructor' identifier ';'[m
[31m-    ;[m
[31m-[m
[31m-formalParameters[m
[31m-    : '(' formalParm ( ';' formalParm )* ')'[m
[31m-    ;[m
[31m-[m
[31m-formalParm[m
[31m-    : ('var' | 'const' | 'out')? paramIdentifier ':' paramType[m
[31m-    ;[m
[31m-[m
[31m-paramIdentifier[m
[31m-    : identifierList[m
[31m-    ;[m
[31m-[m
[31m-paramType[m
[31m-    : identifier[m
[31m-    | 'array' 'of' identifier[m
[31m-    | type_[m
[31m-    ;[m
[31m-[m
[31m-returnType[m
[31m-    : identifier[m
[31m-    ;[m
[31m-[m
[31m-// Method implementation[m
[31m-methodImplementation[m
[31m-    : procedureImplementation[m
[31m-    | functionImplementation[m
[31m-    | constructorImplementation[m
[31m-    | destructorImplementation[m
[31m-    ;[m
[31m-[m
[31m-procedureImplementation[m
[31m-    : 'procedure' className=identifier '.' methodName=identifier[m
[31m-      formalParameters? ';'[m
[31m-      block ';'[m
[31m-    ;[m
[31m-[m
[31m-functionImplementation[m
[31m-    : 'function' className=identifier '.' methodName=identifier[m
[31m-      formalParameters? ':' returnType ';'[m
[31m-      block ';'[m
[31m-    ;[m
[31m-[m
[31m-constructorImplementation[m
[31m-    : 'constructor' className=identifier '.' methodName=identifier[m
[31m-      formalParameters? ';'[m
[31m-      block ';'[m
[31m-    ;[m
[31m-[m
[31m-destructorImplementation[m
[31m-    : 'destructor' className=identifier '.' methodName=identifier ';'[m
[31m-      block ';'[m
[31m-    ;[m
[32m+[m[32m//classType[m
[32m+[m[32m//    : CLASS[m
[32m+[m[32m//    ;[m
 [m
 usesUnitsPart[m
     : USES identifierList SEMI[m
[36m@@ -226,7 +113,6 @@[m [mtype_[m
     : simpleType[m
     | structuredType[m
     | pointerType[m
[31m-    | classType   // class type added[m
     ;[m
 [m
 simpleType[m
[36m@@ -381,6 +267,10 @@[m [mresultType[m
     : typeIdentifier[m
     ;[m
 [m
[32m+[m[32mclassDeclarationPart[m
[32m+[m[32m    : CLASS identifier COLON block[m
[32m+[m[32m    ;[m
[32m+[m
 statement[m
     : label COLON unlabelledStatement[m
     | unlabelledStatement[m
[36m@@ -585,6 +475,10 @@[m [mrecordVariableList[m
     : variable (COMMA variable)*[m
     ;[m
 [m
[32m+[m[32mCLASS[m
[32m+[m[32m    : 'CLASS'[m
[32m+[m[32m    ;[m
[32m+[m
 AND[m
     : 'AND'[m
     ;[m
