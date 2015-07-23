package fr.axicer.SpatiumUtils.Events.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent ev){
		if(!(ev.getEntity() instanceof Player)){
			if(ev.getDamager() instanceof Player){
				ev.setDamage(Integer.MAX_VALUE);
			}
		}
	}
}
