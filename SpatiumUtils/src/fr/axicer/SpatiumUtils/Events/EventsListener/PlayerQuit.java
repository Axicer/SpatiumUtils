package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent ev){
		Player player = ev.getPlayer();
		ev.setQuitMessage(ChatUtils.getPluginPrefix()+player.getDisplayName()+ChatColor.RED+" a quitté la partie !");
	}
	
}
