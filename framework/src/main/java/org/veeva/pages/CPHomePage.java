package org.veeva.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.veeva.config.AppConstants;
import org.veeva.config.ConfigurationManager;
import org.veeva.web.utils.WebActions;

public class CPHomePage extends BasePage {
	private final WebActions webActions;
	private final By alertDeclineButton = By.id("onetrust-reject-all-handler");
	private final By alertCloseButton = By.xpath("//div[contains(@class,'absolute right-3') and text()='x']");
	private final By shopButton = By.xpath("//a[@rel='noreferrer']/span[text()='Shop']");
	private final By mensSubmenu = By.xpath("(//a[contains(@title,'Men')])[1]");
	private final By threeDotsMenu = By.xpath("(//span[text()='...'])[1]");
	private final By newsFeaturesSubmenu = By.xpath("(//a[contains(@title,'News & Features')])[1]");

	public CPHomePage(WebDriver driver, WebActions webActions) {
		super(driver);
		this.webActions = webActions;
	}

	public void openHomePage() {
		webActions.openUrl(AppConstants.URL_CP);
		webActions.maximizeBrowser();
	}

	public void declineTrackingAlert() {
		webActions.clickElement(alertDeclineButton);
	}

	public void closePresaleTicketAlert() {
		webActions.clickElement(alertCloseButton);
	}

	public CPMensShopingPage navigateToMensShopping() {
		webActions.hoverAndClickSubMenu(shopButton, mensSubmenu);
		webActions.setDefaultWindow();
		webActions.switchToNewWindow();
		return new CPMensShopingPage(driver, webActions);
	}

	public CPNewsAndFeaturesPage navigateToNewsAndFeatures() {
		webActions.hoverAndClickSubMenu(threeDotsMenu, newsFeaturesSubmenu);
		return new CPNewsAndFeaturesPage(driver, webActions);
	}

	@Override
	protected boolean isAt() {
		return webActions.checkIfElementExists(shopButton);
	}
}