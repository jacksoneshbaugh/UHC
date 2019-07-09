package me.itguy12.uhc.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.plugin.Plugin;

public class FileManager {

	public void onEnable(Plugin p) {
		// Check for Soft Dependencies, copy them if they don't exist.
		
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
		
		if(needsRestart) {
			LoggerManager.get().LogInfo("Restarting the server to install dependencies. You may have to start the server again yourself.");
			Bukkit.getServer().shutdown();
		}
	}

}
