package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class FwCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.fw") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 0){
					shootFireworks(player);
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/fw"+ChatColor.RESET+"\".");
				}
			}else{
				sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu dois etre un joueur pour effectuer cette commande !");
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}
	public void shootFireworks(Player player){
        Firework fw = (Firework) player.getWorld().spawn(player.getLocation(),Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        Random r = new Random();
        int fType = r.nextInt(5) + 1;
        Type type = Type.BALL;
        switch (fType) {
        case 1:
                type = Type.BALL;
                break;
        case 2:
                type = Type.BALL_LARGE;
                break;
        case 3:
                type = Type.BURST;
                break;
        case 4:
                type = Type.CREEPER;
                break;
        case 5:
                type = Type.STAR;
                break;
        }
   
	    int c1i = r.nextInt(17) + 1;
	    int c2i = r.nextInt(17) + 1;
	    Color c1 = getColor (c1i);
	    Color c2 = getColor (c2i);
	    FireworkEffect effect = FireworkEffect.builder()
	                    .flicker(r.nextBoolean()).withColor(c1).withFade(c2)
	                    .with(type).trail(r.nextBoolean()).build();
	    fm.addEffect(effect);
	    int power = r.nextInt(2)+1;
	    fm.setPower(power);
	    fw.setFireworkMeta(fm);
	}
	public Color getColor(int c){
    switch (c){
	    default:
	    case 1:
	            return Color.AQUA;
	    case 2:
	            return Color.BLACK;
	    case 3:
	            return Color.BLUE;
	    case 4:
	            return Color.FUCHSIA;
	    case 5:
	            return Color.GRAY;
	    case 6:
	            return Color.GREEN;
	    case 7:
	            return Color.LIME;
	    case 8:
	            return Color.MAROON;
	    case 9:
	            return Color.NAVY;
	    case 10:
	            return Color.OLIVE;
	    case 11:
	            return Color.ORANGE;
	    case 12:
	            return Color.PURPLE;
	    case 13:
	            return Color.RED;
	    case 14:
	            return Color.SILVER;
	    case 15:
	            return Color.TEAL;
	    case 16:
	            return Color.WHITE;
	    case 17:
	            return Color.YELLOW;
	    }
	}
}
