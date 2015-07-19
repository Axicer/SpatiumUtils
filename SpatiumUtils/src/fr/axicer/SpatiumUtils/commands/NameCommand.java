package fr.axicer.SpatiumUtils.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class NameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.name") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				UUID uuid = null;
				try{
					uuid = UUID.fromString(args[0]);
				}catch(Exception e){}
				if(uuid != null){
					OfflinePlayer target = null;
					try{
						target = Bukkit.getOfflinePlayer(UUID.fromString(args[0]));
					}catch(Exception e){}
					if(target.getName() != null){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le pseudo de l'UUID "+ChatColor.GOLD+args[0]+ChatColor.GREEN+" est "+ChatColor.GOLD+target.getName()+ChatColor.GREEN+".");
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"L'UUID indiqué est invalide !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur n'a pas été trouvé !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"L'UUID est invalide !");
				}
				
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/name (uuid)"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
