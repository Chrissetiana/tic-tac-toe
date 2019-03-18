package com.chrissetiana.tictactoe;

class Player {

    private String name;
    private String value;

    Player(String name, String value) {
        this.name = name;
        this.value = value;
    }

    String getValue() {
        return value;
    }
}
