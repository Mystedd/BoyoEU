package eu.boyo.games;

import java.util.ArrayList;

public class ActiveGames {

    private static ArrayList<Game> games = new ArrayList<Game>();

    private ActiveGames() {}

    public static void addGame(Game newGame) {
        games.add(newGame);
    }

    public static void killGame(Game oldGame) {
        games.remove(oldGame);
    }
}