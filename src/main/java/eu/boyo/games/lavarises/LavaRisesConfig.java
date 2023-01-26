package eu.boyo.games.lavarises;

import java.util.ArrayList;

public class LavaRisesConfig {

    ArrayList<Integer> levels = new ArrayList<>();
    LavaRisesTheme theme;
    byte lavaSpeed = 3;
    short PVPGracePeriod = 30;
    short lavaGracePeriod = 30;
    byte startingLives = 5;
    byte respawnTime = 3;

    public LavaRisesConfig() {
        // add default levels
        levels.add(1);
        levels.add(2);
        levels.add(3);
        levels.add(4);
        levels.add(5);
        // randomise theme
        LavaRisesTheme[] themes = LavaRisesTheme.values();
        byte choice = (byte) (Math.random() * themes.length);
        theme =  themes[choice];
    }

    public void setTheme(LavaRisesTheme newTheme) {
        theme = newTheme;
    }

    public void setLavaSpeed(byte newSpeed) {
        lavaSpeed = newSpeed;
    }

    public void setPVPGracePeriod(short newPeriod) {
        PVPGracePeriod = newPeriod;
    }

    public void setLavaGracePeriod(short newPeriod) {
        lavaGracePeriod = newPeriod;
    }

    public void setStartingLives(byte newLives) {
        startingLives = newLives;
    }

    public void setRespawnTime(byte newTime) {
        respawnTime = newTime;
    }
}
