package fr.axicer.SpatiumUtils.Events.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;

public class PlayerCommandPreProcess implements Listener {
	
	SpatiumUtils pl;
	
	public PlayerCommandPreProcess(SpatiumUtils pl){
		this.pl = pl;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent ev){
		if(ev.getMessage().startsWith("/op") || ev.getMessage().startsWith("/deop")){
			if(!pl.getConfig().getStringList("OPAuth").contains(ev.getPlayer().getName())){
				ev.setCancelled(true);
				ev.getPlayer().sendMessage(ChatColor.RED+"Commande interdite !");
			}
		}
		String commande = ev.getMessage();
		Player player = ev.getPlayer();
		if(commande.startsWith("/ban")){
			ev.setCancelled(true);
			player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Commande modifié !");
			player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Utiliser la commande "+ChatColor.GOLD+"/sban (player)"+ChatColor.GREEN+" ou "+ChatColor.GOLD+"/sbanuuid (player)"+ChatColor.GREEN+".");
		}
		if(commande.startsWith("/pardon")){
			ev.setCancelled(true);
			player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Commande modifié !");
			player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Utiliser la commande "+ChatColor.GOLD+"/sunban (player)"+ChatColor.GREEN+" ou "+ChatColor.GOLD+"/sunbanuuid (player)"+ChatColor.GREEN+".");
		}
		if(commande.startsWith("/kick")){
			ev.setCancelled(true);
			player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Commande modifié !");
			player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Utiliser la commande "+ChatColor.GOLD+"/skick (player)"+ChatColor.GREEN+".");
		}
	}
}
