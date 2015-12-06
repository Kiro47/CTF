package com.kiro.ctf.arena;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.entity.Player;

import com.kiro.ctf.utils.SettingsManager;

public class ArenaManager {

	private static ArenaManager instance = new ArenaManager();
	
	public static ArenaManager getInstance() {
		return instance;
	}
	
	private ArrayList<Arena> arenas;
	
	private ArenaManager() {
		this.arenas = new ArrayList<Arena>();
	}
	
	public void setup() {
		arenas.clear();
		
		for (String arenaID : SettingsManager.getArenas().getKeys()) {
			arenas.add(new Arena(arenaID));
		}
	}
	
	public Arena getArena(String id) {
		for (Arena arena : arenas) {
			if (arena.getID().equals(id)) {
				return arena;
			}
		}
		return null;
	}
	
	public Arena getArena(Player p) {
		for (Arena arena : arenas) {
			if (arena.hasPlayer(p)) {
				return arena;
			}
		}
		return null;
	}
	
	public ArrayList<Arena> getArenas() {
		return arenas;
		}
	
	private ArrayList<String> arenaNames = new ArrayList<String> ();
	public ArrayList<String> getArenaNames() {
		Iterator<Arena> it = arenas.iterator();
		while (it.hasNext()) {
			Arena an = (Arena) it.next();
			arenaNames.add(an.getID());
		}
		return arenaNames;
	}
}
