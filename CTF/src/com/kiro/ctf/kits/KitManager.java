package com.kiro.ctf.kits;

import java.util.ArrayList;

import com.kiro.ctf.utils.SettingsManager;

public class KitManager {

private static KitManager instance = new KitManager();
	
	public static KitManager getInstance() {
		return instance;
	}
	
	private ArrayList<String> kits;

	private KitManager() {
		kits = new ArrayList<String>();
		kits.addAll(SettingsManager.getKits().getKeys());
	}
	public String kitList() {
		return kits.toString();
		
	}
}
