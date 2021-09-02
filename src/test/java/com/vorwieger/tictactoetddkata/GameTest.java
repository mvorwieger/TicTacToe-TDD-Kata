package com.vorwieger.tictactoetddkata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    @Test()
    public void shouldBeAbleToPlaceAMark() {
        Game game = new Game();
        game.placeMark(new Coordinate(1, 2), new Player("X"));
        game.placeMark(new Coordinate(2, 3), new Player("O"));
        assertThat(game.spaceIsMarkedBy(new Coordinate(1, 2), new Player("X"))).isTrue();
        assertThat(game.spaceIsMarkedBy(new Coordinate(2, 3), new Player("O"))).isTrue();
        assertThat(game.spaceIsMarkedBy(new Coordinate(1, 2), new Player("O"))).isFalse();
    }
}