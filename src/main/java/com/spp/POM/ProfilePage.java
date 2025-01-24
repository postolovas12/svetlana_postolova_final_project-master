package com.spp.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ProfilePage extends BasePage {
    final String PROFILE_PAGE_PATH = "/users/8864";

    @FindBy(css = "h2")
    private WebElement profileUsername;
    @FindBy(id = "upload-img")
    private WebElement uploadProfileImage;
    @FindBy(css = "img[alt='Profile Picture']")
    private WebElement profileImage;
    @FindBy(tagName = "app-post")
    private WebElement postImageDisplayed;
    @FindBy(css = "i.like.far.fa-heart.fa-2x.ng-star-inserted")
    private WebElement likeButton;
    @FindBy(css = "label.delete-ask")
    private WebElement deletePostButton;
    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement areYouSureYesButton;
    @FindBy(xpath = "//div[contains(@aria-label,'Post Deleted!')]")
    private WebElement confirmDeletionMessage;
    @FindBy(xpath = "//div[contains(@aria-label,'Post liked')]")
    private WebElement postLikeMessage;
    @FindBy(xpath = "//div[contains(@aria-label,'Post disliked')]")
    private WebElement postDislikeMessage;
    @FindBy(css = "div[aria-label=\"Profile picture updated\"]")
    private WebElement toastMessage;

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void uploadProfilePicture(File file) {
        uploadProfileImage.sendKeys(file.getAbsolutePath());
        log.info("CONFIRM # The profile picture was successfully uploaded.");
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = Collections.singletonList(wait.until(ExpectedConditions.visibilityOf(postImageDisplayed)));
        posts.get(postIndex).click();
        waitPageTobeFullyLoaded();
    }

    public void clickOnYesButton() {
        waitAndClickOnWebElement(areYouSureYesButton);
    }

    public void clickOnDeletePostButton() {
        waitAndClickOnWebElement(deletePostButton);
    }

    public String getUsername() {
        String usernameText = profileUsername.getText();
        return usernameText;
    }

    public int getPostCount() {
        List<WebElement> posts = Collections.singletonList(wait.until(ExpectedConditions.visibilityOf(postImageDisplayed)));
        return posts.size();
    }

    public String getUploadActionMessage() {
        String actualUploadMessageText = getElementText(toastMessage);
        return actualUploadMessageText;
    }

    public boolean isDeletePostButtonShown() {
        return isElementPresent(deletePostButton);
    }

    public boolean isAreYouSureYesButtonShown() {
        return isElementPresent(areYouSureYesButton);
    }

    public boolean isDeletePostButtonClickable() {
        return isElementClickable(deletePostButton);
    }

    public boolean isAreYouSureYesButtonClickable() {
        return isElementClickable(areYouSureYesButton);
    }

    public boolean isDeletedMessageVisible() {
        boolean isDeletedMessageVisible = false;
        try {
            isDeletedMessageVisible = wait.until(ExpectedConditions.visibilityOf(confirmDeletionMessage)).isDisplayed();
            log.info("CONFIRMATION # The 'Post Deleted!' message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The 'Post Deleted!' message is not displayed!");
            isDeletedMessageVisible = false;
        }
        return isDeletedMessageVisible;
    }

    public boolean isProfileImageVisible() {
        boolean isVisible = false;
        try {
            isVisible = wait.until(ExpectedConditions.visibilityOf(profileImage)).isDisplayed();
            log.info("CONFIRM # The file is visible.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The file is not visible");
            isVisible = false;
        }
        return isVisible;
    }

    public void closePostModal() {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();
            log.info("Post modal closed successfully using ESC key.");
        } catch (Exception e) {
            log.error("Failed to close the post modal using ESC key: " + e.getMessage());
        }
    }
}