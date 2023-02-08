package eu.boyo.games.duels.solo.custom.editor;

import eu.boyo.games.BuildTools;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.HashMap;

public enum EditorCategory {

    TOOLS(10, Material.DIAMOND_SWORD, "Tools & Weapons") {
        public HashMap<Integer, ItemStack> getItems() {
            HashMap<Integer, ItemStack> items = new HashMap<>();

            String[] prefixes = {"WOODEN", "STONE", "GOLDEN", "", "IRON", "DIAMOND", "NETHERITE"};
            String[] suffixes = {"SWORD", "AXE", "PICKAXE", "SHOVEL", "HOE"};

            int col = 0;
            for (String prefix : prefixes) {
                col++;
                if (prefix.isEmpty()) continue;
                int row = 0;
                for (String suffix : suffixes) {
                    int slot = col + (9 * row);
                    Material mat = Material.valueOf(prefix + "_" + suffix);
                    items.put(slot, new ItemStack(mat));
                    row++;
                }
            }
            return items;
        }
    },

    ARMOR(11, Material.DIAMOND_CHESTPLATE, "Armour") {
        public HashMap<Integer, ItemStack> getItems() {
            HashMap<Integer, ItemStack> items = new HashMap<>();

            String[] prefixes = {"LEATHER", "CHAINMAIL", "GOLDEN", "", "IRON", "DIAMOND", "NETHERITE"};
            String[] suffixes = {"HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS"};
            int col = 0;
            for (String prefix : prefixes) {
                col++;
                if (prefix.isEmpty()) continue;
                int row = 0;
                for (String suffix : suffixes) {
                    int slot = col + (9 * row);
                    Material mat = Material.valueOf(prefix + "_" + suffix);
                    items.put(slot, new ItemStack(mat));
                    row++;
                }
            }
            items.put(4, new ItemStack(Material.TURTLE_HELMET));
            return items;
        }
    },

    FOOD(12, Material.COOKED_BEEF, "Food") {
        public HashMap<Integer, ItemStack> getItems() {
            HashMap<Integer, ItemStack> items = new HashMap<>();

            int slot = 0;
            for (Material mat : Material.values()) {
                if (mat.isEdible()) {
                    items.put(slot, new ItemStack(mat));
                    slot++;
                }
            }
            return items;
        }
    },

    POTIONS(13, Material.SPLASH_POTION, "Potions & Arrows") {
        public HashMap<Integer, ItemStack> getItems() {
            HashMap<Integer, ItemStack> items = new HashMap<>();

            PotionType[] leftEffects = {
                    PotionType.STRENGTH,
                    PotionType.SPEED,
                    PotionType.INSTANT_HEAL,
                    PotionType.REGEN,
                    PotionType.FIRE_RESISTANCE,
            };
            PotionType[] rightEffects = {
                    PotionType.WEAKNESS,
                    PotionType.SLOWNESS,
                    PotionType.INSTANT_DAMAGE,
                    PotionType.POISON,
                    PotionType.SLOW_FALLING,
            };
            PotionType[][] effects = {
                    leftEffects,
                    rightEffects,
            };
            Material[] materials = {
                    Material.POTION,
                    Material.SPLASH_POTION,
                    Material.LINGERING_POTION,
                    Material.TIPPED_ARROW,
            };

            for (int side=0; side==0||side==1; side++) {
                int row = 0;
                for (PotionType effect : effects[side]) {
                    int col = 0;
                    for (Material mat : materials) {
                        ItemStack potion = BuildTools.createPotion(mat, effect);
                        potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                        int slot = col + (row * 9) + (side * 5);
                        items.put(slot, potion);
                        col++;
                    }
                    row++;
                }
            }
            return items;
        }
    };

    private final ItemStack icon;
    private final int slot;
    private final String name;

    EditorCategory(int s, Material mat, String n) {
        icon = BuildTools.createItem(mat, n);
        icon.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        icon.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        slot = s;
        name = n;
    }

    public static EditorCategory getCategory(int slot) {
        for (EditorCategory category : EditorCategory.values()) {
            if (category.getSlot() == slot) {
                return category;
            }
        }
        return null;
    }

    public HashMap<Integer, ItemStack> getItems() {
        return null;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public int getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }
}
