package com.kiro.ctf.cmds;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class confirm implements Listener{
		
		public static ArrayList<Player> needsConfirmed = new ArrayList<Player> ();
	 static HashMap<Player,Boolean> returnConfirm = new HashMap<Player, Boolean> ();
		@EventHandler
			public void onCommand(PlayerCommandPreprocessEvent e) {
			Player p = (Player) e.getPlayer();
			
			if (!(needsConfirmed.contains(p))) return;
			else {
				if (e.getMessage().equalsIgnoreCase("yes")) {
					returnConfirm.put(p, true);
					confirmAction(p);
					return;
				}
				if (e.getMessage().equalsIgnoreCase("no")) {
					returnConfirm.put(p, false);
					confirmAction(p);
					return;
				}
				returnConfirm.put(p, null);
				return;
			}
		}
		
		public static boolean confirmAction(Player p) {
			if (returnConfirm.get(p) == null) {
				p.sendMessage("38");
			}
			if (returnConfirm.get(p) == true) {
				needsConfirmed.remove(p);
				returnConfirm.put(p, null);
				return true;
			}
			if (returnConfirm.get(p) == false) {
				needsConfirmed.remove(p);
				returnConfirm.put(p, null);
				return false;
			}
			
			
			return true;
		}
		
}
