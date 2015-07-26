package fr.axicer.SpatiumUtils.Events.EventsListener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev){
		if(ev.getAction() == Action.RIGHT_CLICK_BLOCK){
			try{ // needed because sometimes, getItemMeta() can be null (fast clicking) so we need to remove nullPointerException to log
				if(ev.getItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE+""+ChatColor.ITALIC+"Gravity Gun")){
					ItemStack gravitygun = ev.getItem();
					if(gravitygun.getItemMeta().getLore().get(0).equals("")){
						ItemMeta gravitygunMeta = gravitygun.getItemMeta();
						Block block = ev.getClickedBlock();
						Location targettedBlock = ev.getClickedBlock().getLocation();
						List<String> lores = gravitygunMeta.getLore();
						lores.set(0, block.getType().toString());
						lores.set(1, String.valueOf(block.getData()));
						gravitygunMeta.setLore(lores);
						gravitygun.setItemMeta(gravitygunMeta);
						targettedBlock.getBlock().setType(Material.AIR);
					}else{
						Location targetLocation = ev.getClickedBlock().getLocation();
						while(targetLocation.getBlock().getType() != Material.AIR){
							targetLocation = targetLocation.add(0, 1, 0);
						}
						ArrayList<String> materialsString = new ArrayList<String>();
						for(Material mat : Material.values()){
							materialsString.add(mat.toString());
						}
						ArrayList<String> entityTypesString = new ArrayList<String>();
						for(EntityType type : EntityType.values()){
							entityTypesString.add(type.toString());
						}
						if(materialsString.contains(ev.getItem().getItemMeta().getLore().get(0))){
							Material blocType = Material.valueOf(ev.getItem().getItemMeta().getLore().get(0));
							targetLocation.getBlock().setType(blocType);
							targetLocation.getBlock().setData(Byte.valueOf(ev.getItem().getItemMeta().getLore().get(1)));
							ItemMeta gravityGunMeta = gravitygun.getItemMeta();
							List<String> lores = gravityGunMeta.getLore();
							lores.set(0, "");
							lores.set(1, "");
							gravityGunMeta.setLore(lores);
							gravitygun.setItemMeta(gravityGunMeta);
						}else if(entityTypesString.contains(ev.getItem().getItemMeta().getLore().get(0))){
							EntityType type = EntityType.valueOf(ev.getItem().getItemMeta().getLore().get(0));
							targetLocation.getWorld().spawnEntity(targetLocation, type);
							ItemMeta gravityGunMeta = gravitygun.getItemMeta();
							List<String> lores = gravityGunMeta.getLore();
							lores.set(0, "");
							lores.set(1, "");
							gravityGunMeta.setLore(lores);
							gravitygun.setItemMeta(gravityGunMeta);
						}else{
							Bukkit.getLogger().severe("error in gravitygun type !");
						}
						
					}
				}
			}catch(NullPointerException e){}
		}
	}
}
