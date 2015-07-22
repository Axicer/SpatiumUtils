package fr.axicer.SpatiumUtils.Utils.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class BannedUUIDPlayerConfig {
	public static File bannedUUIDPlayerConfigFile;
	public static YamlConfiguration bannedUUIDPlayerConfig;
	
	public static void setupBannedConfig(SpatiumUtils pl) throws IOException{
		bannedUUIDPlayerConfigFile = new File(pl.getDataFolder()+"/bannedUUIDPlayer.yml");
		if(!bannedUUIDPlayerConfigFile.exists()){
			bannedUUIDPlayerConfigFile.createNewFile();
			bannedUUIDPlayerConfig = YamlConfiguration.loadConfiguration(bannedUUIDPlayerConfigFile);
			List<String> list = bannedUUIDPlayerConfig.getStringList("banned");
			list.add("putPlayerUUIDHere");
			bannedUUIDPlayerConfig.set("banned", list);
			saveBannedUUIDPlayerConfig();
		}else{
			bannedUUIDPlayerConfig = YamlConfiguration.loadConfiguration(bannedUUIDPlayerConfigFile);
			saveBannedUUIDPlayerConfig();
		}
	}
	
	public static void saveBannedUUIDPlayerConfig() throws IOException{
		bannedUUIDPlayerConfig.save(bannedUUIDPlayerConfigFile);
	}
	
	public static YamlConfiguration getBannedUUIDPlayerConfig(){
		return bannedUUIDPlayerConfig;
	}
	
	public static void reloadBannedUUIDPlayerConfig(){
		bannedUUIDPlayerConfig = YamlConfiguration.loadConfiguration(bannedUUIDPlayerConfigFile);
	}
}
