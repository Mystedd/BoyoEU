package eu.boyo.games.duels.solo;

import eu.boyo.games.Game;
import eu.boyo.games.duels.DuelsKit;
import eu.boyo.games.duels.StandardDuelsKit;
import eu.boyo.games.duels.settings.AllPlayerSettings;
import org.bukkit.entity.Player;

public class SoloDuel extends Game {

    DuelsKit kit;

    public SoloDuel(Player player1, Player player2, DuelsKit k) {
        kit = k;
        if (kit instanceof StandardDuelsKit) {
            AllPlayerSettings.getPreferredKit(player1, (StandardDuelsKit) kit).giveItems(player1);
            AllPlayerSettings.getPreferredKit(player2, (StandardDuelsKit) kit).giveItems(player2);
        }
        else {
            kit.giveItems(player1, player2);
        }
    }
}
