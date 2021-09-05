package com.vorwieger.tictactoetddkata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchmakingTest {
    private Matchmaking matchmaking;

    @BeforeEach
    public void setUp() {
        this.matchmaking = new Matchmaking(new InMemoryActiveGames());
    }

    @Test
    public void canCreateAGame() {
        PlayerId playerOne = PlayerFactory.createRandomPlayerId();
        PlayerId playerTwo = PlayerFactory.createRandomPlayerId();
        GameId id = matchmaking.createGame(playerOne, playerTwo);
        Game game = matchmaking.getGame(id);

        Assertions.assertThat(game.id()).isEqualTo(id);
        Assertions.assertThat(game.playerIds()).contains(playerOne, playerTwo);
    }

    @Test
    public void thereCanBeMultipleGamesAtOnce() {
        PlayerId playerOne = PlayerFactory.createRandomPlayerId();
        PlayerId playerTwo = PlayerFactory.createRandomPlayerId();

        PlayerId playerThree = PlayerFactory.createRandomPlayerId();
        PlayerId playerFour = PlayerFactory.createRandomPlayerId();

        GameId id = matchmaking.createGame(playerOne, playerTwo);
        GameId secondId = matchmaking.createGame(playerThree, playerFour);

        Game game = matchmaking.getGame(id);
        Game secondGame = matchmaking.getGame(secondId);

        Assertions.assertThat(game.id()).isEqualTo(id);
        Assertions.assertThat(game.playerIds()).contains(playerOne, playerTwo);

        Assertions.assertThat(secondGame.id()).isEqualTo(secondId);
        Assertions.assertThat(secondGame.playerIds()).contains(playerThree, playerFour);
    }
}