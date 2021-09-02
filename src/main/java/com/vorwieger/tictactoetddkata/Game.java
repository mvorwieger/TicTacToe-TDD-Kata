package com.vorwieger.tictactoetddkata;

public class Game {
    String[][] grid = {
            {null, null, null},
            {null, null, null},
            {null, null, null}
    };
    private Player lastPlayer = null;

    public void placeMark(Coordinate coord, Player player) {
        if (this.lastPlayer != null && this.lastPlayer.mark().equals(player.mark())) {
            throw CouldNotPlaceMark.becauseYouHaveAlreadyTakenYourTurn(player);
        }

        this.lastPlayer = player;

        grid[coord.x() - 1][coord.y() - 1] = player.mark();
    }

    public boolean spaceIsMarkedBy(Coordinate coord, Player player) {
        return player.mark().equals(grid[coord.x() - 1][coord.y() - 1]);
    }
}
