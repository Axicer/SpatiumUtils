package fr.axicer.SpatiumUtils.Events.EventsListener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTarget implements Listener {
	
	private static ArrayList<Player> untargetedPlayers = new ArrayList<Player>();
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent ev){
		if(ev.getTarget() instanceof Player){
			Player target = (Player) ev.getTarget();
			if(getUntargetedPlayers().contains(target)){
				ev.setCancelled(true);
			}
		}
	}

	public static ArrayList<Player> getUntargetedPlayers() {
		return untargetedPlayers;
	}
}
