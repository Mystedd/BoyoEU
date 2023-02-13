package eu.boyo;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;


public class ItemTools {

    public static ItemStack createItem(Material mat, String name, String lore, boolean glint) {
        ItemStack item = new ItemStack(mat);
        ItemMeta data = item.getItemMeta();
        data.setDisplayName(name);
        ArrayList<String> loreList = new ArrayList<String>();
        loreList.add(lore);
        data.setLore(loreList);
        data.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        if (glint) {
            data.addEnchant(Enchantment.BINDING_CURSE, 1, true);
            data.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(data);
        return item;
    }

    public static ItemStack createItem(Material mat, String name, String lore) {
        return createItem(mat, name, lore, false);
    }

    public static ItemStack createItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta data = item.getItemMeta();
        data.setDisplayName(name);
        item.setItemMeta(data);
        return item;
    }

    public static ItemStack createItem(Material mat, String name, int customModelData) {
        ItemStack item = new ItemStack(mat);
        ItemMeta data = item.getItemMeta();
        data.setDisplayName(name);
        data.setCustomModelData(customModelData);
        item.setItemMeta(data);
        return item;
    }

    public static ItemStack createPotion(Material mat, PotionType effect) {
        return createPotion(mat, effect, 1);
    }

    public static ItemStack createPotion(Material mat, PotionType effect, int amount) {
        ItemStack potion = new ItemStack(mat, amount);
        PotionMeta data = (PotionMeta) potion.getItemMeta();
        data.setBasePotionData(new PotionData(effect));
        potion.setItemMeta(data);
        return potion;
    }

    public static ItemStack createPotion(Material mat, PotionType effect, boolean extended, boolean upgraded) {
        return createPotion(mat, effect, 1, extended, upgraded);
    }

    public static ItemStack createPotion(Material mat, PotionType effect, int amount, boolean extended, boolean upgraded) {
        ItemStack potion = new ItemStack(mat, amount);
        PotionMeta data = (PotionMeta) potion.getItemMeta();
        data.setBasePotionData(new PotionData(effect, extended, upgraded));
        potion.setItemMeta(data);
        return potion;
    }

    public static ItemStack createLeatherArmor(Material mat, Color color, String name, int customModelData, boolean hideFlags) {
        ItemStack armor = new ItemStack(mat);
        LeatherArmorMeta data = (LeatherArmorMeta) armor.getItemMeta();
        data.setDisplayName(name);
        data.setCustomModelData(customModelData);
        data.setColor(color);
        if (hideFlags) {
            data.addItemFlags(ItemFlag.HIDE_DYE, ItemFlag.HIDE_ATTRIBUTES);
        }
        armor.setItemMeta(data);
        return armor;
    }
}
