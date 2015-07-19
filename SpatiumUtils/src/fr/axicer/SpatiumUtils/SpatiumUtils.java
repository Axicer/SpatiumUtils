package fr.axicer.SpatiumUtils;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.ConfigUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;
import fr.axicer.SpatiumUtils.commands.CommandManager;
import fr.axicer.SpatiumUtils.events.EventManager;

public class SpatiumUtils extends JavaPlugin{
	public void onEnable(){
		saveDefaultConfig();// needed for create plugins's config file. 
		
		try {
			ConfigUtils.createConfigFiles(this);
		} catch (IOException e) {
			e.printStackTrace();
		}// create config files and load them
		
		Vault.setupPermissions(this);// vault setup
		Vault.setupEconomy(this);
		Vault.setupChat(this);
		
		EventManager.registersEvents(this);// register event
		
		CommandManager.registerCommands(this);// and register command functions
		
		getLogger().info(ChatUtils.getPluginPrefix()+"est ON");// log to server when all is done.
	}
	
	public void onDisable(){
		getLogger().info(ChatUtils.getPluginPrefix()+"est OFF");
	}
}
