package com.example.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import com.example.enums.LogOutput;
import com.example.enums.LogType;

/**
 * 
 * @author Oscar
 *
 */
public final class LogUtil {
	
	// Error messages
	private static final String INVALID_PARAM_ERROR = "Invalid parameters found";
	private static final String INVALID_MESSAGE_ERROR = "Message to be logged can not be empty";
	
	// Logger constants
	private static final Level DEFAULT_LOG_LEVEL = Level.INFO;
	private static final String DEFAULT_LOG_PATH = System.getProperty("user.home");
	private static final String DEFAULT_LOG_FILENAME = "logFile.xml";
	
	private static final Logger logger = Logger.getLogger("MyLog");
	
	private LogUtil() {
	}

	/**
	 * Log a message
	 * @param message The message to be logged
	 * @param logType The LogType needed
	 * @param logOutput The LogOutput needed
	 * @throws SecurityException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void logMessage(String message, LogType logType, LogOutput logOutput) throws SecurityException, IOException, SQLException  {
		if (message == null || logType == null || logOutput == null) {
			throw new IllegalArgumentException(INVALID_PARAM_ERROR);
		}
		
		message = message.trim();
		
		if (message.isEmpty()) {
			throw new IllegalArgumentException(INVALID_MESSAGE_ERROR);
		}
		
		String formattedMessage = formatLogMessage(message, logType); 
		
		if (logOutput == LogOutput.CONSOLE) {
			logToFileOrConsole(message, DEFAULT_LOG_LEVEL, new ConsoleHandler());
		} else if (logOutput == LogOutput.FILE) {
			String logFilePath = getLogFilePath();
			File logFile = new File(logFilePath);
			
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			
			logToFileOrConsole(message, DEFAULT_LOG_LEVEL, new FileHandler(logFilePath));
		} else if (logOutput == LogOutput.DATABASE) {
			persistLog(formattedMessage, logType);
		}
	}
	
	/**
	 * Get the log path including file name and extension
	 * @return String
	 */
	private static String getLogFilePath() {
		return DEFAULT_LOG_PATH + File.separator + DEFAULT_LOG_FILENAME;
	}
	
	/**
	 * Get a formatted log message including the log type and date for reference
	 * @param message The message to be logged
	 * @param logType The LogType needed
	 * @return String
	 */
	private static String formatLogMessage(String message, LogType logType) {
		return String.format("%s %s %s", logType.getValue(), DateFormat.getDateInstance(DateFormat.LONG).format(new Date()), message);
	}
	
	/**
	 * Log a message into a file or console
	 * @param message The message to be logged
	 * @param logLevel The Level needed for logging
	 * @param sh The StreamHandler used to redirect the log output
	 */
	private static void logToFileOrConsole(String message, Level logLevel, StreamHandler sh) {
		LogManager.getLogManager().reset(); // remove previous handlers
		
		logger.addHandler(sh);
		logger.log(logLevel, message);
	}
	
	/**
	 * Save the log message into DB
	 * @param formattedMessage The formattedMessage message including the log type and date for reference
	 * @param logType The LogType needed
	 * @throws SQLException
	 */
	private static void persistLog(String formattedMessage, LogType logType) throws SQLException {
		DBConnector dbConnector = DBConnector.getInstance();
		Connection connection = dbConnector.getConnection();
		
		PreparedStatement prepStmt = connection.prepareStatement("insert into logs (message, type) values (?, ?)");
		prepStmt.setString(1, formattedMessage);
		prepStmt.setInt(2, logType.getDbValue());
		
		prepStmt.execute();
	}
}