package me.itguy12.uhc.utils;

import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;

public class LoggerManager {

	private static LoggerManager lm;

	public static LoggerManager get() {
		if (lm == null) {
			lm = new LoggerManager();
		}

		return lm;
	}

	public void LogInfo(String s) {
		Bukkit.getServer().getLogger()
				.info(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------"
						+ ChatColor.GOLD + "" + ChatColor.BOLD + "UHC" + ChatColor.DARK_GRAY + ""
						+ ChatColor.STRIKETHROUGH + "--------------------------");
		Bukkit.getServer().getLogger()
				.info(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "(*) UHC Returned some Information (*)");
		Bukkit.getServer().getLogger().info(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + s);
		Bukkit.getServer().getLogger()
				.info(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------"
						+ ChatColor.GOLD + "" + ChatColor.BOLD + "UHC" + ChatColor.DARK_GRAY + ""
						+ ChatColor.STRIKETHROUGH + "--------------------------");
	}

	public void LogError(String s) {
		Bukkit.getServer().getLogger()
				.severe(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------"
						+ ChatColor.GOLD + "" + ChatColor.BOLD + "UHC" + ChatColor.DARK_GRAY + ""
						+ ChatColor.STRIKETHROUGH + "--------------------------");
		Bukkit.getServer().getLogger().severe(ChatColor.RED + "" + ChatColor.BOLD + "(!) UHC Returned an Error (!)");
		Bukkit.getServer().getLogger().severe(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + s);
		Bukkit.getServer().getLogger()
				.severe(ChatColor.RED + "" + ChatColor.BOLD + "If you need help, make an issue on the issue tracker:");
		Bukkit.getServer().getLogger()
				.severe(ChatColor.GOLD + "" + ChatColor.BOLD + "https://github.com/JavaGuy12/UHC/issues");
		Bukkit.getServer().getLogger()
				.severe(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------"
						+ ChatColor.GOLD + "" + ChatColor.BOLD + "UHC" + ChatColor.DARK_GRAY + ""
						+ ChatColor.STRIKETHROUGH + "--------------------------");
	}

	public void LogException(Exception e) {
		Bukkit.getServer().getLogger()
				.severe(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------"
						+ ChatColor.GOLD + "" + ChatColor.BOLD + "UHC" + ChatColor.DARK_GRAY + ""
						+ ChatColor.STRIKETHROUGH + "--------------------------");
		Bukkit.getServer().getLogger()
				.severe(ChatColor.RED + "" + ChatColor.BOLD + "(!) UHC Encountered an Exception. (!)");
		Bukkit.getServer().getLogger()
				.severe(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "MESSAGE: " + ChatColor.RED + e.getMessage());
		Bukkit.getServer().getLogger()
				.severe(ChatColor.RED + "" + ChatColor.BOLD + "If you need help, make an issue on the issue tracker:");
		Bukkit.getServer().getLogger()
				.severe(ChatColor.GOLD + "" + ChatColor.BOLD + "https://github.com/JavaGuy12/UHC/issues");
		Bukkit.getServer().getLogger().severe(ChatColor.RED + "" + ChatColor.BOLD + "Printing the stacktrace below.");
		e.printStackTrace();
		Bukkit.getServer().getLogger()
				.severe(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------"
						+ ChatColor.GOLD + "" + ChatColor.BOLD + "UHC" + ChatColor.DARK_GRAY + ""
						+ ChatColor.STRIKETHROUGH + "--------------------------");
	}

}
