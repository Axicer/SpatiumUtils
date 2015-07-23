package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;

public class ServerListPing implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onServerListPingEvent(ServerListPingEvent ev){
		if(ConfigManager.getMaintenanceConfig().getBoolean("activated")){
			ev.setMotd(ConfigManager.getMaintenanceConfig().getString("maintenanceMOTD"));
		}
	}
}
