package eu.boyo.games.tag;

import eu.boyo.games.Game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class TagGame extends Game {

    public TagGame(ArrayList<Player> players) {
        TagSettings settings = new TagSettings(TagWeapon.DEFAULT, TagMode.DEFAULT);
        startGame(settings, players);
    }

    public static UUID startGame(TagSettings settings, ArrayList<Player> players) {
        UUID gameId = UUID.randomUUID();
        return gameId;

        // Choose map


    }

    public static void stopGame(int gameId) {

    }



}
