package com.kiro.ctf;

import org.bukkit.plugin.java.JavaPlugin;

import com.kiro.ctf.cmds.CTFCommand;

public class CTFMain extends JavaPlugin{

	public void onEnable() {
		getCommand("ctf").setExecutor(new CTFCommand());
		
	}
	public void onDisable(){
		
	}
}
