package me.itguy12.uhc.utils.settings;

import me.itguy12.uhc.utils.FileType;

public class Setting {

	private FileType type;
	private String path;
	private Object defaultValue;
	private Object value;

	public Setting(FileType type, String path, Object defaultValue) {
		this.type = type;
		this.path = path;
		this.defaultValue = defaultValue;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getPath() {
		return path;
	}

	public FileType getType() {
		return type;
	}

	public Object getValue() {
		return path;
	}

	public boolean hasBeenModified() {
		if (value == null || value != defaultValue) {
			return true;
		}
		return false;
	}

}
