package me.itguy12.uhc.utils;

public class LoggerManager {

	private static LoggerManager lm;

	public static LoggerManager get() {
		if (lm == null) {
			lm = new LoggerManager();
		}

		return lm;
	}
	
	
	public void LogException(Exception e) {
		
	}

}
