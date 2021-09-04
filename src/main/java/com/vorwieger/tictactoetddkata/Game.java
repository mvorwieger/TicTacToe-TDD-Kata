package com.vorwieger.tictactoetddkata;

import java.util.ArrayList;
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

        if (gameIsFinished) {
            throw CouldNotPlaceMark.becauseTheGameIsAlreadyFinished(player);
        }

        this.lastPlayer = player;

        grid[coord.x() - 1][coord.y() - 1] = player.mark();
        checkForWinner();
    }

    private void checkForWinner() {
        if (playerHasMatchesInAVerticalRow(lastPlayer)
                || playerHasMatchesInAHorizontalRow(lastPlayer)
                || playerHasMatchesInADiagonalRow(lastPlayer)) {
            winner = lastPlayer;
            gameIsFinished = true;
        }
    }

    private boolean playerHasMatchesInADiagonalRow(Player player) {
        return checkDiagonalLeftToRight(player) ||
                checkDiagonalRightToLeft(player);
    }

    private boolean checkDiagonalLeftToRight(Player player) {
        ArrayList<String> diagonalRowLeft = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            diagonalRowLeft.add(
                    grid[i][i]
            );
        }

        return diagonalRowLeft.stream().allMatch((mark) -> player.mark().equals(mark));
    }

    private boolean checkDiagonalRightToLeft(Player player) {
        ArrayList<String> diagonalRowRight = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            var x = (grid.length - 1) - y;
            // x y i
            // 2 0 0
            // 1 1 1
            // 0 2 2
            diagonalRowRight.add(
                    grid[x][y]
            );
        }

        return diagonalRowRight.stream().allMatch(mark -> player.mark().equals(mark));
    }

    private boolean playerHasMatchesInAHorizontalRow(Player player) {
        for (int x = 0; x < grid.length; x++) {
            var columnList = new ArrayList<String>();

            for (String[] strings : grid) {
                columnList.add(strings[x]);
            }

            if (columnList.stream().allMatch((mark) -> player.mark().equals(mark))) {
                return true;
            }
        }

        return false;
    }

    private boolean playerHasMatchesInAVerticalRow(Player player) {
        return Arrays.stream(grid).anyMatch(
                (row) -> Arrays.stream(row).allMatch(
                        (cell) -> player.mark().equals(cell)
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
