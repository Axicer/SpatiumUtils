package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class FakeJoinCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.fakejoin") || Vault.getPermissions().has(sender, "spatium.*")){
			if(!(args.length == 1)){
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/fakejoin (player)"+ChatColor.RESET+"\".");
			}else{
				for(Player player : Bukkit.getOnlinePlayers()){
					player.sendMessage(ChatColor.YELLOW+args[0]+" joined the game.");
				}
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GRAY+""+ChatColor.ITALIC+"Fausse connection établie ;)");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
