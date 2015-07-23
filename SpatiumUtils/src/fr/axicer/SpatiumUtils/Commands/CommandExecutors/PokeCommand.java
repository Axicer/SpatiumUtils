package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class PokeCommand implements CommandExecutor {
	
	private ArrayList<Player> poked = new ArrayList<Player>();
	
	SpatiumUtils pl;
	
	public PokeCommand(SpatiumUtils pl) {
		this.pl = pl;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(Vault.getPermissions().has(sender, "spatium.poke") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				final Player p = (Player) sender;
				if(poked.contains(p)){
					p.sendMessage(ChatColor.RED+"Tu doit attendre 5 secondes avant de pouvoir réenvoyer un poke");
				}else{
					if(args.length != 1){
						if(args.length == 2){
							Player player = null;
							Float level = 0F;
							try{
								player = Bukkit.getServer().getPlayer(args[0]);
								level = Float.parseFloat(args[1]+"F");
							}catch(Exception e){}
						
							if(level == 0 || level < 0 || level > 2 || level == null){
								sender.sendMessage("la valeur entrée pour l'intensité est incorrect");
								sender.sendMessage(ChatColor.RED+"L'intensité est de 0.1 a 2");
							}else{
								if(player == null){
									sender.sendMessage(ChatColor.RED+"Le joueur n'as pas été trouvé !");
								}else{
									player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1F, level);
									player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+sender.getName()+ChatColor.GOLD+" Vous a poké !");
									sender.sendMessage(ChatColor.GRAY+"Poke lancé !");
									if(!p.isOp() && !Vault.getPermissions().has(sender, "spatium.poke.nocountdown") && !Vault.getPermissions().has(sender, "spatium.*")){
										poked.add(p);
										Bukkit.getScheduler().runTaskLater(pl, new BukkitRunnable() {
											@Override
											public void run() {
												poked.remove(p);
											}
										}, 100);
									}
								}
							}
						}else{
							sender.sendMessage(ChatColor.RED+"usage: /poke [player] (intensité)");
							sender.sendMessage(ChatColor.RED+"L'intensité est de 0.1 a 2");
						}
					}else{
						Player player = null;
						try{
							player = Bukkit.getServer().getPlayer(args[0]);
						}catch(Exception e){}
						if(player == null){
							sender.sendMessage(ChatColor.RED+"Le joueur n'as pas été trouvé !");
						}else{
							player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
							player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+sender.getName()+ChatColor.GOLD+" Vous a poké !");
							sender.sendMessage(ChatColor.GRAY+"Poke lancé !");
							if(!p.isOp() && !Vault.getPermissions().has(sender, "spatium.poke.nocountdown") && !Vault.getPermissions().has(sender, "spatium.*")){
								poked.add(p);
								Bukkit.getScheduler().runTaskLater(pl, new BukkitRunnable() {
									@Override
									public void run() {
										poked.remove(p);
									}
								}, 100);
							}
						}
					}
				}
				
			}else{
				if(args.length != 1){
					if(args.length == 2){
						Player player = null;
						Float level = 0F;
						try{
							player = Bukkit.getServer().getPlayer(args[0]);
							level = Float.parseFloat(args[1]+"F");
						}catch(Exception e){}
						
						if(level == 0 || level < 0 || level > 2 || level == null){
							sender.sendMessage("la valeur entrée pour l'intensité est incorrect");
							sender.sendMessage(ChatColor.RED+"L'intensité est de 0.1 a 2");
						}else{
							if(player == null){
								sender.sendMessage(ChatColor.RED+"Le joueur n'as pas été trouvé !");
							}else{
								player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1F, level);
								player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+sender.getName()+ChatColor.GOLD+" Vous a poké !");
								sender.sendMessage(ChatColor.GRAY+"Poke lancé !");
						}
					}
					}else{
						sender.sendMessage(ChatColor.RED+"usage: /poke [player] (intensité)");
						sender.sendMessage(ChatColor.RED+"L'intensité est de 0.1 a 2");
					}
				}else{
					Player player = null;
					try{
						player = Bukkit.getServer().getPlayer(args[0]);
					}catch(Exception e){}
					if(player == null){
						sender.sendMessage(ChatColor.RED+"Le joueur n'as pas été trouvé !");
					}else{
						player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
						player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+sender.getName()+ChatColor.GOLD+" Vous a poké !");
						sender.sendMessage(ChatColor.GRAY+"Poke lancé !");
					}
				}
			}
		}
		return true;
	}

}
