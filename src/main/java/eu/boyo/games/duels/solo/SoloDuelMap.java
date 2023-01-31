package eu.boyo.games.duels.solo;

import eu.boyo.games.duels.StandardDuelsKit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;

public class SoloDuelMap {

    static World world = Bukkit.getWorld("duels");
    static HashMap<StandardDuelsKit, Location> kitMaps = new HashMap<>();
    static ArrayList<Integer> usedPositions = new ArrayList<>();

    static {
        kitMaps.put(StandardDuelsKit.UHC, new Location(world, 0, 0, 0));
    }

    public SoloDuelMap(StandardDuelsKit kit) {

    }
}
