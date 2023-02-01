package eu.boyo.games.duels.preferences;

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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class PreferencesGUI implements InventoryHolder {

    public class PreferencesGUIListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            if (event.getClickedInventory() == null) return;
            if (!(event.getClickedInventory().getHolder() instanceof PreferencesGUI)) return;
            if (event.getCurrentItem() == null) return;
            Player player = (Player) event.getWhoClicked();

            if (blockedSlots.contains(event.getSlot())) {
                event.setCancelled(true);
            }
            else if (event.getSlot() == resetSlot) {
                event.setCancelled(true);
                AllPlayerPreferences.resetPreferredKit(player, kit);
                reset = true;
                player.sendMessage("§4Reset locations for all items");
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

            if (reset) return; // stop if player has clicked the reset button

            // check that all items are present
            boolean valid = true;
            for (ItemStack item : kit.getItems().values()) {
                if (!inv.contains(item.getType())) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                player.sendMessage("§cSome items are missing from that kit");
                return;
            }

            // get HashMap from new layout
            HashMap<Integer, ItemStack> items = new HashMap<>();
            for (byte hotbarSlot=27; hotbarSlot<=35; hotbarSlot++) {
                ItemStack item = inventory.getItem(hotbarSlot);
                items.put(hotbarSlot-27, item);
            }
            for (byte invSlot=0; invSlot<=26; invSlot++) {
                ItemStack item = inventory.getItem(invSlot);
                items.put(invSlot+9, item);
            }
            items.put(103, inventory.getItem(37));
            items.put(102, inventory.getItem(38));
            items.put(101, inventory.getItem(39));
            items.put(100, inventory.getItem(40));
            items.put(104, inventory.getItem(42));
            PreferredDuelsKit newKit = new PreferredDuelsKit(kit, items);
            AllPlayerPreferences.setPreferredKit(player, kit, newKit);
        }
    }

    private final Inventory inventory;
    private final StandardDuelsKit kit;
    private boolean reset = false;
    public static final ArrayList<Integer> blockedSlots = new ArrayList<>();
    public static final int resetSlot = 44;

    static {
        blockedSlots.add(36);
        blockedSlots.add(41);
        blockedSlots.add(43);
    }

    public PreferencesGUI(StandardDuelsKit duelsKit, Player player) {
        getServer().getPluginManager().registerEvents(new PreferencesGUIListener(), BoyoEU.plugin);
        kit = duelsKit;

        inventory = Bukkit.createInventory(this, 45, duelsKit.getDisplayName() + " kit");
        // add blocker items
        ItemStack pane = BuildTools.createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        for (int slot : blockedSlots) {
            inventory.setItem(slot, pane);
        }
        ItemStack reset = BuildTools.createItem(Material.BARRIER, "§4§lReset Kit");
        inventory.setItem(resetSlot, reset);
        // add default kit items
        DuelsKit kit = AllPlayerPreferences.getPreferredKit(player, duelsKit);
        HashMap<Integer, ItemStack> kitItems;
        if (kit == null) {
            kitItems = duelsKit.getItems();
        }
        else {
            kitItems = kit.getItems();
        }
        for (int slot : kitItems.keySet()) {
            ItemStack item = kitItems.get(slot);
            int placeSlot = slot;
            if (slot <= 8) placeSlot += 27;
            else if (slot < 100) placeSlot -= 9;
            else if (slot == 103) placeSlot = 37;
            else if (slot == 102) placeSlot = 38;
            else if (slot == 101) placeSlot = 39;
            else if (slot == 100) placeSlot = 40;
            else if (slot == 104) placeSlot = 42;
            inventory.setItem(placeSlot, item);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
