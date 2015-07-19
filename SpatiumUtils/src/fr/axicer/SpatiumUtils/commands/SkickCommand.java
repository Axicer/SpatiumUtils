package fr.axicer.SpatiumUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SkickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.skick") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length >= 1){
				Player target = null;
				try{
					target = Bukkit.getPlayer(args[0]);
				}catch(Exception ex){}
				if(target != null){
					String phrase = "";
					if(args.length == 1){
						phrase = "raison non definie";
					}else{
						for(int i = 1; i < args.length; i++){
							String arg = args[i] + " ";
							phrase = phrase + arg;
						}
					}
					target.kickPlayer("Tu as été kick pour : "+phrase);
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le joueur "+ChatColor.RESET+target.getDisplayName()+ChatColor.GREEN+" a été kick de la partie !");
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur "+ChatColor.GOLD+args[0]+ChatColor.RED+" est introuvable !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/skick (player) [reason]"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
