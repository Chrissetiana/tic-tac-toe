package com.chrissetiana.tictactoe;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import static com.chrissetiana.tictactoe.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {

    private ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onCellClickk(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.getValue());

            if (game.hasGameEnded()) {
                game.reset();
            } else {
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
