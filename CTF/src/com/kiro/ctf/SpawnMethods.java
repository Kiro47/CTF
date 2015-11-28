package com.kiro.ctf;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.kiro.ctf.arena.Arena;
import com.kiro.ctf.utils.SettingsManager;

public class SpawnMethods {
	public static ArrayList<String> teams = new ArrayList<String> ();
	
	public static void setupTeams() {
		
		teams.add("white");
		teams.add("orange");
		teams.add("magenta");
		teams.add("lightblue");
		teams.add("yellow");
		teams.add("lime");
		teams.add("pink");
		teams.add("gray");
		teams.add("lightgray");
		teams.add("cyan");
		teams.add("purple");
		teams.add("blue");
		teams.add("brown");
		teams.add("green");
		teams.add("red");
		teams.add("black");
	}
	
	public static byte teamToWool(String teamName) {
		
		if (teamName.equalsIgnoreCase("white")) return 0;
		if (teamName.equalsIgnoreCase("orange")) return 1;
		if (teamName.equalsIgnoreCase("magenta")) return 2;
		if (teamName.equalsIgnoreCase("lightblue")) return 3;
		if (teamName.equalsIgnoreCase("yellow")) return 4;
		if (teamName.equalsIgnoreCase("lime")) return 5;
		if (teamName.equalsIgnoreCase("pink")) return 6;
		if (teamName.equalsIgnoreCase("gray")) return 7;
		if (teamName.equalsIgnoreCase("lightgray")) return 8;
		if (teamName.equalsIgnoreCase("cyan")) return 9;
		if (teamName.equalsIgnoreCase("purple")) return 10;
		if (teamName.equalsIgnoreCase("blue")) return 11;
		if (teamName.equalsIgnoreCase("brown")) return 12;
		if (teamName.equalsIgnoreCase("green")) return 13;
		if (teamName.equalsIgnoreCase("red")) return 14;
		if (teamName.equalsIgnoreCase("black")) return 15;
		else {
			return 0;
		}

	
	}

	
	@SuppressWarnings("deprecation")
	public static void addSpawn(Player p, String team, Arena a,String prefix ) {

		 ///////add the spawn
		 a.addSpawn(p.getLocation());
		 if (!SettingsManager.getArenas().contains(a.getID() +".spawns")) {
			 SettingsManager.getArenas().createSection(a.getID() + ".spawns");
		 }
		 if (!SettingsManager.getArenas().contains(a.getID() + ".spawns." + team)) {
			 SettingsManager.getArenas().createSection(a.getID() + ".spawns." + team);
		 }
		 Location roundloc = new Location(p.getWorld(), Math.round(p.getLocation().getX()) + 0.5, 
				 Math.round(p.getLocation().getY()), Math.round(p.getLocation().getZ()) + 0.5);
		 CTFMain.saveLocation(roundloc, SettingsManager.getArenas().createSection(a.getID() + ".spawns." + SettingsManager.getArenas().<ConfigurationSection>get(a.getID() + ".spawns").getKeys(false).size()));
		 SettingsManager.getArenas().save();
		 
		 p.getLocation().getWorld().getBlockAt(p.getLocation().subtract(0.0, 1.0, 0.0)).setType(Material.GLOWSTONE);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(1.0, -1.0, 0.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(1.0, -1.0, -1.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(0.0, -1.0, -1.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(-1.0, -1.0, -1.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(-1.0, -1.0, 0.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(-1.0, -1.0, 1.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(0.0, -1.0, 1.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 p.getLocation().getWorld().getBlockAt(p.getLocation().add(1.0, -1.0, 1.0)).setTypeIdAndData(Material.WOOL.getId(), teamToWool(team), false);
		 
		 p.sendMessage(prefix + ChatColor.GREEN + a.getID()  + "'s spawn for team " + team+ " has been set!");
		 return ;
	 
	}

}
