package fr.axicer.SpatiumUtils.Events;

import org.bukkit.plugin.PluginManager;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Events.events.EntityDamage;
import fr.axicer.SpatiumUtils.Events.events.EntityDamageByEntity;
import fr.axicer.SpatiumUtils.Events.events.PlayerChat;
import fr.axicer.SpatiumUtils.Events.events.PlayerCommandPreProcess;
import fr.axicer.SpatiumUtils.Events.events.PlayerJoin;
import fr.axicer.SpatiumUtils.Events.events.PlayerLogin;
import fr.axicer.SpatiumUtils.Events.events.PlayerMove;
import fr.axicer.SpatiumUtils.Events.events.PlayerQuit;
import fr.axicer.SpatiumUtils.Events.events.ServerListPing;

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
		pm.registerEvents(new EntityDamage(), sp);
	}
}
