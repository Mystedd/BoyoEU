package eu.boyo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BoyoEU extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("BoyoEU plugin loaded");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("BoyoEU plugin unloaded");
    }
}
