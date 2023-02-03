package eu.boyo.games.duels.solo.custom.editor;

import eu.boyo.games.duels.solo.custom.CustomDuelsKit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class EditorGUI implements InventoryHolder {

    Inventory inventory;

    public static void startEditor(Player player) {
        player.sendMessage("Creating new kit");
    }

    public static void startEditor(Player player, CustomDuelsKit kit) {
        player.sendMessage("Editing kit");
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
