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
        String wasmDirPath = "src/main/wasm/";
        String webDirPath = "src/main/web/";

        // Create directories if they don't exist
        try {
            Files.createDirectories(Paths.get(outputDirPath));
            Files.createDirectories(Paths.get(wasmDirPath));
            Files.createDirectories(Paths.get(webDirPath));
        } catch (IOException e) {
            System.err.println("Error creating directories: " + e.getMessage());
            return;
        }

        // Copy web resources once
        copyWebResources(webDirPath);

        for (String testFile : args) {
            if (!testFile.endsWith(".pas")) {
                testFile = testFile + ".pas";
            }

            String filePath = testDirPath + testFile;
            String fileContent = readPasFile(filePath);

            if (fileContent == null) {
                continue;
            }

            try {
                // Parse and generate LLVM IR
                ParseTree tree = parseDelphiFile(fileContent);
                String llvmIR = generateLLVMIR(tree, testFile);

                // Write LLVM IR
                String llFilename = testFile.replace(".pas", ".ll");
                String llPath = outputDirPath + llFilename;
                Files.write(Paths.get(llPath), llvmIR.getBytes());

                // Compile to WASM
                String wasmFilename = testFile.replace(".pas", ".wasm");
                String wasmPath = wasmDirPath + wasmFilename;
                compileToWasm(llPath, wasmPath);

                // Copy WASM to web directory
                Files.copy(Paths.get(wasmPath),
                        Paths.get(webDirPath + wasmFilename),
                        StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Successfully processed: " + testFile);
                System.out.println("  LLVM IR: " + llPath);
                System.out.println("  WASM: " + wasmPath);
                System.out.println();

            } catch (Exception e) {
                System.err.println("Error processing file " + testFile + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Web interface ready at: src/main/web/index.html");
    }

    private static ParseTree parseDelphiFile(String content) {
        delphiLexer lexer = new delphiLexer(CharStreams.fromString(content));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        return parser.program();
    }

    private static String generateLLVMIR(ParseTree tree, String filename) {
        LLVMCodeGenerator codeGenerator = new LLVMCodeGenerator();
        codeGenerator.visit(tree);
        return codeGenerator.generateIR();
    }

    private static void compileToWasm(String llPath, String wasmPath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "C:\\Program Files\\LLVM\\bin\\clang.exe", "-target", "wasm32-unknown-unknown",
                "-O3", "-nostdlib", "-Wl,--no-entry", "-Wl,--export-all",
                "-Wl,--allow-undefined", "-o", wasmPath, llPath
        );

        Process process = pb.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            try (BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line);
                }
            }
            throw new IOException("WASM compilation failed");
        }
    }

    private static void copyWebResources(String webDirPath) {
        try {
            // Create basic web files if they don't exist
            Path indexPath = Paths.get(webDirPath + "index.html");
            if (!Files.exists(indexPath)) {
                String htmlContent = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Delphi WASM Runner</title>\n" +
                        "    <script src=\"runtime.js\"></script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>Select Program to Run</h1>\n" +
                        "    <div id=\"programs\"></div>\n" +
                        "    <div id=\"output\" style=\"white-space:pre;font-family:monospace\"></div>\n" +
                        "</body>\n" +
                        "</html>";
                Files.write(indexPath, htmlContent.getBytes());
            }

            Path jsPath = Paths.get(webDirPath + "runtime.js");
            if (!Files.exists(jsPath)) {
                String jsContent = "const memory = new WebAssembly.Memory({ initial: 1 });\n" +
                        "const imports = { env: { memory } };\n\n" +
                        "async function runWasm(wasmFile) {\n" +
                        "    try {\n" +
                        "        const response = await fetch(wasmFile);\n" +
                        "        const bytes = await response.arrayBuffer();\n" +
                        "        const { instance } = await WebAssembly.instantiate(bytes, imports);\n" +
                        "        document.getElementById('output').textContent = '';\n" +
                        "        instance.exports.main();\n" +
                        "    } catch (err) {\n" +
                        "        document.getElementById('output').textContent = 'Error: ' + err.message;\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// Auto-discover WASM files\n" +
                        "window.onload = () => {\n" +
                        "    fetch('.')\n" +
                        "        .then(r => r.text())\n" +
                        "        .then(html => {\n" +
                        "            const wasmFiles = [...html.matchAll(/href=\"(.*?\\.wasm)\"/g)]\n" +
                        "                .map(m => m[1]);\n" +
                        "            const container = document.getElementById('programs');\n" +
                        "            wasmFiles.forEach(file => {\n" +
                        "                const btn = document.createElement('button');\n" +
                        "                btn.textContent = file;\n" +
                        "                btn.onclick = () => runWasm(file);\n" +
                        "                container.appendChild(btn);\n" +
                        "                container.appendChild(document.createElement('br'));\n" +
                        "            });\n" +
                        "        });\n" +
                        "};";
                Files.write(jsPath, jsContent.getBytes());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not create web resources: " + e.getMessage());
        }
    }

    private static String readPasFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
            return null;
        }
    }
}