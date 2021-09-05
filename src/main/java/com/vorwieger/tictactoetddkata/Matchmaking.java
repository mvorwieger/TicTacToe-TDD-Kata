package com.vorwieger.tictactoetddkata;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Matchmaking {
    private HashMap<GameId, Game> games;

    public Matchmaking() {
        this.games = new HashMap<GameId, Game>();
    }

    public GameId createGame(PlayerId playerOne, PlayerId playerTwo) {
        GameId id = generateGameId();

        this.games.put(
                id,
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
        return this.games.get(id);
    }
}
