package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev){
		Player player = ev.getPlayer();
		ev.setJoinMessage(ChatUtils.getPluginPrefix()+player.getDisplayName()+ChatColor.GREEN+" a rejoint la partie !");
	}
	
}
