package eu.boyo.games.duels.solo;

import eu.boyo.games.Game;
import eu.boyo.games.duels.DuelsKit;
import eu.boyo.games.duels.StandardDuelsKit;
import org.bukkit.entity.Player;

public class SoloDuel extends Game {

    public SoloDuel(Player player1, Player player2, DuelsKit kit) {
        player1.sendMessage("starting duel with "+player2.getName());
        player2.sendMessage("starting duel with "+player1.getName());
        kit.giveItems(player1, player2);
    }
}
