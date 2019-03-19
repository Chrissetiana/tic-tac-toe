package com.chrissetiana.tictactoe.data;

public class Player {

    private String name;
    private String value;

    Player(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
