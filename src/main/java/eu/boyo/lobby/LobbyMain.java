package eu.boyo.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LobbyMain  {

    public static class PlayerJoinEvent implements Listener {
        @EventHandler
        public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
            Player player = event.getPlayer();

            new LobbyCommand().joinLobby(player);
            Bukkit.getLogger().info(player.getName() + " Joined");
        }
    }

}
