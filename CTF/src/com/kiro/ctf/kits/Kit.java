package com.kiro.ctf.kits;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Kit {


	private String name;
	private ArrayList<ItemStack> items;
	
	public Kit(String name) {
		this.name = name;
		this.items = new ArrayList<ItemStack>();
	}
	
	public final String getName() {
		return name;
	}
	
	@SuppressWarnings("unchecked")
	public final ArrayList<ItemStack> getItems() {
		return (ArrayList<ItemStack>) items.clone();
	}
	
	@SuppressWarnings("unchecked")
	public final void addItem(Material material, int amnt, int damage, String name, String[] lore, SimpleEntry<Enchantment, Integer>... enchantments) {
		
		ItemStack item = new ItemStack(material, amnt, (short) damage);
		for (SimpleEntry<Enchantment, Integer> entry : enchantments) {
			item.addEnchantment(entry.getKey(), entry.getValue());
		}
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		
		items.add(item);
	}
	
	/**
	 * Can be overridden to change the player to whom the kit is being applied.
	 * @param p The player.
	 */
	public void apply(Player p) {
		
	}
}
