package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;

public class Interpreter {
    public static void main(String[] args) {
        String input = "PROGRAM Test; VAR x: INTEGER; BEGIN x := 1; END."; // Example input

        //PROGRAM SampleProgram;
        //
        //USES
        //  SysUtils;
        //
        //CONST
        //  MaxValue = 10;
        //
        //TYPE
        //  TPerson = RECORD
        //    Name: STRING;
        //    Age: INTEGER;
        //  END;
        //
        //VAR
        //  Person1: TPerson;
        //
        //PROCEDURE PrintPersonInfo;
        //BEGIN
        //  WriteLn('Name: ', Person1.Name);
        //  WriteLn('Age: ', Person1.Age);
        //END;
        //
        //BEGIN
        //  Person1.Name := 'John Doe';
        //  Person1.Age := 30;
        //  PrintPersonInfo;
        //END.
//        String test1 = "PROGRAM Test;\n" +
//                "\n" +
//                "TYPE\n" +
//                "    Person = CLASS\n" +
//                "        name: STRING;\n" +
//                "        age: INTEGER;\n" +
//                "        PROCEDURE display;\n" +
//                "    END;\n" +
//                "\n" +
//                "PROCEDURE Person.display;\n" +
//                "BEGIN\n" +
//                "    WriteLn('Name: ', name);\n" +
//                "    WriteLn('Age: ', age);\n" +
//                "END;\n" +
//                "\n" +
//                "VAR\n" +
//                "    p: Person;\n" +
//                "\n" +
//                "BEGIN\n" +
//                "    p := Person.Create;\n" +
//                "    p.name := 'John Doe';\n" +
//                "    p.age := 30;\n" +
//                "    p.display;\n" +
//                "    p.Destroy;\n" +
//                "END.";

        String test1 = "PROGRAM SampleProgram;\n" +
                "\n" +
                "USES\n" +
                "  SysUtils;\n" +
                "\n" +
                "CONST\n" +
                "  MaxValue = 10;\n" +
                "\n" +
                "TYPE\n" +
                "  TPerson = RECORD\n" +
                "    Name: STRING;\n" +
                "    Age: INTEGER;\n" +
                "  END;\n" +
                "\n" +
                "VAR\n" +
                "  Person1: TPerson;\n" +
                "\n" +
                "PROCEDURE PrintPersonInfo;\n" +
                "BEGIN\n" +
                "  WriteLn('Name: ', Person1.Name);\n" +
                "  WriteLn('Age: ', Person1.Age);\n" +
                "END;\n" +
                "\n" +
                "BEGIN\n" +
                "  Person1.Name := 'John Doe';\n" +
                "  Person1.Age := 30;\n" +
                "  PrintPersonInfo;\n" +
                "END.\n";

        System.out.println(test1);

        // PROGRAM SampleProgram;
        // type
        //  TMyClass = class
        //  private
        //    FValue: Integer;
        //  public
        //    constructor Create;
        //    procedure ShowValue;
        //    property Value: Integer read FValue write FValue;
        //  end;
        String test2 = "PROGRAM SampleProgram;\n" +
                "\n" +
                "TYPE\n" +
                "TMyClass = class\n" +
                "private\n" +
                "FValue: Integer;\n" +
                "public\n" +
                "constructor Create;\n" +  // Constructor
                "procedure ShowValue;\n" + // Method declaration
                "property Value: Integer read FValue write FValue;\n" + // Property
                "END;\n";


        delphiLexer lexer = new delphiLexer(CharStreams.fromString(test2));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }
}