package fr.axicer.SpatiumUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class BifleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.bifle") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				Player target = null;
				try{
					target = Bukkit.getPlayer(args[0]);
				}catch(Exception ex){}
				if(target != null){
					target.damage(1);
					target.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Tu as été biflé");
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"La bifle est envoyée !");
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur "+ChatColor.GOLD+args[0]+ChatColor.RED+" est introuvable !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/bifle (player)"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
