package fr.axicer.SpatiumUtils.Utils.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class MutedPlayerConfig {
	public static File mutedPlayerConfigFile;
	public static YamlConfiguration mutedPlayerConfig;
	
	public static void setupBannedConfig(SpatiumUtils pl) throws IOException{
		mutedPlayerConfigFile = new File(pl.getDataFolder()+"/mutedPlayer.yml");
		if(!mutedPlayerConfigFile.exists()){
			mutedPlayerConfigFile.createNewFile();
			mutedPlayerConfig = YamlConfiguration.loadConfiguration(mutedPlayerConfigFile);
			List<String> list = mutedPlayerConfig.getStringList("muted");
			list.add("putPlayerPseudoHere");
			mutedPlayerConfig.set("muted", list);
			saveMutedPlayerConfig();
		}else{
			mutedPlayerConfig = YamlConfiguration.loadConfiguration(mutedPlayerConfigFile);
			saveMutedPlayerConfig();
		}
	}
	
	public static void saveMutedPlayerConfig() throws IOException{
		mutedPlayerConfig.save(mutedPlayerConfigFile);
	}
	
	public static YamlConfiguration getMutedPlayerConfig(){
		return mutedPlayerConfig;
	}
	
	public static void reloadMutedPlayerConfig(){
		mutedPlayerConfig = YamlConfiguration.loadConfiguration(mutedPlayerConfigFile);
	}
}
