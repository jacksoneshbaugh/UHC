package me.itguy12.uhc;

import org.bukkit.plugin.java.JavaPlugin;

import me.itguy12.uhc.utils.FileManager;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		FileManager.get().onEnable(this);
	}
	
}
