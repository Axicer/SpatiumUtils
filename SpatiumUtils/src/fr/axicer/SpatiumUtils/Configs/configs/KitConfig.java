package fr.axicer.SpatiumUtils.Configs.configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class KitConfig {
	public static File kitConfigFile;
	public static YamlConfiguration kitConfig;
	
	public static void setupKitConfig(SpatiumUtils pl) throws IOException{
		kitConfigFile = new File(pl.getDataFolder()+"/kit.yml");
		if(!kitConfigFile.exists()){
			kitConfigFile.createNewFile();
			kitConfig = YamlConfiguration.loadConfiguration(kitConfigFile);
			
			ArrayList<String> defaultLores = new ArrayList<String>();
			ArrayList<String> swordLores = new ArrayList<String>();
			ArrayList<String> swordEnchantment = new ArrayList<String>();
			
			defaultLores.add("&1This is the stick of truth");
			defaultLores.add("&2This is the default item from kits =)");
			
			swordLores.add("&4L'épée du grand &eAxicer &4!");
			swordLores.add("&9Cette épée est légendaire !");
			swordEnchantment.add(Enchantment.DAMAGE_ALL.getName()+",5");
			swordEnchantment.add(Enchantment.FIRE_ASPECT.getName()+",2");
			
			kitConfig.set("kits.default.stick.name", "Stick of truth");
			kitConfig.set("kits.default.stick.material", Material.STICK.toString());
			kitConfig.set("kits.default.stick.amount", 1);
			kitConfig.set("kits.default.stick.lores", defaultLores);
			
			kitConfig.set("kits.default.sword.name", "Axicer_'s sword");
			kitConfig.set("kits.default.sword.material", Material.DIAMOND_SWORD.toString());
			kitConfig.set("kits.default.sword.amount", 1);
			kitConfig.set("kits.default.sword.lores", swordLores);
			kitConfig.set("kits.default.sword.enchantment", swordEnchantment);
			
			kitConfig.set("kits.default.wool.name", "&eWool avec data value !");
			kitConfig.set("kits.default.wool.material", Material.WOOL.toString());
			kitConfig.set("kits.default.wool.amount", 5);
			kitConfig.set("kits.default.wool.data", 13);
			
			saveKitConfig();
		}else{
			kitConfig = YamlConfiguration.loadConfiguration(kitConfigFile);
			saveKitConfig();
		}
	}
	public static void saveKitConfig() throws IOException{
		kitConfig.save(kitConfigFile);
	}
	public static YamlConfiguration getKitConfig(){
		return kitConfig;
	}
	public static void reloadKitConfig(){
		kitConfig = YamlConfiguration.loadConfiguration(kitConfigFile);
	}
}
