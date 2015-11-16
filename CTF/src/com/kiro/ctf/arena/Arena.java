package com.kiro.ctf.arena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kiro.ctf.CTFMain;
import com.kiro.ctf.Spawn;
import com.kiro.ctf.utils.Countdown;
import com.kiro.ctf.utils.SettingsManager;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

public class Arena {

	public enum ArenaState {
		WAITING, COUNTDOWN, STARTED
	}
	
	private String id;
	private CuboidSelection bounds;
	private ArenaState state;
	
	private ArrayList<Spawn> spawns;
	
	private ArrayList<Player> players;
	
	private ItemStack kitSelector;
	

	protected Arena(String id) {
		this.id = id;
		
		this.bounds = new CuboidSelection(
				Bukkit.getServer().getWorld(SettingsManager.getArenas().<String>get(id + ".world")),
				CTFMain.loadLocation(SettingsManager.getArenas().<ConfigurationSection>get(id + ".cornerA")),
				CTFMain.loadLocation(SettingsManager.getArenas().<ConfigurationSection>get(id + ".cornerB"))
		);
		
		this.state = ArenaState.WAITING;
		
		this.spawns = new ArrayList<Spawn>();
		if (SettingsManager.getArenas().contains(id + ".spawns")) {
			for (String spawnID : SettingsManager.getArenas().<ConfigurationSection>get(id + ".spawns").getKeys(false)) {
				spawns.add(new Spawn(CTFMain.loadLocation(SettingsManager.getArenas().<ConfigurationSection>get(id + ".spawns." + spawnID))));
			}			
		}
		
		this.players = new ArrayList<Player>();
	}
	
	public String getID() {
		return id;
	}
	
	public CuboidSelection getBounds() {
		return bounds;
	}
	
	public int getMaxPlayers() {
		return spawns.size();
	}
	
	public ArenaState getState() {
		return state;
	}
	
	public Player[] getPlayers() {
		return players.toArray(new Player[players.size()]);
	}
	
	public boolean hasPlayer(Player p) {
		return players.contains(p);
	}
	
	public void addPlayer(Player p) {
		if (state == ArenaState.STARTED) {
			p.sendMessage(ChatColor.RED + "This arena has already started.");
			return;
		}
		
		if (players.size() + 1 > spawns.size()) {
			p.sendMessage(ChatColor.RED + "This arena is full.");
			return;
		}
		
		boolean success = false;
		
		for (Spawn spawn : spawns) {
			if (!spawn.hasPlayer()) {
				spawn.setPlayer(p);
				p.teleport(spawn.getLocation());
				success = true;
				break;
			}
		}
		
		if (!success) {
			p.sendMessage(ChatColor.RED + "Could not find spawn.");
			return;
		}
		
		players.add(p);
		
		p.getInventory().clear();

		p.sendMessage(ChatColor.GREEN + "You have joined arena " + id + ".");
		
		if (players.size() >= spawns.size() && state == ArenaState.WAITING) {
			this.state = ArenaState.COUNTDOWN;
			new Countdown(this, 30, 30, 20, 10, 5, 4, 3, 2, 1).runTaskTimerAsynchronously(CTFMain.getPlugin(), 0, 20);
		}
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
		
		for (Spawn spawn : spawns) {
			if (spawn.hasPlayer() && spawn.getPlayer().equals(p)) {
				spawn.setPlayer(null);
			}
		}
		
		p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation()); // TODO: Temporary.
		
		if (players.size() <= 1) {
			if (players.size() == 1) {
				Bukkit.getServer().broadcastMessage(players.get(0).getName() + " has won arena " + id + "!");
				players.remove(0);
				players.get(0).teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation()); // TODO: Temporary.
			}
			
			else {
				Bukkit.getServer().broadcastMessage("Arena " + id + " has ended.");
			}

            for(Entity e : Bukkit.getWorld(this.getBounds().getWorld().getName()).getEntities()){
                if(this.getBounds().contains(e.getLocation()) && e.getType() == EntityType.DROPPED_ITEM){
                    e.remove();
                }
            }

			players.clear();
			state = ArenaState.WAITING;
		}
	}
	
	public void addSpawn(Location loc) {
		spawns.add(new Spawn(loc));
	}
	

	public void start() {
		this.state = ArenaState.STARTED;
		
		for (Player p : players) {
            if (p.getInventory().contains(kitSelector)){
    //            Kit kit = KitManager.getInstance().getKit("Default");

                p.getInventory().clear();

//                for (ItemStack item : kit.getItems()) {
//                    p.getInventory().addItem(item);
//                }
            }
			p.setHealth(20.0D);
			p.setGameMode(GameMode.SURVIVAL);
		}
		
		for (Spawn spawn : spawns) {
			spawn.setPlayer(null);
		}
	}

    public Location getSpawn(Player p){
        for (Spawn s : spawns){
            if (s.getPlayer() == p){
                return s.getLocation();
            }
        }
        return null;
    }
}
