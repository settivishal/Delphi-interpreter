package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;

public class Interpreter {
    public static void main(String[] args) {
        String input = "PROGRAM Test; VAR x: INTEGER; BEGIN x := 1; END."; // Example input

        // Test 1
//        PROGRAM Test;
//
//        class Car:
//        constructor Cons(var y: integer);
//        begin
//        y := 1;
//        end;
//
//        destructor Desc;
//        begin
//        writeln('destructor');
//        end;
//        var size: integer;
//        function myFunction(var x: integer): integer ;
//        begin
//        x := 1;
//        end;
//
//        procedure myProcedure(var x: integer);
//        begin
//        x := 1
//        end;
//        end;
//
//        var myCar: Car;
//        var z: integer;
//        begin
//        myCar := Car.Cons(1);
//        z := myCar.myFunction(5);
//        myCar.size := 5;
//        myCar.myProcedure(1);
//        myCar.Desc;
//        end.


        delphiLexer lexer = new delphiLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }
}