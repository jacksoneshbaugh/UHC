package me.itguy12.uhc.utils.settings;

import java.util.ArrayList;
import java.util.Arrays;

import me.itguy12.uhc.utils.FileManager;
import me.itguy12.uhc.utils.FileType;
import me.itguy12.uhc.utils.LoggerManager;

public class SettingsManager {

	/* The SettingsManager manages the settings in config.yml only. */

	private String[] defLoreWins = { "&5You have &6&l%solowins% &5solo wins.",
			"&dYou have &6&l%teamwins% &dteam wins." };
	private String[] defLoreLosses = { "&5You have &c&l%sololosses% &5solo losses.",
			"&dYou have &6&l%teamlosses% &dteam losses." };
	private String[] defLoreKills = { "&5You have &2&l%solokills% &5solo kills.",
			"&dYou have &2&l%teamkills% &dteam kills." };
	private String[] defLoreKDR = { "&5You have a &9&l%solokdr% &5solo K/D R.",
			"&dYou have a &9&l%teamkdr% &dteam K/D R." };
	private String[] defLoreWLR = { "&5You have a &3&l%solowlr% &5solo W/L R.",
			"&dYou have a &3&l%teamwlr% &dteam W/L R." };
	private String[] defLoreGames = { "&5You have played &1&l%sologames% &5solo games.",
			"&dYou have played &1&l%teamgames% &dteam games." };

	private static SettingsManager sm;

	public static SettingsManager get() {
		if (sm == null) {
			sm = new SettingsManager();
		}
		return sm;
	}

	private ArrayList<Setting> settings = new ArrayList<Setting>();

	public void onEnable() {

		/* Add all of the settings to the array list */

		/* CONFIG.YML */

		// Games Category
		settings.add(new Setting(FileType.CONFIG, "games.grace-period", 15));
		settings.add(new Setting(FileType.CONFIG, "games.game-time", 15));
		settings.add(new Setting(FileType.CONFIG, "games.deathmatch-time", -1));
		settings.add(new Setting(FileType.CONFIG, "games.solo-players-required", 25));
		settings.add(new Setting(FileType.CONFIG, "games.solo-player-cap", 50));
		settings.add(new Setting(FileType.CONFIG, "games.teams-players-required", 50));
		settings.add(new Setting(FileType.CONFIG, "games.teams-player-cap", 100));
		settings.add(new Setting(FileType.CONFIG, "games.head-drop", true));

		// Border Category
		settings.add(new Setting(FileType.CONFIG, "border.border-start", 500));
		settings.add(new Setting(FileType.CONFIG, "border.border-end", 20));

		// NPC Category
		settings.add(new Setting(FileType.CONFIG, "npcs.solo-name", "&e&lUHC SOLO QUEUE"));
		settings.add(new Setting(FileType.CONFIG, "npcs.solo-skin", "firegamer_8"));
		settings.add(new Setting(FileType.CONFIG, "npcs.team-name", "&e&lUHC TEAMS QUEUE"));
		settings.add(new Setting(FileType.CONFIG, "npcs.team-skin", "firegamer_8"));
		settings.add(new Setting(FileType.CONFIG, "npcs.stats-name", "&e&lUHC STATS"));
		settings.add(new Setting(FileType.CONFIG, "npcs.stats-skin", "AlanBrooke"));

		// Leveling Category
		settings.add(new Setting(FileType.CONFIG, "leveling.enable", true));

		// Chat Category
		settings.add(new Setting(FileType.CONFIG, "chat.lobby-chat",
				"&8[&3&l%level%&e★&8] %prefix% %name% %suffix% &8&l> &e%message%"));
		settings.add(new Setting(FileType.CONFIG, "chat.solo-chat",
				"&8[&3&l%level%&e★&8] %prefix% %name% %suffix% &8&l> &e%message%"));
		settings.add(new Setting(FileType.CONFIG, "chat.team-chat",
				"&9[TEAM] &8[&3&l%level%&e★&8] %prefix% %name% %suffix% &8&l> &e%message%"));
		settings.add(new Setting(FileType.CONFIG, "chat.shout-team-chat",
				"&6&l[SHOUT] &8[&3&l%level%&e★&8] %prefix% %name% %suffix% &8&l> &e%message%"));

		// Stats Category
		settings.add(new Setting(FileType.CONFIG, "stats.wins.item", "DIAMOND"));
		settings.add(new Setting(FileType.CONFIG, "stats.wins.name", "&6&l%wins% &eWins"));
		settings.add(new Setting(FileType.CONFIG, "stats.wins.lore", Arrays.asList(defLoreWins)));

		settings.add(new Setting(FileType.CONFIG, "stats.losses.item", "BARRIER"));
		settings.add(new Setting(FileType.CONFIG, "stats.losses.name", "&c&l%losses% &eLosses"));
		settings.add(new Setting(FileType.CONFIG, "stats.losses.lore", Arrays.asList(defLoreLosses)));

		settings.add(new Setting(FileType.CONFIG, "stats.kills.item", "IRON_SWORD"));
		settings.add(new Setting(FileType.CONFIG, "stats.kills.name", "&2&l%kills% &ekills"));
		settings.add(new Setting(FileType.CONFIG, "stats.kills.lore", Arrays.asList(defLoreKills)));

		settings.add(new Setting(FileType.CONFIG, "stats.kdr.item", "DIAMOND_SWORD"));
		settings.add(new Setting(FileType.CONFIG, "stats.kdr.name", "&9&l%kdr% &eK/D R"));
		settings.add(new Setting(FileType.CONFIG, "stats.kdr.lore", Arrays.asList(defLoreKDR)));

		settings.add(new Setting(FileType.CONFIG, "stats.wlr.item", "GOLD_INGOT"));
		settings.add(new Setting(FileType.CONFIG, "stats.wlr.name", "&3&l%wlr% &eW/L R"));
		settings.add(new Setting(FileType.CONFIG, "stats.wlr.lore", Arrays.asList(defLoreWLR)));

		settings.add(new Setting(FileType.CONFIG, "stats.games.item", "PAPER"));
		settings.add(new Setting(FileType.CONFIG, "stats.games.name", "&1&l%games% &eGames Played"));
		settings.add(new Setting(FileType.CONFIG, "stats.games.lore", Arrays.asList(defLoreGames)));
	}

	public void grabSettings() {

		for (Setting setting : settings) {
			if (FileManager.get().get(FileType.CONFIG, setting.getPath()) != null) {
				setting.setValue(FileManager.get().get(FileType.CONFIG, setting.getPath()));
			}
		}

	}

	public void copyOldSettings() {
		for (Setting setting : settings) {
			if(setting.hasBeenModified()) {
				setting.write();
			}
		}
		
		LoggerManager.get().LogInfo("Copied old settings to the updated configuration file.");
		
	}

}
