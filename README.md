# Delphi - ANTLR4 Grammar
## Team Members
**Vishal Karthikeyan Setti** \
GatorID - 47670880 

**Jaiharishan Arunagiri Veerakumar** \
GatorID - 62333614 

## Description 
This is a Maven-based project named Delphi. It includes an ANTLR dependency for parsing and processing grammar files. The project is configured to compile with Java 23. The project extends Pascal to support object-oriented features like classes, objects, constructors, destructors, and encapsulation.

## Prerequisites
Before running this project, ensure you have the following installed:

- Java Development Kit (JDK) 21 or later.
- Apache Maven 3.6+.
- A compatible IDE, IntelliJ IDEA preferably.
- LLVM installed with clang

## Project Structure
```
Delphi-Interpreter
├── src
│   └── main
│       ├── java
│           └── org.example
│               ├── Compiler                      (Contains the main compiler logic that generates .ll and .wasm files)
│               └── antlr                         (ANTLR-generated parser files and custom visitors)
│                   └── LLVMCodeGenerator.java    (Generates LLVM IR code from the parsed AST)
│       ├── output                                (Directory for generated .ll files during compilation)
│       ├── tests                                 (Test case files .pas)
│       ├── web                                   (Web hosting components)
│           ├── index.html                        (Browser interface for WASM execution)
│           ├── runtime.js                        (JavaScript runtime for WASM module interaction)
│           └── wasm                              (Stores compiled WebAssembly binaries)
│       └── delphi.g4                             (ANTLR4 Grammar file)
├── pom.xml                                       (Maven configuration file with project dependencies)
├── README.md                                     (Project documentation - This file)             
└── .gitignore                                    (Exclude paths)
```

## Features Implemented
This project extends Pascal to support:

- Classes and Objects
    - Enables modular programming by grouping related data and behaviors.
    - Supports defining class blueprints with attributes (fields) and methods.
    - Both Grammar and Interpreter have been implemented.

- Constructors and Destructors
    - Constructors: Special methods that initialize objects upon creation.
    - Destructors: Handle resource cleanup before an object is removed from memory.
    - Both Grammar and Interpreter have been implemented.

- Encapsulation
    - Implements access specifiers (private, public and protected) to restrict or expose class members.
    - Both Grammar and Interpreter have been implemented.

- Interfaces
    - Grammar for interfaces added enabling abstraction in the language.

- FOR Loop
  - Implemented logic for FOR loop

- WHILE Loop
     - Implemented logic for WHILE loop

- BREAK and CONTINUE
  - Implemented Grammar and logic for BREAK and CONTINUE.

- IF THEN ELSE
  - Implemented Logic for IF THEN ELSE

- User-Defined Procedures and Functions
    - Implemented user defined procedures and functions

- Static Scoping
  - Static Scoping Implemented

## Compilation Pipeline
- ANTLR4 parsing to AST
- LLVM IR (.ll) generation
- WebAssembly (.wasm) compilation
- Browser execution with HTML and JavaScript code

## Installation & Build
- Download the .zip file and extract the files into a folder.
- Ensure that Maven is installed and working by running:
```
mvn -version
```
- Build the project and resolve dependencies using Maven:
```
mvn clean install
```

## Running the project
- **Clean the project**
```
mvn clean
```

- **Compile the project**
```
mvn compile
```

## Compilation Process
- RGenerate LLVM IR and WASM
```
mvn exec:java -D"exec.mainClass"="org.example.Compiler" -D"exec.args"="test"
```

- By running the above command, the corresponding ```.ll``` and ```.wasm``` files are generated. ```test.ll``` file is generated in output folder and ```test.wasm``` is generated in ```wasm``` folder inside ```web``` folder.

## Running in Browser
- Generate the WASM file as above


- Start a local web server

```commandline
cd src/main/web
```

```
python -m http.server 8000
```
- Open http://localhost:8000 in your browser
 

- The page will load and execute the WASM module


- Now click the desired test case to get the output in the text box.

**Recommended approach:**


- Run multiple test files present i.e, test1.pas, test2.pas, test3.pas, test4.pas, test5.pas ..etc.


- Run the test cases:
```
mvn exec:java -D"exec.mainClass"="org.example.Interpreter" -D"exec.args"="test test1 test2 test3 test4 test5 test6 test7 test8 test9 test10 test11 test12 test13 test14"
```
