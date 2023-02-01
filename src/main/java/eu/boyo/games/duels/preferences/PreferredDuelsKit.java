package eu.boyo.games.duels.preferences;

import eu.boyo.games.duels.DuelsKit;
import eu.boyo.games.duels.StandardDuelsKit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class PreferredDuelsKit implements DuelsKit {
    final StandardDuelsKit kit;
    final HashMap<Integer, ItemStack> items;

    public HashMap<Integer, ItemStack> getItems() {
        return items;
    }

    public boolean getHunger() {
        return kit.getHunger();
    }

    public String getName() {
        return kit.getName();
    }

    public String getDisplayName() {
        return kit.getDisplayName();
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

    PreferredDuelsKit(StandardDuelsKit baseKit, HashMap<Integer, ItemStack> newItems) {
        kit = baseKit;
        items = newItems;
    }
}
