package com.vorwieger.tictactoetddkata;

import java.util.Arrays;

public class GridView {
    private final PlayerId[][] grid;

    public GridView(PlayerId[][] grid) {
        this.grid = grid;
    }

    public static GridView fromGrid(PlayerId[][] grid) {
        PlayerId[][] gridCopy = copy2dArr(grid);

        return new GridView(gridCopy);
    }

    private static PlayerId[][] copy2dArr(PlayerId[][] grid) {
        return Arrays.stream(grid)
                .map(PlayerId[]::clone)
                .toArray(s -> grid.clone());
    }

    public PlayerId[][] to2dArray() {
        return grid;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(grid);
    }
}
