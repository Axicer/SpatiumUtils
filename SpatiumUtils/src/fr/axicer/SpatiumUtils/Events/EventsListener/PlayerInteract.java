package fr.axicer.SpatiumUtils.Events.EventsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

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
		// same problem
		try{
			if(ev.getAction() == Action.RIGHT_CLICK_AIR || ev.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(ev.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED+"Stick magique !")){
					
					Player player = ev.getPlayer();
					Vector vec = player.getLocation().getDirection();
					
					Firework fw = (Firework) player.getLocation().getWorld().spawn(player.getLocation(),Firework.class);
			        FireworkMeta fm = fw.getFireworkMeta();
			        Random r = new Random();
			        int fType = r.nextInt(5) + 1;
			        Type type = Type.BALL;
			        switch (fType) {
			        case 1:
			                type = Type.BALL;
			                break;
			        case 2:
			                type = Type.BALL_LARGE;
			                break;
			        case 3:
			                type = Type.BURST;
			                break;
			        case 4:
			                type = Type.CREEPER;
			                break;
			        case 5:
			                type = Type.STAR;
			                break;
			        }
				    int c1i = r.nextInt(17) + 1;
				    int c2i = r.nextInt(17) + 1;
				    Color c1 = getColor (c1i);
				    Color c2 = getColor (c2i);
				    FireworkEffect effect = FireworkEffect.builder()
				                    .flicker(r.nextBoolean()).withColor(c1).withFade(c2)
				                    .with(type).trail(r.nextBoolean()).build();
				    fm.addEffect(effect);
				    int power = r.nextInt(2)+1;
				    fm.setPower(power);
				    fw.setFireworkMeta(fm);
				    fw.setVelocity(new Vector(	vec.getX(),vec.getY(),vec.getZ()));
				    fw.detonate();
				}
			}
		}catch(Exception e){}
	}
	public Color getColor(int c){
	    switch (c){
		    default:
		    case 1:
		            return Color.AQUA;
		    case 2:
		            return Color.BLACK;
		    case 3:
		            return Color.BLUE;
		    case 4:
		            return Color.FUCHSIA;
		    case 5:
		            return Color.GRAY;
		    case 6:
		            return Color.GREEN;
		    case 7:
		            return Color.LIME;
		    case 8:
		            return Color.MAROON;
		    case 9:
		            return Color.NAVY;
		    case 10:
		            return Color.OLIVE;
		    case 11:
		            return Color.ORANGE;
		    case 12:
		            return Color.PURPLE;
		    case 13:
		            return Color.RED;
		    case 14:
		            return Color.SILVER;
		    case 15:
		            return Color.TEAL;
		    case 16:
		            return Color.WHITE;
		    case 17:
		            return Color.YELLOW;
	    }
	}
}
