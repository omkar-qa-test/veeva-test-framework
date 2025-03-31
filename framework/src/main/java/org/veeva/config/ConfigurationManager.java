package org.veeva.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Manages configuration properties with support for:
 * - Default properties file (loaded once at startup)
 * - Runtime loading from custom properties files
 */
public class ConfigurationManager {
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(new FileInputStream(AppConstants.DEFAULT_CONFIG_PROPERTY_FILE_PATH));
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config file", e);
		}
	}



	/**
	 * Gets property from default configuration file
	 * @param key Property key to lookup
	 * @return Property value or null if not found
	 */
	public static String get(String key) {
		return properties.getProperty(key);
	}



	/**
	 * Gets property from specified properties file
	 * @param filePath Path to properties file
	 * @param key Property key to lookup
	 * @return Property value
	 * @throws RuntimeException if file loading fails
	 */
	public static String get(String filePath, String key) {
		Properties localProps = new Properties();
		try (InputStream input = new FileInputStream(filePath)) {
			localProps.load(input);
			return localProps.getProperty(key);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read property '" + key + "' from file: " + filePath, e);
		}
	}
}