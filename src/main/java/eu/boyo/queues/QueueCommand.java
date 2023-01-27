package eu.boyo.queues;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class QueueCommand implements CommandExecutor {

    public class QueueTabCompletion implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            List<String> options = new ArrayList<>();
            if (args.length == 1) {
                options.add("lavarises");
                options.add("tag");
            }
            return options;
        }

    }

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
                player.sendMessage("§9You are currently queued for " + currentQueue.getName());
            }
            return true;
        }
        String queueName = args[0];

        // code for leaving queue
        if (queueName.equalsIgnoreCase("leave")) {
            boolean success = Queues.removePlayer(player);
            if (success) {
                player.sendMessage("§aSuccessfully left queue");
            }
            else {
                player.sendMessage("§cYou are not currently in a queue");
            }
            return true;
        }

        // check if the player is already in a queue
        Queue currentQueue = Queues.getPlayerQueue(player);
        if (currentQueue != null) {
            player.sendMessage("§cYou are already queued for " + currentQueue.getName());
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

        player.sendMessage("§aSuccessfully joined queue for " + queueName);
        return true;
    }
}


