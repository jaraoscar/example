package com.example.util;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.example.enums.LogOutput;
import com.example.enums.LogType;

import junit.framework.TestCase;

/**
 * Sample test class
 * @author Oscar
 *
 */
public class LogUtilTest extends TestCase {

	@Test
    public void testLogMessageToConsole() throws SecurityException, IOException, SQLException {
		LogUtil.logMessage("This is a message", LogType.MESSAGE, LogOutput.CONSOLE);
    }
	
	@Ignore("Just for reference, don't create a file every time the project gets built and tests run")
	@Test
    public void testLogMessageToFile() throws SecurityException, IOException, SQLException {
		//LogUtil.logMessage("This is a warning", LogType.WARNING, LogOutput.FILE);
    }
	
	@Ignore("Make sure the DB is ready before running this")
	@Test
    public void testLogMessageToDB() throws SecurityException, IOException, SQLException {
		//LogUtil.logMessage("This is an error", LogType.ERROR, LogOutput.DATABASE);
    }
}
