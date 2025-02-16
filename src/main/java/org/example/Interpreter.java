package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;

public class Interpreter {
    public static void main(String[] args) {
        String input = "PROGRAM Test; VAR x: INTEGER; BEGIN x := 1; END."; // Example input

        // Test 1
        //PROGRAM Test;
        //
        //CLASS myCar:
        //    VAR size: integer;
        //BEGIN
        //x := 1;
        //END
        //
        //VAR x: INTEGER;
        //BEGIN
        //END.


        delphiLexer lexer = new delphiLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }
}