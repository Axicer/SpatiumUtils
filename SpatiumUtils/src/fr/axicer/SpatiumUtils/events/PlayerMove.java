package fr.axicer.SpatiumUtils.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMove implements Listener {
	
	private static ArrayList<Player> moonGravity = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev){
		if(moonGravity.contains(ev.getPlayer())){
			if(ev.getFrom().getY() < ev.getTo().getY()){
				if(ev.getFrom().subtract(0, 1, 0).getBlock().getType() != Material.AIR){
					ev.getPlayer().setVelocity(new Vector(ev.getPlayer().getVelocity().getX()*1.1,
														  ev.getPlayer().getVelocity().getY()*1.3,
														  ev.getPlayer().getVelocity().getZ()*1.1
														 )
											  );
				}
			}
			if(ev.getFrom().getY() > ev.getTo().getY()){
				ev.getPlayer().setVelocity(new Vector(ev.getPlayer().getVelocity().getX()*1.1,
													  ev.getPlayer().getVelocity().getY()*0.5,
													  ev.getPlayer().getVelocity().getZ()*1.1
													 )
										  );
			}
		}
	}

	public static ArrayList<Player> getMoonGravityPlayers() {
		return moonGravity;
	}
}
