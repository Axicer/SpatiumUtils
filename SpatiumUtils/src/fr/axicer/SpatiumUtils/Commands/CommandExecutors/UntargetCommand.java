package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.axicer.SpatiumUtils.Events.EventsListener.EntityTarget;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class UntargetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.untarget") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(EntityTarget.getUntargetedPlayers().contains(player)){
					EntityTarget.getUntargetedPlayers().remove(player);
					player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Les mobs vous targeterons a nouveau !");
				}else{
					EntityTarget.getUntargetedPlayers().add(player);
					player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Les mobs ne vous targeterons plus !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu dois etre un joueur pour effectuer cette commande !");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
