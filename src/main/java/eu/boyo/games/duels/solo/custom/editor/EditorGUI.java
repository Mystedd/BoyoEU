package eu.boyo.games.duels.solo.custom.editor;

import eu.boyo.BoyoEU;
import eu.boyo.ItemTools;
import eu.boyo.games.duels.settings.AllPlayerSettings;
import eu.boyo.games.duels.solo.custom.CustomDuelsKit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.Color;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class EditorGUI implements InventoryHolder {

    Inventory inventory;
    CustomDuelsKit kit;
    Listener activeListener;
    static ArrayList<Integer> blockedSlots = new ArrayList<>();
    static int backSlot = 45;
    static int binSlot = 53;
    static int offHandSlot = 51;
    static ArrayList<Integer> armorSlots = new ArrayList<>();
    static HashMap<Integer, ItemStack> placeHolders = new HashMap<>();

    static {
        blockedSlots.add(46);
        blockedSlots.add(52);
        armorSlots.add(47);
        armorSlots.add(48);
        armorSlots.add(49);
        armorSlots.add(50);
        placeHolders.put(47, ItemTools.createLeatherArmor(Material.LEATHER_HELMET, Color.GRAY, "Helmet", 1301, true));
        placeHolders.put(48, ItemTools.createLeatherArmor(Material.LEATHER_CHESTPLATE, Color.GRAY, "Chestplate", 1302, true));
        placeHolders.put(49, ItemTools.createLeatherArmor(Material.LEATHER_LEGGINGS, Color.GRAY, "Leggings", 1303, true));
        placeHolders.put(50, ItemTools.createLeatherArmor(Material.LEATHER_BOOTS, Color.GRAY, "Boots", 1304, true));
        placeHolders.put(51, ItemTools.createItem(Material.SHIELD, "Offhand", 1305));
    }

    private void clearMainSection() {
        ItemStack blocker = ItemTools.createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ");
        for (int slot=0; slot<=44; slot++) {
            inventory.setItem(slot, blocker);
        }
    }

    private void activateListener(Listener listener) {
        HandlerList.unregisterAll(activeListener);
        activeListener = listener;
        getServer().getPluginManager().registerEvents(activeListener, BoyoEU.plugin);
    }

    public class EditorGUIListener implements Listener {
        @EventHandler
        public void onClickBottomRow(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            if (event.getCurrentItem() == null) return;
            if (event.getSlot() < 45) return;
            Player player = (Player) event.getWhoClicked();

            if (blockedSlots.contains(event.getSlot())) {
                event.setCancelled(true);
            }
            else if (armorSlots.contains(event.getSlot()) || event.getSlot() == offHandSlot) {
                event.setCancelled(true);
                if (placeHolders.containsValue(event.getCurrentItem())) {
                    if (!player.getItemOnCursor().getType().isEmpty()) {
                        inventory.setItem(event.getSlot(), player.getItemOnCursor());
                        player.setItemOnCursor(new ItemStack(Material.AIR));
                    }
                }
                else {
                    if (player.getItemOnCursor().getType().isEmpty()) {
                        player.setItemOnCursor(inventory.getItem(event.getSlot()));
                        inventory.setItem(event.getSlot(), placeHolders.get(event.getSlot()));
                    }
                    else {
                        ItemStack newItem = player.getItemOnCursor();
                        player.setItemOnCursor(inventory.getItem(event.getSlot()));
                        inventory.setItem(event.getSlot(), newItem);
                    }
                }
            }
            // back button
            else if (event.getSlot() == backSlot) {
                event.setCancelled(true);
                openCategories();
            }
            // bin button
            else if (event.getSlot() == binSlot) {
                event.setCancelled(true);
                player.setItemOnCursor(new ItemStack(Material.AIR));
            }
        }

        @EventHandler
        public void onClose(InventoryCloseEvent event) {
            Inventory inv = event.getInventory();
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            HandlerList.unregisterAll(activeListener);
            HandlerList.unregisterAll(this);
            Player player = (Player) event.getPlayer();

            // save kit
            HashMap<Integer, ItemStack> items = new HashMap<>();
            for (int slot=0; slot <= 35; slot++) {
                items.put(slot, player.getInventory().getItem(slot));
            }
            // offhand and armor
            items.put(104, inv.getItem(offHandSlot));
            for (int slot=0, armorSlot=103; slot<=3; slot++, armorSlot--) {
                int invSlot = armorSlots.get(slot);
                items.put(armorSlot, inventory.getItem(invSlot));
            }
            kit.setItems(items);
            player.sendMessage("§2Saved kit");
            player.closeInventory();

            event.getPlayer().getInventory().clear();
            getServer().getScheduler().runTaskLater(BoyoEU.plugin, () -> event.getPlayer().getInventory().clear(), 1L);
        }

        @EventHandler
        public void onDrop(PlayerDropItemEvent event) {
            if (!(event.getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof EditorGUI)) return;
            event.setCancelled(true);
        }
    }

    EditorGUI(CustomDuelsKit k) {
        inventory = Bukkit.createInventory(this, 54, "Custom Duels Editor");
        kit = k;
        getServer().getPluginManager().registerEvents(new EditorGUIListener(), BoyoEU.plugin);

        // add control items
        ItemStack pane = ItemTools.createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        for (int slot : blockedSlots) {
            inventory.setItem(slot, pane);
        }
        ItemStack back = ItemTools.createItem(Material.ARROW, "§9§o<-- Back", 0);
        inventory.setItem(backSlot, back);
        ItemStack bin = ItemTools.createItem(Material.BARRIER, "§4§oDelete Item", 1300);
        inventory.setItem(binSlot, bin);

        // add offhand and armor items
        for (int slot : placeHolders.keySet()) {
            inventory.setItem(slot, placeHolders.get(slot));
        }
        ItemStack offHandItem = k.getItems().get(104);
        if (offHandItem != null && !offHandItem.getType().isEmpty()) inventory.setItem(offHandSlot, offHandItem);
        for (int slot=0, armorSlot=103; slot<=3; slot++, armorSlot--) {
            int invSlot = armorSlots.get(slot);
            ItemStack item = k.getItems().get(armorSlot);
            if (item == null || item.getType().isEmpty()) continue;
            inventory.setItem(invSlot, item);
        }

        openCategories();
    }

    public class CategoriesListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            if (event.getCurrentItem() == null) return;
            if (event.getSlot() > 44) return;
            event.setCancelled(true);

            EditorCategory category = EditorCategory.getCategory(event.getSlot());
            if (category != null) openItemsList(category);
        }
    }

    private void openCategories() {
        clearMainSection();
        activateListener(new CategoriesListener());
        for (EditorCategory category : EditorCategory.values()) {
            inventory.setItem(category.getSlot(), category.getIcon());
        }
    }

    public class ItemsListListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            if (event.getCurrentItem() == null) return;
            if (event.getSlot() > 44) return;
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();
            if (item.getType().isEmpty()) return;

            if (event.isLeftClick()) {
                event.getWhoClicked().getInventory().addItem(item);
            }
            else if (event.isRightClick()) {
                openItemSettings(item);
            }
        }
    }

    private void openItemsList(EditorCategory category) {
        clearMainSection();
        activateListener(new ItemsListListener());
        HashMap<Integer, ItemStack> items = category.getItems();
        for (int slot : items.keySet()) {
            inventory.setItem(slot, items.get(slot));
        }
    }

    private void openItemSettings(ItemStack item) {
        Material mat = item.getType();
        if (Enchantment.MENDING.canEnchantItem(new ItemStack(mat))) {
            openItemEnchantSettings(item);
        }
        else if (mat == Material.POTION || mat == Material.SPLASH_POTION || mat == Material.LINGERING_POTION) {
            openItemEffectSettings(item);
        }
        else if (mat == Material.TIPPED_ARROW) {
            openTippedArrowSettings(item);
        }
        else if (mat.getMaxStackSize() > 1) {
            openItemQuantitySettings(item);
        }
    }

    public class ItemSettingsListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            if (event.getCurrentItem() == null) return;
            if (event.getSlot() > 44) return;
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();
            if (item.getType().isEmpty()) return;

            event.getWhoClicked().getInventory().addItem(item);
        }
    }

    private void openItemQuantitySettings(ItemStack item) {
        clearMainSection();
        activateListener(new ItemSettingsListener());
        Material mat = item.getType();

        inventory.setItem(20, new ItemStack(mat, 4));
        inventory.setItem(21, new ItemStack(mat, 8));
        inventory.setItem(22, new ItemStack(mat, 16));
        if (mat.getMaxStackSize() == 64) {
            inventory.setItem(23, new ItemStack(mat, 32));
            inventory.setItem(24, new ItemStack(mat, 64));
        }
    }

    public class ItemEnchantSettingsListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            Inventory inv = event.getClickedInventory();
            if (inv == null) return;
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            if (event.getCurrentItem() == null) return;
            if (event.getSlot() > 44) return;
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();
            Material mat = item.getType();
            ItemStack tool = inventory.getItem(44);

            if (mat == Material.ENCHANTED_BOOK) {
                Enchantment enchant = (Enchantment) event.getCurrentItem().getEnchantments().keySet().toArray()[0];
                int level = event.getCurrentItem().getAmount();

                // check if tool is already enchanted with that enchantment
                if (tool.containsEnchantment(enchant)) {
                    int existingLevel = tool.getEnchantmentLevel(enchant);
                    if (existingLevel == level) {
                        tool.removeEnchantment(enchant);
                        return;
                    }
                }

                tool.addEnchantment(enchant, level);
            }
            else if (event.getSlot() == 44) {
                event.getWhoClicked().getInventory().addItem(tool);
            }
        }
    }

    private void openItemEnchantSettings(ItemStack item) {
        clearMainSection();
        activateListener(new ItemEnchantSettingsListener());
        Material mat = item.getType();

        // get valid enchantments
        Enchantment[] enchants = Enchantment.values();
        ArrayList<Enchantment> validEnchants = new ArrayList<>();
        for (Enchantment enchant : enchants) {
            if (enchant.equals(Enchantment.BINDING_CURSE)) continue;
            if (enchant.equals(Enchantment.VANISHING_CURSE)) continue;
            if (enchant.canEnchantItem(new ItemStack(mat))) validEnchants.add(enchant);
        }
        validEnchants.sort(Comparator.comparingInt(Enchantment::getMaxLevel));
        Collections.reverse(validEnchants);

        // add enchanted books
        int col = 0;
        for (Enchantment enchantment : validEnchants) {
            for (int level=1; level<=enchantment.getMaxLevel(); level++) {
                int slot = col + (level * 9 - 9);
                ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, level);
                book.addUnsafeEnchantment(enchantment, level);
                inventory.setItem(slot, book);
            }
            col++;
        }

        // add item in corner
        ItemStack tool = new ItemStack(mat);
        tool.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        inventory.setItem(44, tool);
    }

    private void openItemEffectSettings(ItemStack item) {
        clearMainSection();
        activateListener(new ItemSettingsListener());
        Material mat = item.getType();

        PotionMeta data = (PotionMeta) item.getItemMeta();
        PotionType effect = data.getBasePotionData().getType();

        inventory.setItem(21, ItemTools.createPotion(mat, effect));
        if (effect.isExtendable()) {
            inventory.setItem(22, ItemTools.createPotion(mat, effect, true, false));
        }
        if (effect.isUpgradeable()) {
            inventory.setItem(23, ItemTools.createPotion(mat, effect, false, true));
        }
    }

    private void openTippedArrowSettings(ItemStack item) {
        clearMainSection();
        activateListener(new ItemSettingsListener());
        Material mat = item.getType();

        PotionMeta data = (PotionMeta) item.getItemMeta();
        PotionType effect = data.getBasePotionData().getType();

        for (short stack=4, col=2; stack<=64; stack*=2, col++) {
            inventory.setItem(col+9, ItemTools.createPotion(mat, effect, stack));
            if (effect.isExtendable()) {
                inventory.setItem(col+18, ItemTools.createPotion(mat, effect, stack, true, false));
            }
            if (effect.isUpgradeable()) {
                inventory.setItem(col+27, ItemTools.createPotion(mat, effect, stack, false, true));
            }
        }
    }

    public class SettingsListener implements Listener {

    }

    private void openSettings() {

    }

    public static void startEditor(Player player) {
        player.sendMessage("Creating new kit");
        CustomDuelsKit k = new CustomDuelsKit();
        AllPlayerSettings.addCustomKit(player, k);
        k.setLogo(Material.GRASS_BLOCK);
        k.setName("Untitled Kit");
        startEditor(player, k);
    }

    public static void startEditor(Player player, CustomDuelsKit kit) {
        player.sendMessage("Editing kit");
        EditorGUI gui = new EditorGUI(kit);
        player.openInventory(gui.getInventory());
        // add kit items
        player.sendMessage("Clearing and giving items");
        player.getInventory().clear();
        player.sendMessage(String.valueOf(kit.getItems()));
        kit.giveInventoryItems(player);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
