package fr.axicer.SpatiumUtils.Events.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {
	@EventHandler
	public void onEntityTakeDamage(EntityDamageEvent ev){
		if(ev.getEntity() instanceof Player){
			Player player = (Player) ev.getEntity();
			if(PlayerMove.getMoonGravityPlayers().contains(player)){
				if(ev.getCause() == DamageCause.FALL){
					ev.setCancelled(true);
				}
			}
		}
	}
}
