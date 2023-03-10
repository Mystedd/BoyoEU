package eu.boyo.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class LobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        if (!(player == null)) {
            joinLobby(player);
        } else {
            Bukkit.getLogger().info("You have to be a player to execute this command!");
            return false;
        }
        return true;
    }

    public static void joinLobby(Player player) {

        // Teleport Player
        Location spawnPos = new Location(Bukkit.getWorld("lobby"), 0, 65, 0, -90, 0 );
        player.teleport(spawnPos);

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setInvisible(false);

        // Remove potion effects
        Collection<PotionEffect> effects = player.getActivePotionEffects();
        for (PotionEffect effect : effects) {
            player.removePotionEffect(effect.getType());
        }
    }
}
