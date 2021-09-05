package com.vorwieger.tictactoetddkata;

public class GameInteractionService {
    private final ActiveGames activeGames;

    public GameInteractionService(ActiveGames activeGames) {
        this.activeGames = activeGames;
    }

    public void placeMarkAtCoordinateForPlayer(GameId gameId, PlayerId playerId, Coordinate aCoordinate) {
        activeGames.getById(gameId).placeMark(aCoordinate, playerId);
    }

    public GridView seeGridOfGame(GameId gameId) {
        return activeGames.getById(gameId).viewGrid();
    }
}
