/*
 *
 * File: OutputController.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: OutputController.java doesn't do a lot right now. But! Eventually, it will output lexical errors,
 *              cut off output after the output exceeds 60 characters in length, and will receive a
 *              character from the scanner and add it to the program listing.
 *
 */

/* Intended updates to this file:
   - A method to output all the errors found after end of file is detected
   - A method which recognises when the output exceeds 60 characters in length, and terminates the output line
   - A method to receive a character from the scanner and add it to the program listing
*/

package Common;

public class OutputController {

   // I know this should be here since I'd like to print the error with the line number! So, putting it here for later
   private int lineNumber;

   // Is not currently used...
   public String errorOutput() {
      return "LEXICAL ERROR: ";
   }
}