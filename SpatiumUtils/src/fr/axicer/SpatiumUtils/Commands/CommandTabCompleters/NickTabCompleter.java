package fr.axicer.SpatiumUtils.Commands.CommandTabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class NickTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(args.length == 1){
			ArrayList<String> tabComplete = new ArrayList<String>();
			tabComplete.add("reset");
			return tabComplete;
		}
		return null;
	}

}
