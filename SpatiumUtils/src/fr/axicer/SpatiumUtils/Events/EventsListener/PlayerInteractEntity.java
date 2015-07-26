package fr.axicer.SpatiumUtils.Events.EventsListener;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractEntity implements Listener {
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent ev){
		if(!(ev.getRightClicked() instanceof Player)){
			try{
				if(ev.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE+""+ChatColor.ITALIC+"Gravity Gun")){
					ItemStack gravitygun = ev.getPlayer().getItemInHand();
					EntityType entityType = ev.getRightClicked().getType();
					ItemMeta gravitygunMeta = gravitygun.getItemMeta();
					ev.setCancelled(true);
					if(gravitygunMeta.getLore().get(0).equals("")){
						List<String> lores = gravitygunMeta.getLore();
						lores.set(0, entityType.toString());
						gravitygunMeta.setLore(lores);
						gravitygun.setItemMeta(gravitygunMeta);
						ev.getRightClicked().teleport(new Location(ev.getRightClicked().getLocation().getWorld(), 0, 0, 0));
					}
				}
			}catch(NullPointerException e){}
		}
	}
}
