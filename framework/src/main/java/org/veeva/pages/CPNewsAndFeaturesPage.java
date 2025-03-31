package org.veeva.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.veeva.web.utils.WebActions;
import java.util.List;

public class CPNewsAndFeaturesPage extends BasePage {
    private final WebActions webActions;
    private final By videosSection = By.xpath("//h3[text()='VIDEOS']");
    private final By videoPlayButtons = By.cssSelector("div[class*='IconPlay']");
    private final By videoUploadTimes = By.cssSelector("div[class*='TileArticle'] time span");

    public CPNewsAndFeaturesPage(WebDriver driver, WebActions webActions) {
        super(driver);
        this.webActions = webActions;
    }

    public int countAllVideos() {
        webActions.scrollToElement(videosSection);
        return webActions.findElementsSize(videoPlayButtons);
    }

    public int countVideosOlderThanDays(int daysThreshold) {
        int count = 0;
        webActions.scrollToElement(videosSection);
        List<WebElement> uploadTimes = driver.findElements(videoUploadTimes);
        
        for (WebElement time : uploadTimes) {
            String timeText = time.getText();
            if (timeText.contains("d")) {
                int days = Integer.parseInt(timeText.split("d")[0]);
                if (days >= daysThreshold) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    protected boolean isAt() {
        return webActions.checkIfElementExists(videosSection);
    }
}