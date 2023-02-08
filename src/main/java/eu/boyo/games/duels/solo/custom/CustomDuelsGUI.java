package eu.boyo.games.duels.solo.custom;

import eu.boyo.BoyoEU;
import eu.boyo.games.BuildTools;
import eu.boyo.games.duels.settings.AllPlayerSettings;
import eu.boyo.games.duels.solo.custom.editor.EditorGUI;
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
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

public class CustomDuelsGUI implements InventoryHolder {

    public class CustomDuelsGUIListener implements Listener {
        @EventHandler
        public void onClickInventory(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof CustomDuelsGUI)) return;
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            PlayerInventory playerInv = player.getInventory();
        }

        @EventHandler
        public void onClickPlayerInventory(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            Player player = (Player) event.getWhoClicked();
            if (inv != player.getInventory()) return;
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);

            int slot = event.getSlot();
            int kitNum;
            if (slot == 20) kitNum = 0;
            else if (slot == 22) kitNum = 1;
            else if (slot == 24) kitNum = 2;
            else return;
            CustomDuelsKit kit = AllPlayerSettings.getCustomKit(player, kitNum);
            if (kit == null) EditorGUI.startEditor(player);
            else EditorGUI.startEditor(player, kit);
        }

        @EventHandler
        public void onClose(InventoryCloseEvent event) {
            Inventory inv = event.getInventory();
            if (!(inv.getHolder() instanceof CustomDuelsGUI)) return;
            Player player = (Player) event.getPlayer();
            player.sendMessage("Closed CustomDuelsGUI");
            player.getInventory().clear();
            HandlerList.unregisterAll(this);
        }
    }

    private final Inventory inventory;

    public CustomDuelsGUI(Player player) {
        getServer().getPluginManager().registerEvents(new CustomDuelsGUIListener(), BoyoEU.plugin);
        inventory = Bukkit.createInventory(this, 54, "Custom Duels");

        // list available duels
        for (byte slot=0; slot<=53; slot++) {
            ItemStack logo;
            if (slot >= ListedCustomDuels.getSize()) {
                logo = BuildTools.createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
            }
            else {
                CustomDuelsKit kit = ListedCustomDuels.getKit(slot);
                logo = BuildTools.createItem(kit.getLogo(), kit.getName());
            }
            inventory.setItem(slot, logo);
        }
    }

    public void open(Player player) {
        PlayerInventory playerInv = player.getInventory();
        playerInv.clear();
        player.sendMessage("Opened CustomDuelsGUI");
        // duel editor menu
        ItemStack pane = BuildTools.createItem(Material.ORANGE_STAINED_GLASS_PANE, " ");
        for (byte slot=9; slot<=35; slot++) {
            playerInv.setItem(slot, pane);
        }
        for (byte invSlot = 20, kitNum = 0; kitNum<=2; invSlot +=2, kitNum++) {
            CustomDuelsKit kit = AllPlayerSettings.getCustomKit(player, kitNum);
            ItemStack logo;
            if (kit == null) {
                logo = BuildTools.createItem(Material.FIREWORK_STAR, "Create Kit");
            }
            else {
                logo = BuildTools.createItem(kit.getLogo(), kit.getName());
            }
            playerInv.setItem(invSlot, logo);
        }
        player.openInventory(getInventory());
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
