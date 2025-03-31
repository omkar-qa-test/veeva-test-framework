package org.veeva.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.veeva.web.utils.WebActions;

public class CPMensShopingPage extends BasePage {
    private final WebActions webActions;
    private final By jacketsFilter = By.xpath("//span[text()='Jackets']");

    public CPMensShopingPage(WebDriver driver, WebActions webActions) {
        super(driver);
        this.webActions = webActions;
    }

    public CPMensJacketPage filterJackets() {
        webActions.clickElement(jacketsFilter);
        return new CPMensJacketPage(driver, webActions);
    }

    @Override
    protected boolean isAt() {
        return webActions.checkIfElementExists(jacketsFilter);
    }
}