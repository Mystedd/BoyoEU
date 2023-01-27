package eu.boyo.games.tag;

import eu.boyo.games.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class TagGame extends Game {

    public TagGame(ArrayList<Player> players) {
        TagSettings settings = new TagSettings(TagWeapon.DEFAULT, TagMode.DEFAULT, TagMap.DESERT, (byte) 15, (short) 300, true, true);
        // Create Game ID
        startGame(settings, players);
    }

    public static void startGame(TagSettings settings, ArrayList<Player> players) {

        if (settings.map == TagMap.RANDOM) {
            int mapId = new Random().nextInt(0, 2);
        }
    }

    public static void stopGame(String gameId) {

    }



}
