package me.itguy12.uhc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.plugin.Plugin;

public class FileManager {
	
	int configVersion = 1;
	int scoreboardVersion = 1;
	int langVersion = 1;
	
	YamlConfiguration config;
	YamlConfiguration scoreboard;
	YamlConfiguration lang;
	YamlConfiguration stats;
	YamlConfiguration hub;

	private static FileManager fm;

	public static FileManager get() {
		if (fm == null) {
			fm = new FileManager();
		}

		return fm;
	}

	public void onEnable(Plugin p) {
		// Check for Soft Dependencies, copy them if they don't exist.
		checkDepends(p);

		// Load YAML Configuration and Data Files.
		loadFiles(p);

		// Print Module Load Completion Message.
		LoggerManager.get().LogInfo("Completed loading the File Module.");
	}

	public YamlConfiguration getFile(FileType type) {
		switch (type) {
		case CONFIG:
			return config;

		case SCOREBOARD:
			return scoreboard;

		case LANG:
			return lang;

		case STATS:
			return stats;

		case HUB:
			return hub;

		default:
			LoggerManager.get().LogError(
					"getFile() went to default! This shouldn't happen. Make sure to make an issue so I can fix it!");
			break;
		}
		return null;
	}

	public void loadFiles(Plugin p) {
		// Load config.yml
		File configF = new File(p.getDataFolder() + File.separator + "config.yml");
		config = YamlConfiguration.loadConfiguration(configF);

		if(!configF.exists() || config.getInt("version") != configVersion)
		
		try {
			InputStream in = p.getResource("config.yml");
			OutputStream out = new FileOutputStream(configF);
			IOUtils.copy(in, out);
			out.close();
		} catch (FileNotFoundException e) {
			LoggerManager.get().LogException(e);
			return;
		} catch (IOException e) {
			LoggerManager.get().LogException(e);
			return;
		}

		// Load language.yml
		File langF = new File(p.getDataFolder() + File.separator + "language.yml");
		lang = YamlConfiguration.loadConfiguration(langF);

		if (!langF.exists() || lang.getInt("version") != langVersion) {

			try {
				InputStream in = p.getResource("language.yml");
				OutputStream out = new FileOutputStream(langF);
				IOUtils.copy(in, out);
				out.close();
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}
		// Load hub.yml
		File hubF = new File(p.getDataFolder() + File.separator + "hub.yml");
		hub = YamlConfiguration.loadConfiguration(hubF);

		if (!hubF.exists()) {

			try {
				InputStream in = p.getResource("hub.yml");
				OutputStream out = new FileOutputStream(hubF);
				IOUtils.copy(in, out);
				out.close();
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}
		// Load scoreboard.yml
		File scoreF = new File(p.getDataFolder() + File.separator + "scoreboard.yml");
		scoreboard = YamlConfiguration.loadConfiguration(scoreF);

		if (!scoreF.exists() || scoreboard.getInt("version") != scoreboardVersion) {
			try {
				InputStream in = p.getResource("scoreboard.yml");
				OutputStream out = new FileOutputStream(scoreF);
				IOUtils.copy(in, out);
				out.close();
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		// Load stats.yml
		File statsF = new File(p.getDataFolder() + File.separator + "stats.yml");
		stats = YamlConfiguration.loadConfiguration(statsF);

		if (!statsF.exists()) {
			try {
				InputStream in = p.getResource("stats.yml");
				OutputStream out = new FileOutputStream(statsF);
				IOUtils.copy(in, out);
				out.close();
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}
	}

	public void checkDepends(Plugin p) {
		boolean needsRestart = false;

		if (!p.getServer().getPluginManager().isPluginEnabled("ActionBarAPI")) {
			needsRestart = true;
			InputStream in = p.getResource("ActionBarAPI.jar");
			OutputStream out;
			try {
				out = new FileOutputStream("../plugins");
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			}

			try {
				IOUtils.copy(in, out);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		if (!p.getServer().getPluginManager().isPluginEnabled("ParticleAPI")) {
			needsRestart = true;
			InputStream in = p.getResource("ParticleAPI.jar");
			OutputStream out;
			try {
				out = new FileOutputStream("../plugins");
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			}

			try {
				IOUtils.copy(in, out);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		if (!p.getServer().getPluginManager().isPluginEnabled("TitleAPI")) {
			needsRestart = true;
			InputStream in = p.getResource("TitleAPI.jar");
			OutputStream out;
			try {
				out = new FileOutputStream("../plugins");
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			}

			try {
				IOUtils.copy(in, out);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		if (!p.getServer().getPluginManager().isPluginEnabled("Vault")) {
			needsRestart = true;
			InputStream in = p.getResource("Vault.jar");
			OutputStream out;
			try {
				out = new FileOutputStream("../plugins");
			} catch (FileNotFoundException e) {
				LoggerManager.get().LogException(e);
				return;
			}

			try {
				IOUtils.copy(in, out);
			} catch (IOException e) {
				LoggerManager.get().LogException(e);
				return;
			}
		}

		if (needsRestart) {
			LoggerManager.get().LogInfo(
					"Shutting down the server to install dependencies. You may have to start the server again yourself.");
			Bukkit.getServer().shutdown();
		}
	}

}
