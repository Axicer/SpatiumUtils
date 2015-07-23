package fr.axicer.SpatiumUtils.Events;

import org.bukkit.plugin.PluginManager;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Events.EventsListener.EntityDamage;
import fr.axicer.SpatiumUtils.Events.EventsListener.EntityDamageByEntity;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerChat;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerCommandPreProcess;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerInteract;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerJoin;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerLogin;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerMove;
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerQuit;
import fr.axicer.SpatiumUtils.Events.EventsListener.ServerListPing;

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
		pm.registerEvents(new PlayerInteract(), sp);
	}
}
