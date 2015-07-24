package fr.axicer.SpatiumUtils.Events.EventsListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class PlayerRespawn implements Listener {
	SpatiumUtils pl;
	
	public PlayerRespawn(SpatiumUtils pl) {
		this.pl = pl;
	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent ev){
		if(!ev.isBedSpawn()){
			FileConfiguration config = pl.getConfig();
			ev.setRespawnLocation(new Location(Bukkit.getWorld(config.getString("spawnpoint.world")),
											   config.getDouble("spawnpoint.x"),
											   config.getDouble("spawnpoint.y"),
											   config.getDouble("spawnpoint.z"),
											   config.getInt("spawnpoint.yaw"),
											   config.getInt("spawnpoint.pitch")
											   )
								 );
		}
	}
}
