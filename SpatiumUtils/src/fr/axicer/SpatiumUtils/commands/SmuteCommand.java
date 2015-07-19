package fr.axicer.SpatiumUtils.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.ConfigUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SmuteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.smute") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 1){
				Player target = null;
				try{
					target = Bukkit.getPlayer(args[0]);
				}catch(Exception ex){}
				if(target != null){
					if(!ConfigUtils.getmutedConfig().getStringList("muted").contains(target.getName())){
						List<String> list = ConfigUtils.getmutedConfig().getStringList("muted");
						list.add(target.getName());
						ConfigUtils.getmutedConfig().set("muted", list);
						ConfigUtils.saveMutedConfig();
						target.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu as été mute !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GRAY+"Le joueur "+target.getDisplayName()+ChatColor.GREEN+" a été mute !");
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est deja mute !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"Pour le demute, la commande est \""+ChatColor.GOLD+"/sdemute (player)"+ChatColor.RESET+"\".");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur "+ChatColor.GOLD+args[0]+ChatColor.RED+" est introuvable !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/smute (player)"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
