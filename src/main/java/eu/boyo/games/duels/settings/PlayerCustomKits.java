package eu.boyo.games.duels.settings;

import eu.boyo.games.duels.solo.custom.CustomDuelsKit;

import java.util.ArrayList;

public class PlayerCustomKits {

    private ArrayList<CustomDuelsKit> kits = new ArrayList<>();

    public void replaceKit(int slot, CustomDuelsKit kit) {
        kits.set(slot, kit);
    }

    public CustomDuelsKit getKit(int slot) {
        if (slot >= kits.size()) {
            return null;
        }
        return kits.get(slot);
    }
}
