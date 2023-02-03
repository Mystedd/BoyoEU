package eu.boyo.games.duels.settings;

import eu.boyo.games.duels.DuelsKit;
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

    public DuelsKit getPreferredKit(StandardDuelsKit kit) {
        PreferredDuelsKit preferredKit = preferredKits.get(kit);
        if (preferredKit == null) return kit;
        return preferredKits.get(kit);
    }
}
