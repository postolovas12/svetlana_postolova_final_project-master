package com.spp.POM;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    final String HOME_PAGE_PATH = "/posts/all";

    @FindBy(id = "homeIcon")
    private WebElement headerHomeIcon;
    @FindBy(id = "nav-link-home")
    private WebElement navBarHomeLink;
    @FindBy(id = "nav-link-login")
    private WebElement navBarLoginLink;
    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfileLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPostLink;
    @FindBy(id = "search-bar")
    private WebElement navBarSearchInputField;
    @FindBy(xpath = "//i[contains(@class,'fas fa-sign-out-alt fa-lg')]")
    private WebElement navBarSignOutButton;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        navigateTo(HOME_PAGE_PATH);
    }

    public void clickOnLoginLink() {
        waitAndClickOnWebElement(navBarLoginLink);
    }

    public void clickOnProfileLink() {
        waitAndClickOnWebElement(navBarProfileLink);
    }

    public void clickOnNewPostLink() {
        waitAndClickOnWebElement(navBarNewPostLink);
    }

    public void clickOnSignOutButton() {
        waitAndClickOnWebElement(navBarSignOutButton);
    }

    public boolean isLoginLinkShown() {
        return isElementPresent(navBarLoginLink);
    }

    public boolean isProfileLinkShown() {
        return isElementPresent(navBarProfileLink);
    }

    public boolean isNewPostLinkShown() {
        return isElementPresent(navBarNewPostLink);
    }

    public boolean isSignOutButtonShown() {
        return isElementPresent(navBarSignOutButton);
    }

    public boolean isSignOutButtonClickable() {
        return isElementClickable(navBarSignOutButton);
    }

    public boolean isLoginLinkClickable() {
        return isElementClickable(navBarLoginLink);
    }

    public boolean isProfileLinkClickable() {
        return isElementClickable(navBarProfileLink);
    }

    public boolean isNavBarNewPostLinkClickable() {
        return isElementClickable(navBarNewPostLink);
    }
}