package fr.axicer.SpatiumUtils.Commands.CommandExecutors;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.axicer.SpatiumUtils.Configs.ConfigManager;
import fr.axicer.SpatiumUtils.Utils.ChatUtils;
import fr.axicer.SpatiumUtils.Utils.KitLoader;
import fr.axicer.SpatiumUtils.Utils.Vault;

public class KitCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.kit") || Vault.getPermissions().has(sender, "spatium.*")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("material")){
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Executer cette commande depuis la console pour tout voir =p");
					}else if(args[0].equalsIgnoreCase("enchantment")){
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--Liste des enchantments pour les kits--");
						for(Enchantment enchant : Enchantment.values()){
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+enchant.getName());
						}
					}else if(args[0].equalsIgnoreCase("list")){
						player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--Liste des kits diponible--");
						for(String kit : KitLoader.getKits().keySet()){
							String displayName = ConfigManager.getKitConfig().getString("kits."+kit+".displayName");
							String description = ConfigManager.getKitConfig().getString("kits."+kit+".description");
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+kit);
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"  "+ChatColor.RED+"|-> displayName: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', displayName));
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"  "+ChatColor.RED+"|-> description: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', description));
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"  "+ChatColor.RED+"|-> contenu: ");
							for(ItemStack item: KitLoader.getKits().get(kit)){
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"       |-> "+ChatColor.RESET+item.getItemMeta().getDisplayName()+ChatColor.RESET+","+ChatColor.RESET+item.getType().toString()+ChatColor.RESET+","+ChatColor.RESET+item.getAmount());
							}
							player.sendMessage(ChatUtils.getPluginPrefix()+" ");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("get")){
						if(KitLoader.getKits().containsKey(args[1])){
							for(ItemStack item : KitLoader.getKits().get(args[1])){
								player.getInventory().addItem(item);
							}
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le kit a été donné !");
						}else{
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Ce kit n'existe pas !");
						}
					}else if(args[0].equalsIgnoreCase("addItem")){
						if(player.isOp() || Vault.getPermissions().has(player, "spatium.kit.addItem") || Vault.getPermissions().has(player, "spatium.*")){
							if(KitLoader.getKits().containsKey(args[1])){
								if(player.getItemInHand().getType() != Material.AIR){
									ItemStack newItem = new ItemStack(player.getItemInHand());
									ConfigManager.getKitConfig().set("kits."+args[1]+".items."+newItem.getType().toString()+".name", newItem.getItemMeta().getDisplayName());
									ConfigManager.getKitConfig().set("kits."+args[1]+".items."+newItem.getType().toString()+".material", newItem.getType().toString());
									ConfigManager.getKitConfig().set("kits."+args[1]+".items."+newItem.getType().toString()+".amount", newItem.getAmount());
									ConfigManager.getKitConfig().set("kits."+args[1]+".items."+newItem.getType().toString()+".data", newItem.getData());
									ConfigManager.getKitConfig().set("kits."+args[1]+".items."+newItem.getType().toString()+".lores", newItem.getItemMeta().getLore());
									ArrayList<String> enchantsCompiled = new ArrayList<String>();
									for(Enchantment enchant: newItem.getEnchantments().keySet()){
										enchantsCompiled.add(enchant.getName()+","+newItem.getEnchantmentLevel(enchant));
									}
									ConfigManager.getKitConfig().set("kits."+args[1]+".items."+newItem.getType().toString()+".enchantment", enchantsCompiled);
									ConfigManager.saveKitConfig();
									KitLoader.loadKits();
									player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"L'item a été ajouté !");
								}else{
									player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"L'item dans la main ne peut pas etre de l'air !");
								}
							}else{
								player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le kit est introuvable !");
							}
						}else{
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 3){
					if(args[0].equalsIgnoreCase("get")){
						if(KitLoader.getKits().containsKey(args[1])){
							if(player.isOp() || Vault.getPermissions().has(player, "spatium.kit.other") || Vault.getPermissions().has(player, "spatium.*")){
								Player target = null;
								try{
									target = Bukkit.getPlayer(args[2]);
								}catch(Exception e){}
								if(target != null){
									for(ItemStack item : KitLoader.getKits().get(args[1])){
										target.getInventory().addItem(item);
									}
									target.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Tu a reçu le kit "+args[1]+" !");
									player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le kit a été donné !");
								}else{
									player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
								}
							}else{
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
							}
						}else{
							player.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Ce kit n'existe pas !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
				}
			}else{
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("material")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--== Liste des material pour les kits ==--");
						for(String material: getMaterialName()){
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+material);
						}
					}else if(args[0].equalsIgnoreCase("enchantment")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--== Liste des enchantments pour les kits ==--");
						for(Enchantment enchant : Enchantment.values()){
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+enchant.getName());
						}
					}else if(args[0].equalsIgnoreCase("list")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.AQUA+"--== Liste des kits diponible ==--");
						for(String kit : KitLoader.getKits().keySet()){
							String displayName = ConfigManager.getKitConfig().getString("kits."+kit+".displayName");
							String description = ConfigManager.getKitConfig().getString("kits."+kit+".description");
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"- "+ChatColor.RED+kit);
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"  "+ChatColor.RED+"|-> displayName: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', displayName));
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"  "+ChatColor.RED+"|-> description: "+ChatColor.RESET+ChatColor.translateAlternateColorCodes('&', description));
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.WHITE+"  "+ChatColor.RED+"|-> contenu: ");
							for(ItemStack item: KitLoader.getKits().get(kit)){
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"      |-> "+ChatColor.RESET+item.getItemMeta().getDisplayName()+ChatColor.RESET+","+ChatColor.RESET+item.getType().toString()+ChatColor.RESET+","+ChatColor.RESET+item.getAmount());
							}
							sender.sendMessage(ChatUtils.getPluginPrefix()+" ");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("get")){
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Il faut etre un joueur pour executer cette commande !");
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
					}
				}else if(args.length == 3){
					if(args[0].equalsIgnoreCase("get")){
						if(KitLoader.getKits().containsKey(args[1])){
							if(sender.isOp() || Vault.getPermissions().has(sender, "spatium.kit.other") || Vault.getPermissions().has(sender, "spatium.*")){
								Player target = null;
								try{
									target = Bukkit.getPlayer(args[2]);
								}catch(Exception e){}
								if(target != null){
									for(ItemStack item : KitLoader.getKits().get(args[1])){
										target.getInventory().addItem(item);
									}
									target.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Tu a reçu le kit "+args[1]+" !");
									sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.GREEN+"Le kit a été donné !");
								}else{
									sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Le joueur est introuvable !");
								}
							}else{
								sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
							}
						}else{
							sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Ce kit n'existe pas !");
						}
					}else{
						sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
						sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
					}
				}else{
					sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"La syntaxe est incorrecte !");
					sender.sendMessage(ChatUtils.getPluginPrefix()+"La commande est \""+ChatColor.GOLD+"/kit (get|material|enchantment|list|addItem) [kit]"+ChatColor.RESET+"\".");
				}
			}
		}else{
			sender.sendMessage(ChatUtils.getPluginPrefix()+ChatColor.RED+"Tu n'es pas autorisé a effectuer cette commande !");
		}
		return true;
	}
	
	public ArrayList<String> getMaterialName(){
		ArrayList<String> materialName = new ArrayList<String>();
		materialName.add(Material.ACACIA_DOOR.toString());
		materialName.add(Material.ACACIA_DOOR_ITEM.toString());
		materialName.add(Material.ACACIA_FENCE.toString());
		materialName.add(Material.ACACIA_FENCE_GATE.toString());
		materialName.add(Material.ACACIA_STAIRS.toString());
		materialName.add(Material.ACTIVATOR_RAIL.toString());
		materialName.add(Material.AIR.toString());
		materialName.add(Material.ANVIL.toString());
		materialName.add(Material.APPLE.toString());
		materialName.add(Material.ARMOR_STAND.toString());
		materialName.add(Material.ARROW.toString());
		materialName.add(Material.BAKED_POTATO.toString());
		materialName.add(Material.BANNER.toString());
		materialName.add(Material.BARRIER.toString());
		materialName.add(Material.BEACON.toString());
		materialName.add(Material.BED.toString());
		materialName.add(Material.BED_BLOCK.toString());
		materialName.add(Material.BEDROCK.toString());
		materialName.add(Material.BIRCH_DOOR.toString());
		materialName.add(Material.BIRCH_DOOR_ITEM.toString());
		materialName.add(Material.BIRCH_FENCE.toString());
		materialName.add(Material.BIRCH_FENCE_GATE.toString());
		materialName.add(Material.BIRCH_WOOD_STAIRS.toString());
		materialName.add(Material.BLAZE_POWDER.toString());
		materialName.add(Material.BLAZE_ROD.toString());
		materialName.add(Material.BOAT.toString());
		materialName.add(Material.BONE.toString());
		materialName.add(Material.BOOK.toString());
		materialName.add(Material.BOOK_AND_QUILL.toString());
		materialName.add(Material.BOOKSHELF.toString());
		materialName.add(Material.BOW.toString());
		materialName.add(Material.BOWL.toString());
		materialName.add(Material.BREAD.toString());
		materialName.add(Material.BREWING_STAND.toString());
		materialName.add(Material.BREWING_STAND_ITEM.toString());
		materialName.add(Material.BRICK.toString());
		materialName.add(Material.BRICK_STAIRS.toString());
		materialName.add(Material.BROWN_MUSHROOM.toString());
		materialName.add(Material.BUCKET.toString());
		materialName.add(Material.BURNING_FURNACE.toString());
		materialName.add(Material.CACTUS.toString());
		materialName.add(Material.CAKE.toString());
		materialName.add(Material.CAKE_BLOCK.toString());
		materialName.add(Material.CARPET.toString());
		materialName.add(Material.CARROT.toString());
		materialName.add(Material.CARROT_ITEM.toString());
		materialName.add(Material.CARROT_STICK.toString());
		materialName.add(Material.CAULDRON.toString());
		materialName.add(Material.CAULDRON_ITEM.toString());
		materialName.add(Material.CHAINMAIL_BOOTS.toString());
		materialName.add(Material.CHAINMAIL_CHESTPLATE.toString());
		materialName.add(Material.CHAINMAIL_HELMET.toString());
		materialName.add(Material.CHAINMAIL_LEGGINGS.toString());
		materialName.add(Material.CHEST.toString());
		materialName.add(Material.CLAY.toString());
		materialName.add(Material.CLAY_BALL.toString());
		materialName.add(Material.CLAY_BRICK.toString());
		materialName.add(Material.COAL.toString());
		materialName.add(Material.COAL_BLOCK.toString());
		materialName.add(Material.COAL_ORE.toString());
		materialName.add(Material.COBBLE_WALL.toString());
		materialName.add(Material.COBBLESTONE.toString());
		materialName.add(Material.COCOA.toString());
		materialName.add(Material.COMMAND.toString());
		materialName.add(Material.COMMAND_MINECART.toString());
		materialName.add(Material.COMPASS.toString());
		materialName.add(Material.COOKED_BEEF.toString());
		materialName.add(Material.COOKED_CHICKEN.toString());
		materialName.add(Material.COOKED_FISH.toString());
		materialName.add(Material.COOKED_MUTTON.toString());
		materialName.add(Material.COOKED_RABBIT.toString());
		materialName.add(Material.COOKIE.toString());
		materialName.add(Material.CROPS.toString());
		materialName.add(Material.DARK_OAK_DOOR.toString());
		materialName.add(Material.DARK_OAK_DOOR_ITEM.toString());
		materialName.add(Material.DARK_OAK_FENCE.toString());
		materialName.add(Material.DARK_OAK_FENCE_GATE.toString());
		materialName.add(Material.DARK_OAK_STAIRS.toString());
		materialName.add(Material.DAYLIGHT_DETECTOR.toString());
		materialName.add(Material.DAYLIGHT_DETECTOR_INVERTED.toString());
		materialName.add(Material.DEAD_BUSH.toString());
		materialName.add(Material.DETECTOR_RAIL.toString());
		materialName.add(Material.DIAMOND.toString());
		materialName.add(Material.DIAMOND_AXE.toString());
		materialName.add(Material.DIAMOND_BARDING.toString());
		materialName.add(Material.DIAMOND_BLOCK.toString());
		materialName.add(Material.DIAMOND_BOOTS.toString());
		materialName.add(Material.DIAMOND_CHESTPLATE.toString());
		materialName.add(Material.DIAMOND_HELMET.toString());
		materialName.add(Material.DIAMOND_HOE.toString());
		materialName.add(Material.DIAMOND_LEGGINGS.toString());
		materialName.add(Material.DIAMOND_ORE.toString());
		materialName.add(Material.DIAMOND_PICKAXE.toString());
		materialName.add(Material.DIAMOND_SPADE.toString());
		materialName.add(Material.DIAMOND_SWORD.toString());
		materialName.add(Material.DIODE.toString());
		materialName.add(Material.DIODE_BLOCK_OFF.toString());
		materialName.add(Material.DIODE_BLOCK_ON.toString());
		materialName.add(Material.DIRT.toString());
		materialName.add(Material.DISPENSER.toString());
		materialName.add(Material.DOUBLE_PLANT.toString());
		materialName.add(Material.DOUBLE_STEP.toString());
		materialName.add(Material.DOUBLE_STONE_SLAB2.toString());
		materialName.add(Material.DRAGON_EGG.toString());
		materialName.add(Material.DROPPER.toString());
		materialName.add(Material.EGG.toString());
		materialName.add(Material.EMERALD.toString());
		materialName.add(Material.EMERALD_BLOCK.toString());
		materialName.add(Material.EMERALD_ORE.toString());
		materialName.add(Material.EMPTY_MAP.toString());
		materialName.add(Material.ENCHANTED_BOOK.toString());
		materialName.add(Material.ENCHANTMENT_TABLE.toString());
		materialName.add(Material.ENDER_CHEST.toString());
		materialName.add(Material.ENDER_PEARL.toString());
		materialName.add(Material.ENDER_PORTAL.toString());
		materialName.add(Material.ENDER_PORTAL_FRAME.toString());
		materialName.add(Material.ENDER_STONE.toString());
		materialName.add(Material.EXP_BOTTLE.toString());
		materialName.add(Material.EXPLOSIVE_MINECART.toString());
		materialName.add(Material.EYE_OF_ENDER.toString());
		materialName.add(Material.FEATHER.toString());
		materialName.add(Material.FENCE.toString());
		materialName.add(Material.FENCE_GATE.toString());
		materialName.add(Material.FERMENTED_SPIDER_EYE.toString());
		materialName.add(Material.FIRE.toString());
		materialName.add(Material.FIREBALL.toString());
		materialName.add(Material.FIREWORK.toString());
		materialName.add(Material.FIREWORK_CHARGE.toString());
		materialName.add(Material.FISHING_ROD.toString());
		materialName.add(Material.FLINT.toString());
		materialName.add(Material.FLINT_AND_STEEL.toString());
		materialName.add(Material.FLOWER_POT.toString());
		materialName.add(Material.FLOWER_POT_ITEM.toString());
		materialName.add(Material.FURNACE.toString());
		materialName.add(Material.GHAST_TEAR.toString());
		materialName.add(Material.GLASS.toString());
		materialName.add(Material.GLASS_BOTTLE.toString());
		materialName.add(Material.GLOWING_REDSTONE_ORE.toString());
		materialName.add(Material.GLOWSTONE.toString());
		materialName.add(Material.GLOWSTONE_DUST.toString());
		materialName.add(Material.GOLD_AXE.toString());
		materialName.add(Material.GOLD_BARDING.toString());
		materialName.add(Material.GOLD_BLOCK.toString());
		materialName.add(Material.GOLD_BOOTS.toString());
		materialName.add(Material.GOLD_CHESTPLATE.toString());
		materialName.add(Material.GOLD_HELMET.toString());
		materialName.add(Material.GOLD_HOE.toString());
		materialName.add(Material.GOLD_INGOT.toString());
		materialName.add(Material.GOLD_LEGGINGS.toString());
		materialName.add(Material.GOLD_NUGGET.toString());
		materialName.add(Material.GOLD_ORE.toString());
		materialName.add(Material.GOLD_PICKAXE.toString());
		materialName.add(Material.GOLD_PLATE.toString());
		materialName.add(Material.GOLD_RECORD.toString());
		materialName.add(Material.GOLD_SPADE.toString());
		materialName.add(Material.GOLD_SWORD.toString());
		materialName.add(Material.GOLDEN_APPLE.toString());
		materialName.add(Material.GOLDEN_CARROT.toString());
		materialName.add(Material.GRASS.toString());
		materialName.add(Material.GRAVEL.toString());
		materialName.add(Material.GREEN_RECORD.toString());
		materialName.add(Material.GRILLED_PORK.toString());
		materialName.add(Material.HARD_CLAY.toString());
		materialName.add(Material.HAY_BLOCK.toString());
		materialName.add(Material.HOPPER.toString());
		materialName.add(Material.HOPPER_MINECART.toString());
		materialName.add(Material.HUGE_MUSHROOM_1.toString());
		materialName.add(Material.HUGE_MUSHROOM_2.toString());
		materialName.add(Material.ICE.toString());
		materialName.add(Material.INK_SACK.toString());
		materialName.add(Material.IRON_AXE.toString());
		materialName.add(Material.IRON_BARDING.toString());
		materialName.add(Material.IRON_BLOCK.toString());
		materialName.add(Material.IRON_BOOTS.toString());
		materialName.add(Material.IRON_CHESTPLATE.toString());
		materialName.add(Material.IRON_DOOR.toString());
		materialName.add(Material.IRON_DOOR_BLOCK.toString());
		materialName.add(Material.IRON_FENCE.toString());
		materialName.add(Material.IRON_HELMET.toString());
		materialName.add(Material.IRON_HOE.toString());
		materialName.add(Material.IRON_INGOT.toString());
		materialName.add(Material.IRON_LEGGINGS.toString());
		materialName.add(Material.IRON_ORE.toString());
		materialName.add(Material.IRON_PICKAXE.toString());
		materialName.add(Material.IRON_PLATE.toString());
		materialName.add(Material.IRON_SPADE.toString());
		materialName.add(Material.IRON_SWORD.toString());
		materialName.add(Material.IRON_TRAPDOOR.toString());
		materialName.add(Material.ITEM_FRAME.toString());
		materialName.add(Material.JACK_O_LANTERN.toString());
		materialName.add(Material.JUKEBOX.toString());
		materialName.add(Material.JUNGLE_DOOR.toString());
		materialName.add(Material.JUNGLE_DOOR_ITEM.toString());
		materialName.add(Material.JUNGLE_FENCE.toString());
		materialName.add(Material.JUNGLE_FENCE_GATE.toString());
		materialName.add(Material.JUNGLE_WOOD_STAIRS.toString());
		materialName.add(Material.LADDER.toString());
		materialName.add(Material.LAPIS_BLOCK.toString());
		materialName.add(Material.LAPIS_ORE.toString());
		materialName.add(Material.LAVA.toString());
		materialName.add(Material.LAVA_BUCKET.toString());
		materialName.add(Material.LEASH.toString());
		materialName.add(Material.LEATHER.toString());
		materialName.add(Material.LEATHER_BOOTS.toString());
		materialName.add(Material.LEATHER_CHESTPLATE.toString());
		materialName.add(Material.LEATHER_HELMET.toString());
		materialName.add(Material.LEATHER_LEGGINGS.toString());
		materialName.add(Material.LEAVES.toString());
		materialName.add(Material.LEAVES_2.toString());
		materialName.add(Material.LEVER.toString());
		materialName.add(Material.LOG.toString());
		materialName.add(Material.LOG_2.toString());
		materialName.add(Material.LONG_GRASS.toString());
		materialName.add(Material.MAGMA_CREAM.toString());
		materialName.add(Material.MAP.toString());
		materialName.add(Material.MELON.toString());
		materialName.add(Material.MELON_BLOCK.toString());
		materialName.add(Material.MELON_SEEDS.toString());
		materialName.add(Material.MELON_STEM.toString());
		materialName.add(Material.MILK_BUCKET.toString());
		materialName.add(Material.MINECART.toString());
		materialName.add(Material.MOB_SPAWNER.toString());
		materialName.add(Material.MONSTER_EGG.toString());
		materialName.add(Material.MONSTER_EGGS.toString());
		materialName.add(Material.MOSSY_COBBLESTONE.toString());
		materialName.add(Material.MUSHROOM_SOUP.toString());
		materialName.add(Material.MUTTON.toString());
		materialName.add(Material.MYCEL.toString());
		materialName.add(Material.NAME_TAG.toString());
		materialName.add(Material.NETHER_BRICK.toString());
		materialName.add(Material.NETHER_BRICK_ITEM.toString());
		materialName.add(Material.NETHER_BRICK_STAIRS.toString());
		materialName.add(Material.NETHER_FENCE.toString());
		materialName.add(Material.NETHER_STALK.toString());
		materialName.add(Material.NETHER_STAR.toString());
		materialName.add(Material.NETHER_WARTS.toString());
		materialName.add(Material.NETHERRACK.toString());
		materialName.add(Material.NOTE_BLOCK.toString());
		materialName.add(Material.OBSIDIAN.toString());
		materialName.add(Material.PACKED_ICE.toString());
		materialName.add(Material.PAINTING.toString());
		materialName.add(Material.PAPER.toString());
		materialName.add(Material.PISTON_BASE.toString());
		materialName.add(Material.PISTON_EXTENSION.toString());
		materialName.add(Material.PISTON_MOVING_PIECE.toString());
		materialName.add(Material.PISTON_STICKY_BASE.toString());
		materialName.add(Material.POISONOUS_POTATO.toString());
		materialName.add(Material.PORK.toString());
		materialName.add(Material.PORTAL.toString());
		materialName.add(Material.POTATO.toString());
		materialName.add(Material.POTATO_ITEM.toString());
		materialName.add(Material.POTION.toString());
		materialName.add(Material.POWERED_MINECART.toString());
		materialName.add(Material.POWERED_RAIL.toString());
		materialName.add(Material.PRISMARINE.toString());
		materialName.add(Material.PRISMARINE_CRYSTALS.toString());
		materialName.add(Material.PRISMARINE_SHARD.toString());
		materialName.add(Material.PUMPKIN.toString());
		materialName.add(Material.PUMPKIN_PIE.toString());
		materialName.add(Material.PUMPKIN_SEEDS.toString());
		materialName.add(Material.PUMPKIN_STEM.toString());
		materialName.add(Material.QUARTZ.toString());
		materialName.add(Material.QUARTZ_BLOCK.toString());
		materialName.add(Material.QUARTZ_ORE.toString());
		materialName.add(Material.QUARTZ_STAIRS.toString());
		materialName.add(Material.RABBIT.toString());
		materialName.add(Material.RABBIT_FOOT.toString());
		materialName.add(Material.RABBIT_HIDE.toString());
		materialName.add(Material.RABBIT_STEW.toString());
		materialName.add(Material.RAILS.toString());
		materialName.add(Material.RAW_BEEF.toString());
		materialName.add(Material.RAW_CHICKEN.toString());
		materialName.add(Material.RAW_FISH.toString());
		materialName.add(Material.RECORD_10.toString());
		materialName.add(Material.RECORD_11.toString());
		materialName.add(Material.RECORD_12.toString());
		materialName.add(Material.RECORD_3.toString());
		materialName.add(Material.RECORD_4.toString());
		materialName.add(Material.RECORD_5.toString());
		materialName.add(Material.RECORD_6.toString());
		materialName.add(Material.RECORD_7.toString());
		materialName.add(Material.RECORD_8.toString());
		materialName.add(Material.RECORD_9.toString());
		materialName.add(Material.RED_MUSHROOM.toString());
		materialName.add(Material.RED_ROSE.toString());
		materialName.add(Material.RED_SANDSTONE.toString());
		materialName.add(Material.RED_SANDSTONE_STAIRS.toString());
		materialName.add(Material.REDSTONE.toString());
		materialName.add(Material.REDSTONE_BLOCK.toString());
		materialName.add(Material.REDSTONE_COMPARATOR.toString());
		materialName.add(Material.REDSTONE_COMPARATOR_OFF.toString());
		materialName.add(Material.REDSTONE_COMPARATOR_ON.toString());
		materialName.add(Material.REDSTONE_LAMP_OFF.toString());
		materialName.add(Material.REDSTONE_LAMP_ON.toString());
		materialName.add(Material.REDSTONE_ORE.toString());
		materialName.add(Material.REDSTONE_TORCH_OFF.toString());
		materialName.add(Material.REDSTONE_TORCH_ON.toString());
		materialName.add(Material.REDSTONE_WIRE.toString());
		materialName.add(Material.ROTTEN_FLESH.toString());
		materialName.add(Material.SADDLE.toString());
		materialName.add(Material.SAND.toString());
		materialName.add(Material.SANDSTONE.toString());
		materialName.add(Material.SANDSTONE_STAIRS.toString());
		materialName.add(Material.SAPLING.toString());
		materialName.add(Material.SEA_LANTERN.toString());
		materialName.add(Material.SEEDS.toString());
		materialName.add(Material.SHEARS.toString());
		materialName.add(Material.SIGN.toString());
		materialName.add(Material.SIGN_POST.toString());
		materialName.add(Material.SKULL.toString());
		materialName.add(Material.SKULL_ITEM.toString());
		materialName.add(Material.SLIME_BALL.toString());
		materialName.add(Material.SLIME_BLOCK.toString());
		materialName.add(Material.SMOOTH_BRICK.toString());
		materialName.add(Material.SMOOTH_STAIRS.toString());
		materialName.add(Material.SNOW.toString());
		materialName.add(Material.SNOW_BALL.toString());
		materialName.add(Material.SNOW_BLOCK.toString());
		materialName.add(Material.SOIL.toString());
		materialName.add(Material.SOUL_SAND.toString());
		materialName.add(Material.SPECKLED_MELON.toString());
		materialName.add(Material.SPIDER_EYE.toString());
		materialName.add(Material.SPONGE.toString());
		materialName.add(Material.SPRUCE_DOOR.toString());
		materialName.add(Material.SPRUCE_DOOR_ITEM.toString());
		materialName.add(Material.SPRUCE_FENCE.toString());
		materialName.add(Material.SPRUCE_FENCE_GATE.toString());
		materialName.add(Material.SPRUCE_WOOD_STAIRS.toString());
		materialName.add(Material.STAINED_CLAY.toString());
		materialName.add(Material.STAINED_GLASS.toString());
		materialName.add(Material.STAINED_GLASS_PANE.toString());
		materialName.add(Material.STANDING_BANNER.toString());
		materialName.add(Material.STATIONARY_LAVA.toString());
		materialName.add(Material.STATIONARY_WATER.toString());
		materialName.add(Material.STEP.toString());
		materialName.add(Material.STICK.toString());
		materialName.add(Material.STONE.toString());
		materialName.add(Material.STONE_AXE.toString());
		materialName.add(Material.STONE_BUTTON.toString());
		materialName.add(Material.STONE_HOE.toString());
		materialName.add(Material.STONE_PICKAXE.toString());
		materialName.add(Material.STONE_PLATE.toString());
		materialName.add(Material.STONE_SLAB2.toString());
		materialName.add(Material.STONE_SPADE.toString());
		materialName.add(Material.STONE_SWORD.toString());
		materialName.add(Material.STORAGE_MINECART.toString());
		materialName.add(Material.STRING.toString());
		materialName.add(Material.SUGAR.toString());
		materialName.add(Material.SUGAR_CANE.toString());
		materialName.add(Material.SUGAR_CANE_BLOCK.toString());
		materialName.add(Material.SULPHUR.toString());
		materialName.add(Material.THIN_GLASS.toString());
		materialName.add(Material.TNT.toString());
		materialName.add(Material.TORCH.toString());
		materialName.add(Material.TRAP_DOOR.toString());
		materialName.add(Material.TRAPPED_CHEST.toString());
		materialName.add(Material.TRIPWIRE.toString());
		materialName.add(Material.TRIPWIRE_HOOK.toString());
		materialName.add(Material.VINE.toString());
		materialName.add(Material.WALL_BANNER.toString());
		materialName.add(Material.WALL_SIGN.toString());
		materialName.add(Material.WATCH.toString());
		materialName.add(Material.WATER.toString());
		materialName.add(Material.WATER_BUCKET.toString());
		materialName.add(Material.WATER_LILY.toString());
		materialName.add(Material.WEB.toString());
		materialName.add(Material.WHEAT.toString());
		materialName.add(Material.WOOD.toString());
		materialName.add(Material.WOOD_AXE.toString());
		materialName.add(Material.WOOD_BUTTON.toString());
		materialName.add(Material.WOOD_DOOR.toString());
		materialName.add(Material.WOOD_DOUBLE_STEP.toString());
		materialName.add(Material.WOOD_HOE.toString());
		materialName.add(Material.WOOD_PICKAXE.toString());
		materialName.add(Material.WOOD_PLATE.toString());
		materialName.add(Material.WOOD_SPADE.toString());
		materialName.add(Material.WOOD_STAIRS.toString());
		materialName.add(Material.WOOD_STEP.toString());
		materialName.add(Material.WOOD_SWORD.toString());
		materialName.add(Material.WOODEN_DOOR.toString());
		materialName.add(Material.WOOL.toString());
		materialName.add(Material.WORKBENCH.toString());
		materialName.add(Material.WRITTEN_BOOK.toString());
		materialName.add(Material.YELLOW_FLOWER.toString());
		return materialName;
	}
}
