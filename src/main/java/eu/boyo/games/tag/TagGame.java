package eu.boyo.games.tag;

import eu.boyo.games.BuildTools;
import eu.boyo.games.Game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class TagGame extends Game {

    public TagGame(ArrayList<Player> players) {
        TagSettings settings = new TagSettings(TagWeapon.DEFAULT, TagMode.DEFAULT, TagMap.DESERT, (byte) 15, (short) 300, true, true);
        // Create Game ID
        startGame(settings, players);
    }

    public static void startGame(TagSettings settings, ArrayList<Player> players) {

        for (Player player : players) {
            player.sendMessage("Game is starting!");
        }

        // Set Map
        TagMap tagMap = settings.map;
        if (tagMap == TagMap.RANDOM) {
            int mapId = new Random().nextInt(2);
            TagMap[] tagMaps = TagMap.values();
            for (TagMap map : tagMaps) {
                if (map.ordinal() == mapId) {
                    break;
                }
            }
        }




        // Generate Map And Teleport Players
        if (tagMap == TagMap.JUNGLE) {
            Bukkit.getLogger().info("Tag map is Jungle");
            BuildTools.clone(new Location(Bukkit.getWorld("TagMaps"), 100, 0, 0), new Location(Bukkit.getWorld("TagMaps"), 147, 31, 53), new Location(Bukkit.getWorld("TagMaps"), 0, 0, 0), Biome.JUNGLE);
        } else if (tagMap == TagMap.DESERT) {
            Bukkit.getLogger().info("Tag map is Desert");
        } else if (tagMap == TagMap.CONSTRUCTION_SITE) {
            Bukkit.getLogger().info("Tag map is Construction Site");
        }

    }

    public static void stopGame(String gameId) {

    }



}
