/*
 *
 * File: Token.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: This class holds information about a token, and a toString() method for the purpose of printing.
 *
 */

package Token;

public class Token {

    // An enum containing all reserved keywords, punctuation, and operators
    public enum Tokens {
        TTEOF,
        TCD22,
        TCONS,
        TTYPS,
        TTDEF,
        TARRS,
        TMAIN,
        TBEGN,
        TTEND,
        TARAY,
        TTTOF,
        TFUNC,
        TVOID,
        TCNST,
        TINTG,
        TFLOT,
        TBOOL,
        TTFOR,
        TREPT,
        TUNTL,
        TIFTH,
        TELSE,
        TELIF,
        TINPT,
        TPRNT,
        TPRLN,
        TRETN,
        TNOTT,
        TTAND,
        TTTOR,
        TTXOR,
        TTRUE,
        TFALS,
        TCOMA,
        TLBRK,
        TRBRK,
        TLPAR,
        TRPAR,
        TEQUL,
        TPLUS,
        TMINS,
        TSTAR,
        TDIVD,
        TPERC,
        TCART,
        TLESS,
        TGRTR,
        TCOLN,
        TSEMI,
        TDOTT,
        TLEQL,
        TGEQL,
        TNEQL,
        TEQEQ,
        TPLEQ,
        TMNEQ,
        TSTEQ,
        TDVEQ,
        TIDEN,
        TILIT,
        TFLIT,
        TSTRG,
        TUNDF,
    }

    // An array containing the string values of all reserved keywords, punctuation, and operators
    private static final String[] TPRINT = {
            "TTEOF ",
            "TCD22 ",
            "TCONS ",
            "TTYPS ",
            "TTDEF ",
            "TARRS ",
            "TMAIN ",
            "TBEGN ",
            "TTEND ",
            "TARAY ",
            "TTTOF ",
            "TFUNC ",
            "TVOID ",
            "TCNST ",
            "TINTG ",
            "TFLOT ",
            "TBOOL ",
            "TTFOR ",
            "TREPT ",
            "TUNTL ",
            "TIFTH ",
            "TELSE ",
            "TELIF ",
            "TINPT ",
            "TPRNT ",
            "TPRLN ",
            "TRETN ",
            "TNOTT ",
            "TTAND ",
            "TTTOR ",
            "TTXOR ",
            "TTRUE ",
            "TFALS ",
            "TCOMA ",
            "TLBRK ",
            "TRBRK ",
            "TLPAR ",
            "TRPAR ",
            "TEQUL ",
            "TPLUS ",
            "TMINS ",
            "TSTAR ",
            "TDIVD ",
            "TPERC ",
            "TCART ",
            "TLESS ",
            "TGRTR ",
            "TCOLN ",
            "TSEMI ",
            "TDOTT ",
            "TLEQL ",
            "TGEQL ",
            "TNEQL ",
            "TEQEQ ",
            "TPLEQ ",
            "TMNEQ ",
            "TSTEQ ",
            "TDVEQ ",
            "TIDEN ",
            "TILIT ",
            "TFLIT ",
            "TSTRG ",
            "TUNDF "
    };

    private final Tokens tokenID;
    private final int lineNumber;
    private final int columnNumber;
    private final String lexeme;

    public Token(Tokens token, int lineNumber, int columnNumber, String lexeme) {
        this.tokenID = token;
        this.lineNumber = 0;
        this.columnNumber = 0;
        this.lexeme = "";
    }

    // Not a lot is happening with the following classes at the moment, but they will be used in the next submission
    public Tokens getTokenID() {
        return tokenID;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public String getLexeme() {
        return lexeme;
    }

    // Allows the program to provide the string representation of the Tokens
    public String toString() {
        return TPRINT[tokenID.ordinal()];

        // Pads printed output into spaced sections of 6, one field containing the token value, and the following containing the lexeme
    }
}
