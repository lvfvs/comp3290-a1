/*
 *
 * File: TableEntry.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: TableEntry.java is a symbol which is assigned to the SymbolTable
 *
 */

package Common;

public class TableEntry {
    private String type;
    private String symbol;

    public TableEntry(String type, String symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }
    public String getSymbol() {
        return symbol;
    }
}