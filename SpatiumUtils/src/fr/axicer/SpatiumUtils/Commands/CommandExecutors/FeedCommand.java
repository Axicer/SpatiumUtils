package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class FeedCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.feed") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 0){
					player.setExhaustion(5F);
					player.setFoodLevel(20);
					player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Tu as été nourri !");
				}else if(args.length == 1){
					if(player.isOp() || Vault.getPermissions().has(player, "spatium.feed.other")){
						Player target = null;
						try{
							target = Bukkit.getPlayer(args[0]);
						}catch(Exception e){}
						if(target != null){
							target.setFoodLevel(20);
							target.setExhaustion(5F);
							player.sendMessage(ChatUtils.getPluginPrefix()+target.getDisplayName()+ChatColor.GREEN+" a été nourri");
							target.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Tu as été nourri !");
						}else{
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/feed [player]"+ChatColor.RESET+"\".");
				}
			}else{
				if(args.length == 1){
					if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.feed.other")){
						Player target = null;
						try{
							target = Bukkit.getPlayer(args[0]);
						}catch(Exception e){}
						if(target != null){
							target.setFoodLevel(20);
							target.setExhaustion(5F);
							sender.sendMessage(ChatUtils.getPluginPrefix()+target.getDisplayName()+ChatColor.GREEN+" a été nourri");
							target.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Tu as été nourri !");
						}else{
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/feed [player]"+ChatColor.RESET+"\".");
				}
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
