package com.kiro.ctf.arena;

import org.bukkit.entity.Player;

import com.kiro.ctf.utils.SettingsManager;
import com.sk89q.worldedit.bukkit.selections.Selection;

import net.md_5.bungee.api.ChatColor;

public class ArenaMethods {

	public static boolean createArena(Player player, String name, Selection selection) {
		if (SettingsManager.getArenas().getKeys().contains(name)) {
			player.sendMessage(ChatColor.RED + "This an arena with this name already exists!");
			return false;
		}
		else {
			String loc = name + ".location";
			SettingsManager.getArenas().createSection(name);
			SettingsManager.getArenas().set(name + ".type", "CTF");
			SettingsManager.getArenas().set(loc + ".world", selection.getWorld() );
			SettingsManager.getArenas().set(loc + ".cornerA", "FIX THIS TEMP");
			return true;
}
