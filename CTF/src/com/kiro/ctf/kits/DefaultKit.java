package com.kiro.ctf.kits;

import java.util.AbstractMap.SimpleEntry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class DefaultKit extends Kit{

	@SuppressWarnings("unchecked")
	public DefaultKit() {
		super("Archer");
		addItem(Material.STONE_SWORD, 1, 0, "Scout's Knife", new String[0], new SimpleEntry<Enchantment, Integer>(Enchantment.DURABILITY, 1));
		addItem(Material.BOW, 1, 0, "Archer's Bow", new String[0], new SimpleEntry<Enchantment, Integer>(Enchantment.ARROW_INFINITE, 1));
		addItem(Material.ARROW, 32, 0, "Archer's Arrows", new String[0]);
		
		addItem(Material.POTION , 1, 8258, "Speed Potion 1", new String[0]);
		addItem(Material.ENDER_PEARL, 1, 0, "Scout's Pearl", new String[0]);
		
		String[] porkLore = ("That is pig......right? / ").split("/");
		addItem(Material.GRILLED_PORK, 4, 0, "A Scout's Diet", porkLore);
	}
}
