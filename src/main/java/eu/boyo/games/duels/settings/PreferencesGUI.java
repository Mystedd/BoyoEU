package eu.boyo.games.duels.settings;

import eu.boyo.BoyoEU;
import eu.boyo.games.BuildTools;
import eu.boyo.games.duels.DuelsKit;
import eu.boyo.games.duels.StandardDuelsKit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class PreferencesGUI implements InventoryHolder {

    public class PreferencesGUIListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof PreferencesGUI)) return;
            if (event.getCurrentItem() == null) return;
            Player player = (Player) event.getWhoClicked();
            PlayerInventory playerInv = player.getInventory();

            if (blockedSlots.contains(event.getSlot())) {
                event.setCancelled(true);
            }
            // reset button
            else if (event.getSlot() == resetSlot) {
                event.setCancelled(true);
                // check that the player has not clicked with an item
                if (player.getItemOnCursor().getType() != Material.AIR) return;
                
                AllPlayerSettings.resetPreferredKit(player, kit);
                player.sendMessage("§4Reset locations for all items");
                player.closeInventory();
            }
            // save button
            else if (event.getSlot() == saveSlot) {
                event.setCancelled(true);
                // check that the player has not clicked with an item
                if (player.getItemOnCursor().getType() != Material.AIR) return;

                // get HashMap from new layout
                HashMap<Integer, ItemStack> items = new HashMap<>();
                for (int slot=0; slot <= 35; slot++) {
                    items.put(slot, playerInv.getItem(slot));
                }
                items.put(104, inv.getItem(offHandSlot));
                for (int armorSlot=100; armorSlot <= 103; armorSlot++) {
                    items.put(armorSlot, kit.getItems().get(armorSlot));
                }
                PreferredDuelsKit newKit = new PreferredDuelsKit(kit, items);
                AllPlayerSettings.setPreferredKit(player, kit, newKit);
                player.sendMessage("§2Saved kit");
                player.closeInventory();
            }
        }

        @EventHandler
        public void onClose(InventoryCloseEvent event) {
            Inventory inv = event.getInventory();
            if (!(inv.getHolder() instanceof PreferencesGUI)) return;
            Player player = (Player) event.getPlayer();
            player.getInventory().clear();
            HandlerList.unregisterAll(this);
        }

        @EventHandler
        public void onDrop(PlayerDropItemEvent event) {
            if (!(event.getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof PreferencesGUI)) return;
            event.setCancelled(true);
        }
    }

    private final Inventory inventory;
    private final StandardDuelsKit kit;
    public static final ArrayList<Integer> blockedSlots = new ArrayList<>();
    public static final int saveSlot = 0;
    public static final int resetSlot = 8;
    public static final int offHandSlot = 4;

    static {
        blockedSlots.add(1);
        blockedSlots.add(2);
        blockedSlots.add(3);
        blockedSlots.add(5);
        blockedSlots.add(6);
        blockedSlots.add(7);
    }

    public PreferencesGUI(StandardDuelsKit duelsKit, Player player) {
        getServer().getPluginManager().registerEvents(new PreferencesGUIListener(), BoyoEU.plugin);
        player.getInventory().clear();
        kit = duelsKit;

        inventory = Bukkit.createInventory(this, 9, duelsKit.getDisplayName() + " kit");
        // add blocker items
        ItemStack pane = BuildTools.createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        for (int slot : blockedSlots) {
            inventory.setItem(slot, pane);
        }
        ItemStack reset = BuildTools.createItem(Material.BARRIER, "§4§lReset Kit");
        inventory.setItem(resetSlot, reset);
        ItemStack save = BuildTools.createItem(Material.ENDER_CHEST, "§2§lSave Kit");
        inventory.setItem(saveSlot, save);
        // add default kit items
        DuelsKit preferredKit = AllPlayerSettings.getPreferredKit(player, duelsKit);
        preferredKit.giveInventoryItems(player);
        inventory.setItem(offHandSlot, preferredKit.getItems().get(104));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
