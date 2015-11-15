package com.kiro.ctf.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CTFCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String init = ChatColor.GOLD + "[" + ChatColor.AQUA + "CTF" + ChatColor.GOLD + "]";
		
		if (!(s instanceof Player)) {
			s.sendMessage(ChatColor.RED + "Only usable ingame!");
			return true;
		}
		
		Player p = (Player) s;
		
		if (args.length == 0) {
			p.sendMessage(init);
			p.sendMessage(ChatColor.RED + "Please Specify an argument! Use /ctf help for a list of commands!");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("help")) {
			p.sendMessage(init);
			// HELP COMMAND
			return true;
		}
		
		if (args[0] == null) {
			p.sendMessage(init);
			return true;
		}
		return true;
	}

	
}
