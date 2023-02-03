package eu.boyo.games.duels.solo.custom;

import java.util.ArrayList;

public class ListedCustomDuels {

    static ArrayList<CustomDuelsKit> kits = new ArrayList<>();

    public static void addKit(CustomDuelsKit kit) {
        kits.add(kit);
        if (kits.size() > 54) {
            kits.remove(0);
        }
    }

    public static CustomDuelsKit getKit(int slot) {
        return kits.get(slot);
    }

    public static ArrayList<CustomDuelsKit> getKits() {
        return kits;
    }

    public static int getSize() {
        return kits.size();
    }
}
