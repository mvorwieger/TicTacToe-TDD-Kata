package com.vorwieger.tictactoetddkata;

import java.util.HashMap;

public class InMemoryActiveGames implements ActiveGames {
    private final HashMap<GameId, Game> games = new HashMap<>();

    @Override
    public void save(Game game) {
        games.put(game.id(), game);
    }

    @Override
    public Game getById(GameId gameId) {
        return games.get(gameId);
    }
}
