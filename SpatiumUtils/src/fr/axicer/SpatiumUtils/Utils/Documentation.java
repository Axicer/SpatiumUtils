package fr.axicer.SpatiumUtils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import fr.axicer.SpatiumUtils.SpatiumUtils;

public class Documentation {
	public static void createFile(SpatiumUtils pl) throws IOException{
		File documentation = new File(pl.getDataFolder()+"/documentation.txt");
		if(!documentation.exists()){
			documentation.createNewFile();
		}
		Writer writer = new OutputStreamWriter(new FileOutputStream(documentation));
		try{
			writer.write("Ce document contient les permissions des differentes commandes:\n"
					+ "\n"
					+ "Toutes les commande: spatium.*\n"
					+ "\n"
					+ "/bifle --> spatium.bifle\n"
					+ "/fakejoin --> spatium.fakejoin\n"
					+ "/fakeleft --> spatium.fakeleft\n"
					+ "/invi --> spatium.invi\n"
					+ "/maintenance --> spatium.maintenance\n"
					+ "/poke --> spatium.poke\n"
					+ "      |-> spatium.poke.nocountdown\n"
					+ "/sban --> spatium.sban\n"
					+ "/sbanuuid --> spatium.banuuid\n"
					+ "/skick --> spatium.skick\n"
					+ "/smute -- spatium.smute\n"
					+ "/sreload --> spatium.sreload\n"
					+ "/sunban --> spatium.sunban\n"
					+ "/sunbanuuid --> spatium.sunbanuuid\n"
					+ "/sunmute --> spatium.sunmute\n"
					+ "/uuid --> spatium.uuid\n"
					+ "/money --> spatium.money.get\n"
					+ "       |-> spatium.money.add\n"
					+ "       |-> spatium.money.remove\n"
					+ "/gravitygun --> spatium.gravitygun\n"
					+ "/day --> spatium.day\n"
					+ "/night --> spatium.night\n"
					+ "/sun --> spatium.sun\n"
					+ "/rain --> spatium.rain\n"
					+ "/setspawn --> spatium.setspawn\n"
					+ "/spawn --> spatium.spawn\n"
					+ "/untarget --> spatium.untarget\n"
					+ "/nick --> spatium.nick\n"
					+ "/fw --> spatium.fw\n"
					+ "/pay --> spatium.pay\n"
					+ "\n"
					+ "Il ne reste plus qu'a les installer ! =p");
		}finally{
			writer.close();
		}
	}
}
