package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class EntityExplode implements Listener {
	
	SpatiumUtils pl;
	
	public EntityExplode(SpatiumUtils pl) {
		this.pl = pl;
	}
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent ev){
		if(ev.getEntity() instanceof Creeper){
			if(!pl.getConfig().getBoolean("creeperDamage")){
				Location loc = ev.getLocation();
				ev.setCancelled(true);
				loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 4, false, false);
			}
		}
	}
}
