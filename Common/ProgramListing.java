/*
 *
 * File: ProgramListing.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: ProgramListing.java generates a program listing, which is a reproduction of the input which has been
 *              scanned throughout compilation
 *
 */

package Common;

import java.io.FileWriter;
import java.io.IOException;

public class ProgramListing {
    private String currentLine;
    private int lineNumber;

    public ProgramListing() {
        currentLine = "";
        lineNumber = 1;

        // Opening and then closing the file will reset the files content
        try {
            new FileWriter("program-listing.txt").close();
        } catch (IOException e) {
            System.err.println("Error: Unable to generate the program listing");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Output the current line to the listing file and clear the currentLine variable
     */
    private void outputCurrentLine() {
        try {
            FileWriter fileWriter = new FileWriter("program-listing.txt", true);
            fileWriter.write(lineNumber + currentLine + "\n");
            fileWriter.close();
            currentLine = "";
        } catch (IOException e) {
            System.err.println("Error: Unable to output program listing to file");
            e.printStackTrace();
        }

    }

    /**
     * Add a single character to the listing
     *
     * @param character character to be added
     */
    public void addCharacter(String character) {
        if (character.compareTo("\n") == 0) {
            outputCurrentLine();
            lineNumber++;
        } else {
            currentLine += character;
        }
    }
}