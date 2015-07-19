package fr.axicer.SpatiumUtils.Utils;

import org.bukkit.ChatColor;

public class ChatUtils {
	
	private static String PLUGIN_PREFIX = ChatColor.GRAY+"["+ChatColor.GOLD+ChatColor.BOLD+"SpatiumUtils"+ChatColor.RESET+ChatColor.GRAY+"] "+ChatColor.RESET;
	
	public static String getPluginPrefix(){
		return PLUGIN_PREFIX;
	}
}
