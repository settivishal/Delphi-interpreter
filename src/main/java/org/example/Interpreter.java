package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.*;
import java.io.*;
import java.nio.file.*;

public class Interpreter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide test file names as arguments.");
            System.out.println("Example: mvn exec:java -D\"exec.mainClass\"=\"org.example.Interpreter\" -D\"exec.args\"=\"test1 ...\"");
            return;
        }

        String testDirPath = "src/main/tests/";
        String outputDirPath = "src/main/output/";

        // Create output directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(outputDirPath));
        } catch (IOException e) {
            System.err.println("Error creating output directory: " + e.getMessage());
            return;
        }

        for (String testFile : args) {
            if (!testFile.endsWith(".pas")) {
                testFile = testFile + ".pas";
            }

            String filePath = testDirPath + testFile;
            String fileContent = readPasFile(filePath);

            if (fileContent == null) {
                continue; // Skip if file couldn't be read
            }

            // Process each file
            delphiLexer lexer = new delphiLexer(CharStreams.fromString(fileContent));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            delphiParser parser = new delphiParser(tokens);

            try {
                ParseTree tree = parser.program();

                System.out.println("Processing file: " + testFile);

                // Generate LLVM IR
                LLVMCodeGenerator codeGenerator = new LLVMCodeGenerator();
                codeGenerator.visit(tree);
                String llvmIR = codeGenerator.generateIR();

                // Write LLVM IR to file
                String outputFileName = testFile.replace(".pas", ".ll");
                String outputPath = outputDirPath + outputFileName;
                Files.write(Paths.get(outputPath), llvmIR.getBytes());

                System.out.println("Successfully generated LLVM IR: " + outputPath);
                System.out.println();

            } catch (Exception e) {
                System.err.println("Error processing file " + testFile + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static String readPasFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + filePath + " - " + e.getMessage());
            return null;
        }
    }
}