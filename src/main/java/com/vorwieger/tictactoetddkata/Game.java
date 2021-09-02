package com.vorwieger.tictactoetddkata;

import java.util.Arrays;
import java.util.Optional;

public class Game {
    String[][] grid = {
            {null, null, null},
            {null, null, null},
            {null, null, null}
    };
    private Player lastPlayer = null;
    private Player winner;
    private boolean gameIsFinished = false;

    public void placeMark(Coordinate coord, Player player) {
        if (playedTheLastTurn(player)) {
            throw CouldNotPlaceMark.becauseYouHaveAlreadyTakenYourTurn(player);
        }

        if(gameIsFinished) {
            throw CouldNotPlaceMark.becauseTheGameIsAlreadyFinished(player);
        }

        this.lastPlayer = player;

        grid[coord.x() - 1][coord.y() - 1] = player.mark();
        checkForWinner();
    }

    private void checkForWinner() {
        if(lastPlayerHasThreeMatchingSpacesInARow()) {
            winner = lastPlayer;
            gameIsFinished = true;
        }
    }

    private boolean lastPlayerHasThreeMatchingSpacesInARow() {
        return Arrays.stream(grid).anyMatch(
                (row) -> Arrays.stream(row).allMatch(
                        (cell) -> lastPlayer.mark().equals(cell)
                )
        );
    }

    private boolean playedTheLastTurn(Player player) {
        return this.lastPlayer != null && this.lastPlayer.mark().equals(player.mark());
    }

    public boolean spaceIsMarkedBy(Coordinate coord, Player player) {
        return player.mark().equals(grid[coord.x() - 1][coord.y() - 1]);
    }

    public Optional<Player> winner() {
        return Optional.ofNullable(winner);
    }
}
