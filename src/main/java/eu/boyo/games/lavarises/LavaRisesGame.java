package eu.boyo.games.lavarises;

import eu.boyo.BoyoEU;
import eu.boyo.games.ActiveGames;
import eu.boyo.games.Game;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class LavaRisesGame extends Game {

    public class PlayerDeathEvent implements Listener {
        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event) {
            Player player = event.getPlayer();
            if (players.containsKey(player)) {
                int y = player.getLocation().getBlockY();

                if (y <= lavaLevel) {
                    playerLosesLife(player);
                }
            }
        }
    }

    static World world = Bukkit.getWorld("lrs_overworld");
    LavaRisesMap map = new LavaRisesMap();
    HashMap<Player, Integer> players = new HashMap<>();
    int lavaLevel = -61;
    public boolean isActive;

    public LavaRisesGame(ArrayList<Player> newPlayers) {
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), BoyoEU.plugin);
        for (Player player : newPlayers) {
            players.put(player, 5);
        }
        map.generateMap();
        startGame();
        isActive = true;
    }

    private void startGame() {
        world.setPVP(false);
        for (Player player : players.keySet()) {
            player.setGameMode(GameMode.ADVENTURE);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.getInventory().clear();
            player.getInventory().setArmorContents(new ItemStack[4]);
            player.teleport(map.getInitialSpawnPos());
        }

        // begin the game in 30 secs
        getServer().getScheduler().runTaskLater(BoyoEU.plugin, new Runnable(){
            public void run(){
                beginGame();
            }
        },600L);
    }

    private void beginGame() {
        if (!isActive) {
            return;
        }

        world.setPVP(true);
        // "Begin Game!" title
        for (Player player : players.keySet()) player.sendTitle("§6Begin Game!", "", 10, 40, 10);
        raiseLava();
    }

    private void raiseLava() {
        if (!isActive) {
            return;
        }

        lavaLevel++;
        map.placeLava(lavaLevel);

        // check that no players have been covered by the lava
        for (Player player : players.keySet()) {
            int y = player.getLocation().getBlockY();
            if (y <= lavaLevel) {
                playerLosesLife(player);
            }
        }

        // raise the lava again in 3 secs
        getServer().getScheduler().runTaskLater(BoyoEU.plugin, new Runnable() {
            public void run() {
                raiseLava();
            }
        }, 60L);
    }

    private void playerLosesLife(Player player) {
        if (!isActive) {
            return;
        }

        int livesLeft = players.get(player);
        if (livesLeft <= 0) {
            return;
        }

        player.setFireTicks(0);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setInvisible(true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 255, false, false));

        player.teleport(map.getSpectatorSpawnPos(lavaLevel));

        livesLeft--;
        players.put(player, -livesLeft); // negative lives signify respawning

        if (livesLeft == 0) {
            playerDies(player);
            return;
        }
        player.sendTitle("§4You died!", String.format("§6%d lives remaining", livesLeft), 10, 40, 10);

        getServer().getScheduler().runTaskLater(BoyoEU.plugin, new Runnable(){
            public void run(){
                playerRespawns(player);
            }
        },60L);
    }

    private void playerRespawns(Player player) {
        players.put(player, -(players.get(player))); // make lives left positive again

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setInvisible(false);
        player.removePotionEffect(PotionEffectType.WEAKNESS);

        Location spawnPos = map.getRespawnSpawnPos(lavaLevel+10);
        player.teleport(spawnPos);
    }

    private void playerDies(Player deadPlayer) {
        deadPlayer.sendTitle("§4You died!", "§6No lives remaining", 10, 40, 10);
        // check if 1+ players are still alive
        int playersLeft = 0;
        String alivePlayerName = null;
        for (Player player : players.keySet()) {
            int lives = players.get(player);
            if (lives != 0) {
                playersLeft++;
                alivePlayerName = player.getName();
                if (playersLeft >= 2) break;
            }
        }
        if (playersLeft <= 1) {
            gameOver(alivePlayerName);
        }
    }

    private void gameOver(String winnerName) {
        isActive = false;

        for (Player player : players.keySet()) {
            player.sendTitle("§4Game Over!", "§a"+winnerName+" won", 10, 40, 10);
            if (players.get(player) > 0) {
                winnerName = player.getName();
            }
        }

        // teleport back to lobby 5 secs later
        getServer().getScheduler().runTaskLater(BoyoEU.plugin, new Runnable(){
            public void run(){
                killGame();
            }
        },100L);
    }

    private void killGame() {
        World lobby = Bukkit.getWorld("lobby");
        Location spawnPos = new Location(lobby,-36, 66, -13, -90, 0);
        for (Player player : players.keySet()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setInvisible(false);
            player.removePotionEffect(PotionEffectType.WEAKNESS);
            player.teleport(spawnPos);
        }

        map.remove();
        ActiveGames.killGame(this);
    }
}
