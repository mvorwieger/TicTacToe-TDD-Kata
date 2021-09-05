package com.vorwieger.tictactoetddkata;

public interface ActiveGames {
    void save(Game game);
    Game getById(GameId gameId);
}
