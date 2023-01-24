package eu.boyo.queues;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage("§cYou must be an operator to use that command");
            return true;
        }

        Queue queue = Queues.getPlayerQueue(player);
        if (queue == null) {
            player.sendMessage("§cYou are not currently in a queue");
            return true;
        }

        queue.queueFull();
        return true;
    }
}
