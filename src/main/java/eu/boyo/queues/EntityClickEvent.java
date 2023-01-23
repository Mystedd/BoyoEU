package eu.boyo.queues;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EntityClickEvent implements Listener {

    @EventHandler
    public static void onPlayerClickEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        String queueName = entity.getCustomName();
        Player player = event.getPlayer();

        // get queue if it exists, or null
        Queue queue = Queues.getQueue(queueName);
        if (queue == null) {
            return;
        }

        // check if the player is already in a queue
        Queue currentQueue = Queues.getPlayerQueue(player);
        if (currentQueue != null) {
            player.sendMessage("§cYou are already queued for " + currentQueue.getName());
            return;
        }

        //add player to queue
        queue.addPlayer(player);

        player.sendMessage("§aSuccessfully joined queue for " + queueName);
    }
}
