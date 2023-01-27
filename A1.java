/*
 *
 * File: A1.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: A1.java is the driver class for this program, and instantiates our Scanner,
 *              Output, Parser, and SymbolTable.
 *
 */

import Scanner.Scanner;
import Token.Token;

public class A1 {
    public static void main(String[] args) {
        String fileName = args[0];

        Common.SymbolTable symbolTable = new Common.SymbolTable();

        Common.Output output = new Common.Output();

        Scanner scanner = new Scanner(fileName, output, symbolTable);

        Parser parser = new Parser(scanner);

        Token token;

        // A compact driver which allows the scanner to read input until end of file, and prints the returned token
        while (!scanner.eof()) {
            token = scanner.getToken();
            System.out.println(token.toString());
        }

        System.out.println(scanner.getToken());
    }
}