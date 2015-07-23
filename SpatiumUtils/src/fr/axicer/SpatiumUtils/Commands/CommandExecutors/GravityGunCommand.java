package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class GravityGunCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.gravitygun") || Vault.getPermissions().has(sender, "spatium.*")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player player = (Player) sender;
					ItemStack gravitygun = new ItemStack(Material.STICK);
					ItemMeta gravitygunMeta = gravitygun.getItemMeta();
					gravitygunMeta.setDisplayName(ChatColor.BLUE+""+ChatColor.ITALIC+"Gravity Gun");
					ArrayList<String> lores = new ArrayList<String>();
					lores.add("");
					lores.add("");
					lores.add(ChatColor.RED+"clic droit sur un bloc pour absorber le bloc");
					lores.add(ChatColor.RED+"clic droit a nouveau pour poser le bloc");
					gravitygunMeta.setLore(lores);
					gravitygun.setItemMeta(gravitygunMeta);
					gravitygun.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
					player.getInventory().addItem(gravitygun);
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu dois etre un joueur pour effectuer cette commande !");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
				sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/gravitygun"+ChatColor.RESET+"\".");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}
	
}
