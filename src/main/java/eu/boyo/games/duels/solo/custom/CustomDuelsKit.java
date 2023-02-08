package eu.boyo.games.duels.solo.custom;

import eu.boyo.games.duels.DuelsKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class CustomDuelsKit implements DuelsKit {

    private Material logo;
    private String name;
    private HashMap<Integer, ItemStack> items = new HashMap<>();

    public Material getLogo() {
        return logo;
    }

    public HashMap<Integer, ItemStack> getItems() {
        return items;
    }

    public boolean getHunger() {
        return false;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return null;
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

    public void giveInventoryItems(Player player) {
        PlayerInventory inventory = player.getInventory();
        HashMap<Integer, ItemStack> items = getItems();
        for (int slot : items.keySet()) {
            ItemStack item = items.get(slot);
            if (0 <= slot && slot <= 35) {
                inventory.setItem(slot, item);
            }
        }
    }

    public void giveItems(Player player1, Player player2) {

    }

    public void setLogo(Material newLogo) {
        logo = newLogo;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setItems(HashMap<Integer, ItemStack> i) {
        items = i;
    }
}
