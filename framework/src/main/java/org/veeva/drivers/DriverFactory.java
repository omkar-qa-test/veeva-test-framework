package org.veeva.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.veeva.config.AppConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	/**
	 * Creates and returns a WebDriver instance for the specified browser.
	 * Automatically handles driver setup using WebDriverManager.
	 * 
	 * @param browserName The name of the browser to create (should match constants in AppConstants)
	 * @return Configured WebDriver instance for the requested browser
	 * @throws IllegalArgumentException if the browser is not supported
	 * @throws UnsupportedOperationException if trying to use Safari on non-Mac systems	
	 */
	public static WebDriver createInstance(String browserName) {
		WebDriver driver;
		String browser = browserName.toLowerCase();

		switch (browser) {
		case AppConstants.CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments(
					"--start-maximized",
					"--remote-allow-origins=*",
					"--disable-infobars"
					);
			driver = new ChromeDriver(chromeOptions);
			break;

		case AppConstants.FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--start-maximized");
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case AppConstants.EDGE:
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments(
					"--start-maximized",
					"--remote-allow-origins=*",
					"--disable-infobars"
					);
			driver = new EdgeDriver(edgeOptions);
			break; 

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		return driver;
	}
}