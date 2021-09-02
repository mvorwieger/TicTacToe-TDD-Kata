package com.vorwieger.tictactoetddkata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test()
    public void aPlayerCannotPlaceAMarkTwiceInARowEachPlayerTakesTurns() {
        Game game = new Game();
        Player player = new Player("X");

        assertThatThrownBy(() -> {
            game.placeMark(new Coordinate(1, 2), player);
            game.placeMark(new Coordinate(2, 3), player);
        }).isInstanceOf(CouldNotPlaceMark.class);
    }
}