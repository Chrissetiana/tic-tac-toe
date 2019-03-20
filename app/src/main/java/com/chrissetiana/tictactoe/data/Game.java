package com.chrissetiana.tictactoe.data;

import android.arch.lifecycle.MutableLiveData;

import static com.chrissetiana.tictactoe.util.Utils.isNullOrEmpty;

public class Game {

    private static final int SIZE = 3;
    private Player playerA;
    private Player playerB;
    public Player currentPlayer;
    public Cell[][] cells;
    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(String player1, String player2) {
        cells = new Cell[SIZE][SIZE];
        playerA = new Player(player1, "x");
        playerB = new Player(player2, "o");
        currentPlayer = playerA;
    }

    public boolean isGameOver() {
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
            for (int i = 0; i < SIZE; i++) {
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
            for (int i = 0; i < SIZE; i++) {
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
            if (cell == null || isNullOrEmpty(cell.player.value)) {
                return false;
            }
        }

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++) {
            if (!comparisonBase.player.value.equals(cells[i].player.value)) {
                return false;
            }
        }

        return true;
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == playerA ? playerB : playerA;
    }

    public void reset() {
        playerA = null;
        playerB = null;
        currentPlayer = null;
        cells = null;
    }
}
