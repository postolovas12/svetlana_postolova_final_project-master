package com.spp.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final String BASE_URL = "http://training.skillo-bg.com:4300";

    WebDriver driver;
    WebDriverWait wait;
    Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void navigateTo(String pageURLPath) {
        String currentURL = BASE_URL + pageURLPath;
        driver.get(currentURL);
        log.info("CONFIRM # The user has navigated to: " + currentURL);
        waitPageTobeFullyLoaded();
    }

    public void waitAndClickOnWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        waitPageTobeFullyLoaded();
    }

    public void waitAndTypeTextInField(WebElement textField, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(inputText);
        waitPageTobeFullyLoaded();
    }

    public void waitPageTobeFullyLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public String getElementText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getText();
        return elementText;
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue;
    }

    public boolean isURLLoaded(String pageURL) {
        waitPageTobeFullyLoaded();
        return wait.until(ExpectedConditions.urlContains(pageURL));
    }

    public boolean isElementPresent(WebElement element) {
        boolean isWebElementPresent = false;
        String locatorInfo = locatorInfo(element);

        log.info("ACTION @ The user is verifying web element with locator info: " + locatorInfo);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("# SHOWN # Web element is shown with locator info: " + locatorInfo);
            isWebElementPresent = true;
        } catch (TimeoutException e) {
            log.error("# NOT SHOWN # Web element is NOT shown with locator info: " + locatorInfo);
            isWebElementPresent = false;
        }
        return isWebElementPresent;
    }

    public boolean isElementClickable(WebElement element) {
        boolean isElementClickable = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isElementClickable = true;
        } catch (TimeoutException e) {
            isElementClickable = false;
        }
        return isElementClickable;
    }

    private String locatorInfo(WebElement element) {
        String[] rawWebElmInfo = element.toString().split("->");
        String[] webElmInfo = rawWebElmInfo[1].split(":");
        String locatorStrategy = webElmInfo[0];
        String locatorExpression = webElmInfo[1];
        String info = "LOCATOR STRATEGY BY:" + locatorStrategy.toUpperCase() + " LOCATOR EXPRESSION:" + locatorExpression;
        return info;
    }
}