package fr.axicer.SpatiumUtils.Events.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.axicer.SpatiumUtils.Utils.ConfigUtils;

public class PlayerLogin implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent ev){
		if(ConfigUtils.getbannedPlayerConfig().getStringList("banned").contains(ev.getPlayer().getName()) || ConfigUtils.getbannedUUIDPlayerConfig().getStringList("banned").contains(ev.getPlayer().getUniqueId().toString())){
			ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Tu es banni(e) !");
		}
		if(ConfigUtils.getMaintenanceConfig().getBoolean("activated")){
			if(!ConfigUtils.getMaintenanceConfig().getStringList("authorized").contains(ev.getPlayer().getName())){
				ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Le serveur est en maintenance !");
			}
		}
	}
	
}
