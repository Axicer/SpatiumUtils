package fr.axicer.SpatiumUtils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.axicer.SpatiumUtils.Utils.ConfigUtils;

public class PlayerLogin implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent ev){
		if(ConfigUtils.getbannedConfig().getStringList("banned").contains(ev.getPlayer().getName()) || ConfigUtils.getbannedUUIDConfig().getStringList("banned").contains(ev.getPlayer().getUniqueId().toString())){
			ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Tu es banni(e) !");
		}
		if(ConfigUtils.getMaintenanceWhiteListConfig().getBoolean("activated")){
			if(!ConfigUtils.getMaintenanceWhiteListConfig().getStringList("authorized").contains(ev.getPlayer().getName())){
				ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Le serveur est en maintenance !");
			}
		}
	}
	
}
