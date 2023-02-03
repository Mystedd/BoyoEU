package eu.boyo.games.duels.solo.custom;

import eu.boyo.games.duels.DuelsKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CustomDuelsKit implements DuelsKit {

    private Material logo;
    private String name;

    public Material getLogo() {
        return logo;
    }

    public HashMap<Integer, ItemStack> getItems() {
        return null;
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

    }

    public void giveInventoryItems(Player player) {

    }


    public void giveItems(Player player1, Player player2) {

    }

    public void setLogo(Material newLogo) {
        logo = newLogo;
    }

    public void setName(String newName) {
        name = newName;
    }
}
