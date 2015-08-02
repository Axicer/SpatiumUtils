package fr.axicer.SpatiumUtils.Commands.CommandTabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class KitTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> tabComplete = new ArrayList<String>();
		if(args.length == 1){
			tabComplete.add("get");
			tabComplete.add("material");
			tabComplete.add("enchantment");
			tabComplete.add("list");
			return tabComplete;
		}
		return null;
	}

}
