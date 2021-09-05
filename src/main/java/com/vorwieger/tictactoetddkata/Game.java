package com.vorwieger.tictactoetddkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Game {
    private final GameId id;
    private final PlayerId[][] grid = {
            {null, null, null},
            {null, null, null},
            {null, null, null}
    };

    private PlayerId lastPlayer = null;
    private PlayerId winner;
    private boolean gameIsFinished = false;
    private final List<PlayerId> playerIds;

    public Game(GameId id, List<PlayerId> playerIds) {
        this.id = id;
        this.playerIds = playerIds;
    }

    public void placeMark(Coordinate coord, PlayerId playerId) {
        if (playedTheLastTurn(playerId)) {
            throw CouldNotPlaceMark.becauseYouHaveAlreadyTakenYourTurn(playerId);
        }

        if (gameIsFinished) {
            throw CouldNotPlaceMark.becauseTheGameIsAlreadyFinished(playerId);
        }

        this.lastPlayer = playerId;

        grid[coord.x() - 1][coord.y() - 1] = playerId;
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

    private boolean playerHasMatchesInADiagonalRow(PlayerId playerId) {
        return checkDiagonalLeftToRight(playerId) ||
                checkDiagonalRightToLeft(playerId);
    }

    private boolean checkDiagonalLeftToRight(PlayerId player) {
        ArrayList<PlayerId> diagonalRowLeft = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            diagonalRowLeft.add(
                    grid[i][i]
            );
        }

        return diagonalRowLeft.stream().allMatch(player::equals);
    }

    private boolean checkDiagonalRightToLeft(PlayerId playerId) {
        ArrayList<PlayerId> diagonalRowRight = new ArrayList<>();
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

        return diagonalRowRight.stream().allMatch(playerId::equals);
    }

    private boolean playerHasMatchesInAHorizontalRow(PlayerId playerId) {
        for (int x = 0; x < grid.length; x++) {
            ArrayList<PlayerId> columnList = new ArrayList<>();

            for (PlayerId[] ids : grid) {
                columnList.add(ids[x]);
            }

            if (columnList.stream().allMatch(playerId::equals)) {
                return true;
            }
        }

        return false;
    }

    private boolean playerHasMatchesInAVerticalRow(PlayerId playerId) {
        return Arrays.stream(grid).anyMatch(
                (row) -> Arrays.stream(row).allMatch(
                        playerId::equals
                )
        );
    }

    private boolean playedTheLastTurn(PlayerId player) {
        return this.lastPlayer != null && this.lastPlayer.id().equals(player.id());
    }

    public boolean spaceIsMarkedBy(Coordinate coord, Player player) {
        return player.id().equals(grid[coord.x() - 1][coord.y() - 1]);
    }

    public Optional<PlayerId> winner() {
        return Optional.ofNullable(winner);
    }

    public GameId id() {
        return id;
    }

    public List<PlayerId> playerIds() {
        return this.playerIds;
    }

    public GridView viewGrid() {
        return GridView.fromGrid(grid);
    }
}
