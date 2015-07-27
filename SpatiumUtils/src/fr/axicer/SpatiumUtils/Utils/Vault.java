package fr.axicer.SpatiumUtils.Utils;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class Vault {
	private static Permission permission = null;
    private static Economy economy = null;
    private static Chat chat = null;

	public static boolean setupPermissions(SpatiumUtils pl){
        RegisteredServiceProvider<Permission> permissionProvider = pl.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null){
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    public static boolean setupChat(SpatiumUtils pl){
        RegisteredServiceProvider<Chat> chatProvider = pl.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null){
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }

    public static boolean setupEconomy(SpatiumUtils pl){
        RegisteredServiceProvider<Economy> economyProvider = pl.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null){
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
    
    public static Permission getPermissions(){
    	return permission;
    }
    public static Chat getChat(){
    	return chat;
    }
    public static Economy getEconomy(){
    	return economy;
    }
}
