package fr.axicer.SpatiumUtils.Commands.CommandTabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class GmTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<String> tabComplete = new ArrayList<String>();
		if(args.length == 1){
			tabComplete.add("0");
			tabComplete.add("1");
			tabComplete.add("2");
			tabComplete.add("3");
			tabComplete.add("survival");
			tabComplete.add("creative");
			tabComplete.add("adventure");
			tabComplete.add("spectator");
			return tabComplete;
		}
		return null;
	}

}
