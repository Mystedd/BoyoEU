package eu.boyo.games.duels;

import eu.boyo.games.ActiveGames;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class DuelsQueues {

    static HashMap<DuelsKit, Player> queues = new HashMap<>();

    static {
        for (DuelsKit kit : DuelsKit.values()) {
            queues.put(kit, null);
        }
    }

    public static void addPlayer(Player player, DuelsKit kit) {
        Player existingPlayer = queues.get(kit);
        if (existingPlayer == player) {
            removePlayer(player, kit);
            player.sendMessage("§dLeft queue for §5" + kit.getDisplayName());
        }
        else {
            if (existingPlayer == null) {
                queues.replace(kit, player);
            } else {
                removePlayer(existingPlayer);
                removePlayer(player);
                Duel newDuel = new Duel(existingPlayer, player);
                ActiveGames.addGame(newDuel);
            }
            player.sendMessage("§aJoined queue for §2" + kit.getDisplayName());
        }

        HashSet<DuelsKit> otherQueues = getPlayerQueues(player);
        if (!otherQueues.isEmpty()) {
            player.sendMessage("§3You are currently queued for:");
            for (DuelsKit queuedKit : getPlayerQueues(player)) {
                player.sendMessage("§9" + queuedKit.getDisplayName());
            }
        }
    }

    public static void removePlayer(Player player) {
        for (DuelsKit kit : queues.keySet()) {
            if (queues.get(kit) == player) {
                queues.replace(kit, null);
            }
        }
    }

    public static void removePlayer(Player player, DuelsKit kit) {
        if (queues.get(kit) == player) {
            queues.replace(kit, null);
        }
    }

    public static HashSet<DuelsKit> getPlayerQueues(Player player) {
        HashSet<DuelsKit> kits = new HashSet<>();
        for (DuelsKit kit : queues.keySet()) {
            if (queues.get(kit) == player) {
                kits.add(kit);
            }
        }
        return kits;
    }
}
