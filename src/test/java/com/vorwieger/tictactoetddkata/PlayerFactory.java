package com.vorwieger.tictactoetddkata;

import com.vorwieger.tictactoetddkata.Player;
import com.vorwieger.tictactoetddkata.PlayerId;

import java.util.Random;

public class PlayerFactory {
    public static PlayerId createRandomPlayerId() {
        Random random = new Random();
        int i = random.nextInt();
        return new PlayerId(String.valueOf(i));
    }

    public static Player createPlayerWithMark(String mark) {
       return new Player(createRandomPlayerId(), mark);
    }
}
