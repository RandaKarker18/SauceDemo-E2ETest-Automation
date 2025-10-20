package com.automation.e2eTests.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private String propertyFilePath = "src/test/resources/configs/env-config.properties";

	/**
	 * Instanciation de config file reader.
	 */
	public ConfigFileReader() {

		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				System.out.println("Exception: ," + e);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Exception: ," + e);
			throw new RuntimeException("Configuration.propertiesnot found at " + propertyFilePath);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Exception: ," + e);
				}
			}
		}

	}

	/**
	 * Accesseur de l'attribut param.
	 * 
	 * @param adress application param
	 * @return application param
	 */
	public String getProperties(String prop) {
		String param = properties.getProperty(prop);
		if (param != null)
			return param;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
}
