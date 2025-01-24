package com.spp.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends BasePage {
    final String REGISTRATION_PAGE_PATH = "/users/register";

    @FindBy(xpath = "//h4[contains(text(),'Sign up')]")
    private WebElement registrationFormHeaderTitle;
    @FindBy(xpath = "//input[contains(@placeholder,'Username')]")
    private WebElement usernameInputField;
    @FindBy(xpath = "//input[contains(@placeholder,'email')]")
    private WebElement emailInputField;
    @FindBy(xpath = "//input[contains(@placeholder,'Birth date')]")
    private WebElement birthDateInputField;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordInputField;
    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordInputField;
    @FindBy(xpath = "//textarea[contains(@placeholder,'Public info')]")
    private WebElement publicInfoInputField;
    @FindBy(css = "span.invalid-feedback")
    private WebElement invalidFeedbackMessage;
    @FindBy(id = "sign-in-button")
    private WebElement registrationSubmitButton;
    @FindBy(css = ".toast-message.ng-star-inserted")
    private WebElement toastMessage;

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void provideUsername(String username) {
        waitAndTypeTextInField(usernameInputField, username);
    }

    public void provideEmail(String email) {
        waitAndTypeTextInField(emailInputField, email);
    }

    public void provideBirthDate(String birthDate) {
        waitAndTypeTextInField(birthDateInputField, birthDate);
    }

    public void providePassword(String password) {
        waitAndTypeTextInField(passwordInputField, password);
    }

    public void confirmTheProvidedPassword(String password) {
        waitAndTypeTextInField(confirmPasswordInputField, password);
    }

    public void providePublicInfo(String publicInfo) {
        waitAndTypeTextInField(publicInfoInputField, publicInfo);
    }

    public void provideUserCredentials(String username, String email, String birthDate, String password, String confirmPassword, String publicInfo) {
        waitAndTypeTextInField(usernameInputField, username);
        waitAndTypeTextInField(emailInputField, email);
        waitAndTypeTextInField(birthDateInputField, birthDate);
        waitAndTypeTextInField(passwordInputField, password);
        waitAndTypeTextInField(confirmPasswordInputField, password);
        waitAndTypeTextInField(publicInfoInputField, publicInfo);
    }

    public void clickOnRegistrationFormSubmitButton() {
        waitAndClickOnWebElement(registrationSubmitButton);
    }

    public String getRegistrationFormHeaderTitleText() {
        String actualTitleText = getElementText(registrationFormHeaderTitle);
        return actualTitleText;
    }

    public String getRegistrationFormSubmitButtonText() {
        String actualButtonText = getElementText(registrationSubmitButton);
        return actualButtonText;
    }

    public String getInvalidFeedbackMessageText() {
        String actualFeedbackMessageText = getElementText(invalidFeedbackMessage);
        return actualFeedbackMessageText;
    }

    public String getActionMessageText() {
        String actualMessageText = getElementText(toastMessage);
        return actualMessageText;
    }

    public boolean isRegistrationSubmitButtonShown() {
        return isElementPresent(registrationSubmitButton);
    }

    public boolean isRegistrationSubmitButtonClickable() {
        return isElementClickable(registrationSubmitButton);
    }

    public String verifyUsernameInputFieldPlaceholder() {
        String actualUsernamePlaceholder = getAttributeValue(usernameInputField, "placeholder");
        return actualUsernamePlaceholder;
    }

    public String verifyEmailInputFieldPlaceholder() {
        String actualEmailPlaceholder = getAttributeValue(emailInputField, "placeholder");
        return actualEmailPlaceholder;
    }

    public String verifyBirthdateInputFieldPlaceholder() {
        String actualBirthdatePlaceholder = getAttributeValue(birthDateInputField, "placeholder");
        return actualBirthdatePlaceholder;
    }

    public String verifyPasswordInputFieldPlaceholder() {
        String actualPasswordPlaceholder = getAttributeValue(passwordInputField, "placeholder");
        return actualPasswordPlaceholder;
    }

    public String verifyConfirmPasswordInputFieldPlaceholder() {
        String actualConfirmPasswordPlaceholder = getAttributeValue(confirmPasswordInputField, "placeholder");
        return actualConfirmPasswordPlaceholder;
    }

    public String verifyPublicInfoInputFieldPlaceholder() {
        String actualPublicInfoPlaceholder = getAttributeValue(publicInfoInputField, "placeholder");
        return actualPublicInfoPlaceholder;
    }

    public void verifyFieldsInvalidFeedback() {
        List<WebElement> fields = driver.findElements(By.cssSelector("input, textarea"));
        List<String> errors = new ArrayList<>();

        for (WebElement field : fields) {
            String fieldName = field.getAttribute("placeholder");

            field.click();
            fields.get((fields.indexOf(field) + 1) % fields.size()).click();

            List<WebElement> errorMessages = field.findElements(By.xpath("./following-sibling::span[contains(@class, 'invalid-feedback')]"));

            if (!errorMessages.isEmpty() && errorMessages.get(0).isDisplayed()) {
                log.info("CONFIRMATION # Invalid feedback message is displayed for field: " + fieldName);
            } else {
                String errorMessage = "ERROR : Invalid feedback message is not displayed for field: " + fieldName;
                log.error(errorMessage);
                errors.add(errorMessage);
            }
        }

        if (!errors.isEmpty()) {
            Assert.fail("The following validation errors were found:\n" + String.join("\n", errors));
        }
    }
}