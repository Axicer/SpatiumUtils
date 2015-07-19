package fr.axicer.SpatiumUtils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.ConfigUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SreloadCommand implements CommandExecutor {

	SpatiumUtils pl;
	
	public SreloadCommand(SpatiumUtils pl){
		this.pl = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.sreload") || Vault.getPermissions().has(sender, "spatium.*")){
			ConfigUtils.reloadConfigFile(pl);
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Les configs on été reload !");
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
