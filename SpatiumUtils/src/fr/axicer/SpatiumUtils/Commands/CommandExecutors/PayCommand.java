package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class PayCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.pay") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 2){
				if(sender instanceof Player){
					Player player = (Player) sender;
					OfflinePlayer offPlayer = null;
					try{
						offPlayer = Bukkit.getOfflinePlayer(args[0]);
					}catch(Exception e){}
					if(offPlayer != null){
						if(ConfigManager.getMoneyConfig().getConfigurationSection("players").contains(offPlayer.getUniqueId().toString())){
							int somme = 0;
							try{
								somme = Integer.valueOf(args[1]);
							}catch(Exception e){}
							if(somme != 0){
								int offPlayerMoney = ConfigManager.getMoneyConfig().getInt("players."+offPlayer.getUniqueId().toString());
								int playerMoney = ConfigManager.getMoneyConfig().getInt("players."+player.getUniqueId().toString());
								int addedValue = offPlayerMoney+somme;
								int removedValue = playerMoney-somme;
								if(addedValue < 0){
									int maxValue = Integer.MAX_VALUE-offPlayerMoney;
									sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La somme du joueur ne peut pas depasser "+ChatColor.GOLD+maxValue);
								}else{
									if(removedValue >= 0){
										ConfigManager.getMoneyConfig().set("players."+offPlayer.getUniqueId().toString(), addedValue);
										ConfigManager.getMoneyConfig().set("players."+player.getUniqueId().toString(), removedValue);
										ConfigManager.saveMoneyConfig();
										sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GOLD+somme+"€ "+ChatColor.GREEN+"envoyé a "+ChatColor.GOLD+offPlayer.getName());
									}else{
										sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'as pas assez d'argent !");
									}
								}			
							}else{
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La somme indiquée est invalide !");
							}
						}else{
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable dans la config !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Il faut etre un joueur pour effectuer cette commande !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/pay (player) [somme]"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
