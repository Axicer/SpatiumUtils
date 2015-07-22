package fr.axicer.SpatiumUtils.Utils;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Utils.config.BannedPlayerConfig;
import fr.axicer.SpatiumUtils.Utils.config.BannedUUIDPlayerConfig;
import fr.axicer.SpatiumUtils.Utils.config.MaintenanceConfig;
import fr.axicer.SpatiumUtils.Utils.config.MoneyConfig;
import fr.axicer.SpatiumUtils.Utils.config.MutedPlayerConfig;

public class ConfigUtils {
	
	public static void setupConfigFiles(SpatiumUtils pl) throws IOException{
		BannedPlayerConfig.setupBannedConfig(pl);
		BannedUUIDPlayerConfig.setupBannedConfig(pl);
		MaintenanceConfig.setupBannedConfig(pl);
		MoneyConfig.setupBannedConfig(pl);
		MutedPlayerConfig.setupBannedConfig(pl);
	}
	
	public static void reloadConfigFile(SpatiumUtils pl){
		BannedPlayerConfig.reloadBannedPlayerConfig();
		BannedUUIDPlayerConfig.reloadBannedUUIDPlayerConfig();
		MutedPlayerConfig.reloadMutedPlayerConfig();
		MaintenanceConfig.reloadMaintenanceConfig();
		MoneyConfig.reloadMoneyConfig();
		pl.reloadConfig();
	}
	
	public static YamlConfiguration getbannedPlayerConfig(){
		return BannedPlayerConfig.getBannedPlayerConfig();
	}
	public static YamlConfiguration getbannedUUIDPlayerConfig(){
		return BannedUUIDPlayerConfig.getBannedUUIDPlayerConfig();
	}
	public static YamlConfiguration getMutedPlayerConfig(){
		return MutedPlayerConfig.getMutedPlayerConfig();
	}
	public static YamlConfiguration getMaintenanceConfig(){
		return MaintenanceConfig.getMaintenanceConfig();
	}
	public static YamlConfiguration getMoneyConfig(){
		return MoneyConfig.getMoneyConfig();
	}
	
	public static void saveBannedPlayerConfig(){
		try {
			BannedPlayerConfig.saveBannedPlayerConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveBannedUUIDPlayerConfig(){
		try {
			BannedUUIDPlayerConfig.saveBannedUUIDPlayerConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMutedPlayerConfig(){
		try {
			MutedPlayerConfig.saveMutedPlayerConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMaintenanceConfig(){
		try {
			MaintenanceConfig.saveMaintenanceConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMoneyConfig(){
		try{
			MoneyConfig.saveMoneyConfig();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
