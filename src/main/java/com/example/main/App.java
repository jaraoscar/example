package com.example.main;

import java.io.IOException;
import java.sql.SQLException;

import com.example.enums.LogOutput;
import com.example.enums.LogType;
import com.example.util.LogUtil;

/**
 * Sample runnable class
 * @author Oscar
 *
 */
public class App {

	public static void main(String[] args) throws SecurityException, IOException, SQLException {
		LogUtil.logMessage("This is a message", LogType.MESSAGE, LogOutput.CONSOLE);
	}
}