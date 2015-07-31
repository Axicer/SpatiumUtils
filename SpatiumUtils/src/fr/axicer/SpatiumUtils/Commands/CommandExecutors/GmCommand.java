package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class GmCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.gm") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en SURVIVAL");
					}else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
						player.setGameMode(GameMode.CREATIVE);
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en CREATIVE");
					}else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
						player.setGameMode(GameMode.ADVENTURE);
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en ADVENTURE");
					}else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
						player.setGameMode(GameMode.SPECTATOR);
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en SPEACTATOR");
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/gm (gamemode) [player]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 2){
					if(Vault.getPermissions().has(sender, "spatium.gm.other") || Vault.getPermissions().has(sender, "spatium.*")){
						Player target = null;
						try{
							target = Bukkit.getPlayer(args[1]);
						}catch(Exception e){}
						if(target != null){
							if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
								target.setGameMode(GameMode.SURVIVAL);
								player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en SURVIVAL pour "+target.getDisplayName());
							}else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
								target.setGameMode(GameMode.CREATIVE);
								player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en CREATIVE pour "+target.getDisplayName());
							}else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
								target.setGameMode(GameMode.ADVENTURE);
								player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en ADVENTURE pour "+target.getDisplayName());
							}else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
								target.setGameMode(GameMode.SPECTATOR);
								player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en SPEACTATOR pour "+target.getDisplayName());
							}else{
								player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
								player.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/gm (gamemode) [player]"+ChatColor.RESET+"\".");
							}
						}else{
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/gm (gamemode) [player]"+ChatColor.RESET+"\".");
				}
			}else{
				if(args.length == 2){
					if(Vault.getPermissions().has(sender, "spatium.gm.other") || Vault.getPermissions().has(sender, "spatium.*")){
						Player target = null;
						try{
							target = Bukkit.getPlayer(args[1]);
						}catch(Exception e){}
						if(target != null){
							if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
								target.setGameMode(GameMode.SURVIVAL);
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en SURVIVAL pour "+target.getDisplayName());
							}else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
								target.setGameMode(GameMode.CREATIVE);
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en CREATIVE pour "+target.getDisplayName());
							}else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
								target.setGameMode(GameMode.ADVENTURE);
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en ADVENTURE pour "+target.getDisplayName());
							}else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
								target.setGameMode(GameMode.SPECTATOR);
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Gamemode defini en SPEACTATOR pour "+target.getDisplayName());
							}else{
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
								sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/gm (gamemode) [player]"+ChatColor.RESET+"\".");
							}
						}else{
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				}
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}
}
