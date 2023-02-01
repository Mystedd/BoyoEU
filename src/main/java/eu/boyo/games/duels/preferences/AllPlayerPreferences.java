package eu.boyo.games.duels.preferences;

import eu.boyo.games.duels.StandardDuelsKit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AllPlayerPreferences {

    static HashMap<Player, PlayerPreferences> playerPreferences = new HashMap<>();

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

    public static PreferredDuelsKit getPreferredKit(Player player, StandardDuelsKit kit) {
        PlayerPreferences preferences = playerPreferences.get(player);
        if (preferences == null) return null;
        return preferences.getPreferredKit(kit);
    }
}
