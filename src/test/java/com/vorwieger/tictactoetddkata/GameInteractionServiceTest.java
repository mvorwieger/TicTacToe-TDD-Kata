package com.vorwieger.tictactoetddkata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameInteractionServiceTest {
    Matchmaking matchmaking;
    GameInteractionService gameInteractionService;
    ActiveGames activeGames;

    @BeforeEach
    public void setup() {
        this.activeGames = new InMemoryActiveGames();
        this.matchmaking = new Matchmaking(this.activeGames);
        this.gameInteractionService = new GameInteractionService(this.activeGames);
    }

    @Test
    public void canCreateAGameAndPlaceMarks() {
        Player playerToPlaceAMark = PlayerFactory.createPlayerWithMark("X");
        Player playerO = PlayerFactory.createPlayerWithMark("O");

        Coordinate aCoordinate = new Coordinate(1, 1);

        GameId gameId = matchmaking.createGame(playerToPlaceAMark.id(), playerO.id());
        gameInteractionService.placeMarkAtCoordinateForPlayer(
                gameId,
                playerToPlaceAMark.id(),
                aCoordinate
        );

        GridView grid = gameInteractionService.seeGridOfGame(gameId);
        PlayerId[][] twoDimensionalGrid = grid.to2dArray();

        // the Coordinate x: 1, y: 1 translates index [0][0] in a 2d Array
        System.out.println(grid);
        Assertions.assertThat(twoDimensionalGrid[0][0]).isEqualTo(playerToPlaceAMark.id());
    }
}