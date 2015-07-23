package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent ev){
		if(ConfigManager.getMutedPlayerConfig().getStringList("muted").contains(ev.getPlayer().getName())){
			ev.setCancelled(true);
			ev.getPlayer().sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu es mute, tu ne peux pas parler !");
		}
	}
}
