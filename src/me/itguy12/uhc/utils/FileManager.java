package me.itguy12.uhc.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.itguy12.uhc.Main;
import me.itguy12.uhc.utils.settings.SettingsManager;

public class FileManager {

	// Making the #get() method possible.
	private static FileManager fm;

	public static FileManager get() {
		if (fm == null) {
			fm = new FileManager();
		}

		return fm;
	}

	// Config File Versions
	int configVersion = 1;
	int langVersion = 1;
	int scoreboardVersion = 1;

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

	@SuppressWarnings("unchecked")
	public <T> T get(FileType type, String path) {
		switch (type) {

		case CONFIG:
			return (T) config.get(path);
		case SCOREBOARD:
			return (T) scoreboard.get(path);
		case STATS:
			return (T) stats.get(path);
		case LANG:
			return (T) lang.get(path);
		case HUB:
			return (T) hub.get(path);
		}
		return null;
	}
	
	
	public void set(FileType type, String path, Object value) {
		switch (type) {

		case CONFIG:
			config.set(path, value);
			try {
				config.save(configF);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
			}
			return;
		case SCOREBOARD:
			scoreboard.set(path, value);
			try {
				scoreboard.save(scoreboardF);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
			}
			return;
		case STATS:
			stats.set(path, value);
			try {
				stats.save(statsF);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
			}
			return;
		case LANG:
			lang.set(path, value);
			try {
				lang.save(langF);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
			}
			return;
		case HUB:
			hub.set(path, value);
			try {
				hub.save(hubF);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
			}
			return;
		}
	}

	public void loadYaml(Main p) {
		boolean updateConfig = false;
		
		// Load config.yml
		configF = new File(p.getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(configF);
		
		// log the old settings in case of update
		if (configF.exists()) {
			SettingsManager.get().grabSettings();
		}
		
		if(config.getInt("version") != configVersion) {
			updateConfig = true;
		}
		
		if (!configF.exists() || config.getInt("version") != configVersion) {
			InputStream in = p.getResource("config.yml");
			try {
				FileUtils.copyInputStreamToFile(in, configF);
				LoggerManager.get().LogInfo(
						"File config.yml not found or outdated; copying new file.");
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		config = YamlConfiguration.loadConfiguration(configF);
		
		
		if(updateConfig) {
			SettingsManager.get().copyOldSettings();
		}
		
		
		// Load scoreboard.yml
		scoreboardF = new File(p.getDataFolder(), "scoreboard.yml");
		scoreboard = YamlConfiguration.loadConfiguration(scoreboardF);

		if (!scoreboardF.exists() || scoreboard.getInt("verison") != scoreboardVersion) {
			InputStream in = p.getResource("scoreboard.yml");
			try {
				FileUtils.copyInputStreamToFile(in, scoreboardF);
				LoggerManager.get().LogInfo(
						"File scoreboard.yml not found or outdated; copying new file. You will have to reconfigure this file.");
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		scoreboard = YamlConfiguration.loadConfiguration(scoreboardF);

		// Load stats.yml
		statsF = new File(p.getDataFolder(), "stats.yml");
		stats = YamlConfiguration.loadConfiguration(statsF);

		if (!statsF.exists()) {
			InputStream in = p.getResource("stats.yml");
			try {
				FileUtils.copyInputStreamToFile(in, statsF);
				LoggerManager.get().LogInfo(
						"File stats.yml not found or outdated; copying new file. This requires no action; stats.yml is NOT a configurable file.");
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		stats = YamlConfiguration.loadConfiguration(statsF);

		// Load lang.yml
		langF = new File(p.getDataFolder(), "lang.yml");
		lang = YamlConfiguration.loadConfiguration(langF);

		if (!langF.exists()) {
			InputStream in = p.getResource("lang.yml");
			try {
				FileUtils.copyInputStreamToFile(in, langF);
				LoggerManager.get().LogInfo(
						"File lang.yml not found or outdated; copying new file. You will need to configure this file again.");
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		lang = YamlConfiguration.loadConfiguration(langF);

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
