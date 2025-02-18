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

## Project Structure
```
Delphi/
│── src/
│   ├── main/java/org/example/antlr/                    (Generated ANTLR files)
│   ├── main/java/org/example/antlr/ExecuteVisitor.java (Java file extends delphiBaseVisitor)
│   ├── test/java/org/example/Interpreter               (Main Interpreter file)
│   ├── main/delphi.g4                                  (ANTLR4 Grammar file)
│   ├── main/tests                                      (Test case files)
│── pom.xml     (Maven build configuration)
│── README.md   (This file)
│── .gitignore  (Exclude paths)
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

- **Run the program**
```
mvn exec:java -D"exec.mainClass"="org.example.Interpreter"
```

## Testing
You need to write test cases covering all implemented features. Recommended approach:

- Run multiple test files present i.e, test1.pas, test2.pas, test3.pas, test4.pas, test5.pas.

- Run the test cases:
```
mvn exec:java -D"exec.mainClass"="org.example.Interpreter" -D"exec.args"="test1 test2 test3 test4 test5"
```
