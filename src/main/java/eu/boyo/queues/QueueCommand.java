package eu.boyo.queues;

import eu.boyo.games.duels.solo.SoloDuelsQueues;
import eu.boyo.games.duels.DuelsKit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class QueueCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        // code for querying queue
        if (args.length == 0) {
            Queue currentQueue = Queues.getPlayerQueue(player);
            if (currentQueue == null) {
                player.sendMessage("§cYou are not currently in a queue");
            }
            else {
                player.sendMessage("§3You are currently queued for §9" + currentQueue.getName());
            }
            return true;
        }
        String queueName = args[0];

        // code for leaving queue
        if (queueName.equalsIgnoreCase("leave")) {
            Queue oldQueue = Queues.removePlayer(player);
            if (oldQueue == null) {
                player.sendMessage("§cYou are not currently in a queue");
            }
            else {
                player.sendMessage("§dLeft queue for §5" + oldQueue.getName());
            }
            return true;
        }

        // check if the player is already in a queue
        Queue currentQueue = Queues.getPlayerQueue(player);
        if (currentQueue != null) {
            player.sendMessage("§cYou are already queued for §4" + currentQueue.getName());
            return true;
        }
        HashSet<DuelsKit> duelsQueues = SoloDuelsQueues.getPlayerQueues(player);
        if (!duelsQueues.isEmpty()) {
            player.sendMessage("§cYou are already queued for §4duels");
            return true;
        }

        // get queue if it exists, or null
        Queue queue = Queues.getQueue(queueName);
        if (queue == null) {
            player.sendMessage("§cThat game does not exist");
            return true;
        }
        //add player to queue
        queue.addPlayer(player);

        player.sendMessage("§aJoined queue for §2" + queue.getName());
        return true;
    }

}


