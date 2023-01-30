package eu.boyo.games.duels;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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

    CRYSTAL("Crystal", true) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // sword
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        sword.addEnchantment(Enchantment.MENDING, 1);
        sword.addEnchantment(Enchantment.DURABILITY, 3);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantment.SWEEPING_EDGE, 3);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(0, sword);
        // respawn anchors
        ItemStack anchors = new ItemStack(Material.RESPAWN_ANCHOR, 64);
        items.put(1, anchors);
        // glowstone
        ItemStack glowstone = new ItemStack(Material.GLOWSTONE, 64);
        items.put(2, glowstone);
        // pearls
        ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 16);
        int[] pearlSlots = {3, 9, 18, 19, 27, 28, 29, 30};
        for (int slot : pearlSlots) items.put(slot, pearls);
        // totems
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        int[] totemSlots = {4, 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 31, 104};
        for (int slot : totemSlots) items.put(slot, totem);
        // obsidian
        ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 64);
        items.put(5, obsidian);
        items.put(24, obsidian);
        items.put(32, obsidian);
        // crystals
        ItemStack crystals = new ItemStack(Material.END_CRYSTAL, 64);
        items.put(6, crystals);
        items.put(25, crystals);
        items.put(33, crystals);
        items.put(34, crystals);
        // gapples
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 64);
        items.put(7, gapples);
        // pickaxe
        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        pickaxe.addEnchantment(Enchantment.DURABILITY, 3);
        pickaxe.addEnchantment(Enchantment.MENDING, 1);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 5);
        items.put(8, pickaxe);
        // xp bottles
        ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 64);
        items.put(17, xp);
        items.put(26, xp);
        items.put(35, xp);
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
        leggings.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        boots.addEnchantment(Enchantment.MENDING, 1);
        boots.addEnchantment(Enchantment.DURABILITY, 3);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
        items.put(100, boots);
        return items;
    }},

    BOW("Bow", false) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // bow
        ItemStack bow = new ItemStack(Material.BOW);
        items.put(0, bow);
        // arrows
        ItemStack arrows = new ItemStack(Material.ARROW, 64);
        items.put(1, arrows);
        // helmet
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        items.put(103, helmet);
        // chestplate
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        items.put(102, chestplate);
        // leggings
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        items.put(100, boots);
        return items;
    }},

    CLASSIC_CPVP("Classic CPvP", true) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // potions
        PotionMeta data;
        // sword
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.KNOCKBACK, 2);
        sword.addEnchantment(Enchantment.MENDING, 1);
        sword.addEnchantment(Enchantment.DURABILITY, 3);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantment.SWEEPING_EDGE, 3);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(0, sword);
        // pearls
        ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 16);
        int[] pearlSlots = {1, 9, 10, 18, 19, 27, 28};
        for (int slot : pearlSlots) items.put(slot, pearls);
        // obsidian
        ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 64);
        items.put(2, obsidian);
        items.put(29, obsidian);
        // crystals
        ItemStack crystals = new ItemStack(Material.END_CRYSTAL, 64);
        items.put(3, crystals);
        items.put(30, crystals);
        // gapples
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 64);
        items.put(4, gapples);
        // strength
        ItemStack strength = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) strength.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
        strength.setItemMeta(data);
        int[] strengthSlots = {5, 12, 13, 14, 15, 16, 17};
        for (int slot : strengthSlots) items.put(slot, strength);
        // speed
        ItemStack speed = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) speed.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
        speed.setItemMeta(data);
        int[] speedSlots = {6, 21, 22, 23, 24, 25, 26};
        for (int slot : speedSlots) items.put(slot, speed);
        // pickaxe
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        pickaxe.addEnchantment(Enchantment.DURABILITY, 3);
        pickaxe.addEnchantment(Enchantment.MENDING, 1);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 5);
        items.put(7, pickaxe);
        // totems
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        int[] totemSlots = {8, 31, 32, 33, 34, 35, 104};
        for (int slot : totemSlots) items.put(slot, totem);
        // xp bottles
        ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);
        items.put(11, xp);
        items.put(20, xp);
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
        leggings.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        boots.addEnchantment(Enchantment.MENDING, 1);
        boots.addEnchantment(Enchantment.DURABILITY, 3);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
        items.put(100, boots);
        return items;
    }},

    HSG("HSG", false) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // sword
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 4);
        items.put(0, sword);
        // gappples
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 6);
        items.put(1, gapples);
        // bow
        ItemStack bow = new ItemStack(Material.BOW);
        items.put(2, bow);
        // pufferfish
        ItemStack pufferfish = new ItemStack(Material.PUFFERFISH_BUCKET);
        items.put(3, pufferfish);
        items.put(30, pufferfish);
        // pearls
        ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 2);
        items.put(4, pearls);
        // cobwebs
        ItemStack cobwebs = new ItemStack(Material.COBWEB, 6);
        items.put(5, cobwebs);
        // water
        ItemStack water = new ItemStack(Material.WATER_BUCKET);
        items.put(6, water);
        items.put(33, water);
        // lava
        ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
        items.put(7, lava);
        items.put(34, lava);
        // cobblestone
        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE, 64);
        items.put(8, cobblestone);
        items.put(35, cobblestone);
        // pickaxe
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        items.put(20, pickaxe);
        // arrows
        ItemStack arrows = new ItemStack(Material.ARROW, 16);
        items.put(27, arrows);
        // axe
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        axe.addEnchantment(Enchantment.DIG_SPEED, 3);
        items.put(28, axe);
        // golden heads
        ItemStack heads = new ItemStack(Material.PLAYER_HEAD, 3);
        SkullMeta data = (SkullMeta) heads.getItemMeta();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString("57a8704d-b3f4-4c8f-bea0-64675011fe7b"));
        data.setOwningPlayer(player);
        heads.setItemMeta(data);
        items.put(29, heads);
        // crossbow
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        crossbow.addEnchantment(Enchantment.PIERCING, 1);
        items.put(32, crossbow);
        // helmet
        ItemStack helmet = new ItemStack(Material.TURTLE_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        items.put(103, helmet);
        // chestplate
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(102, chestplate);
        // leggings
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(101, leggings);
        // boots
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        items.put(100, boots);
        return items;
    }},

    NETHPOT("Nethpot", true) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // potions
        PotionMeta data;
        int[] slots;
        // sword
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        sword.addEnchantment(Enchantment.MENDING, 1);
        sword.addEnchantment(Enchantment.DURABILITY, 3);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantment.SWEEPING_EDGE, 3);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        items.put(0, sword);
        // healing
        ItemStack healing = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) healing.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
        healing.setItemMeta(data);
        int[] healingSlots = {1, 2, 3, 4, 8, 10, 11, 12, 13, 19, 20, 21, 22, 28, 29, 30, 31, 32};
        for (int slot : healingSlots) items.put(slot, healing);
        // speed
        ItemStack speed = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) speed.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
        speed.setItemMeta(data);
        int[] speedSlots = {6, 14, 15, 23, 24, 33};
        for (int slot : speedSlots) items.put(slot, speed);
        // strength
        ItemStack strength = new ItemStack(Material.SPLASH_POTION);
        data = (PotionMeta) strength.getItemMeta();
        data.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
        strength.setItemMeta(data);
        int[] strengthSlots = {7, 16, 17, 25, 26, 34};
        for (int slot : strengthSlots) items.put(slot, strength);
        // totems
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        items.put(5, totem);
        items.put(27, totem);
        items.put(35, totem);
        // xp bottles
        ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 64);
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
        // gapples
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 64);
        items.put(104, gapples);
        return items;
    }},

    SUMO("Sumo", false) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // fish
        ItemStack fish = new ItemStack(Material.COD);
        items.put(0, fish);
        return items;
    }},

    RANDOM("Random", true) { public HashMap<Integer, ItemStack> getItems() {
        HashMap<Integer, ItemStack> items = new HashMap<>();

        // get all materials that are items
        ArrayList<Material> validMaterials = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isItem()) validMaterials.add(material);
        }

        // choose a random material for each slot
        for (int slot=0; slot<=35; slot++) {
            if (slot == 9) slot = 18;

            int n = (int) (Math.random() * validMaterials.size());
            Material material = validMaterials.get(n);
            byte stack = (byte) (Math.random() * material.getMaxStackSize() + 1);

            ItemStack item = new ItemStack(material, stack);
            items.put(slot, item);
            validMaterials.remove(material);
        }
        return items;
    }};

    final boolean hasHunger;
    final String name;

    public HashMap<Integer, ItemStack> getItems() {
        return new HashMap<>();
    }

    public boolean getHunger() {
        return hasHunger;
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

    DuelsKit(String newName, boolean hunger) {
        name = newName;
        hasHunger = hunger;
    }
}
