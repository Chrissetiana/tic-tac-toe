package com.chrissetiana.tictactoe.data;

import com.chrissetiana.tictactoe.util.Utils;

public class Cell {

    Player player;

    public Cell(Player player) {
        this.player = player;
    }

    boolean isEmpty() {
        return player == null || Utils.isNullOrEmpty(player.value);
    }
}
