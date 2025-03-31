package org.veeva.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.veeva.config.AppConstants;
import io.cucumber.java.Scenario;
import java.time.Duration;

public abstract class BasePage {
	public Scenario scenario;
	protected final WebDriver driver;
	protected final WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		// Configure implicit wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppConstants.IMPLICIT_WAIT));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(AppConstants.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
	}

	protected abstract boolean isAt();
}