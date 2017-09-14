package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * DB connection handler
 * @author Oscar
 *
 */
public class DBConnector {

	// Properties file name
	private static final String CONFIG_FILENAME = "config.properties";
	
	private static DBConnector dbConnector;
	private Connection connection;
	
	/**
	 * Initialize the DB connection in the constructor
	 */
	private DBConnector() {
		Properties properties = this.getDBConfig();
		
		String url = properties.getProperty("jdbc.url");
		String driver = properties.getProperty("jdbc.driver");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");

		try {
			Class.forName(driver).newInstance();
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if a DBConnector instance was already created 
	 * @return DBConnector dbConnector
	 */
	public static synchronized DBConnector getInstance() {
		if (dbConnector == null) {
			dbConnector = new DBConnector();
		}

		return dbConnector;
	}
	
	/**
	 * Get connection
	 * @return Connection connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Get DB config from properties file
	 * @return Properties properties
	 */
	private Properties getDBConfig() {
		Properties properties = new Properties();
		InputStream is = null;

		try {
			is = DBConnector.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);

			if (is != null) {
				properties.load(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties;
	}
}
