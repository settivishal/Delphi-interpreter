package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;

public class Interpreter {
    public static void main(String[] args) {
        String input = "PROGRAM Test; VAR x: INTEGER; BEGIN x := 1; END."; // Example input
        String test1 = "PROGRAM Test;\n" +
                "\n" +
                "TYPE\n" +
                "    Person = CLASS\n" +
                "        name: STRING;\n" +
                "        age: INTEGER;\n" +
                "        PROCEDURE display;\n" +
                "    END;\n" +
                "\n" +
                "PROCEDURE Person.display;\n" +
                "BEGIN\n" +
                "    WriteLn('Name: ', name);\n" +
                "    WriteLn('Age: ', age);\n" +
                "END;\n" +
                "\n" +
                "VAR\n" +
                "    p: Person;\n" +
                "\n" +
                "BEGIN\n" +
                "    p := Person.Create;\n" +
                "    p.name := 'John Doe';\n" +
                "    p.age := 30;\n" +
                "    p.display;\n" +
                "    p.Destroy;\n" +
                "END.";
        delphiLexer lexer = new delphiLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        ParseTree tree = parser.program(); // Start parsing from the program rule

        System.out.println(tree.toStringTree(parser)); // Print the parse tree
    }
}