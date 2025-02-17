package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;
import java.io.*;

public class Interpreter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide test file names as arguments.");
            System.out.println("Example: mvn exec:java -D\"exec.mainClass\"=\"org.example.Interpreter\" -D\"exec.args\"=\"test1 ...\"");
            return;
        }

        String testDirPath = "src/main/tests/";

        for (String testFile : args) {
            if (!testFile.endsWith(".pas")) {
                testFile = testFile + ".pas";
            }

            String filePath = testDirPath + testFile;
            String fileContent = readPasFile(filePath);

            // Process each file
            delphiLexer lexer = new delphiLexer(CharStreams.fromString(fileContent));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            delphiParser parser = new delphiParser(tokens);
            ParseTree tree = parser.program();

            System.out.println("Processing file: " + testFile);
            System.out.println(tree.toStringTree(parser));
            System.out.println();
        }
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