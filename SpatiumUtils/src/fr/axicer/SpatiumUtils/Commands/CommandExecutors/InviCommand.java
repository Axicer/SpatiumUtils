package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class InviCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.invi") || Vault.getPermissions().has(sender, "spatium.*")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu dois etre un joueur !");
			}else{
				Player p = (Player) sender;
				if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)){
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.sendMessage(ChatColor.GRAY+""+ChatColor.ITALIC+"Apparition !");
				}else{
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1), true);
					p.sendMessage(ChatColor.GRAY+""+ChatColor.ITALIC+"Disparition !");
				}
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
