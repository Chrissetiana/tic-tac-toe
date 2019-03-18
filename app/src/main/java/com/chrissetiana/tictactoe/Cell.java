package com.chrissetiana.tictactoe;

public class Cell {

    Player player;

    public Cell(Player player) {
        this.player = player;
    }

    boolean isEmpty() {
        return player == null || StringUtility.isNullOrEmpty(player.getValue());
    }
}
