package eu.boyo;

import eu.boyo.lobby.LobbyCommand;
import eu.boyo.queues.ForceStartCommand;
import eu.boyo.queues.QueueCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class BoyoEU extends JavaPlugin {

    public static BoyoEU plugin;
    private FileConfiguration duelsItemPowersConfig;

    @Override
    public void onEnable() {
        plugin = this;

        // config
        createDuelsItemPowersConfig();

        // Commands
        getCommand("queue").setExecutor(new QueueCommand());
        getCommand("forcestart").setExecutor(new ForceStartCommand());
        getCommand("lobby").setExecutor(new LobbyCommand());

        // Events
        getServer().getPluginManager().registerEvents(new eu.boyo.queues.EntityClickEvent(), this);
        getServer().getPluginManager().registerEvents(new eu.boyo.lobby.LobbyMain.PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new eu.boyo.games.duels.QueueClickEvent(), this);

        Bukkit.getLogger().info("BoyoEU plugin loaded");
    }

    @Override
    public void onDisable() {
        plugin = null;
        Bukkit.getLogger().info("BoyoEU plugin unloaded");
    }

    public FileConfiguration getDuelsItemPowersConfig() {
        return duelsItemPowersConfig;
    }

    private void createDuelsItemPowersConfig() {
        File duelsItemPowersConfigFile = new File(getDataFolder(), "item_powers.yml");
        if (!duelsItemPowersConfigFile.exists()) {
            duelsItemPowersConfigFile.getParentFile().mkdirs();
            saveResource("item_powers.yml", false);
        }

        duelsItemPowersConfig = new YamlConfiguration();
        try {
            duelsItemPowersConfig.load(duelsItemPowersConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
