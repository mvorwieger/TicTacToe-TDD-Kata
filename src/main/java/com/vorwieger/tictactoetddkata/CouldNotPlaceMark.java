package com.vorwieger.tictactoetddkata;

public class CouldNotPlaceMark extends RuntimeException {
    private CouldNotPlaceMark(String message) {
       super(message);
    }

    public static CouldNotPlaceMark becauseYouHaveAlreadyTakenYourTurn(Player player) {
        return new CouldNotPlaceMark(String.format("Could not place Mark(%s) because you have already taken your turn", player.mark()));
    }

    public static CouldNotPlaceMark becauseTheGameIsAlreadyFinished(Player player) {
        return new CouldNotPlaceMark(String.format("Could not place Mark(%s) because the game is already finished", player.mark()));
    }
}
