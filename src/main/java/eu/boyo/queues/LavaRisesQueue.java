package eu.boyo.queues;

import com.hbop.boyominigames.games.ActiveGames;
import com.hbop.boyominigames.games.Game;
import com.hbop.boyominigames.games.lavarises.LavaRisesGame;

public class LavaRisesQueue extends Queue {

    public LavaRisesQueue(String name) {
        super(name, 1);
    }

    public void queueFull() {
        Game newGame = new LavaRisesGame(players);
        ActiveGames.addGame(newGame);
    }
}
