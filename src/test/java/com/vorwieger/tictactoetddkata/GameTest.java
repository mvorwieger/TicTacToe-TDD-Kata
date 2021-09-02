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

    @Test()
    public void gameEndsWhenAVerticalRowIsFullOfOnePlayersMarks() {
        Game game = new Game();
        Player firstPlayer = new Player("X");
        Player secondPlayer = new Player("Y");

        /**
         * Grid:
         * x o .
         * x o .
         * x . .
         * ^
         * 3 Marks Match
         */
        game.placeMark(new Coordinate(1,1), firstPlayer);
        game.placeMark(new Coordinate(2,2), secondPlayer);
        game.placeMark(new Coordinate(1,2), firstPlayer);
        game.placeMark(new Coordinate(2,3), secondPlayer);
        game.placeMark(new Coordinate(1,3), firstPlayer);


        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get().mark()).isEqualTo(firstPlayer.mark());
    }

    @Test()
    public void gameEndsWhenAHorizontalRowIsFullOfOnePlayersMarks() {
        Game game = new Game();
        Player firstPlayer = new Player("X");
        Player secondPlayer = new Player("Y");

        /**
         * Grid:
         * . o .
         * . o .
         * x x x <-- 3 Marks Match
         */
        game.placeMark(new Coordinate(1,1), firstPlayer);
        game.placeMark(new Coordinate(2,2), secondPlayer);
        game.placeMark(new Coordinate(2,1), firstPlayer);
        game.placeMark(new Coordinate(2,3), secondPlayer);
        game.placeMark(new Coordinate(3,1), firstPlayer);


        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get().mark()).isEqualTo(firstPlayer.mark());
    }

    @Test()
    public void cannotPlaceAnotherMarkWhenTheGameEnded() {
        Game game = new Game();
        Player firstPlayer = new Player("X");
        Player secondPlayer = new Player("Y");

        // game that is in a state where the first player would have won
        game.placeMark(new Coordinate(1,1), firstPlayer);
        game.placeMark(new Coordinate(2,2), secondPlayer);
        game.placeMark(new Coordinate(1,2), firstPlayer);
        game.placeMark(new Coordinate(2,3), secondPlayer);
        game.placeMark(new Coordinate(1,3), firstPlayer);

        // now try to make another move as the second player
        assertThatThrownBy(() -> {
            game.placeMark(new Coordinate(2,2), secondPlayer);
        }).isInstanceOf(CouldNotPlaceMark.class);

        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get().mark()).isEqualTo(firstPlayer.mark());
    }
}