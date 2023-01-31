package eu.boyo.games.duels.solo.custom;

import eu.boyo.games.duels.DuelsKit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CustomDuelsKit implements DuelsKit {

    public HashMap<Integer, ItemStack> getItems() {
        return null;
    }

    public boolean getHunger() {
        return false;
    }

    public String getName() {
        return null;
    }

    public String getDisplayName() {
        return null;
    }

    public void giveItems(Player player) {

    }

    public void giveItems(Player player1, Player player2) {

    }
}
