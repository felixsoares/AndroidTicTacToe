package com.droiddrank.tictactoe;

/**
 * Created by felix on 24/04/2018.
 */

public class User {

    private int id;
    private String symbol;

    public User(int id, String symbol) {
        this.setId(id);
        this.setSymbol(symbol);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
