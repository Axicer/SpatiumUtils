package fr.axicer.SpatiumUtils.Configs.configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class MoneyConfig {
	public static File moneyConfigFile;
	public static YamlConfiguration moneyConfig;
	
	public static void setupBannedConfig(SpatiumUtils pl) throws IOException{
		moneyConfigFile = new File(pl.getDataFolder()+"/money.yml");
		if(!moneyConfigFile.exists()){
			moneyConfigFile.createNewFile();
			moneyConfig = YamlConfiguration.loadConfiguration(moneyConfigFile);
			moneyConfig.createSection("players");
			saveMoneyConfig();
		}else{
			moneyConfig = YamlConfiguration.loadConfiguration(moneyConfigFile);
			saveMoneyConfig();
		}
	}
	
	public static void saveMoneyConfig() throws IOException{
		moneyConfig.save(moneyConfigFile);
	}
	
	public static YamlConfiguration getMoneyConfig(){
		return moneyConfig;
	}
	
	public static void reloadMoneyConfig(){
		moneyConfig = YamlConfiguration.loadConfiguration(moneyConfigFile);
	}
}
