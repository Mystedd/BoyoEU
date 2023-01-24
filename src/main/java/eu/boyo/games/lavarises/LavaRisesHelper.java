package eu.boyo.games.lavarises;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockSupport;

import java.util.ArrayList;
import java.util.HashMap;

public class LavaRisesHelper {

    static World world = LavaRisesGame.world;

    private LavaRisesHelper() {}

    // similar to vanilla /clone, but replaces blocks based on theme
    public static void clone(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int z, HashMap<Material, Material> theme) {
        // tx = lower northwestern coordinate of target
        // sx = length/depth/height of target
        // dx = lower northwestern coordinate of destination
        for (int tx=x1, dx=x; tx<=x2; tx++, dx++) {
            for (int ty=y1, dy=y; ty<=y2; ty++, dy++) {
                for (int tz=z1, dz=z; tz<=z2; tz++, dz++) {
                    Location target = new Location(world, tx, ty, tz);
                    Location destination = new Location(world, dx, dy, dz);

                    Material templateMaterial = world.getBlockAt(target).getType();
                    Material material;
                    if (theme.containsKey(templateMaterial)) {
                        material = theme.get(templateMaterial);
                    }
                    else {
                        material = templateMaterial;
                    }

                    BlockState data = world.getBlockState(target);
                    data.setType(material);
                    world.setBlockData(destination, data.getBlockData());
                }
            }
        }
    }

    // get a valid spawn location above a y level
    public static Location getSpawn(int y) {
        Location location = null;
        while (location == null) {
            location = getSpawnOnLevel(y);
            y++;
        }
        return location;
    }

    // get a random valid spawn location on a specific y level
    private static Location getSpawnOnLevel(int y) {
        ArrayList<Location> possibilities = new ArrayList<>();

        for (int x=-19; x<=19; x++) {
            for (int z=-19; z<=19; z++) {
                Block spawnBlock = world.getBlockAt(x, y, z);
                Block headBlock = world.getBlockAt(x, y+1, z);
                Block standBlock = world.getBlockAt(x, y-1, z);
                boolean supported = standBlock.getBlockData().isFaceSturdy(BlockFace.UP, BlockSupport.FULL);
                if (spawnBlock.isEmpty() && headBlock.isEmpty() && supported) {
                    Location location = new Location(world, x+0.5, y, z+0.5);
                    possibilities.add(location);
                }
            }
        }

        if (possibilities.isEmpty()) {
            return null;
        }
        int chosen = (int) (Math.random()*possibilities.size());
        return possibilities.get(chosen);
    }
}
