package com.example.enums;

/**
 * Enum describing log type available options
 * @author Oscar
 *
 */
public enum LogType {
	MESSAGE("MESSAGE", 1), 
	ERROR("ERROR", 2), 
	WARNING("WARNING", 3);

	private String value = "";
	private int dbValue = 0;

	/**
	 * 
	 * @param value
	 * @param dbValue
	 */
	private LogType(String value, int dbValue) {
		this.value = value;
		this.dbValue = dbValue;
	}

	/**
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @return
	 */
	public int getDbValue() {
		return dbValue;
	}
}
