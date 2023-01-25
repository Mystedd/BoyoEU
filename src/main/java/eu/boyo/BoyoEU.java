package eu.boyo;

import eu.boyo.lobby.LobbyCommand;
import eu.boyo.queues.EntityClickEvent;
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

        getServer().getPluginManager().registerEvents(new EntityClickEvent(), this);
        Bukkit.getLogger().info("BoyoEU plugin loaded");
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getLogger().info("BoyoEU plugin unloaded");
    }
}
