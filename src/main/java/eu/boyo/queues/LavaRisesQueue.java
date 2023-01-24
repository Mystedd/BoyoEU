package eu.boyo.queues;

import eu.boyo.games.ActiveGames;
import eu.boyo.games.Game;
import eu.boyo.games.lavarises.LavaRisesGame;

public class LavaRisesQueue extends Queue {

    public LavaRisesQueue(String name) {
        super(name, 5);
    }

    public void queueFull() {
        Game newGame = new LavaRisesGame(players);
        ActiveGames.addGame(newGame);
        resetQueue();
    }
}
