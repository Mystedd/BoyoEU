package eu.boyo.games.lavarises;

import eu.boyo.BoyoEU;
import eu.boyo.games.Game;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

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
    HashMap<Player, Integer> players = new HashMap<>();
    int lavaLevel = -61;
    ArrayList<HashMap<Material, Material>> themes = new ArrayList<>();
    boolean isActive;

    public LavaRisesGame(ArrayList<Player> newPlayers) {
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), BoyoEU.plugin);
        for (Player player : newPlayers) {
            players.put(player, 5);
        }
        createThemes();
        generateMap();
        startGame();
        isActive = true;
    }

    private void createThemes() {
        HashMap<Material, Material> theme1 = new HashMap<>();
        // testing theme
        theme1.put(Material.GRASS_BLOCK, Material.ACACIA_PLANKS);
        theme1.put(Material.OAK_TRAPDOOR, Material.BIRCH_TRAPDOOR);
        themes.add(theme1);
    }

    private void generateMap() {
        // get random theme
        int themeNum = (int) (Math.random() * themes.size());
        HashMap<Material, Material> theme = themes.get(themeNum);
        Bukkit.broadcastMessage(String.valueOf(theme));

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
            clone(tx, ty, tz, tx+40, ty+19, tz+40, dx, dy, dz, theme);
        }
    }

    private void clone(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int z, HashMap<Material, Material> theme) {
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

    private void startGame() {
        world.setPVP(false);
        Location spawnPos = new Location(world,0,-52,0);

        for (Player player : players.keySet()) {
            player.teleport(spawnPos);
        }
        // begin the game in 30 secs
        getServer().getScheduler().runTaskLater(BoyoEU.plugin, new Runnable(){
            public void run(){
                beginGame();
            }
        },600L);
    }

    private void beginGame() {
        world.setPVP(true);
        // "Begin Game!" title
        for (Player player : players.keySet()) player.sendTitle("Begin Game!", "", 10, 40, 10);
        raiseLava();
    }

    private void raiseLava() {
        lavaLevel++;
        for (int x=-19; x<=19; x++) {
            for (int z=-19; z<=19; z++) {
                Block block = world.getBlockAt(x, lavaLevel, z);
                if (!block.getType().isSolid()) {
                    block.setType(Material.LAVA);
                }
            }
        }
        // raise the lava again in 3 secs
        if (isActive) {
            getServer().getScheduler().runTaskLater(BoyoEU.plugin, new Runnable(){
                public void run(){
                    raiseLava();
                }
            }, 60L);
        }
    }

    private void playerLosesLife(Player player) {
        int livesLeft = players.get(player);
        if (livesLeft == 0) {
            return;
        }

        livesLeft--;
        players.put(player, livesLeft);
        if (livesLeft == 0) {
            playerDies(player);
            return;
        }
        player.sendTitle("§4You died!", String.format("§6%d lives remaining", livesLeft), 10, 40, 10);
        player.teleport(new Location(world, 0, -52, 0));
    }

    private void playerDies(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(new Location(world, 0, lavaLevel+10, 0));
        player.sendTitle("§4You died!", "§6No lives remaining", 10, 40, 10);
    }
}
