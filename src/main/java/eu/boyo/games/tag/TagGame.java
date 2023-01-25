package eu.boyo.games.tag;

import eu.boyo.games.Game;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TagGame extends Game {

    public TagGame(ArrayList<Player> newPlayers) {
        TagSettings settings = new TagSettings(TagWeapon.BOW, TagMode.DEFAULT);
    }

}
