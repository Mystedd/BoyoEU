package eu.boyo.games.duels.solo;

import eu.boyo.games.ActiveGames;
import eu.boyo.games.duels.DuelsKit;
import eu.boyo.games.duels.StandardDuelsKit;
import eu.boyo.games.duels.solo.random.RandomDuelsKit;
import eu.boyo.queues.Queue;
import eu.boyo.queues.Queues;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class SoloDuelsQueues {

    static HashMap<DuelsKit, Player> queues = new HashMap<>();

    static {
        for (StandardDuelsKit kit : StandardDuelsKit.values()) {
            queues.put(kit, null);
        }
        for (RandomDuelsKit kit : RandomDuelsKit.values()) {
            queues.put(kit, null);
        }
    }

    public static void addPlayer(Player player, DuelsKit kit) {
        // check if the player is queued for any other games
        Queue gameQueue = Queues.getPlayerQueue(player);
        if (gameQueue != null) {
            player.sendMessage("§cYou are already queued for " + gameQueue.getName());
            return;
        }

        Player existingPlayer = queues.get(kit);
        // leave queue if already in it
        if (existingPlayer == player) {
            removePlayer(player, kit);
            player.sendMessage("§dLeft queue for §5" + kit.getDisplayName());
        }
        // join queue
        else {
            player.sendMessage("§aJoined queue for §2" + kit.getDisplayName());
            SoloDuel newDuel = new SoloDuel(player, player, kit);
            ActiveGames.addGame(newDuel);
            if (true) return;
            if (existingPlayer == null) {
                queues.replace(kit, player);
            } else {
                removePlayer(existingPlayer);
                removePlayer(player);
                // SoloDuel newDuel = new SoloDuel(existingPlayer, player, kit);
                ActiveGames.addGame(newDuel);
            }
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
