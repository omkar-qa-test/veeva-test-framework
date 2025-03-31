package org.veeva.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.veeva.web.utils.WebActions;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CPMensJacketPage extends BasePage {
	private final WebActions webActions;
	private final By jacketList = By.cssSelector(".product-card.row");
	private final By jacketTitle = By.cssSelector("div:nth-child(2) > div:nth-child(2) > a");
	private final By jacketPrice = By.cssSelector("div:nth-child(2) > div:nth-child(1) > div > div > div > span > span > span > span");
	private final By nextPageButton = By.cssSelector("li.next-page");
	private final By disabledNextPageButton = By.cssSelector("li.next-page.disabled");

	public CPMensJacketPage(WebDriver driver, WebActions webActions) {
		super(driver);
		this.webActions = webActions;
	}

	public void exportJacketDetailsToFile(String filePath) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			int count =0;
			while (count<6) { //intentionally put this condition as there is some problem with app, will discuss this during the call.

				webActions.checkIfElementIsVisible(jacketList);
				List<WebElement> jackets = webActions.findElements(jacketList);

				for (WebElement jacket : jackets) {
					String title = jacket.findElement(jacketTitle).getText();
					String price = jacket.findElement(jacketPrice).getText();
					writer.write(String.format("Title: %s | Price: %s%n", title, price));
				}

				if (!webActions.checkIfElementIsVisible(nextPageButton)) { break; }

				webActions.clickElement(nextPageButton);
				count=count+1;

			}
		}
	}

	@Override
	protected boolean isAt() {
		return webActions.checkIfElementExists(jacketList);
	}
}