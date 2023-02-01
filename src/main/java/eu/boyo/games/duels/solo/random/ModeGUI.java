package eu.boyo.games.duels.solo.random;

import eu.boyo.BoyoEU;
import eu.boyo.games.BuildTools;
import eu.boyo.games.duels.StandardDuelsKit;
import eu.boyo.games.duels.solo.SoloDuelsQueues;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class ModeGUI implements InventoryHolder {

    public static class ModeGUIListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            if (event.getClickedInventory() == null) return;
            if (!(event.getClickedInventory().getHolder() instanceof ModeGUI)) return;
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == effectButtonSlot) {
                player.sendMessage("Random effect");
                player.closeInventory();
            }
            else if (event.getSlot() == ultimateButtonSlot) {
                player.sendMessage("Ultimate random");
                player.closeInventory();
            }
            else if (event.getSlot() == itemButtonSlot) {
                player.sendMessage("Random item");
                player.closeInventory();
                SoloDuelsQueues.addPlayer(player, RandomDuelsKit.RANDOM_ITEM);
            }
        }
    }

    private static final byte effectButtonSlot = 11;
    private static final byte ultimateButtonSlot = 13;
    private static final byte itemButtonSlot = 15;
    private final Inventory inventory;

    static {
        getServer().getPluginManager().registerEvents(new ModeGUIListener(), BoyoEU.plugin);
    }

    public ModeGUI() {
        inventory = Bukkit.createInventory(this, 27, "§5§lRandom Duels");
        ItemStack pane = BuildTools.createItem(Material.MAGENTA_STAINED_GLASS_PANE, " ");
        ItemStack effect = BuildTools.createItem(Material.POTION, "§3§rRandom Effect Duels", "§b1v1 duel with random status effects and a basic kit", true);
        ItemStack ultimate = BuildTools.createItem(Material.SUSPICIOUS_STEW, "§6§rUltimate Random Duels", "§e1v1 duel with random items and effects", true);
        ItemStack item = BuildTools.createItem(Material.LARGE_AMETHYST_BUD, "§2§rRandom Item Duels", "§a1v1 duel with a selection of randomised items", true);
        for (byte slot=0; slot<27; slot++) {
            inventory.setItem(slot, pane);
        }
        inventory.setItem(effectButtonSlot, effect);
        inventory.setItem(ultimateButtonSlot, ultimate);
        inventory.setItem(itemButtonSlot, item);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
