package fr.axicer.SpatiumUtils;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import fr.axicer.SpatiumUtils.Commands.CommandManager;
import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Events.EventManager;
import fr.axicer.SpatiumUtils.Utils.Documentation;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class SpatiumUtils extends JavaPlugin{
	public void onEnable(){
		saveDefaultConfig();// needed for create plugins's config file.
		
		try {
			ConfigManager.setupConfigFiles(this);
			Documentation.createFile(this);
		} catch (IOException e) {
			getLogger().severe("Les configs n'ont pas reussi a etre crées !");
			e.printStackTrace();
		}// create config files and load them
		
		Vault.setupPermissions(this);// vault setup
		Vault.setupEconomy(this);
		Vault.setupChat(this);
		
		if(getConfig().getInt("spawnpoint.y") == 0){
			World world = Bukkit.getWorlds().get(0);
			getConfig().set("spawnpoint.world", world.getName());
			getConfig().set("spawnpoint.x", world.getSpawnLocation().getX());
			getConfig().set("spawnpoint.y", world.getSpawnLocation().getY());
			getConfig().set("spawnpoint.z", world.getSpawnLocation().getZ());
			getConfig().set("spawnpoint.yaw", world.getSpawnLocation().getYaw());
			getConfig().set("spawnpoint.pitch", world.getSpawnLocation().getPitch());
			saveConfig();
		}
		
		EventManager.registersEvents(this);// register event
		
		CommandManager.registerCommands(this);// and register command functions
		
		getLogger().info("est ON");// log to server when all is done.
	}
	
	public void onDisable(){
		getLogger().info("est OFF");
	}
}
