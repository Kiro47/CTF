package com.kiro.ctf.cmds;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.kiro.ctf.CTFMain;
import com.kiro.ctf.arena.Arena;
import com.kiro.ctf.arena.ArenaManager;
import com.kiro.ctf.utils.SettingsManager;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class arena {

	private static String prefix = (ChatColor.GOLD + "[" + ChatColor.AQUA
			+ "CTF" + ChatColor.GOLD + "]  ");

	public static void delete(final Player p, String arenaName) {

		final String name = arenaName;
		p.sendMessage(ChatColor.GOLD
				+ "Please confirm or deny deleting arena: " + name);
		p.sendMessage(ChatColor.GOLD + "Confirm with: /yes  or  /no");
		confirm.needsConfirmed.add(p);
		
			SettingsManager.getArenas().set(name, null);
			SettingsManager.getArenas().save();
			return;
		}

	public static void create(Player p, String arenaName, Selection sel) {

		SettingsManager.getArenas().createSection(arenaName);
		SettingsManager.getArenas().set(arenaName + ".world",
				sel.getWorld().getName());
		CTFMain.saveLocation(sel.getMinimumPoint(), SettingsManager.getArenas()
				.createSection(arenaName + ".cornerA"));
		CTFMain.saveLocation(sel.getMaximumPoint(), SettingsManager.getArenas()
				.createSection(arenaName + ".cornerB"));
		SettingsManager.getArenas().save();
		ArenaManager.getInstance().setup();
		p.sendMessage(prefix + ChatColor.GREEN + "Created Arena:" + arenaName
				+ ", Please set spawns now.");
		return;

	}

	public static void list(Player p) {
		Iterator<Arena> it = ArenaManager.getInstance().getArenas().iterator();
		while (it.hasNext()) {
			p.sendMessage(ChatColor.LIGHT_PURPLE + it.next().toString());
		}
	}
}
