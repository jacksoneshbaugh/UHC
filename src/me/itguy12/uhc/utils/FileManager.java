package me.itguy12.uhc.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.NonReadableChannelException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.apache.commons.io.FileUtils;

import me.itguy12.uhc.Main;

public class FileManager {

	// Making the #get() method possible.
	private static FileManager fm;

	public static FileManager get() {
		if (fm == null) {
			fm = new FileManager();
		}

		return fm;
	}

	// File Objects
	File configF;
	File scoreboardF;
	File statsF;
	File langF;
	File hubF;

	// FileConfiguration Objects
	FileConfiguration config;
	FileConfiguration scoreboard;
	FileConfiguration stats;
	FileConfiguration lang;
	FileConfiguration hub;

	public void onEnable(Main p) {
		
		// Check that the dependencies are installed.
		checkDepends(p);
		
		// Create/Load YAML Data and Configuration files.
		loadYaml(p);

	}
	
	
	public void loadYaml(Main p) {
		
		// Load config.yml
		configF = new File(p.getDataFolder(), "config.yml");
		
	}
	

	public void checkDepends(Main p) {
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled("ActionBarAPI")) {
			LoggerManager.get().LogError(
					"Make sure you copy all of the dependencies included in the .zip file that you downloaded! ActionBarAPI is not installed. The UHC plugin will now disable. Install the plugins, then restart the server. Thanks!");
			Bukkit.getServer().getPluginManager().disablePlugin(p);
			return;
		}

		if (!Bukkit.getServer().getPluginManager().isPluginEnabled("ParticleAPI")) {
			LoggerManager.get().LogError(
					"Make sure you copy all of the dependencies included in the .zip file that you downloaded! ParticleAPI is not installed. The UHC plugin will now disable. Install the plugins, then restart the server. Thanks!");
			Bukkit.getServer().getPluginManager().disablePlugin(p);
		}

		if (!Bukkit.getServer().getPluginManager().isPluginEnabled("TitleAPI")) {
			LoggerManager.get().LogError(
					"Make sure you copy all of the dependencies included in the .zip file that you downloaded! TitleAPI is not installed. The UHC plugin will now disable. Install the plugins, then restart the server. Thanks!");
			Bukkit.getServer().getPluginManager().disablePlugin(p);
		}

		if (!Bukkit.getServer().getPluginManager().isPluginEnabled("Vault")) {
			LoggerManager.get().LogError(
					"Make sure you copy all of the dependencies included in the .zip file that you downloaded! Vault is not installed. The UHC plugin will now disable. Install the plugins, then restart the server. Thanks!");
			Bukkit.getServer().getPluginManager().disablePlugin(p);
		}
	}

}
