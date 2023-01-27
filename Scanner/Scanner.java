/*
 *
 * File: Scanner.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: Scanner.java is the main class of this program, scanning and reading the input provided by
 *              a file, and assigning characters read into tokens which are returned.
 *
 */

package Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

import Token.Token.Tokens;
import Token.Token;
import Common.*;

public class Scanner {
    private boolean eof;

    // lineNumber and columnNumber will be used in later parts of the assignments
    private int lineNumber;
    private int columnNumber;
    private HashMap<String, Tokens> reservedKeywords;
    private HashMap<String, Tokens> reservedPunctuation;
    private HashMap<String, Tokens> reservedSingleOperators;
    private HashMap<String, Tokens> reservedDoubleOperators;
    private ScannerState currentState;
    java.util.Scanner scanner;
    String character;
    StringBuilder charactersRead;

    // An enum which acts as a state machine, keeping track of which state the scanner is in
    private enum ScannerState {
        START,
        IDENTIFIER,
        INTEGER,
        FLOAT,
        STRING,
        PUNCTUATION

        /*
        COMMENT,
        SL_COMMENT,
        ML_COMMENT,
        DELIMITER,
        UNDEFINED,
        */
    }

    // Opens file with the specified filename and configures it for reading one character at a time
    public Scanner(String fileName, Output output, SymbolTable symbolTable) {
        File file = new File(fileName);

        reservedKeywordList();
        reservedPunctuationList();
        reservedSingleOperatorList();
        reservedDoubleOperatorList();

        charactersRead = new StringBuilder();

        try {
            scanner = new java.util.Scanner(file);
            scanner.useDelimiter("");


        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(1);
        }
    }

    // A method which defines the conditions for end of file
    public boolean eof() {
        return eof && charactersRead.isEmpty();
    }

    // getToken() scans through the provided file and passes data to the readInput() method
    public Token getToken() {
        if (eof()) {
            return new Token(Tokens.TTEOF, lineNumber, columnNumber, null);
        }

        if (charactersRead.length() == 0) {
            while (scanner.hasNext()) {
                character = scanner.next();

                if (character.equals(" ") || character.equals("\t") || character.equals("\n")) {
                    break;
                }

                charactersRead.append(character);

                if (!scanner.hasNext()) {
                    eof = true;
                }
            }
        }

        return readInput();
    }

    // A HashMap containing each reserved keyword in the CD22 language
    public void reservedKeywordList() {
        reservedKeywords = new HashMap<>();

        reservedKeywords.put("cd22", Tokens.TCD22);
        reservedKeywords.put("constants", Tokens.TCONS);
        reservedKeywords.put("types", Tokens.TTYPS);
        reservedKeywords.put("def", Tokens.TTDEF);
        reservedKeywords.put("arrays", Tokens.TARRS);
        reservedKeywords.put("main", Tokens.TMAIN);
        reservedKeywords.put("begin", Tokens.TBEGN);
        reservedKeywords.put("end", Tokens.TTEND);
        reservedKeywords.put("array", Tokens.TARAY);
        reservedKeywords.put("of", Tokens.TTTOF);
        reservedKeywords.put("func", Tokens.TFUNC);
        reservedKeywords.put("void", Tokens.TVOID);
        reservedKeywords.put("const", Tokens.TCNST);
        reservedKeywords.put("int", Tokens.TINTG);
        reservedKeywords.put("float", Tokens.TFLOT);
        reservedKeywords.put("bool", Tokens.TBOOL);
        reservedKeywords.put("for", Tokens.TTFOR);
        reservedKeywords.put("repeat", Tokens.TREPT);
        reservedKeywords.put("until", Tokens.TUNTL);
        reservedKeywords.put("if", Tokens.TIFTH);
        reservedKeywords.put("else", Tokens.TELSE);
        reservedKeywords.put("elif", Tokens.TELIF);
        reservedKeywords.put("input", Tokens.TINPT);
        reservedKeywords.put("print", Tokens.TPRNT);
        reservedKeywords.put("printline", Tokens.TPRLN);
        reservedKeywords.put("return", Tokens.TRETN);
        reservedKeywords.put("not", Tokens.TNOTT);
        reservedKeywords.put("and", Tokens.TTAND);
        reservedKeywords.put("or", Tokens.TTTOR);
        reservedKeywords.put("xor", Tokens.TTXOR);
        reservedKeywords.put("true", Tokens.TTRUE);
        reservedKeywords.put("false", Tokens.TFALS);
    }

    // A HashMap containing each reserved punctuation in the C22 language
    public void reservedPunctuationList() {
        reservedPunctuation = new HashMap<>();

        reservedPunctuation.put(",", Tokens.TCOMA);
        reservedPunctuation.put("[", Tokens.TLBRK);
        reservedPunctuation.put("]", Tokens.TRBRK);
        reservedPunctuation.put("(", Tokens.TLPAR);
        reservedPunctuation.put(")", Tokens.TRPAR);
        reservedPunctuation.put(":", Tokens.TCOLN);
        reservedPunctuation.put(";", Tokens.TSEMI);
        reservedPunctuation.put(".", Tokens.TDOTT);
    }

    // A HashMap containing each reserved single operator in the CD22 language
    public void reservedSingleOperatorList() {
        reservedSingleOperators = new HashMap<>();

        reservedSingleOperators.put("=", Tokens.TEQUL);
        reservedSingleOperators.put("+", Tokens.TPLUS);
        reservedSingleOperators.put("-", Tokens.TMINS);
        reservedSingleOperators.put("*", Tokens.TSTAR);
        reservedSingleOperators.put("/", Tokens.TDIVD);
        reservedSingleOperators.put("%", Tokens.TPERC);
        reservedSingleOperators.put("^", Tokens.TCART);
        reservedSingleOperators.put("<", Tokens.TLESS);
        reservedSingleOperators.put(">", Tokens.TGRTR);
    }

    // A HashMap containing each reserved double operator in the CD22 language
    public void reservedDoubleOperatorList() {
        reservedDoubleOperators = new HashMap<>();

        reservedDoubleOperators.put("<=", Tokens.TLEQL);
        reservedDoubleOperators.put(">=", Tokens.TGEQL);
        reservedDoubleOperators.put("==", Tokens.TEQEQ);
        reservedDoubleOperators.put("+=", Tokens.TPLEQ);
        reservedDoubleOperators.put("-=", Tokens.TMNEQ);
        reservedDoubleOperators.put("*=", Tokens.TSTEQ);
        reservedDoubleOperators.put("/=", Tokens.TDVEQ);
        reservedDoubleOperators.put("!=", Tokens.TNEQL);
    }

    /*
       Reads charactersRead into a StringBuilder called characterBuffer, and loops through
       a switch case which represents a state machine, identifying which state the scanner
       has entered.
     */
    private Token readInput() {
        currentState = ScannerState.START;
        StringBuilder characterBuffer = new StringBuilder(String.valueOf(charactersRead.charAt(0)));
        boolean tokenFound = false;

        // An enum representing different number states, a solution to differentiating integers and floats
        enum NumberState {
            INTEGER,
            FLOAT_CHECK,
            FLOAT_CONFIRMED,
        }

        NumberState currentNumberState = NumberState.INTEGER;

        for (int currentCharacter = 0; currentCharacter < charactersRead.length(); currentCharacter++) {
            switch (currentState) {

                // START state is the initial state, which immediately calls the checkInput method, where the next state transition will be identified
                case START:
                    checkInput(charactersRead.charAt(currentCharacter));
                    break;

                // IDENTIFIER is transitioned to if the currentCharacter begins with a letter char, and a possible token is evaluated based upon the conditions defined within the state
                case IDENTIFIER:
                    if (Character.isLetter(charactersRead.charAt(currentCharacter))) {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                    } else if (Character.isDigit(charactersRead.charAt(currentCharacter))) {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                    } else {
                        tokenFound = true;
                    }

                    break;

                // INTEGER is transitioned to if the currentCharacter is identified as a digit char. It contains a smaller state machine within, determining whether an integer or float has been read
                case INTEGER:
                    if (Character.isDigit(charactersRead.charAt(currentCharacter)) && currentNumberState == NumberState.INTEGER) {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                    }

                    // If there is a '.' found while reading a sequence of digits, the currentNumberState becomes FLOAT_CHECK
                    if (charactersRead.charAt(currentCharacter) == '.') {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                        currentNumberState = NumberState.FLOAT_CHECK;
                        break;
                    }

                    // If the currentNumberState is FLOAT_CHECK upon entering the INTEGER state, we can confirm that we have a float. The currentNumberState will transition to FLOAT_CONFIRMED, and the currentState becomes FLOAT
                    if (currentNumberState == NumberState.FLOAT_CHECK) {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                        currentNumberState = NumberState.FLOAT_CONFIRMED;
                        currentState = ScannerState.FLOAT;
                    }

                    break;

                // STRING is transitioned to if the currentCharacter is an opening double-quotation mark
                case STRING:
                    if (String.valueOf(charactersRead.charAt(currentCharacter)).equals("\"")) {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                        tokenFound = true;
                    } else if (charactersRead.charAt(charactersRead.length() - 1) == '\n') {
                        charactersRead = null;
                    } else {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));
                    }

                    break;

                // PUNCTUATION is transitioned to if a character from the ArrayList of punctuation is found. This case includes both single and double operators
                case PUNCTUATION:
                    if (characterBuffer.length() == 1) {
                        characterBuffer.append(charactersRead.charAt(currentCharacter));

                        if (reservedDoubleOperators.get(characterBuffer.toString()) != null) {
                            tokenFound = true;
                            break;
                        }

                        characterBuffer.deleteCharAt(characterBuffer.length() - 1);

                        if (reservedSingleOperators.get(characterBuffer.toString()) != null) {
                            tokenFound = true;
                            break;
                        }

                        // After this, handle an undefined token....

                    }

                    break;

                // The following is code that's in the works. Going to try and get it working for A2!

                /*
                case COMMENT:
                    if(charactersRead.isEmpty()) {
                        charactersRead.append(character.charAt(currentCharacter));
                    }

                    else if(charactersRead.length() == 1) {
                        if(character.charAt(currentCharacter) == '-') {
                            charactersRead.append(character.charAt(currentCharacter));
                            currentState = ScannerState.SL_COMMENT;
                        }
                    }

                    else if(character.charAt(currentCharacter) == '*') {
                        charactersRead.append(character.charAt(currentCharacter));
                        currentState = ScannerState.ML_COMMENT;
                    }

                    break;

                case SL_COMMENT:
                    if(charactersRead.length() == 2) {
                        if(character.charAt(currentCharacter) == '-') {
                            charactersRead = null;
                        }
                    }

                    break;

                case ML_COMMENT:
                    if(charactersRead.length() == 2) {
                        if(character.charAt(currentCharacter) == '*') {

                            charactersRead = null;
                        }
                    }
                    break;

                case DELIMITER:

                    break;
                */
            }

            // Breaks as soon as a token is found, bringing the program into token identification
            if (tokenFound) {
                break;
            }
        }

        // The following lines of code will identify tokenFound which are in any of the four reserved HashMaps
        charactersRead.delete(0, characterBuffer.length());

        Tokens identifiedToken = reservedKeywords.get(characterBuffer.toString().toLowerCase());

        if (identifiedToken == null) {
            identifiedToken = reservedPunctuation.get(characterBuffer.toString().toLowerCase());
        }

        if (identifiedToken == null) {
            identifiedToken = reservedSingleOperators.get(characterBuffer.toString().toLowerCase());
        }

        if (identifiedToken == null) {
            identifiedToken = reservedDoubleOperators.get(characterBuffer.toString().toLowerCase());
        }

        // If the tokenFound does not correspond to any reserved HashMaps, we will assign the identifiedToken based on its currentState
        if (currentState == ScannerState.INTEGER) {
            if (characterBuffer.toString().contains(".")) {
                return new Token(Tokens.TFLIT, lineNumber, columnNumber, characterBuffer.toString());
            }

            return new Token(Tokens.TILIT, lineNumber, columnNumber, characterBuffer.toString());
        }

        if (currentState == ScannerState.FLOAT) {
            return new Token(Tokens.TFLIT, lineNumber, columnNumber, characterBuffer.toString());
        }

        if (currentState == ScannerState.STRING) {
            return new Token(Tokens.TSTRG, lineNumber, columnNumber, characterBuffer.toString());
        }

        // If tokenFound does not apply to any of the above if statements, and the tokenFound is not undefined, returns a TIDEN Token
        return identifiedToken == null ? new Token(Tokens.TIDEN, lineNumber, columnNumber, characterBuffer.toString()) : new Token(identifiedToken, lineNumber, columnNumber, characterBuffer.toString());
    }

    // Accessed by the START state of the ScannerState machine, checkInput() will interpret the first character of input, and determine which state it will visit next
    private void checkInput(char character) {
        if (Character.isLetter(character)) {
            currentState = ScannerState.IDENTIFIER;
        } else if (Character.isDigit(character)) {
            currentState = ScannerState.INTEGER;
        } else if (String.valueOf(character).equals("\"")) {
            currentState = ScannerState.STRING;
        } else if (new ArrayList<>(Arrays.asList(",", "[", "]", "(", ")", "=", "+", "-", "*", "/", "%", "^", "<", ">", "!", "\"", ":", ";", ".")).contains(String.valueOf(character))) {
            currentState = ScannerState.PUNCTUATION;
        }
    }
}





