package eu.boyo.games.duels;

import eu.boyo.games.duels.solo.SoloDuelsQueues;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockClickEvent implements Listener {

    @EventHandler
    public static void onPlayerClickBlock(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getType() != Material.OAK_WALL_SIGN) return;
        if (!block.getWorld().getName().equals("Lobby")) return;

        String text = ((Sign)block.getState()).getLine(1);
        StandardDuelsKit kit = null;
        for (StandardDuelsKit testKit : StandardDuelsKit.values()) {
            if (text.contains(testKit.getName())) {
                kit = testKit;
            }
        }
        if (kit == null) return;

        Player player = event.getPlayer();
        if (text.contains("1v1")) {
            SoloDuelsQueues.addPlayer(player, kit);
        }
        else if (text.contains("FFA")) {
            player.sendMessage("FFA duels not made yet");
        }
    }
}
