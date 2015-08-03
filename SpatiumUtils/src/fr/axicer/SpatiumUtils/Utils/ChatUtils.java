package fr.axicer.SpatiumUtils.Utils;

import org.bukkit.ChatColor;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;

public class ChatUtils {
	
	private static String PLUGIN_PREFIX = ChatColor.GRAY+"["+ChatColor.GOLD+ChatColor.BOLD+"SpatiumUtils"+ChatColor.RESET+ChatColor.GRAY+"] "+ChatColor.RESET;
	
	public static String getPluginPrefix(){
		if(ConfigManager.getDefaultConfig().getBoolean("pluginPrefix")){
			return PLUGIN_PREFIX;
		}else{
			return "";
		}
	}
}
