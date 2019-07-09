package me.itguy12.uhc.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.plugin.Plugin;

public class FileManager {
	
	public void onEnable(Plugin p) {
		//Check for Soft Dependencies, copy them if they don't exist.
		
		boolean needsRestart = false;
		
		if(!p.getServer().getPluginManager().isPluginEnabled("ActionBarAPI")) {
			needsRestart = true;
			InputStream in = p.getResource("ActionBarAPI");
			try {
				OutputStream out = new FileOutputStream("../plugins");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}

}
