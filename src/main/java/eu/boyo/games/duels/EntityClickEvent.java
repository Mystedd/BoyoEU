package eu.boyo.games.duels;

import eu.boyo.games.duels.solo.SoloDuelsQueues;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class EntityClickEvent implements Listener {

    @EventHandler
    public static void onPlayerClickEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (entity.getType() != EntityType.VINDICATOR) return;
        if (!entity.getWorld().getName().equals("Lobby")) return;

        Player player = event.getPlayer();
        String name = entity.getName();
        if (name.contains("Random Duels")) {
            SoloDuelsQueues.addPlayer(player, DuelsKit.RANDOM);
        }
    }
}
