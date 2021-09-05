package com.vorwieger.tictactoetddkata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {
    @Test()
    public void shouldBeAbleToPlaceAMark() {
        Player playerX = PlayerFactory.createPlayerWithMark("X") ;
        Player playerO = PlayerFactory.createPlayerWithMark("O");
        Game game = new Game(createRandomGameId(), List.of(playerX.id(), playerO.id()));

        game.placeMark(new Coordinate(1, 2), playerX.id());
        game.placeMark(new Coordinate(2, 3), playerO.id());
        assertThat(game.spaceIsMarkedBy(new Coordinate(1, 2), playerX)).isTrue();
        assertThat(game.spaceIsMarkedBy(new Coordinate(2, 3), playerO)).isTrue();
        assertThat(game.spaceIsMarkedBy(new Coordinate(1, 2), playerO)).isFalse();
    }

    private GameId createRandomGameId() {
        return new GameId("random");
    }

    @Test()
    public void aPlayerCannotPlaceAMarkTwiceInARowEachPlayerTakesTurns() {
        Player player = PlayerFactory.createPlayerWithMark("X");
        Game game = new Game(createRandomGameId(), List.of(player.id()));

        assertThatThrownBy(() -> {
            game.placeMark(new Coordinate(1, 2), player.id());
            game.placeMark(new Coordinate(2, 3), player.id());
        }).isInstanceOf(CouldNotPlaceMark.class);
    }

    @Test()
    public void gameEndsWhenAVerticalRowIsFullOfOnePlayersMarks() {
        Player firstPlayer = PlayerFactory.createPlayerWithMark("X");
        Player secondPlayer = PlayerFactory.createPlayerWithMark("O");
        Game game = new Game(createRandomGameId(), List.of(firstPlayer.id(), secondPlayer.id()));

        /**
         * Grid:
         * x o .
         * x o .
         * x . .
         * ^
         * 3 Marks Match
         */
        game.placeMark(new Coordinate(1,1), firstPlayer.id());
        game.placeMark(new Coordinate(2,2), secondPlayer.id());
        game.placeMark(new Coordinate(1,2), firstPlayer.id());
        game.placeMark(new Coordinate(2,3), secondPlayer.id());
        game.placeMark(new Coordinate(1,3), firstPlayer.id());


        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get()).isEqualTo(firstPlayer.id());
    }

    @Test()
    public void gameEndsWhenAHorizontalRowIsFullOfOnePlayersMarks() {
        Player firstPlayer = PlayerFactory.createPlayerWithMark("X");
        Player secondPlayer = PlayerFactory.createPlayerWithMark("Y");
        Game game = new Game(createRandomGameId(), List.of(firstPlayer.id(), secondPlayer.id()));

        /**
         * Grid:
         * . o .
         * . o .
         * x x x <-- 3 Marks Match
         */
        game.placeMark(new Coordinate(1,1), firstPlayer.id());
        game.placeMark(new Coordinate(2,2), secondPlayer.id());
        game.placeMark(new Coordinate(2,1), firstPlayer.id());
        game.placeMark(new Coordinate(2,3), secondPlayer.id());
        game.placeMark(new Coordinate(3,1), firstPlayer.id());


        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get()).isEqualTo(firstPlayer.id());
    }

    @Test()
    public void gameEndsWhenADiagonalRowIsFullOfOnePlayersMarks() {
        Player firstPlayer = PlayerFactory.createPlayerWithMark("X");
        Player secondPlayer = PlayerFactory.createPlayerWithMark("Y");
        Game game = new Game(createRandomGameId(), List.of(firstPlayer.id(), secondPlayer.id()));

        /**
         * Grid:
         * x o .
         * . x .
         * . o x <-- 3 Marks Match
         */
        game.placeMark(new Coordinate(1,3), firstPlayer.id());
        game.placeMark(new Coordinate(2,3), secondPlayer.id());
        game.placeMark(new Coordinate(2,2), firstPlayer.id());
        game.placeMark(new Coordinate(2,1), secondPlayer.id());
        game.placeMark(new Coordinate(3,1), firstPlayer.id());


        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get()).isEqualTo(firstPlayer.id());
    }

    @Test()
    public void cannotPlaceAnotherMarkWhenTheGameEnded() {
        Player firstPlayer = PlayerFactory.createPlayerWithMark("X");
        Player secondPlayer = PlayerFactory.createPlayerWithMark("Y");
        Game game = new Game(createRandomGameId(), List.of(firstPlayer.id(), secondPlayer.id()));

        // game that is in a state where the first player would have won
        game.placeMark(new Coordinate(1,1), firstPlayer.id());
        game.placeMark(new Coordinate(2,2), secondPlayer.id());
        game.placeMark(new Coordinate(1,2), firstPlayer.id());
        game.placeMark(new Coordinate(2,3), secondPlayer.id());
        game.placeMark(new Coordinate(1,3), firstPlayer.id());

        // now try to make another move as the second player
        assertThatThrownBy(() -> {
            game.placeMark(new Coordinate(2,2), secondPlayer.id());
        }).isInstanceOf(CouldNotPlaceMark.class);

        assertThat(game.winner().isPresent()).isTrue();
        assertThat(game.winner().get()).isEqualTo(firstPlayer.id());
    }
}