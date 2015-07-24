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
import fr.axicer.SpatiumUtils.Events.EventsListener.PlayerRespawn;
import fr.axicer.SpatiumUtils.Events.EventsListener.ServerListPing;

public class EventManager {
	
	public static void registersEvents(SpatiumUtils pl){
		PluginManager pm = pl.getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(), pl);
		pm.registerEvents(new PlayerQuit(), pl);
		pm.registerEvents(new PlayerLogin(), pl);
		pm.registerEvents(new PlayerChat(), pl);
		pm.registerEvents(new ServerListPing(), pl);
		pm.registerEvents(new PlayerCommandPreProcess(pl), pl);
		pm.registerEvents(new EntityDamageByEntity(), pl);
		pm.registerEvents(new PlayerMove(), pl);
		pm.registerEvents(new EntityDamage(), pl);
		pm.registerEvents(new PlayerInteract(), pl);
		pm.registerEvents(new PlayerRespawn(pl), pl);
	}
}
