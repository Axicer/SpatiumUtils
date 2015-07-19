package fr.axicer.SpatiumUtils.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.ConfigUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SunbanUUIDCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.sunbanuuid") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				OfflinePlayer target = null;
				try{
					target = Bukkit.getOfflinePlayer(args[0]);
				}catch(Exception ex){}
				if(target != null){
					if(ConfigUtils.getbannedUUIDConfig().getStringList("banned").contains(target.getUniqueId().toString())){
						List<String> list = ConfigUtils.getbannedUUIDConfig().getStringList("banned");
						list.remove(target.getUniqueId().toString());
						ConfigUtils.getbannedUUIDConfig().set("banned", list);
						ConfigUtils.saveBannedUUIDConfig();
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le joueur a été débanni !");
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur n'est pas banni !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"Pour le ban, la commande est \""+ChatColor.GOLD+"/sbanuuid (player)"+ChatColor.RESET+"\".");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur "+ChatColor.GOLD+args[0]+ChatColor.RED+" est introuvable !");
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
