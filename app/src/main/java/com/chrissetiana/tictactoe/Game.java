package com.chrissetiana.tictactoe;

public class Game {

    private static final String LOG_TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    private Player player1;
    private Player player2;

    private Player currentPlayer = player1;
    private Cell[][] cells;
}
