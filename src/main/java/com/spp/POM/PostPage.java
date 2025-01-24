package com.spp.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class PostPage extends BasePage {
    final String POST_PAGE_PATH = "/posts/create";

    @FindBy(css = "h3.text-center")
    private WebElement postImageHeaderText;
    @FindBy(css = "img.image-preview")
    private WebElement image;
    @FindBy(css = "input.input-lg")
    private WebElement imageTextElement;
    @FindBy(css = "input.file")
    private WebElement uploadImageButton;
    @FindBy(name = "caption")
    private WebElement imageCaptionElement;
    @FindBy(id = "create-post")
    private WebElement createPostButton;
    @FindBy(css = "div[aria-label=\"Post created!\"]")
    private WebElement toastMessage;

    public PostPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void uploadImage(File file) {
        isElementPresent(uploadImageButton);
        uploadImageButton.sendKeys(file.getAbsolutePath());
        log.info("CONFIRM # The file was successfully uploaded.");
    }

    public void providePostCaption(String caption) {
        waitAndTypeTextInField(imageCaptionElement, caption);
        log.info("CONFIRM # The user has provided caption text: " + caption);
    }

    public void clickCreatePostButton() {
        waitAndClickOnWebElement(createPostButton);
        log.info("CONFIRM # The user has clicked on the create post submit button.");
    }

    public String getPostImageHeaderText() {
        String actualPostImageFormHeaderText = postImageHeaderText.getText();
        return actualPostImageFormHeaderText;
    }

    public String getFileName() {
        String fileName = getAttributeValue(imageTextElement, "placeholder");
        log.info("CONFIRM # " + "The file name is: " + fileName);
        return fileName;
    }

    public String getUploadActionMessage() {
        String actualUploadMessageText = getElementText(toastMessage);
        return actualUploadMessageText;
    }

    public boolean isImageVisible() {
        boolean isVisible = false;
        try {
            isVisible = wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
            log.info("CONFIRM # The file is visible.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The file is not visible");
            isVisible = false;
        }
        return isVisible;
    }

    public boolean isCreatePostButtonShown() {
        return isElementPresent(createPostButton);
    }

    public boolean isCreatePostButtonClickable() {
        return isElementClickable(createPostButton);
    }
}