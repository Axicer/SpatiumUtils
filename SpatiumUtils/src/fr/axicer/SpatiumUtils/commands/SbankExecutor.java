package fr.axicer.SpatiumUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.ConfigUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SbankExecutor implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(args.length >= 2){
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("get")){
					if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.sbank.get") || Vault.getPermissions().has(sender, "spatium.*")){
						OfflinePlayer player = null;
						try{
							player = Bukkit.getOfflinePlayer(args[1]);
						}catch(Exception e){}
						if(player != null){
							if(ConfigUtils.getMoneyConfig().getConfigurationSection("players").contains(player.getUniqueId().toString())){
								int playerMoney = ConfigUtils.getMoneyConfig().getInt("players."+player.getUniqueId().toString());
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"La somme d'argent de "+ChatColor.GOLD+player.getName()+ChatColor.GREEN+" est de "+ChatColor.GOLD+playerMoney+"€");
							}else{
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable dans la config !");
							}
						}else{
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/sbank get|add|remove (player) [somme]"+ChatColor.RESET+"\".");
				}
			}else{
				//TODO 3 args command
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
			sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/sbank get|add|remove (player) [somme]"+ChatColor.RESET+"\".");
		}
		return true;
	}

}
