package eu.boyo.games.duels.settings;

import eu.boyo.games.duels.DuelsKit;
import eu.boyo.games.duels.StandardDuelsKit;
import eu.boyo.games.duels.solo.custom.CustomDuelsKit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AllPlayerSettings {

    static HashMap<Player, PlayerPreferences> playerPreferences = new HashMap<>();
    static HashMap<Player, PlayerCustomKits> playerCustomKits = new HashMap<>();

    private static PlayerPreferences createPlayerPreferences(Player player) {
        PlayerPreferences preferences = new PlayerPreferences();
        playerPreferences.put(player, preferences);
        return preferences;
    }

    public static void setPreferredKit(Player player, StandardDuelsKit standardKit, PreferredDuelsKit preferredKit) {
        PlayerPreferences preferences = playerPreferences.get(player);
        if (preferences == null) {
            preferences = createPlayerPreferences(player);
        }
        preferences.setPreferredKit(standardKit, preferredKit);
    }

    public static void resetPreferredKit(Player player, StandardDuelsKit kit) {
        PlayerPreferences preferences = playerPreferences.get(player);
        if (preferences != null) preferences.resetPreferredKit(kit);
    }

    public static DuelsKit getPreferredKit(Player player, StandardDuelsKit kit) {
        PlayerPreferences preferences = playerPreferences.get(player);
        if (preferences == null) return kit;
        return preferences.getPreferredKit(kit);
    }

    private static PlayerCustomKits createPlayerCustomKits(Player player) {
        PlayerCustomKits customKits = new PlayerCustomKits();
        playerCustomKits.put(player, customKits);
        return customKits;
    }

    public static void replaceCustomKit(Player player, int slot, CustomDuelsKit kit) {
        PlayerCustomKits customKits = playerCustomKits.get(player);
        if (customKits == null) {
            customKits = createPlayerCustomKits(player);
        }
        customKits.replaceKit(slot, kit);
    }

    public static CustomDuelsKit getCustomKit(Player player, int slot) {
        PlayerCustomKits customKits = playerCustomKits.get(player);
        if (customKits == null) return null;
        return customKits.getKit(slot);
    }
}
