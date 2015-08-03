package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.KitLoader;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class KitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.kit") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("material")){
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Executer cette commande depuis la console pour tout voir =p");
					}else if(args[0].equalsIgnoreCase("enchantment")){
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--Liste des enchantments pour les kits--");
						for(Enchantment enchant : Enchantment.values()){
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+enchant.getName());
						}
					}else if(args[0].equalsIgnoreCase("list")){
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--Liste des kits diponible--");
						for(String kit : KitLoader.getKits().keySet()){
							String displayName = ConfigManager.getKitConfig().getString("kits."+kit+".displayName");
							String description = ConfigManager.getKitConfig().getString("kits."+kit+".description");
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+kit);
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+"|-> displayName: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', displayName));
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+"|-> description: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', description));
							player.sendMessage(ChatUtils.getPluginPrefix()+" ");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list) [kit]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("get")){
						if(KitLoader.getKits().containsKey(args[1])){
							for(ItemStack item : KitLoader.getKits().get(args[1])){
								player.getInventory().addItem(item);
							}
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le kit a été donné !");
						}else{
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Ce kit n'existe pas !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list) [kit]"+ChatColor.RESET+"\".");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list) [kit]"+ChatColor.RESET+"\".");
				}
			}else{
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("material")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--== Liste des material pour les kits ==--");
						for(Material material: Material.values()){
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+material.toString());
						}
					}else if(args[0].equalsIgnoreCase("enchantment")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--== Liste des enchantments pour les kits ==--");
						for(Enchantment enchant : Enchantment.values()){
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+enchant.getName());
						}
					}else if(args[0].equalsIgnoreCase("list")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--== Liste des kits diponible ==--");
						for(String kit : KitLoader.getKits().keySet()){
							String displayName = ConfigManager.getKitConfig().getString("kits."+kit+".displayName");
							String description = ConfigManager.getKitConfig().getString("kits."+kit+".description");
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+kit);
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+"|-> displayName: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', displayName));
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+"|-> description: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', description));
							sender.sendMessage(ChatUtils.getPluginPrefix()+" ");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list) [kit]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("get")){
						sender.sendMessage("Il faut etre un joueur pour executer cette commande !");
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list) [kit]"+ChatColor.RESET+"\".");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list) [kit]"+ChatColor.RESET+"\".");
				}
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}

}
