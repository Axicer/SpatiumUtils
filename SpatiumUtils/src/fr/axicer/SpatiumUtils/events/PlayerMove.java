package fr.axicer.SpatiumUtils.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
	
	private static ArrayList<Player> moonGravity = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev){
		if(moonGravity.contains(ev.getPlayer())){
			if(ev.getTo().subtract(0, 1, 0).getBlock().getType() != Material.AIR){
				if(ev.getFrom().getY() < ev.getTo().getY()){
					ev.getPlayer().getVelocity().multiply(1.5);
				}
				if(ev.getFrom().getY() > ev.getTo().getY()){
					ev.getPlayer().getVelocity().multiply(0.3);
				}
			}
		}
	}

	public static ArrayList<Player> getMoonGravityPlayers() {
		return moonGravity;
	}
}
