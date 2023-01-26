package eu.boyo.games.lavarises;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class LavaRisesMap {

    static ArrayList<Integer> usedPositions = new ArrayList<>();
    int zPos;
    Location initialSpawnPos;

    public LavaRisesMap() {
        for (int z=-20; true; z+=50) {
            if (!usedPositions.contains(z)) {
                zPos = z;
                usedPositions.add(z);
                break;
            }
        }

        initialSpawnPos = new Location(LavaRisesGame.world, 0, -52, zPos+20);
    }

    public Location getInitialSpawnPos() {
        return initialSpawnPos;
    }

    public Location getSpectatorSpawnPos(int lavaLevel) {
        return new Location(LavaRisesGame.world, 0, lavaLevel+10, zPos+20);
    }

    public Location getRespawnSpawnPos(int y) {
        Location location = null;
        while (location == null) {
            location = LavaRisesHelper.getSpawnOnLevel(y, this);
            y++;
        }
        return location;
    }

    private LavaRisesTheme getRandomTheme() {
        LavaRisesTheme[] themes = LavaRisesTheme.values();
        byte choice = (byte) (Math.random() * themes.length);
        return themes[choice];
    }

    public void generateMap() {
        // get random theme
        HashMap<Material, Material> theme = getRandomTheme().getTheme();

        // generate bottom section
        LavaRisesHelper.clone(-70, -60, -20, -30, -51, 20, -20, -62, -20, theme);

        // generate each level
        for (int level=0; level<5; level++) {
            // get target coordinates
            int tx = (level*-50)-20;
            int ty = -60;
            int rand = (int) (Math.random() * 5);
            int tz = (rand*-50)-70;
            // get destination coordinates
            int dx = -20;
            int dy = (level*20)-52;
            int dz = -20;
            // clone into place
            LavaRisesHelper.clone(tx, ty, tz, tx+40, ty+19, tz+40, dx, dy, dz, theme);
        }
    }

    public void placeLava(int yLevel) {
        for (int x=-19; x<=19; x++) {
            for (int z=zPos+1; z<=zPos+39; z++) {
                Block block = LavaRisesGame.world.getBlockAt(x, yLevel, z);

                if (block.getType().isAir()) {
                    block.setType(Material.LAVA);
                }
            }
        }
    }
}
