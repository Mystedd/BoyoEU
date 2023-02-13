package eu.boyo;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;

public class BuildTools {

    public static void clone(Location minCorner, Location maxCorner, Location newCorner, Biome newBiome) {

        // tx = lower northwestern coordinate of target
        // sx = length/depth/height of target
        // dx = lower northwestern coordinate of destination

        World world = newCorner.getWorld();
        for (int tx=minCorner.getBlockX(), dx=newCorner.getBlockX(); tx<=maxCorner.getBlockX(); tx++, dx++) {
            for (int ty=minCorner.getBlockY(), dy=newCorner.getBlockY(); ty<=maxCorner.getBlockY(); ty++, dy++) {
                for (int tz=minCorner.getBlockZ(), dz=newCorner.getBlockZ(); tz<=maxCorner.getBlockZ(); tz++, dz++) {
                    Location target = new Location(world, tx, ty, tz);
                    Location destination = new Location(world, dx, dy, dz);

                    world.setBiome(destination, newBiome);

                    BlockState data = world.getBlockState(target);
                    world.setBlockData(destination, data.getBlockData());
                }
            }
        }
    }
}
