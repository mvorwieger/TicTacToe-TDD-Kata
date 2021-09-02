package com.vorwieger.tictactoetddkata;

public class Game {
    String[][] grid = {
            {null, null, null},
            {null, null, null},
            {null, null, null}
    };

    public void placeMark(Coordinate coord, Player player) {
        grid[coord.x() - 1][coord.y() - 1] = player.mark();
    }

    public boolean spaceIsMarkedBy(Coordinate coord, Player player) {
        return player.mark().equals(grid[coord.x() - 1][coord.y() - 1]);
    }
}
