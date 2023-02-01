package eu.boyo.games.duels.preferences;

import eu.boyo.games.duels.StandardDuelsKit;

import java.util.HashMap;

public class PlayerPreferences {

    HashMap<StandardDuelsKit, PreferredDuelsKit> preferredKits = new HashMap<>();

    public void setPreferredKit(StandardDuelsKit standardKit, PreferredDuelsKit preferredKit) {
        preferredKits.put(standardKit, preferredKit);
    }

    public void resetPreferredKit(StandardDuelsKit kit) {
        preferredKits.remove(kit);
    }

    public PreferredDuelsKit getPreferredKit(StandardDuelsKit kit) {
        return preferredKits.get(kit);
    }
}
