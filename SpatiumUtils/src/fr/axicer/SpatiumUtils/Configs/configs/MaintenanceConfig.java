package fr.axicer.SpatiumUtils.Configs.configs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class MaintenanceConfig {
	public static File MaintenanceConfigFile;
	public static YamlConfiguration MaintenanceConfig;
	
	public static void setupBannedConfig(SpatiumUtils pl) throws IOException{
		MaintenanceConfigFile = new File(pl.getDataFolder()+"/maintenance.yml");
		if(!MaintenanceConfigFile.exists()){
			MaintenanceConfigFile.createNewFile();
			MaintenanceConfig = YamlConfiguration.loadConfiguration(MaintenanceConfigFile);
			List<String> list = MaintenanceConfig.getStringList("authorized");
			list.add("putPlayerPseudoHere");
			MaintenanceConfig.set("authorized", list);
			MaintenanceConfig.set("activated", false);
			MaintenanceConfig.set("maintenanceMOTD", ChatColor.RED+"Maintenance en cours !");
			saveMaintenanceConfig();
		}else{
			MaintenanceConfig = YamlConfiguration.loadConfiguration(MaintenanceConfigFile);
			saveMaintenanceConfig();
		}
	}
	
	public static void saveMaintenanceConfig() throws IOException{
		MaintenanceConfig.save(MaintenanceConfigFile);
	}
	
	public static YamlConfiguration getMaintenanceConfig(){
		return MaintenanceConfig;
	}
	
	public static void reloadMaintenanceConfig(){
		MaintenanceConfig = YamlConfiguration.loadConfiguration(MaintenanceConfigFile);
	}
}
