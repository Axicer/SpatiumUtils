package fr.axicer.SpatiumUtils.events;

import org.bukkit.plugin.PluginManager;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class EventManager {
	
	public static void registersEvents(SpatiumUtils sp){
		PluginManager pm = sp.getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(), sp);
		pm.registerEvents(new PlayerQuit(), sp);
		pm.registerEvents(new PlayerLogin(), sp);
		pm.registerEvents(new PlayerChat(), sp);
		pm.registerEvents(new ServerListPing(), sp);
		pm.registerEvents(new PlayerCommandPreProcess(sp), sp);
		pm.registerEvents(new EntityDamageByEntity(), sp);
		pm.registerEvents(new PlayerMove(), sp);
	}
}
