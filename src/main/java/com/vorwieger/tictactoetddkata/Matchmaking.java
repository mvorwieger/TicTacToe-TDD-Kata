package com.vorwieger.tictactoetddkata;

import java.util.List;
import java.util.Random;

/**
 * Responsible for creating and finding Games.
 */
public class Matchmaking {
    private final ActiveGames games;

    public Matchmaking(ActiveGames games) {
        this.games = games;
    }

    public GameId createGame(PlayerId playerOne, PlayerId playerTwo) {
        GameId id = generateGameId();

        this.games.save(
                new Game(id, List.of(playerOne, playerTwo))
        );

        return id;
    }

    private GameId generateGameId() {
        Random random = new Random();
        int i = random.nextInt();
        return new GameId(String.valueOf(i));
    }

    public Game getGame(GameId id) {
        return this.games.getById(id);
    }
}
