package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;
import java.io.*;
    import java.util.Map;
    import java.util.HashMap;

public class Interpreter {
    public static void main(String[] args) {
        String test1Content = readPasFile("src\\main\\tests\\test1.pas");
        String test2Content = readPasFile("src\\main\\tests\\test2.pas");

        delphiLexer lexer = new delphiLexer(CharStreams.fromString(test2Content));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }

    private static String readPasFile(String filePath) {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n"); // Preserve line breaks
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath + " - " + e.getMessage());
        }

        return fileContent.toString();
    }
}