/*
 *
 * File: SymbolTable.java
 * Assignment: Project Part 1
 * Course: COMP3290 (Compiler Design)
 * Author: Olivia Favos
 * Student Number: c3188124
 *
 * Description: SymbolTable.java allows for the efficient lookup of symbols
 *
 */

package Common;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private HashMap<String, TableEntry> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<String, TableEntry>();
    }

    public int addTableEntry() {
        return 0;
    }


    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Map.Entry<String, TableEntry> entry : symbolTable.entrySet()) {
            out.append(entry.getKey()).append(", ").append(entry.getValue().getSymbol()).append("\n");
        }

        return out.toString();
    }
}