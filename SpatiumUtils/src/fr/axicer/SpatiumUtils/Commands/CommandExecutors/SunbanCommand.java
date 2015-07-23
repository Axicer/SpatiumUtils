package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SunbanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.sunban") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				if(ConfigManager.getbannedPlayerConfig().getStringList("banned").contains(args[0])){
					List<String> list = ConfigManager.getbannedPlayerConfig().getStringList("banned");
					list.remove(args[0]);
					ConfigManager.getbannedPlayerConfig().set("banned", list);
					ConfigManager.saveBannedPlayerConfig();
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
