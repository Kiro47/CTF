package com.kiro.ctf.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kiro.ctf.CTFMain;
import com.kiro.ctf.SpawnMethods;
import com.kiro.ctf.arena.Arena;
import com.kiro.ctf.arena.ArenaManager;
import com.kiro.ctf.kits.KitManager;
import com.kiro.ctf.kits.LoadoutUtils;
import com.kiro.ctf.utils.Chat;
import com.kiro.ctf.utils.PermCache;
import com.kiro.ctf.utils.SettingsManager;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class CTFCommands implements CommandExecutor {

	private String noPerm = (ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF"
			+ ChatColor.GOLD + "]" + ChatColor.RED + "Not Enough Permissions!");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label,
			String[] args) {

		String init = ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF"
				+ ChatColor.GOLD + "]";
		String prefix = (ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF"
				+ ChatColor.GOLD + "]  ");
		if (!(s instanceof Player)) {
			s.sendMessage(ChatColor.RED + "Only usable ingame!");
			return true;
		}
		Player p = (Player) s;
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (!(p.hasPermission(PermCache.PERM_BASE))) {
			p.sendMessage(noPerm);
			return true;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args.length == 0) {
			p.sendMessage(init);
			p.sendMessage(ChatColor.RED
					+ "Please Specify an argument! Use /ctf help for a list of commands!");
			return true;
		}
		// ///////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("help")) {
			if (!(p.hasPermission(PermCache.PERM_HELP))) {
				p.sendMessage(noPerm);
				return true;
			}
			String pre = prefix + ChatColor.LIGHT_PURPLE + "/ctf ";
			p.sendMessage(Chat.center(init));
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "setkit <KitName>  ; "
					+ ChatColor.GOLD
					+ "Saves your current inventory and armor as a kit.");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "loadkit <KitName> ; "
					+ ChatColor.GOLD
					+ "Replaces your current inventory with a saved kit!");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE
					+ "listkit               ; " + ChatColor.GOLD
					+ "Lists the available kits that have been made!");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "delkit <KitName>  ; "
					+ ChatColor.GOLD + "Deletes a kit from the config file!");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE
					+ "setarena <ArenaName>  ; " + ChatColor.GOLD
					+ "Sets an arena!");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE
					+ "delarena <ArenaName>  ; " + ChatColor.GOLD
					+ "Deletes an arena from the config file!");

			p.sendMessage(pre + ChatColor.LIGHT_PURPLE
					+ "help                ; " + ChatColor.GOLD
					+ "Displays this message!");
			// HELP COMMAND
			return true;
		}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("setspawn")) {

			 if (!(p.hasPermission(PermCache.PERM_ADD_SPAWN))){
				 p.sendMessage(noPerm);
				 return true;
			 }
			 if (!(args.length == 3)) {
				 p.sendMessage(prefix + ChatColor.RED + "Invalid Syntax! Please Select which team the spawn belonds too and the arena!!");
				 p.sendMessage(prefix + ChatColor.DARK_RED + "/ctf setspawn <Arena> <Team>"); 
				 return true;
			 }
			 String arena = args[1];
			 String team = args[2];
			 Arena a = ArenaManager.getInstance().getArena(arena);
			 if (a == null) {
				 p.sendMessage(prefix + ChatColor.RED + "The arena: " + arena + " does not exist!");
				 p.sendMessage(prefix + ChatColor.DARK_RED + "/ctf setspawn <Arena> <Team>"); 
				return true;
			 }
			 if (!(SpawnMethods.teams.contains(team))) {
				 p.sendMessage(prefix + ChatColor.RED + "Team: " + team + " is not a valid team! Use /ctf listteam for a list of available ");
				 //////////MAKE LIST TEAM COMMAND
				 p.sendMessage(prefix + ChatColor.DARK_RED + "/ctf setspawn <Arena> <Team>");
				 return true;
			 }
			 else {
				 SpawnMethods.addSpawn(p, team, a, prefix);
				 return true;
			 }
		 
		}
		
	/////////////////// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("kit")) {

			if (!(args.length == 2) || !(args.length == 3)) {
				p.sendMessage(Chat.center(init));
				p.sendMessage(ChatColor.LIGHT_PURPLE + "/ctf kit list");
				p.sendMessage(ChatColor.LIGHT_PURPLE + "/ctf kit set <KitName>");
				p.sendMessage(ChatColor.LIGHT_PURPLE
						+ "/ctf kit delete <KitName>");
				p.sendMessage(ChatColor.LIGHT_PURPLE
						+ "/ctf kit load <KitName>");
				return true;
			}
			// ////////////////////////////////////
			if (args[1].equalsIgnoreCase("list")) {
				if (!(p.hasPermission(PermCache.PERM_KIT_LIST))) {
					p.sendMessage(noPerm);
					return true;
				} else {
					p.sendMessage(prefix + ChatColor.GREEN + "Saved Kits: "
							+ ChatColor.LIGHT_PURPLE
							+ KitManager.getInstance().kitList());
					return true;
				}
			}
			// ////////////////////////////////////////
			if (args[1].equalsIgnoreCase("delete")) {
				if (!(p.hasPermission(PermCache.PERM_DELTE_KIT))) {
					p.sendMessage(noPerm);
					return true;
				}
				if (!(args.length == 3)) {
					p.sendMessage(ChatColor.RED
							+ "Please specify a kit to delete!");
					p.sendMessage(ChatColor.DARK_RED
							+ "/ctf kit delete <KitName>");
					return true;
				}
				if (SettingsManager.getKits().get(args[2]) == null) {
					p.sendMessage(prefix + ChatColor.RED + "The kit " + args[2]
							+ " is not a valid kit!");
					return true;
				} else {
					kit.delete(p, args[2]);
					return true;
				}
			}
			// ///////////////////////////////////
			if (args[1].equalsIgnoreCase("set")) {
				if (!(p.hasPermission(PermCache.PERM_KIT_SET))) {
					p.sendMessage(noPerm);
					return true;
				}
				if (!(args.length == 3)) {
					p.sendMessage(ChatColor.RED
							+ "Please specify a kit to delete!");
					p.sendMessage(ChatColor.DARK_RED + "/ctf kit set <KitName>");
					return true;
				}
				if (SettingsManager.getKits().get(args[2]) == null) {
					p.sendMessage(prefix + ChatColor.RED + "The kit " + args[2]
							+ " is not a valid kit!");
					return true;
				} else {
					LoadoutUtils.saveKit(p, args[1]);
					p.sendMessage(prefix + ChatColor.GREEN + "Kit "
							+ ChatColor.LIGHT_PURPLE + args[1]
							+ ChatColor.GREEN + " succesfully saved");
					return true;
				}
			}
			// ////////////////////////////////////////
			if (args[1].equalsIgnoreCase("load")) {
				if (!(p.hasPermission(PermCache.PERM_KIT_LOAD))) {
					p.sendMessage(noPerm);
					return true;
				}
				if (!(args.length == 3)) {
					p.sendMessage(ChatColor.RED
							+ "Please specify a kit to delete!");
					p.sendMessage(ChatColor.DARK_RED
							+ "/ctf kit load <KitName>");
					return true;
				} else {

					if (LoadoutUtils.loadKit(p, args[1]) == false) {
						p.sendMessage(prefix + ChatColor.RED + "Kit: "
								+ ChatColor.LIGHT_PURPLE + args[1]
								+ ChatColor.RED + " is an invalid kit name!");
						return true;
					} else {
						LoadoutUtils.loadKit(p, args[1]);
						p.sendMessage(prefix + ChatColor.GREEN + "Kit: "
								+ ChatColor.LIGHT_PURPLE + args[1]
								+ ChatColor.GREEN + " has been loaded");
						return true;
					}

				}
			}
			// ////////////////////////////////////////
	/////////////// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
		if (args[0].equalsIgnoreCase("arena")) {

			if (!(args.length == 2) || !(args.length == 3)) {
				p.sendMessage(Chat.center(init));
				p.sendMessage(ChatColor.LIGHT_PURPLE + "/ctf arena list");
				p.sendMessage(ChatColor.LIGHT_PURPLE
						+ "/ctf arena create <KitName>");
				p.sendMessage(ChatColor.LIGHT_PURPLE
						+ "/ctf arena delete <KitName>");
				return true;
			}
			// ////////////////////////////////////
			if (args[1].equalsIgnoreCase("list")) {
				if (!(p.hasPermission(PermCache.PERM_ARENA_LIST))) {
					p.sendMessage(noPerm);
					return true;
				} else {
					p.sendMessage(prefix + "Arenas: ");
					arena.list(p);
					return true;
				}
			}
			// ////////////////////////////////////////
			if (args[1].equalsIgnoreCase("delete")) {
				if (!(p.hasPermission(PermCache.PERM_DELETE_ARENA))) {
					p.sendMessage(noPerm);
					return true;
				}
				if (!(args.length == 3)) {
					p.sendMessage(ChatColor.RED
							+ "Please specify an arena to delete!");
					p.sendMessage(ChatColor.DARK_RED
							+ "/ctf arena delete <ArenaName>");
					return true;
				}
				final String name = args[1];
				if (ArenaManager.getInstance().getArena(name) == null) {
					p.sendMessage(ChatColor.RED + "The arena " + name
							+ " does not exist!");
					return true;
				} else {
					arena.delete(p, name);
					return true;
				}
			}
			// ///////////////////////////////////
			if (args[1].equalsIgnoreCase("create")) {
				if (!(p.hasPermission(PermCache.PERM_KIT_SET))) {
					p.sendMessage(noPerm);
					return true;
				}
				if (!(args.length == 3)) {
					p.sendMessage(ChatColor.RED
							+ "Please specify an arena to delete!");
					p.sendMessage(ChatColor.DARK_RED
							+ "/ctf arena create <ArenaName>");
					return true;
				}
				String arenaName = args[2];
				if (ArenaManager.getInstance().getArena(arenaName) != null) {
					p.sendMessage(ChatColor.RED
							+ "An arena with that name already exists!");
					return true;
				}
				Selection sel = CTFMain.getWorldEdit().getSelection(p);
				if (sel == null) {
					p.sendMessage(ChatColor.RED
							+ "Please make a worldedit selection for the arena!");
					return true;
				} else {
					arena.create(p, arenaName, sel);
					return true;
				}

			}
			// ////////////////////////////////////////
			// /////////////////////////////////////////////////////////////////////

		}
		 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0] == null) {
			p.sendMessage(init);
			return true;
		}
		
		else {
			p.sendMessage(prefix + ChatColor.RED + "Invalid argument. Use" + ChatColor.LIGHT_PURPLE +  "/ctf help" + ChatColor.RED + " for help!");
			return true;
		}
	}

	
	}
