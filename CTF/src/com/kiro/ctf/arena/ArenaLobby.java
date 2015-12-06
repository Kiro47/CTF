package com.kiro.ctf.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import com.kiro.ctf.CTFMain;
import com.kiro.ctf.utils.SettingsManager;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

public class ArenaLobby {

	private String id;
	private CuboidSelection bounds;
	
	
	private Location warp;
	
	protected ArenaLobby(String id) {
		this.id = id;
		
		this.bounds = new CuboidSelection(
				Bukkit.getServer().getWorld(SettingsManager.getArenas().<String>get(id + ".lobby.world")),
				CTFMain.loadLocation(SettingsManager.getArenas().<ConfigurationSection>get(id + ".lobby.cornerA")),
				CTFMain.loadLocation(SettingsManager.getArenas().<ConfigurationSection>get(id + ".lobby.cornerB"))
					);
		
		this.warp = new Location(Bukkit.getServer().getWorld(SettingsManager.getArenas().<String>get(id + ".lobby.warp.world")),
				SettingsManager.getArenas().<Double>get(id + ".lobby.warp.x") + 0.5,
				SettingsManager.getArenas().<Integer>get(id + ".lobby.warp.y") + 0.1,
				SettingsManager.getArenas().<Integer>get(id + ".lobby.warp.z") + 0.5 );
	}
	
	public String getID() {
		return id;
	}
	
	public CuboidSelection getBounds() {
		return bounds;
	}
	
	public Location getWarp() {
		return this.warp;
	}
	
}
