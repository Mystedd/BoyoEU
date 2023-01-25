package eu.boyo.games.tag;

import eu.boyo.games.ActiveGames;
import eu.boyo.games.Game;
import eu.boyo.queues.Queue;

public class TagQueue extends Queue {

    public TagQueue(String name) {
        super(name, 5);
    }

    public void queueFull() {
        Game newGame = new TagGame(players);
        ActiveGames.addGame(newGame);
        resetQueue();
    }

}
