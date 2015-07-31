package fr.axicer.SpatiumUtils.Commands.CommandTabCompleters;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;

public class MoneyTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> tabComplete = new ArrayList<String>();
		if(args.length == 1){
			tabComplete.add("get");
			tabComplete.add("add");
			tabComplete.add("remove");
			return tabComplete;
		}
		if(args.length == 2){
			try{
				for(String uuidString : ConfigManager.getMoneyConfig().getConfigurationSection("players").getKeys(true)){
					tabComplete.add(Bukkit.getOfflinePlayer(UUID.fromString(uuidString)).getName());
				}
				if(tabComplete.size()!= 0){
					return tabComplete;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}

}
