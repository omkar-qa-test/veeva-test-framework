package org.veeva.web.utils;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.veeva.config.AppConstants;


// Helper class for common web browser actions
public class WebActions {

	private WebDriver driver;
	private String defaultWindow;  // Stores main window handle
	private WebDriverWait wait;    // For explicit waits

	// Initialize with WebDriver and set up wait time
	public WebActions(WebDriver driver) {
		this.driver = driver;		
		wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT));

	}

	// Open a URL in the browser
	public void openUrl(String url) {
		driver.get(url);
	}

	// Maximize the browser window
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	// Click on an element after waiting for it to be clickable
	public void clickElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	// Check if element is present
	public boolean checkIfElementExists(By locator) {

		return !driver.findElements(locator).isEmpty();

	}


	// Check if element is visible (with wait)
	public boolean checkIfElementIsVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		}catch(Exception e) {
			return false;
		}

	}
	// Hover over menu and click submenu item
	public boolean hoverAndClickSubMenu(By menuLocator, By subMenuLocator) {
		try {
			WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuLocator));
			WebElement subMenu = driver.findElement(subMenuLocator);

			new Actions(driver)
			.moveToElement(menu)
			.moveToElement(subMenu)
			.click()
			.perform();
			return true;
		} catch (Exception e) {
			return false;  // Hover action failed
		}
	}

	// Smooth scroll to bring element into view
	public boolean scrollToElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			((JavascriptExecutor)driver).executeScript(
					"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", 
					element);
			return true;
		} catch (Exception e) {
			return false;  // Scroll failed
		}
	}

	// Remember the main browser window
	public boolean setDefaultWindow() {
		try {
			defaultWindow = driver.getWindowHandle();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Switch to newly opened window
	public boolean switchToNewWindow() {
		try {
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				if (!window.equals(defaultWindow)) {
					driver.switchTo().window(window);
					return true;
				}
			}
			return false;  // No new window found
		} catch (Exception e) {
			return false;
		}
	}

	// Find multiple elements
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	// Count elements matching locator
	public int findElementsSize(By locator) {
		return driver.findElements(locator).size();
	}

	// Get text from child element
	public String getElementText(WebElement parentElement, By childLocator) {
		return parentElement.findElement(childLocator).getText();
	}
}