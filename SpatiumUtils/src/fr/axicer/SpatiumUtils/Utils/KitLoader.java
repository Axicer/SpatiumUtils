package fr.axicer.SpatiumUtils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;

public class KitLoader {
	
	private static HashMap<String, ArrayList<ItemStack>> kits = new HashMap<String, ArrayList<ItemStack>>();

	public static HashMap<String, ArrayList<ItemStack>> getKits() {
		return kits;
	}
	public static void loadKits(){
		for(String kit: ConfigManager.getKitConfig().getConfigurationSection("kits").getKeys(false)){
			ArrayList<ItemStack> content = new ArrayList<ItemStack>();
			for(String item : ConfigManager.getKitConfig().getConfigurationSection("kits."+kit).getKeys(false)){
				String root = "kits."+kit+"."+item;
				if(ConfigManager.getKitConfig().getString(root+".name") == null || ConfigManager.getKitConfig().getString(root+".amount") == null || ConfigManager.getKitConfig().getString(root+".material") == null){
					Bukkit.getLogger().severe("Erreur lors du chargement du kit "+kit+" !");
					Bukkit.getLogger().severe("Le nom, la quantité ou le type n'est pas defini pour l'item : "+item+" !");
				}else{
					Material itemMaterial = null;
					String itemName = null;
					int itemAmount = 0;
					try{
						itemMaterial = Material.valueOf(ConfigManager.getKitConfig().getString(root+".material"));
						itemName = ChatColor.translateAlternateColorCodes('&', ConfigManager.getKitConfig().getString(root+".name"));
						itemAmount = ConfigManager.getKitConfig().getInt(root+".amount");
					}catch(Exception e){}
					if(itemMaterial == null){
						Bukkit.getLogger().severe("Le type d'item est incorrect pour : "+item+" !");
					}else if(itemName == null){
						Bukkit.getLogger().severe("Le nom de l'item est invalide pour : "+item+" !");
					}else if(itemAmount == 0){
						Bukkit.getLogger().severe("Le nombre d'item est invalide pour : "+item+" !");
					}else{
						ItemStack newItem = new ItemStack(itemMaterial, itemAmount);
						ItemMeta newItemMeta = newItem.getItemMeta();
						newItemMeta.setDisplayName(itemName);
						if(ConfigManager.getKitConfig().getStringList(root+".lores").size() != 0){
							ArrayList<String> lores = new ArrayList<String>();
							for(String lore : ConfigManager.getKitConfig().getStringList(root+".lores")){
								try{
									lores.add(ChatColor.translateAlternateColorCodes('&', lore));
								}catch(Exception e){
									Bukkit.getLogger().warning("Une erreur est survenue lors du chargement des lores pour \""+lore+"\" !");
								}
							}
							newItemMeta.setLore(lores);
						}
						newItem.setItemMeta(newItemMeta);
						if(ConfigManager.getKitConfig().getStringList(root+".enchantment").size() != 0){
							HashMap<Enchantment, Integer> enchants = decompileEnchantmentString(ConfigManager.getKitConfig().getStringList(root+".enchantment"));
							newItem.addUnsafeEnchantments(enchants);
						}
						content.add(newItem);
					}
				}
			}
			kits.put(kit, content);
		}
	}
	private static HashMap<Enchantment, Integer> decompileEnchantmentString(List<String> list){
		HashMap<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
		for(String compiled : list){
			String[] separated = compiled.split(",");
			Enchantment enchant = null;
			int level = 0;
			try{
				enchant = Enchantment.getByName(separated[0]);
				level = Integer.valueOf(separated[1]);
			}catch(Exception e){}
			if(enchant != null && level != 0){
				enchants.put(enchant, level);
			}else{
				Bukkit.getLogger().warning("Erreur lors du chargement des enchantments !");
			}
		}
		return enchants;
	}
}
