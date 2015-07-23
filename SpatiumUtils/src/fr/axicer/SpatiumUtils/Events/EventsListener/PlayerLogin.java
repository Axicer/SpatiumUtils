package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;

public class PlayerLogin implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent ev){
		if(ConfigManager.getbannedPlayerConfig().getStringList("banned").contains(ev.getPlayer().getName()) || ConfigManager.getbannedUUIDPlayerConfig().getStringList("banned").contains(ev.getPlayer().getUniqueId().toString())){
			ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Tu es banni(e) !");
		}
		if(ConfigManager.getMaintenanceConfig().getBoolean("activated")){
			if(!ConfigManager.getMaintenanceConfig().getStringList("authorized").contains(ev.getPlayer().getName())){
				ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Le serveur est en maintenance !");
			}
		}
	}
	
}
