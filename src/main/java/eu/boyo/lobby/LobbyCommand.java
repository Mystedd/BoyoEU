package eu.boyo.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Location spawnPos = new Location(Bukkit.getWorld("lobby"), 0, 65, 0, -90, 0 );
        try {
            return true;
        } catch (Exception error) {
            return false;
        }

    }

}
