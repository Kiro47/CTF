package com.kiro.ctf.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kiro.ctf.CTFMain;
import com.kiro.ctf.SpawnMethods;
import com.kiro.ctf.arena.ArenaManager;
import com.kiro.ctf.kits.KitManager;
import com.kiro.ctf.kits.LoadoutUtils;
import com.kiro.ctf.utils.Chat;
import com.kiro.ctf.utils.PermCache;
import com.kiro.ctf.utils.SettingsManager;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class CTFCommand implements CommandExecutor{

	private String noPerm = (ChatColor.RED + "Not Enough Permissions!");
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String init = ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF" + ChatColor.GOLD + "]";
		String prefix = (ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF" + ChatColor.GOLD + "]  ");
		if (!(s instanceof Player)) {
			s.sendMessage(ChatColor.RED + "Only usable ingame!");
			return true;
		}
		Player p = (Player) s;
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (!(p.hasPermission(PermCache.PERM_BASE))){
			p.sendMessage(noPerm);
			return true;
		}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args.length == 0) {
			p.sendMessage(init);
			p.sendMessage(ChatColor.RED + "Please Specify an argument! Use /ctf help for a list of commands!");
			return true;
		}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("help")) {
			if (!(p.hasPermission(PermCache.PERM_HELP))) {
				p.sendMessage(noPerm);
				return true;
			}
			String pre = prefix + ChatColor.LIGHT_PURPLE +"/ctf ";
			p.sendMessage(Chat.center(init));
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "setkit <KitName>  ; " + ChatColor.GOLD + "Saves your current inventory and armor as a kit.");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "loadkit <KitName> ; " + ChatColor.GOLD + "Replaces your current inventory with a saved kit!");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "listkit               ; " + ChatColor.GOLD + "Lists the available kits that have been made!"); 
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "delkit <KitName>  ; " + ChatColor.GOLD + "Deletes a kit from the config file!" );
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "setarena <ArenaName>  ; " + ChatColor.GOLD + "Sets an arena!");
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "delarena <ArenaName>  ; " + ChatColor.GOLD + "Deletes an arena from the config file!");
			
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "help                ; " + ChatColor.GOLD + "Displays this message!");
			// HELP COMMAND
			return true;
		}
	////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("delkit")) {
			if (!(p.hasPermission(PermCache.PERM_DELTE_KIT))) {
				p.sendMessage(noPerm);
				return true;
			}
			if (!(args.length == 2)){
				p.sendMessage(ChatColor.RED + "Please specify a kit to delete!");
				p.sendMessage(ChatColor.DARK_RED + "/ctf delkit <KitName>");
				return true;
			}
			final String name = args[2];
			if (SettingsManager.getKits().get(name) == null) {
				 p.sendMessage(ChatColor.RED + "The kit " + name + " does not exist!");
				return true;
			}
			else {
				final Player player = p;
				confirm.needsConfirmed.add(p);
				 p.sendMessage(ChatColor.GOLD + "Please confirm or deny deleting kit: " + name);
				 p.sendMessage(ChatColor.GOLD + "Confirm with: /yes  or  /no");
				 CTFMain.getPlugin().getServer().getScheduler().scheduleAsyncDelayedTask(CTFMain.getPlugin(), new Runnable() {
					 public void run() {
						 if (confirm.needsConfirmed.contains(player)) {
							 player.sendMessage(ChatColor.DARK_RED + "Deletion of kit neither confirmed nor denied! Deletion Request for kit: " + name + " has been cancelled!");
							 confirm.needsConfirmed.remove(player);
							 return;
						 }
						 else {
							 return;
						 }
					 }
				 },(long) 200);
				 if (confirm.confirmAction(p) == true) {
					 SettingsManager.getArenas().set(name, null);
					 p.sendMessage(ChatColor.GREEN + "Kit: " + name + " has been succefully deleted!");
					 p.sendMessage(ChatColor.GREEN + "Only Effective after a reload!");
					 SettingsManager.getKits().save();
					 return true;
				 }
				 else {
					 p.sendMessage(ChatColor.RED + "Deletion of kit: "  + name + " has been cancelled!");
					 return true;
				 }
			 
			}
		}
	////////////////////////////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("setkit")) {
			if (!(p.hasPermission(PermCache.PERM_KIT_SET))) {
				p.sendMessage(noPerm);
				return true;
			}
			if (!(args.length == 2)) {
				p.sendMessage(prefix + ChatColor.RED + "Please specify a name for the kit!");
				p.sendMessage(prefix + ChatColor.DARK_RED + "/ctf setkit <KitName>");
				return true;
			}
			else {				
				LoadoutUtils.saveKit(p, args[1]);
				p.sendMessage(prefix + ChatColor.GREEN + "Kit " + ChatColor.LIGHT_PURPLE + args[1]+ ChatColor.GREEN  + " succesfully saved");
				return true;
			}
			
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////	
		if (args[0].equalsIgnoreCase("loadkit")) {
			if (!(p.hasPermission(PermCache.PERM_KIT_LOAD))) {
				p.sendMessage(noPerm);
				return true;
			}
			if (!(args.length == 2)) {
				p.sendMessage(prefix + ChatColor.RED + "Please specify a name for the kit!");
				p.sendMessage(prefix + ChatColor.DARK_RED + "/ctf loadkit <KitName>");
				return true;
			}
			else {
				if (LoadoutUtils.loadKit(p, args[1]) == false) {
					p.sendMessage(prefix+ ChatColor.RED + "Kit: " + ChatColor.LIGHT_PURPLE + args[1] + ChatColor.RED + " is an invalid kit name!");
					return true;
				}
				else {
				LoadoutUtils.loadKit(p, args[1]);
				p.sendMessage(prefix + ChatColor.GREEN + "Kit: " + ChatColor.LIGHT_PURPLE +  args[1] + ChatColor.GREEN + " has been loaded"); 
				return true;
					}
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		if (args[0].equalsIgnoreCase("listkit")) {
			if (!(p.hasPermission(PermCache.PERM_KIT_LIST))) {
				p.sendMessage(noPerm);
				return true;
			}
			p.sendMessage(prefix + ChatColor.GREEN + "Saved Kits: " + ChatColor.LIGHT_PURPLE + KitManager.getInstance().kitList() );
			return true;
		}
	//////////////////////////////////////////////////////////////////////////////
		if (args[0].equalsIgnoreCase("setarena")) {
			if (!(p.hasPermission(PermCache.PERM_KIT_SET))) {
				p.sendMessage(noPerm);
				return true;
			}
			if (!(args.length == 2)) {
				p.sendMessage(ChatColor.RED + "Please specify a name for the arena!");
				p.sendMessage(ChatColor.DARK_RED + "/ctf setarena <ArenaName>");
				return true;
			}
			else {
				//////////////////////////////////
				String name = args[1].replace(".", "") ; 
				if (ArenaManager.getInstance().getArena(name) !=null) {
					p.sendMessage(ChatColor.RED + "An arena with that name already exists!");
					return true;
				}
				Selection sel = CTFMain.getWorldEdit().getSelection(p);
				if (sel == null) {
					p.sendMessage(ChatColor.RED + "Please make a worldedit selection for the arena!");
					return true;
				}
				else {
					SettingsManager.getArenas().createSection(name);
					SettingsManager.getArenas().set(name + ".world", sel.getWorld().getName());
					CTFMain.saveLocation(sel.getMinimumPoint(), SettingsManager.getArenas().createSection(name + ".cornerA") );
					CTFMain.saveLocation(sel.getMaximumPoint(), SettingsManager.getArenas().createSection(name + ".cornerB") );
					SettingsManager.getArenas().save();
					ArenaManager.getInstance().setup();
					p.sendMessage(prefix + ChatColor.GREEN + "Created Arena:"  + name + ", Please set spawns now."); 
					return true;
				}
			}
		
		}
////////////////////////////////////////////////////////////////////////////////////////
	 if (args[0].equalsIgnoreCase("delarena")) {
		 if (!(p.hasPermission(PermCache.PERM_DELETE_ARENA))) {
			 p.sendMessage(noPerm);
			 return true;
		 }
		 if (!(args.length == 2)) {
			 p.sendMessage(ChatColor.RED + "Please specify an arena to delete!");
			 p.sendMessage(ChatColor.DARK_RED + "/ctf delarena <ArenaName>");
			 return true;
		 }
		 final String name = args[1];
		 if (ArenaManager.getInstance().getArena(name) == null) {
			 p.sendMessage(ChatColor.RED + "The arena " + name + " does not exist!");
			 return true;
		 }
		 else {
			final Player player = p;
			 p.sendMessage(ChatColor.GOLD + "Please confirm or deny deleting arena: " + name);
			 p.sendMessage(ChatColor.GOLD + "Confirm with: /yes  or  /no");
			 confirm.needsConfirmed.add(p);
			 CTFMain.getPlugin().getServer().getScheduler().scheduleAsyncDelayedTask(CTFMain.getPlugin(), new Runnable() {
				 public void run() {
					 if (confirm.needsConfirmed.contains(player)) {
						 player.sendMessage(ChatColor.DARK_RED + "Deletion of arena neither confirmed nor denied! Deletion Request for kit: " + name + " has been cancelled!");
						 confirm.needsConfirmed.remove(player);
						 return;
					 }
					 else {
						 return;
					 }
				 }
			 },(long) 200);
			 if (confirm.confirmAction(p) == true) {
				 SettingsManager.getArenas().set(name, null);
				 p.sendMessage(ChatColor.GREEN + "Arena: " + name + " has been succefully deleted!");
				 p.sendMessage(ChatColor.GREEN + "Only Effective after a reload!");
				 SettingsManager.getArenas().save();
				 return true;
			 }
			 else {
				 p.sendMessage(ChatColor.RED + "Deletion of arena: "  + name + " has been cancelled!");
				 return true;
			 }
		 }
	 }
	 /////////////////////////////////////////////////////////////////////////////////////////////////
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
		 if (ArenaManager.getInstance().getArena(arena) == null) {
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
			 ///////add the spawn
		 }
	 }
	 /////////////////////////////////////////////////////////////////////////////////////////////////
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
