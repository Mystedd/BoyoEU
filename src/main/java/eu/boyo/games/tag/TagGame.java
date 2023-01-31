package eu.boyo.games.tag;

import eu.boyo.BoyoEU;
import eu.boyo.games.BuildTools;
import eu.boyo.games.Game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.lang.reflect.Array;
import java.util.*;

import static org.bukkit.Bukkit.getServer;


public class TagGame extends Game {

    Timer timer = new Timer();

    static TagSettings settings;
    static Player tagger;

    public static class TagPvP implements Listener {
        @EventHandler
        public void playerTagged(EntityDamageByEntityEvent event) {
            if (tagger == event.getDamager()) {
                Entity entityHit = event.getEntity();
                if (entityHit.getType() == EntityType.PLAYER) {
                    tagger = (Player) entityHit;
                    tagger.sendMessage("You tagged " + entityHit.getName());
                    entityHit.sendMessage(tagger.name() + " tagged you.");
                }
            }
        }
    }

    public TagGame(ArrayList<Player> players) {
        getServer().getPluginManager().registerEvents(new TagGame.TagPvP(), BoyoEU.plugin);
        settings = new TagSettings(TagWeapon.DEFAULT, TagMode.DEFAULT, TagMap.DESERT, (byte) 15, (short) 300, true, true);
        // Create Game ID
        startGame(players);
    }

    private void startGame(ArrayList<Player> players) {

        Bukkit.getLogger().info("Tag Game Started");

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

        // Generate Map
        if (tagMap == TagMap.JUNGLE) {
            Bukkit.getLogger().info("Tag map is Jungle");
            BuildTools.clone(new Location(Bukkit.getWorld("TagMaps"), 100, 0, 0), new Location(Bukkit.getWorld("TagMaps"), 147, 31, 53), new Location(Bukkit.getWorld("TagMaps"), 0, 0, 0), Biome.JUNGLE);
        } else if (tagMap == TagMap.DESERT) {
            Bukkit.getLogger().info("Tag map is Desert");
            BuildTools.clone(new Location(Bukkit.getWorld("TagMaps"), 100, 0, 100), new Location(Bukkit.getWorld("TagMaps"), 147, 31, 53), new Location(Bukkit.getWorld("TagMaps"), 0, 0, 0), Biome.JUNGLE);
        } else if (tagMap == TagMap.CONSTRUCTION_SITE) {
            Bukkit.getLogger().info("Tag map is Construction Site");
            BuildTools.clone(new Location(Bukkit.getWorld("TagMaps"), 100, 0, -100), new Location(Bukkit.getWorld("TagMaps"), 147, 31, 53), new Location(Bukkit.getWorld("TagMaps"), 0, 0, 0), Biome.JUNGLE);
        }

        // End Grace Period
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endGrace(players);
            }
        }, settings.gracePeriod * 1000);

    }

    private void endGrace(ArrayList<Player> players) {

        Bukkit.getLogger().info("Tag Grace Period Ended");

        tagger = players.get(new Random().nextInt(players.size()));

        HashMap<Player, Float> taggedTime = new HashMap();
        for (Player player : players) {
            taggedTime.put(player, (float) 0);
        }

        for (Player player : players) {
            player.sendMessage(tagger.getName() + " is the tagger!");
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                taggedTime.replace(tagger, taggedTime.get(tagger) + (float) 0.001);
            }
        }, 1, 1);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopGame(players, taggedTime);
            }
        }, settings.gameLength * 1000);

    }

    private void stopGame(ArrayList<Player> players, HashMap<Player, Float> taggedTime) {

        Bukkit.getLogger().info("Tag Game Ended");

        timer.cancel();

        for (Player player : players) {
            player.sendTitle("Tag Game Ended!","",10,30,10);
            for (Map.Entry entry : taggedTime.entrySet()) {
                Player entryPlayer = (Player) entry.getKey();
                Float entryTime = (Float) entry.getValue();

                player.sendMessage(entryPlayer.getName() + ": " + entryTime + " Seconds.");
            }
        }

    }



}
