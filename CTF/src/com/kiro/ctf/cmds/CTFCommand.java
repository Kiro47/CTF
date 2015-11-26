package com.kiro.ctf.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kiro.ctf.CTFMain;
import com.kiro.ctf.arena.ArenaManager;
import com.kiro.ctf.kits.KitManager;
import com.kiro.ctf.kits.LoadoutUtils;
import com.kiro.ctf.utils.Chat;
import com.kiro.ctf.utils.PermCache;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class CTFCommand implements CommandExecutor{

	private String noPerm = (ChatColor.RED + "Not Enough Permissions!");
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String init = ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF" + ChatColor.GOLD + "]";
		String prefix = (ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF" + ChatColor.GOLD + "]  ");
		if (!(s instanceof Player)) {
			s.sendMessage(ChatColor.RED + "Only usable ingame!");
			return true;
		}
		Player p = (Player) s;
		
		if (!(p.hasPermission(PermCache.PERM_BASE))){
			p.sendMessage(noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage(init);
			p.sendMessage(ChatColor.RED + "Please Specify an argument! Use /ctf help for a list of commands!");
			return true;
		}
		
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
			
			p.sendMessage(pre + ChatColor.LIGHT_PURPLE + "help                ; " + ChatColor.GOLD + "Displays this message!");
			// HELP COMMAND
			return true;
		}
		
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
		
		if (args[0].equalsIgnoreCase("listkit")) {
			if (!(p.hasPermission(PermCache.PERM_KIT_LIST))) {
				p.sendMessage(noPerm);
				return true;
			}
			p.sendMessage(prefix + ChatColor.GREEN + "Saved Kits: " + ChatColor.LIGHT_PURPLE + KitManager.getInstance().kitList() );
			return true;
		}
		
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
				String name = args[2].replace(".", "") ; 
				if (ArenaManager.getInstance().getArena(name) !=null) {
					p.sendMessage(ChatColor.RED + "An arena with that name already exists!");
					return true;
				}
				Selection sel = CTFMain.getWorldEdit().getSelection(p);
				if (sel == null) {
					p.sendMessage(ChatColor.RED + "Please make a worldedit selection for the arena!");
					return true;
				}
				return true;
			}
		
		}
		
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
		 else {
			 /////////////////
			 
			 return true;
		 }
		 
		 
	 }
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
