package com.kiro.ctf.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.kiro.ctf.utils.SettingsManager;

public class kit {

	public static void delete(Player p, String kitName) {

		final String name = kitName;
		
	/*	confirm.needsConfirmed.add(p);
		 p.sendMessage(ChatColor.GOLD + "Please confirm or deny deleting kit: " + name);
		 p.sendMessage(ChatColor.GOLD + "Confirm with: /yes  or  /no");
		 CTFMain.getPlugin().getServer().getScheduler().scheduleAsyncDelayedTask(CTFMain.getPlugin(), new Runnable() {
			 public void run() {
				 if (confirm.needsConfirmed.contains(player)) {
					 player.sendMessage(ChatColor.DARK_RED + "Deletion of kit neither confirmed nor denied! Deletion Request for kit: " + name + " has been cancelled!");
					 confirm.needsConfirmed.remove(player);
					 return;
				 }
				 else {
					 return;
				 }
			 }
		 },(long) 200); */
		 /*if (confirm.confirmAction(p) == true) {*/
			 
			 SettingsManager.getKits().set(name, null);
			 p.sendMessage(ChatColor.GREEN + "Kit: " + name + " has been succefully deleted!");
			 p.sendMessage(ChatColor.GREEN + "Only Effective after a reload!");
			 SettingsManager.getKits().save();
			 return;
		 /*}
		 else {
			 p.sendMessage(ChatColor.RED + "Deletion of kit: "  + name + " has been cancelled!");
			 return;
		 }
	 */
		
	}
}
