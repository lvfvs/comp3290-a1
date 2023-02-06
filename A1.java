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

import java.util.ArrayList;

public class A1 {
    ArrayList<Token> tokenList = new ArrayList<>();
    public static void main(String[] args) {
        // This program requires at least 3 arguments to run
        if (args.length >= 1) {
            A1 program = new A1();
            program.run(args);
        } else {
            // If the required amount of program arguments are not detected, output error, and terminate
            System.out.println("Error: Please input the required arguments");
            System.exit(1);
        }
    }

    public void run(String[] args) {

        String fileName = args[0];

        Common.SymbolTable symbolTable = new Common.SymbolTable();
        Common.OutputController outputController = new Common.OutputController();
        Scanner scanner = new Scanner(fileName, outputController, symbolTable);

        Token token;

        // A compact driver which allows the scanner to read input until end of file, and prints the returned token
        while (!scanner.eof()) {
            token = scanner.getToken();
            tokenList.add(token);
        }

        tokenList.add(scanner.getToken());

        for (int i = 0; i < tokenList.size() ; i++) {
            System.out.print(tokenList.get(i));

            if (i % 10 == 0 && i > 0) {
                System.out.println();
            }
        }
    }
}