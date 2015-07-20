package fr.axicer.SpatiumUtils.commands;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class CommandManager {
	public static void registerCommands(SpatiumUtils pl){
		pl.getCommand("sban").setExecutor(new SbanCommand());
		pl.getCommand("sunban").setExecutor(new SunbanCommand());
		pl.getCommand("sbanuuid").setExecutor(new SbanUUIDCommand());
		pl.getCommand("sunbanuuid").setExecutor(new SunbanUUIDCommand());
		pl.getCommand("uuid").setExecutor(new UUIDCommand());
		pl.getCommand("skick").setExecutor(new SkickCommand());
		pl.getCommand("smute").setExecutor(new SmuteCommand());
		pl.getCommand("sunmute").setExecutor(new SunmuteCommand());
		pl.getCommand("maintenance").setExecutor(new MaintenanceCommand());
		pl.getCommand("sreload").setExecutor(new SreloadCommand(pl));
		pl.getCommand("bifle").setExecutor(new BifleCommand());
		pl.getCommand("poke").setExecutor(new PokeCommand(pl));
		pl.getCommand("invi").setExecutor(new InviCommand());
		pl.getCommand("fakejoin").setExecutor(new FakeJoinCommand());
		pl.getCommand("fakeleft").setExecutor(new FakeLeftCommand());
		pl.getCommand("name").setExecutor(new NameCommand());
		pl.getCommand("feuerfrei").setExecutor(new FeuerFreiCommand());
		pl.getCommand("sbank").setExecutor(new SbankExecutor());
	}
}