package fr.axicer.SpatiumUtils.Commands;

import fr.axicer.SpatiumUtils.SpatiumUtils;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.BifleCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.DayCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.FakeJoinCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.FakeLeftCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.FeedCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.FeuerFreiCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.FwCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.GmCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.GravityGunCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.InviCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.MaintenanceCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.MoonCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.NameCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.NickCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.NightCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.PayCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.PokeCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.RainCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SbanCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SbanUUIDCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.MoneyCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SetSpawnCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SkickCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SmuteCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SpawnCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SreloadCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SunCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SunbanCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SunbanUUIDCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.SunmuteCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.UUIDCommand;
import fr.axicer.SpatiumUtils.Commands.CommandExecutors.UntargetCommand;
import fr.axicer.SpatiumUtils.Commands.CommandTabCompleters.GmTabCompleter;
import fr.axicer.SpatiumUtils.Commands.CommandTabCompleters.NickTabCompleter;
import fr.axicer.SpatiumUtils.Commands.CommandTabCompleters.MoneyTabCompleter;

public class CommandManager {
	public static void registerCommands(SpatiumUtils pl){
		//setting command executors
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
		pl.getCommand("money").setExecutor(new MoneyCommand());
		pl.getCommand("moon").setExecutor(new MoonCommand());
		pl.getCommand("gravitygun").setExecutor(new GravityGunCommand());
		pl.getCommand("day").setExecutor(new DayCommand());
		pl.getCommand("night").setExecutor(new NightCommand());
		pl.getCommand("sun").setExecutor(new SunCommand());
		pl.getCommand("rain").setExecutor(new RainCommand());
		pl.getCommand("setspawn").setExecutor(new SetSpawnCommand(pl));
		pl.getCommand("spawn").setExecutor(new SpawnCommand(pl));
		pl.getCommand("untarget").setExecutor(new UntargetCommand());
		pl.getCommand("nick").setExecutor(new NickCommand());
		pl.getCommand("fw").setExecutor(new FwCommand());
		pl.getCommand("pay").setExecutor(new PayCommand());;
		pl.getCommand("gm").setExecutor(new GmCommand());
		pl.getCommand("feed").setExecutor(new FeedCommand());
		
		// setting tab executors
		pl.getCommand("money").setTabCompleter(new MoneyTabCompleter());
		pl.getCommand("nick").setTabCompleter(new NickTabCompleter());
		pl.getCommand("gm").setTabCompleter(new GmTabCompleter());
	}
}