package com.kiro.ctf.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kiro.ctf.utils.SettingsManager;

public class LoadoutUtils {

	public static boolean loadKit(Player p,String loadout) {
		if (SettingsManager.getKits().get(loadout + ".inv.0") == null) {
			return false;
		}
		p.getInventory().setItem(0, (ItemStack) SettingsManager.getKits().get(loadout +".inv.0"));
		p.getInventory().setItem(1, (ItemStack) SettingsManager.getKits().get(loadout +".inv.1"));
		p.getInventory().setItem(2, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.2"));
		p.getInventory().setItem(3, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.3"));
		p.getInventory().setItem(4, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.4"));
		p.getInventory().setItem(5, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.5"));
		p.getInventory().setItem(6, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.6"));
		p.getInventory().setItem(7, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.7"));
		p.getInventory().setItem(8, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.8"));
		p.getInventory().setItem(9, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.9"));
		p.getInventory().setItem(10, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.10"));
		p.getInventory().setItem(11, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.11"));
		p.getInventory().setItem(12, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.12"));
		p.getInventory().setItem(13, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.13"));
		p.getInventory().setItem(14, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.14"));
		p.getInventory().setItem(15, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.15"));
		p.getInventory().setItem(16, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.16"));
		p.getInventory().setItem(17, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.17"));
		p.getInventory().setItem(18, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.18"));
		p.getInventory().setItem(19, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.19"));
		p.getInventory().setItem(20, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.20"));
		p.getInventory().setItem(21, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.21"));
		p.getInventory().setItem(22, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.22"));
		p.getInventory().setItem(23, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.23"));
		p.getInventory().setItem(24, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.24"));
		p.getInventory().setItem(25, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.25"));
		p.getInventory().setItem(26, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.26"));
		p.getInventory().setItem(27, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.27"));
		p.getInventory().setItem(28, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.28"));
		p.getInventory().setItem(29, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.29"));
		p.getInventory().setItem(30, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.30"));
		p.getInventory().setItem(31, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.31"));
		p.getInventory().setItem(32, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.32"));
		p.getInventory().setItem(33, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.33"));
		p.getInventory().setItem(34, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.34"));
		p.getInventory().setItem(35, (ItemStack) SettingsManager.getKits().get(loadout + ".inv.35"));

		p.getInventory().setHelmet((ItemStack) SettingsManager.getKits().get(loadout + ".armor.helmet"));
		p.getInventory().setChestplate((ItemStack) SettingsManager.getKits().get(loadout + ".armor.chestplate"));
		p.getInventory().setLeggings((ItemStack) SettingsManager.getKits().get(loadout + ".armor.leggings"));
		p.getInventory().setBoots((ItemStack) SettingsManager.getKits().get(loadout + ".armor.boots"));
		return true;
	}
	
	public static void saveKit(Player p, String args1) {
		
		String name = args1;
		String start = (name);
		
		SettingsManager.getKits().set(start, name );

		SettingsManager.getKits().set(start + ".inv.0", p.getInventory().getItem(0));
		SettingsManager.getKits().set(start + ".inv.1", p.getInventory().getItem(1));
		SettingsManager.getKits().set(start + ".inv.2", p.getInventory().getItem(2));
		SettingsManager.getKits().set(start + ".inv.3", p.getInventory().getItem(3));
		SettingsManager.getKits().set(start + ".inv.4", p.getInventory().getItem(4));
		SettingsManager.getKits().set(start + ".inv.5", p.getInventory().getItem(5));
		SettingsManager.getKits().set(start + ".inv.6", p.getInventory().getItem(6));
		SettingsManager.getKits().set(start + ".inv.7", p.getInventory().getItem(7));
		SettingsManager.getKits().set(start + ".inv.8", p.getInventory().getItem(8));
		SettingsManager.getKits().set(start + ".inv.9", p.getInventory().getItem(9));
		SettingsManager.getKits().set(start + ".inv.10", p.getInventory().getItem(10));
		SettingsManager.getKits().set(start + ".inv.11", p.getInventory().getItem(11));
		SettingsManager.getKits().set(start + ".inv.12", p.getInventory().getItem(12));
		SettingsManager.getKits().set(start + ".inv.13", p.getInventory().getItem(13));
		SettingsManager.getKits().set(start + ".inv.14", p.getInventory().getItem(14));
		SettingsManager.getKits().set(start + ".inv.15", p.getInventory().getItem(15));
		SettingsManager.getKits().set(start + ".inv.16", p.getInventory().getItem(16));
		SettingsManager.getKits().set(start + ".inv.17", p.getInventory().getItem(17));
		SettingsManager.getKits().set(start + ".inv.18", p.getInventory().getItem(18));
		SettingsManager.getKits().set(start + ".inv.19", p.getInventory().getItem(19));
		SettingsManager.getKits().set(start + ".inv.20", p.getInventory().getItem(20));
		SettingsManager.getKits().set(start + ".inv.21", p.getInventory().getItem(21));
		SettingsManager.getKits().set(start + ".inv.22", p.getInventory().getItem(22));
		SettingsManager.getKits().set(start + ".inv.23", p.getInventory().getItem(23));
		SettingsManager.getKits().set(start + ".inv.24", p.getInventory().getItem(24));
		SettingsManager.getKits().set(start + ".inv.25", p.getInventory().getItem(25));
		SettingsManager.getKits().set(start + ".inv.26", p.getInventory().getItem(26));
		SettingsManager.getKits().set(start + ".inv.27", p.getInventory().getItem(27));
		SettingsManager.getKits().set(start + ".inv.28", p.getInventory().getItem(28));
		SettingsManager.getKits().set(start + ".inv.29", p.getInventory().getItem(29));
		SettingsManager.getKits().set(start + ".inv.30", p.getInventory().getItem(30));
		SettingsManager.getKits().set(start + ".inv.31", p.getInventory().getItem(31));
		SettingsManager.getKits().set(start + ".inv.32", p.getInventory().getItem(32));
		SettingsManager.getKits().set(start + ".inv.33", p.getInventory().getItem(33));
		SettingsManager.getKits().set(start + ".inv.34", p.getInventory().getItem(34));
		SettingsManager.getKits().set(start + ".inv.35", p.getInventory().getItem(35));
		
		SettingsManager.getKits().set(start + ".armor.helmet", p.getInventory().getHelmet());
		SettingsManager.getKits().set(start + ".armor.chestplate", p.getInventory().getChestplate());
		SettingsManager.getKits().set(start + ".armor.leggings", p.getInventory().getLeggings());
		SettingsManager.getKits().set(start + ".armor.boots", p.getInventory().getBoots());
		
		SettingsManager.getKits().save();
	}
}
