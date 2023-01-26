package eu.boyo.lobby;

import eu.boyo.lobby.LobbyCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyMain  {

    public static class PlayerJoinEvent implements Listener {
        @EventHandler
        public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
            Player player = event.getPlayer();

            LobbyCommand.joinLobby(player);
            Bukkit.getLogger().info(player.getName() + " Joined");
        }
    }

}
