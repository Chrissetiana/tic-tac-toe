package com.chrissetiana.tictactoe;

import android.text.TextUtils;

public class Cell {

    Player player;

    public Cell(Player player) {
        this.player = player;
    }

    boolean isEmpty() {
        return player == null || TextUtils.isEmpty(player.getValue());
    }
}
