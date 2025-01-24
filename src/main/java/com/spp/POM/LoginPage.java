package com.spp.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    final String LOGIN_PAGE_PATH = "/users/login";

    @FindBy(css = "p.h4")
    private WebElement loginFormHeaderTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;
    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement checkBoxLabelText;
    @FindBy(xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement rememberMeCheckBox;
    @FindBy(id = "sign-in-button")
    private WebElement loginSubmitButton;
    @FindBy(xpath = "//span[contains(text(),'Not a member')]")
    private WebElement loginFormFooterLabelText;
    @FindBy(xpath = "//a[contains(.,'Register')]")
    private WebElement registerLink;
    @FindBy(css = "div[aria-label=\"Successful login!\"]")
    private WebElement signInToastMessage;
    @FindBy(css = "div[aria-label=\"Wrong username or password!\"]")
    private WebElement wrongCredentialsToastMessage;
    @FindBy(css = "div[aria-label=\"Successful logout!\"]")
    private WebElement signOutToastMessage;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void provideUsername(String username) {
        waitAndTypeTextInField(usernameInputField, username);
    }

    public void providePassword(String password) {
        waitAndTypeTextInField(passwordInputField, password);
    }

    public void provideUserCredentials(String username, String password) {
        provideUsername(username);
        providePassword(password);
    }

    public void clickOnRememberMeCheckBox() {
        waitAndClickOnWebElement(rememberMeCheckBox);
    }

    public void clickOnSignInButton() {
        waitAndClickOnWebElement(loginSubmitButton);
    }

    public void clickOnRegisterLink() {
        waitAndClickOnWebElement(registerLink);
    }

    public String getLoginFormHeaderTitleText() {
        String actualLoginHeaderText = getElementText(loginFormHeaderTitle);
        return actualLoginHeaderText;
    }

    public String getCheckBoxLabelText() {
        String actualCheckBoxLabelText = getElementText(checkBoxLabelText);
        return actualCheckBoxLabelText;
    }

    public String getLoginFormSubmitButtonText() {
        String actualLoginFormSubmitButtonText = getElementText(loginSubmitButton);
        return actualLoginFormSubmitButtonText;
    }

    public String getLoginFormFooterLabelText() {
        String actualFooterText = getElementText(loginFormFooterLabelText);
        return actualFooterText;
    }

    public String getSignInActionMessageText() {
        String actualMessageText = getElementText(signInToastMessage);
        return actualMessageText;
    }

    public String getSignOutActionMessageText() {
        String actualMessageText = getElementText(signOutToastMessage);
        return actualMessageText;
    }

    public String getWrongCredentialsActionMessageText() {
        String actualMessageText = getElementText(wrongCredentialsToastMessage);
        return actualMessageText;
    }

    public boolean isRememberMeCheckboxShown() {
        return isElementPresent(rememberMeCheckBox);
    }

    public boolean isLoginSubmitButtonShown() {
        return isElementPresent(loginSubmitButton);
    }

    public boolean isRegisterLinkShown() {
        return isElementPresent(registerLink);
    }

    public boolean isCheckBoxClickable() {
        return isElementClickable(rememberMeCheckBox);
    }

    public boolean isLoginSubmitButtonClickable() {
        return isElementClickable(loginSubmitButton);
    }

    public boolean isRegisterLinkClickable() {
        return isElementClickable(registerLink);
    }

    public boolean isRememberMeCheckboxClickable() {
        return isElementClickable(rememberMeCheckBox);
    }

    public String verifyUsernameInputFieldPlaceholder() {
        String actualUsernamePlaceholder = getAttributeValue(usernameInputField, "placeholder");
        return actualUsernamePlaceholder;
    }

    public String verifyPasswordInputFieldPlaceholder() {
        String actualPasswordPlaceholder = getAttributeValue(passwordInputField, "placeholder");
        return actualPasswordPlaceholder;
    }
}