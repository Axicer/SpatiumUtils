package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;

public class PlayerJoin implements Listener {
	
	SpatiumUtils pl;
	
	public PlayerJoin(SpatiumUtils pl){
		this.pl = pl;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev){
		Player player = ev.getPlayer();
		ev.setJoinMessage(ChatUtils.getPluginPrefix()+player.getDisplayName()+ChatColor.GREEN+" a rejoint la partie !");
		if(!ev.getPlayer().hasPlayedBefore()){
			FileConfiguration config = pl.getConfig();
			ev.getPlayer().teleport(new Location(Bukkit.getWorld(config.getString("spawnpoint.world")),
												 config.getDouble("spawnpoint.x"),
												 config.getDouble("spawnpoint.y"),
												 config.getDouble("spawnpoint.z"),
												 config.getInt("spawnpoint.yaw"),
												 config.getInt("spawnpoint.pitch")
												)
			);
		}
		if(ConfigManager.getMoneyConfig().getInt("players."+ev.getPlayer().getUniqueId().toString()) == 0){
			ConfigManager.getMoneyConfig().set("players."+ev.getPlayer().getUniqueId().toString(), 0);
			ConfigManager.saveMoneyConfig();
		}
	}
}
