package eu.boyo;

import eu.boyo.games.tag.TagGame;
import eu.boyo.lobby.LobbyCommand;
import eu.boyo.queues.ForceStartCommand;
import eu.boyo.queues.QueueCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BoyoEU extends JavaPlugin {

    public static BoyoEU plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Commands
        getCommand("queue").setExecutor(new QueueCommand());
        getCommand("forcestart").setExecutor(new ForceStartCommand());
        getCommand("lobby").setExecutor(new LobbyCommand());

        // Events
        getServer().getPluginManager().registerEvents(new eu.boyo.queues.EntityClickEvent(), this);
        getServer().getPluginManager().registerEvents(new eu.boyo.lobby.LobbyMain.PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new eu.boyo.games.duels.BlockClickEvent(), this);
        getServer().getPluginManager().registerEvents(new eu.boyo.games.duels.solo.random.EntityClickEvent(), this);
        getServer().getPluginManager().registerEvents(new eu.boyo.games.duels.solo.random.ModeGUI.ClickEvent(), this);

        Bukkit.getLogger().info("BoyoEU plugin loaded");
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getLogger().info("BoyoEU plugin unloaded");
    }
}
