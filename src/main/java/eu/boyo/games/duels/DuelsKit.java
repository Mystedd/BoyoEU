package eu.boyo.games.duels;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface DuelsKit {

    HashMap<Integer, ItemStack> getItems();

    boolean getHunger();

    String getName();

    String getDisplayName();

    void giveItems(Player player);

    void giveInventoryItems(Player player);

    void giveItems(Player player1, Player player2);

}
