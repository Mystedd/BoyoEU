package eu.boyo.games.duels;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.HashMap;

public enum DuelsKit {

    UHC("UHC", false) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // sword
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 4);
        items.put(0, sword);
        // axe
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        axe.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        items.put(1, axe);
        // bow
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        items.put(2, bow);
        // water buckets
        ItemStack water = new ItemStack(Material.WATER_BUCKET);
        items.put(3, water);
        items.put(21, water);
        items.put(22, water);
        items.put(31, water);
        // gapples
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 13);
        items.put(4, gapples);
        // cobwebs
        ItemStack cobwebs = new ItemStack(Material.COBWEB, 8);
        items.put(5, cobwebs);
        // lava buckets
        ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
        items.put(6, lava);
        items.put(7, lava);
        // cobblestone
        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE, 64);
        items.put(8, cobblestone);
        items.put(35, cobblestone);
        // shields
        ItemStack shield = new ItemStack(Material.SHIELD);
        items.put(104, shield);
        items.put(14, shield);
        // pickaxe
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);
        items.put(23, pickaxe);
        // logs
        ItemStack logs = new ItemStack(Material.OAK_LOG, 64);
        items.put(26, logs);
        // arrows
        ItemStack arrows = new ItemStack(Material.ARROW, 16);
        items.put(27, arrows);
        // crossbow
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        crossbow.addEnchantment(Enchantment.PIERCING, 1);
        // helmet
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(103, helmet);
        // chestplate
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(102, chestplate);
        // leggings
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(100, boots);
        return items;
    }},

    POT("Pot", true) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // sword
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(0, sword);
        // potions
        PotionMeta data;
        // healing
        ItemStack healing = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) healing.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
        healing.setItemMeta(data);
        int[] slots = {1, 2, 3, 4, 5, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33};
        for (int slot : slots) items.put(slot, healing);
        // strength
        ItemStack strength = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) strength.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
        strength.setItemMeta(data);
        items.put(6, strength);
        items.put(34, strength);
        items.put(35, strength);
        // speed
        ItemStack speed = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) speed.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
        speed.setItemMeta(data);
        items.put(7, speed);
        items.put(25, speed);
        items.put(26, speed);
        // regen
        ItemStack regen = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) regen.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.REGEN, false, false));
        regen.setItemMeta(data);
        items.put(8, regen);
        items.put(16, regen);
        items.put(17, regen);
        // bow
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
        bow.addEnchantment(Enchantment.DURABILITY, 1);
        bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        items.put(9, bow);
        // arrows
        ItemStack arrows = new ItemStack(Material.ARROW, 24);
        items.put(10, arrows);
        // helmet
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(103, helmet);
        // chestplate
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(102, chestplate);
        // leggings
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(100, boots);
        // steak
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 20);
        items.put(104, steak);
        return items;
    }},

    SMP("SMP", true) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // potions
        PotionMeta data;
        // kb sword
        ItemStack kbSword = new ItemStack(Material.NETHERITE_SWORD);
        kbSword.addEnchantment(Enchantment.KNOCKBACK, 2);
        kbSword.addEnchantment(Enchantment.MENDING, 1);
        kbSword.addEnchantment(Enchantment.DURABILITY, 3);
        kbSword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        kbSword.addEnchantment(Enchantment.SWEEPING_EDGE, 3);
        kbSword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(0, kbSword);
        // pearls
        ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 16);
        items.put(1, pearls);
        items.put(19, pearls);
        items.put(28, pearls);
        // gapples
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 64);
        items.put(2, gapples);
        items.put(27, gapples);
        // axe
        ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
        axe.addEnchantment(Enchantment.MENDING, 1);
        axe.addEnchantment(Enchantment.DURABILITY, 3);
        axe.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(3, axe);
        // sword
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        sword.addEnchantment(Enchantment.MENDING, 1);
        sword.addEnchantment(Enchantment.DURABILITY, 3);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantment.SWEEPING_EDGE, 3);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(4, kbSword);
        // fire res
        ItemStack fireres = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) fireres.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE, true, false));
        fireres.setItemMeta(data);
        items.put(5, fireres);
        items.put(10, fireres);
        items.put(11, fireres);
        // totem
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        items.put(6, totem);
        // strength
        ItemStack strength = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) strength.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
        strength.setItemMeta(data);
        int[] strengthSlots = {7, 13, 15, 17, 20, 21, 22, 23, 24, 25, 26};
        for (int slot : strengthSlots) items.put(slot, strength);
        // speed
        ItemStack speed = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) speed.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
        speed.setItemMeta(data);
        int[] speedSlots = {8, 12, 14, 16, 29, 30, 31, 32, 33, 34, 35};
        for (int slot : speedSlots) items.put(slot, speed);
        // xp bottles
        ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);
        items.put(9, xp);
        items.put(18, xp);
        // helmet
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
        helmet.addEnchantment(Enchantment.MENDING, 1);
        helmet.addEnchantment(Enchantment.DURABILITY, 3);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        items.put(103, helmet);
        // chestplate
        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.MENDING, 1);
        chestplate.addEnchantment(Enchantment.DURABILITY, 3);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        items.put(102, chestplate);
        // leggings
        ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
        leggings.addEnchantment(Enchantment.MENDING, 1);
        leggings.addEnchantment(Enchantment.DURABILITY, 3);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        boots.addEnchantment(Enchantment.MENDING, 1);
        boots.addEnchantment(Enchantment.DURABILITY, 3);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
        items.put(100, boots);
        // shield
        ItemStack shield = new ItemStack(Material.SHIELD);
        shield.addEnchantment(Enchantment.MENDING, 1);
        shield.addEnchantment(Enchantment.DURABILITY, 3);
        items.put(104, shield);
        return items;
    }},

    AXE("Axe + Shield", false) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // axe
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        items.put(0, axe);
        // sword
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        items.put(1, sword);
        // crossbow
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        items.put(2, crossbow);
        // bow
        ItemStack bow = new ItemStack(Material.BOW);
        items.put(3, bow);
        // arrows
        ItemStack arrows = new ItemStack(Material.ARROW, 6);
        items.put(4, arrows);
        // helmet
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        items.put(103, helmet);
        // chestplate
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        items.put(102, chestplate);
        // leggings
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        items.put(100, boots);
        // shield
        ItemStack shield = new ItemStack(Material.SHIELD);
        items.put(104, shield);
        return items;
    }},

    RANDOM("Random", false) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();

        ArrayList<Material> materials = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isItem()) materials.add(material);
        }

        for (int slot=0; slot<=35; slot++) {
            if (slot == 9) slot = 18;

            int n = (int) (Math.random() * materials.size());
            Material material = materials.get(n);
            byte stack = (byte) (Math.random() * material.getMaxStackSize() + 1);
            ItemStack item = new ItemStack(material, stack);
            items.put(slot, item);
        }
        return items;
    }};

    final boolean hasSaturation;
    final String name;

    public HashMap<Integer, ItemStack> getItems() {
        return new HashMap<>();
    }

    public boolean getSaturation() {
        return hasSaturation;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return name + " duels";
    }

    public void giveItems(Player player) {
        PlayerInventory inventory = player.getInventory();
        HashMap<Integer, ItemStack> items = getItems();
        for (int slot : items.keySet()) {
            ItemStack item = items.get(slot);
            if (0 <= slot && slot <= 35) inventory.setItem(slot, item);
            else if (slot == 104) inventory.setItemInOffHand(item);
            else if (slot == 103) inventory.setHelmet(item);
            else if (slot == 102) inventory.setChestplate(item);
            else if (slot == 101) inventory.setLeggings(item);
            else if (slot == 100) inventory.setBoots(item);
        }
    }

    public void giveItems(Player player1, Player player2) {
        giveItems(player1);
        giveItems(player2);
    }

    DuelsKit(String newName, boolean saturation) {
        name = newName;
        hasSaturation = saturation;
    }
}
