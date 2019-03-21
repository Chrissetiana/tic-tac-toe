package com.chrissetiana.tictactoe.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.chrissetiana.tictactoe.data.Cell;
import com.chrissetiana.tictactoe.data.Game;
import com.chrissetiana.tictactoe.data.Player;

import static com.chrissetiana.tictactoe.util.Utils.stringFormat;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int col) {
        if (game.cells[row][col] == null) {
            game.cells[row][col] = new Cell(game.currentPlayer);
            cells.put(stringFormat(row, col), game.currentPlayer.value);

            if (game.isGameOver()) {
                game.reset();
            } else {
                game.changePlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
