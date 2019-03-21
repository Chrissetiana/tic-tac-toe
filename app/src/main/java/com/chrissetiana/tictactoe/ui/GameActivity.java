package com.chrissetiana.tictactoe.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.chrissetiana.tictactoe.R;
import com.chrissetiana.tictactoe.data.Player;
import com.chrissetiana.tictactoe.databinding.ActivityGameBinding;
import com.chrissetiana.tictactoe.util.GameViewModel;

import static com.chrissetiana.tictactoe.util.Utils.isNullOrEmpty;

public class GameActivity extends AppCompatActivity {

    private static final String GAME_BEGIN = "game_dialog_tag";
    private static final String GAME_END = "game_end_dialog_tag";
    private static final String NO_WINNER = "No one";
    private GameViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startNewGame();
    }

    public void startNewGame() {
        GameBeginDialog dialog = GameBeginDialog.getInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN);
    }

    public void setPlayers(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        viewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        viewModel.init(player1, player2);

        activityGameBinding.setGameViewModel(viewModel);
        setGameListener();
    }

    private void setGameListener() {
        viewModel.getWinner().observe(this, this::endCurrentGame);
    }

    @VisibleForTesting
    public void endCurrentGame(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.name) ? NO_WINNER : winner.name;

        GameEndDialog dialog = GameEndDialog.getInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END);
    }
}
