package com.kiro.ctf.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.kiro.ctf.utils.SettingsManager;

public class kit {

	public static void delete(Player p, String kitName) {

		final String name = kitName;
		
			 SettingsManager.getKits().set(name, null);
			 p.sendMessage(ChatColor.GREEN + "Kit: " + name + " has been succefully deleted!");
			 p.sendMessage(ChatColor.GREEN + "Only Effective after a reload!");
			 SettingsManager.getKits().save();
			 return;

	}
}
