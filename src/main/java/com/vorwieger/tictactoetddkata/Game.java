package com.vorwieger.tictactoetddkata;

public class Game {
    String[][] grid = {
            {null, null, null},
            {null, null, null},
            {null, null, null}
    };

    public void placeMark(int x, int y, String mark) {
        grid[x - 1][y - 1] = mark;
    }

    public Space getSpace(int i, int i1) {
        return null;
    }

    public boolean spaceIsMarkedBy(int xCord, int yCord, String mark) {
        return mark.equals(grid[xCord - 1][yCord - 1]);
    }
}
