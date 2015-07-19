package fr.axicer.SpatiumUtils.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.ConfigUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SunbanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.sunban") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				if(ConfigUtils.getbannedConfig().getStringList("banned").contains(args[0])){
					List<String> list = ConfigUtils.getbannedConfig().getStringList("banned");
					list.remove(args[0]);
					ConfigUtils.getbannedConfig().set("banned", list);
					ConfigUtils.saveBannedConfig();
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le joueur a été débanni !");
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur n'est pas banni !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"Pour le ban, la commande est \""+ChatColor.GOLD+"/sban (player)"+ChatColor.RESET+"\".");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/ban (player)"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
