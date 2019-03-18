package com.chrissetiana.tictactoe;

import android.arch.lifecycle.MutableLiveData;

public class Game {

    private static final String LOG_TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;
    private final String playerOne = "P1";
    private final String playerTwo = "P2";


    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private Cell[][] cells;

    private MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "x");
        player2 = new Player(playerTwo, "o");
        currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean hasGameEnded() {
        if (hasSameHorizontalCells() || hasSameVerticalCells() || hasSameDiagonalCells()) {
            winner.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winner.setValue(null);
            return true;
        }

        return false;
    }

    private boolean hasSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(cells[i][0], cells[i][1], cells[i][2])) {
                    return true;
                }
            }

            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(cells[0][i], cells[1][i], cells[2][i])) {
                    return true;
                }
            }

            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isBoardFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == null || cell.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0) {
            return false;
        }

        for (Cell cell : cells) {
            if (cell == null ||
                    cell.player.getValue() == null ||
                    cell.player.getValue().length() == 0) {
                return false;
            }
        }

        Cell compareCell = cells[0];

        for (int i = 0; i < cells.length; i++) {
            if (!compareCell.player.getValue().equals(cells[i].player.getValue())) {
                return false;
            }
        }

        return true;
    }

    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }
}
