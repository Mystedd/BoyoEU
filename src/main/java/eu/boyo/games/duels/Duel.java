package eu.boyo.games.duels;

import eu.boyo.games.Game;
import org.bukkit.entity.Player;

public class Duel extends Game {

    public Duel(Player player1, Player player2, DuelsKit kit) {
        player1.sendMessage("starting duel with "+player2.getName());
        player2.sendMessage("starting duel with "+player1.getName());
        kit.giveItems(player1, player2);
    }
}
