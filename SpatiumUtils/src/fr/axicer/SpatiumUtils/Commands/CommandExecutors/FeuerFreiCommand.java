package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class FeuerFreiCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.feuerfrei") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				Player target = null;
				try{
					target = Bukkit.getPlayer(args[0]);
				}catch(Exception ex){}
				if(target != null){
					target.setFireTicks(100);
					target.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"FEUER FREI ! BANG BANG !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le joueur est en feu !");
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur "+ChatColor.GOLD+args[0]+ChatColor.RED+" est introuvable !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/feuerfrei (player)"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
