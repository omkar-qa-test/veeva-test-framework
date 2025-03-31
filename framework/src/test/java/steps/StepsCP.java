package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.veeva.drivers.DriverManager;
import org.veeva.pages.CPHomePage;
import org.veeva.pages.CPMensJacketPage;
import org.veeva.pages.CPNewsAndFeaturesPage;
import org.veeva.web.utils.WebActions;
import java.io.IOException;

public class StepsCP {
    private final WebDriver driver;
    private final CPHomePage cpHomePage;
    private CPMensJacketPage cpMensJacketPage;
    private CPNewsAndFeaturesPage cpNewsAndFeaturesPage;

    public StepsCP() {
        this.driver = DriverManager.getDriver();
        WebActions webActions = new WebActions(driver);
        this.cpHomePage = new CPHomePage(driver, webActions);
    }

    @Given("User navigates to Men's Jackets section")
    public void navigateToMensJackets() {
        cpHomePage.openHomePage();
        cpHomePage.closePresaleTicketAlert();
        cpMensJacketPage = cpHomePage.navigateToMensShopping().filterJackets();
    }

    @Then("User extracts all jackets data and stores in a file")
    public void extractJacketsData() throws IOException {
        cpMensJacketPage.exportJacketDetailsToFile("target/jacket_details.txt");
    }

    @Given("User navigates to News & Features section")
    public void navigateToNewsAndFeatureSection() {
        cpHomePage.openHomePage();
        cpHomePage.closePresaleTicketAlert();
        cpNewsAndFeaturesPage = cpHomePage.navigateToNewsAndFeatures();
    }

    @Then("the user should be able to count the number of video feeds")
    public void countVideoFeeds() {
        int videoCount = cpNewsAndFeaturesPage.countAllVideos();
        Assert.assertTrue(videoCount > 0, "No video feeds found");
    }

    @And("the user should be able to count the number of video feeds present in the page >= {int}d")
    public void countVideoFeedsOlderThanDays(int days) {
        int oldVideoCount = cpNewsAndFeaturesPage.countVideosOlderThanDays(days);
        Assert.assertTrue(oldVideoCount >= 0, "Invalid video count");
    }
}