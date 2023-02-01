package eu.boyo.games;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class BuildTools {

    public static void clone(Location minCorner, Location maxCorner, Location newCorner, Biome newBiome) {

        // tx = lower northwestern coordinate of target
        // sx = length/depth/height of target
        // dx = lower northwestern coordinate of destination

        World world = newCorner.getWorld();
        for (int tx=minCorner.getBlockX(), dx=newCorner.getBlockX(); tx<=maxCorner.getBlockX(); tx++, dx++) {
            for (int ty=minCorner.getBlockY(), dy=newCorner.getBlockY(); ty<=maxCorner.getBlockY(); ty++, dy++) {
                for (int tz=minCorner.getBlockZ(), dz=newCorner.getBlockZ(); tz<=maxCorner.getBlockZ(); tz++, dz++) {
                    Location target = new Location(world, tx, ty, tz);
                    Location destination = new Location(world, dx, dy, dz);

                    world.setBiome(destination, newBiome);

                    BlockState data = world.getBlockState(target);
                    world.setBlockData(destination, data.getBlockData());
                }
            }
        }
    }

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

}
