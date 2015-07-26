package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent ev){
		if(!(ev.getEntity() instanceof Player)){
			if(ev.getDamager() instanceof Player){
				Player player = (Player) ev.getDamager();
				if(player.getGameMode() == GameMode.CREATIVE){
					ev.setDamage(Integer.MAX_VALUE);
				}
			}
		}
	}
}
