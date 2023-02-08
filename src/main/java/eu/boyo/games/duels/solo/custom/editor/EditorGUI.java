package eu.boyo.games.duels.solo.custom.editor;

import eu.boyo.BoyoEU;
import eu.boyo.games.BuildTools;
import eu.boyo.games.duels.settings.AllPlayerSettings;
import eu.boyo.games.duels.solo.custom.CustomDuelsKit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class EditorGUI implements InventoryHolder {

    Inventory inventory;
    CustomDuelsKit kit;
    Listener activeListener;
    static ArrayList<Integer> blockedSlots = new ArrayList<>();
    static int backSlot = 45;
    static int saveSlot = 53;
    static int offHandSlot = 51;
    static ArrayList<Integer> armorSlots = new ArrayList<>();

    static {
        blockedSlots.add(46);
        blockedSlots.add(52);
        armorSlots.add(47);
        armorSlots.add(48);
        armorSlots.add(49);
        armorSlots.add(50);
    }

    private enum Screen {
        CATEGORIES,
        ITEMS_LIST,
        ITEM_QUANTITY_SETTINGS,
        ITEM_ENCHANT_SETTINGS,
        ITEM_EFFECT_SETTINGS,
        SETTINGS
    }

    private void clearMainSection() {
        for (int slot=0; slot<=44; slot++) {
            inventory.clear(slot);
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
            PlayerInventory playerInv = player.getInventory();

            if (blockedSlots.contains(event.getSlot())) {
                event.setCancelled(true);
            }
            // back button
            else if (event.getSlot() == backSlot) {
                event.setCancelled(true);
                openCategories();
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
                kit.setItems(items);
                player.sendMessage("§2Saved kit");
                player.closeInventory();
            }
        }

        @EventHandler
        public void onClose(InventoryCloseEvent event) {
            Inventory inv = event.getInventory();
            if (!(inv.getHolder() instanceof EditorGUI)) return;
            HandlerList.unregisterAll(activeListener);
            HandlerList.unregisterAll(this);
        }
    }

    EditorGUI(CustomDuelsKit k) {
        inventory = Bukkit.createInventory(this, 54, "Custom Duels Editor");
        kit = k;
        getServer().getPluginManager().registerEvents(new EditorGUIListener(), BoyoEU.plugin);

        // add control items
        ItemStack pane = BuildTools.createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        for (int slot : blockedSlots) {
            inventory.setItem(slot, pane);
        }
        ItemStack back = BuildTools.createItem(Material.ARROW, "§9§o<-- Back");
        inventory.setItem(backSlot, back);
        ItemStack save = BuildTools.createItem(Material.ENDER_CHEST, "§2§lSave Kit");
        inventory.setItem(saveSlot, save);

        // add offhand and armor items
        inventory.setItem(offHandSlot, k.getItems().get(104));

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
        if (mat.getMaxStackSize() > 1) {
            openItemQuantitySettings(item);
        }
        else if (Enchantment.MENDING.canEnchantItem(new ItemStack(mat))) {
            openItemEnchantSettings(item);
        }
        else if (mat == Material.POTION || mat == Material.SPLASH_POTION || mat == Material.LINGERING_POTION) {
            openItemEffectSettings(item);
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

        inventory.setItem(21, BuildTools.createPotion(mat, effect));
        if (effect.isExtendable()) {
            inventory.setItem(22, BuildTools.createPotion(mat, effect, true, false));
        }
        if (effect.isUpgradeable()) {
            inventory.setItem(23, BuildTools.createPotion(mat, effect, false, true));
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
        player.getInventory().clear();
        kit.giveInventoryItems(player);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
