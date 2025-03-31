package org.veeva.drivers;

import org.openqa.selenium.WebDriver;
import java.util.Objects;

/**
 * Thread-safe WebDriver manager that maintains separate driver instances per thread.
 * Ensures proper driver cleanup and prevents memory leaks.
 */
public class DriverManager {
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			return null; // Explicit null initialization
		}
	};


	/**
	 * Gets the WebDriver instance for current thread
	 * @return Active WebDriver instance
	 * @throws IllegalStateException if no driver initialized for current thread
	 */
	public static WebDriver getDriver() {
		WebDriver currentDriver = driver.get();
		if (currentDriver == null) {
			throw new IllegalStateException("Driver not initialized for thread: " + Thread.currentThread().getId());
		}
		return currentDriver;
	}


	/**
	 * Sets the WebDriver instance for current thread
	 * @param driverRef Non-null WebDriver instance
	 */
	public static void setDriver(WebDriver driverRef) {
		Objects.requireNonNull(driverRef, "Driver cannot be null");
		if (driver.get() != null) {
			quitDriver();
		}
		driver.set(driverRef);
	}

	/**
	 * Properly quits and removes the current thread's WebDriver instance
	 */
	public static void quitDriver() {
		WebDriver currentDriver = driver.get();
		if (currentDriver != null) {
			try {
				currentDriver.quit();
			} finally {
				driver.remove();
			}
		}
	}

	/**
	 * Checks if current thread has an active driver instance
	 * @return true if driver exists, false otherwise
	 */
	public static boolean hasDriver() {
		return driver.get() != null;
	}
}