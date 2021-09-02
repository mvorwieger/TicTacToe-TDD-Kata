package com.vorwieger.tictactoetddkata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test()
    public void shouldBeAbleToPlaceAMark() {
        Game game = new Game();
        game.placeMark(1, 2, "X");
        game.placeMark(2, 3, "O");
        assertThat(game.spaceIsMarkedBy(1, 2, "X")).isTrue();
        assertThat(game.spaceIsMarkedBy(2, 3, "O")).isTrue();
        assertThat(game.spaceIsMarkedBy(1, 2, "O")).isFalse();
    }
}