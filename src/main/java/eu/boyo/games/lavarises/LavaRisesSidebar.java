package eu.boyo.games.lavarises;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class LavaRisesSidebar {

    Objective objective;
    String livesLine;
    String playersLine;

    public LavaRisesSidebar() {
        setLives(5);
        setPlayers(7);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("LavaRises", "dummy", "Lava Rises");

        objective.setDisplayName("§c-----§6§lLava Rises§r-----");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("").setScore(3);
        objective.getScore(livesLine).setScore(2);
        objective.getScore(playersLine).setScore(1);
    }

    public void setLives(int lives) {
        livesLine = String.format("§2§l%d §a§rLives Left", lives);
    }

    public void setPlayers(int players) {
        playersLine = String.format("§5§l%d §d§rPlayers Alive", players);
    }
}
