package fr.axicer.SpatiumUtils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class ConfigUtils {
	
	private static YamlConfiguration bannedConfig;
	private static File fichierConfigban;
	private static YamlConfiguration bannedUUIDConfig;
	private static File fichierConfigbanuuid;
	private static YamlConfiguration mutedConfig;
	private static File fichierConfigMuted;
	private static YamlConfiguration maintenanceWhiteList;
	private static File fichierMaintenanceWhiteList;
	private static YamlConfiguration moneyConfig;
	private static File fichierMoneyConfig;
	
	public static void createConfigFiles(SpatiumUtils pl) throws IOException{
		
		fichierConfigban = new File(pl.getDataFolder()+"/banned.yml");
		fichierConfigbanuuid = new File(pl.getDataFolder()+"/banneduuid.yml");
		fichierConfigMuted = new File(pl.getDataFolder()+"/muted.yml");
		fichierMaintenanceWhiteList = new File(pl.getDataFolder()+"/maintenance_White_List.yml");
		fichierMoneyConfig = new File(pl.getDataFolder()+"/money.yml");
		
		if(!fichierConfigban.exists()){
			fichierConfigban.createNewFile();
			bannedConfig = YamlConfiguration.loadConfiguration(fichierConfigban);
			List<String> list = bannedConfig.getStringList("banned");
			list.add("putPlayerPseudoHere");
			bannedConfig.set("banned", list);
			saveBannedConfig();
		}else{
			bannedConfig = YamlConfiguration.loadConfiguration(fichierConfigban);
			saveBannedConfig();
		}
		
		if(!fichierConfigbanuuid.exists()){
			fichierConfigbanuuid.createNewFile();
			bannedUUIDConfig = YamlConfiguration.loadConfiguration(fichierConfigbanuuid);
			List<String> list = bannedUUIDConfig.getStringList("banned");
			list.add("putPlayerUUIDHere");
			bannedUUIDConfig.set("banned", list);
			saveBannedUUIDConfig();
		}else{
			bannedUUIDConfig = YamlConfiguration.loadConfiguration(fichierConfigbanuuid);
			saveBannedUUIDConfig();
		}
		if(!fichierConfigMuted.exists()){
			fichierConfigMuted.createNewFile();
			mutedConfig = YamlConfiguration.loadConfiguration(fichierConfigMuted);
			List<String> list = mutedConfig.getStringList("muted");
			list.add("putPlayerPseudoHere");
			mutedConfig.set("muted", list);
			saveMutedConfig();
		}else{
			mutedConfig = YamlConfiguration.loadConfiguration(fichierConfigMuted);
			saveMutedConfig();
		}
		if(!fichierMaintenanceWhiteList.exists()){
			fichierMaintenanceWhiteList.createNewFile();
			maintenanceWhiteList = YamlConfiguration.loadConfiguration(fichierMaintenanceWhiteList);
			List<String> list = maintenanceWhiteList.getStringList("authorized");
			list.add("putPlayerPseudoHere");
			maintenanceWhiteList.set("authorized", list);
			maintenanceWhiteList.set("activated", false);
			maintenanceWhiteList.set("maintenanceMOTD", ChatColor.RED+"Maintenance en cours !");
			saveMaintenanceWhiteListConfig();
		}else{
			maintenanceWhiteList = YamlConfiguration.loadConfiguration(fichierMaintenanceWhiteList);
			saveMaintenanceWhiteListConfig();
		}
		if(!fichierMoneyConfig.exists()){
			fichierMoneyConfig.createNewFile();
			moneyConfig = YamlConfiguration.loadConfiguration(fichierMoneyConfig);
			moneyConfig.createSection("players");
		}else{
			moneyConfig = YamlConfiguration.loadConfiguration(fichierMoneyConfig);
			
		}
	}
	
	public static void reloadConfigFile(SpatiumUtils pl){
		bannedConfig = YamlConfiguration.loadConfiguration(fichierConfigban);
		bannedUUIDConfig = YamlConfiguration.loadConfiguration(fichierConfigbanuuid);
		mutedConfig = YamlConfiguration.loadConfiguration(fichierConfigMuted);
		maintenanceWhiteList = YamlConfiguration.loadConfiguration(fichierMaintenanceWhiteList);
		moneyConfig = YamlConfiguration.loadConfiguration(fichierMoneyConfig);
		pl.reloadConfig();
	}
	
	public static YamlConfiguration getbannedConfig(){
		return bannedConfig;
	}
	
	public static YamlConfiguration getbannedUUIDConfig(){
		return bannedUUIDConfig;
	}
	public static YamlConfiguration getmutedConfig(){
		return mutedConfig;
	}
	public static YamlConfiguration getMaintenanceWhiteListConfig(){
		return maintenanceWhiteList;
	}
	public static YamlConfiguration getMoneyConfig(){
		return moneyConfig;
	}
	public static File getbannedConfigFile(){
		return fichierConfigban;
	}
	public static File getbannedUUIDConfigFile(){
		return fichierConfigbanuuid;
	}
	public static File getmutedConfigFile(){
		return fichierConfigMuted;
	}
	public static File getMaintenanceWhiteListFile(){
		return fichierMaintenanceWhiteList;
	}
	public static File getMoneyConfigFile(){
		return fichierMoneyConfig;
	}
	public static void saveBannedConfig(){
		try {
			bannedConfig.save(getbannedConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveBannedUUIDConfig(){
		try {
			bannedUUIDConfig.save(getbannedUUIDConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMutedConfig(){
		try {
			mutedConfig.save(getmutedConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMaintenanceWhiteListConfig(){
		try {
			maintenanceWhiteList.save(getMaintenanceWhiteListFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMoneyConfig(){
		try{
			moneyConfig.save(getMoneyConfigFile());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
