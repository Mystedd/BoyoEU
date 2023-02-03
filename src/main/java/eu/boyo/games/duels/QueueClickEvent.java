package eu.boyo.games.duels;

import eu.boyo.games.duels.settings.PreferencesGUI;
import eu.boyo.games.duels.solo.SoloDuelsQueues;
import eu.boyo.games.duels.solo.custom.CustomDuelsGUI;
import eu.boyo.games.duels.solo.random.ModeGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class QueueClickEvent implements Listener {

    @EventHandler
    public static void onPlayerClickBlock(PlayerInteractEvent event) {
        // check that the sign is in the lobby
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getType() != Material.OAK_WALL_SIGN) return;
        if (!block.getWorld().getName().equals("Lobby")) return;

        // get the kit written on the sign
        String text = ((Sign)block.getState()).getLine(1);
        StandardDuelsKit kit = null;
        for (StandardDuelsKit testKit : StandardDuelsKit.values()) {
            if (text.contains(testKit.getName())) {
                kit = testKit;
            }
        }
        if (kit == null) return;

        Player player = event.getPlayer();
        if (player.isSneaking()) {
            PreferencesGUI gui = new PreferencesGUI(kit, player);
            player.openInventory(gui.getInventory());
        }
        else {
            if (text.contains("1v1")) {
                SoloDuelsQueues.addPlayer(player, kit);
            } else if (text.contains("FFA")) {
                player.sendMessage("FFA duels not made yet");
            }
        }
    }

    @EventHandler
    public static void onPlayerClickEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (entity.getType() != EntityType.VINDICATOR) return;
        if (!entity.getWorld().getName().equals("Lobby")) return;

        Player player = event.getPlayer();
        String name = entity.getName();
        if (name.contains("Random Duels")) {
            ModeGUI gui = new ModeGUI();
            player.openInventory(gui.getInventory());
        }
        else if (name.contains("Custom Duels")) {
            CustomDuelsGUI gui = new CustomDuelsGUI(player);
            player.openInventory(gui.getInventory());
        }
    }
}
