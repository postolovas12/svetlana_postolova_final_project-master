package login;

import base.BaseTest;
import com.spp.POM.HomePage;
import com.spp.POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageLayoutTest extends BaseTest {
    private static final String HOME_PAGE_PATH = "/posts/all";
    private static final String LOGIN_PAGE_PATH = "/users/login";

    private static final String LOGIN_FORM_HEADER_TITLE = "Sign in";
    private static final String USERNAME_PLACEHOLDER = "Username or email";
    private static final String PASSWORD_PLACEHOLDER = "Password";
    private static final String CHECKBOX_LABEL_TEXT = "Remember me";
    private static final String SUBMIT_BUTTON_TEXT = "Sign in";
    private static final String FOOTER_LABEL_TEXT = "Not a member?";

    @Test
    public void verifyLoginPageLayout() {
        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Open the ISkillo Home Page.");
        homePage.navigateToHomePage();

        log.info("STEP 1.1: Verify the Home Page fully loaded.");
        boolean isHomePageLoaded = homePage.isURLLoaded(HOME_PAGE_PATH);
        Assert.assertTrue(isHomePageLoaded, "Home Page failed to load!");

        log.info("STEP 1.2: Verify the Login link is visible.");
        boolean isLoginLinkVisible = homePage.isLoginLinkShown();
        Assert.assertTrue(isLoginLinkVisible,
                "Login link is not visible in the navigation bar.");

        log.info("STEP 1.3: Verify the Login link is clickable.");
        boolean isLoginLinkClickable = homePage.isLoginLinkClickable();
        Assert.assertTrue(isLoginLinkClickable, "Login link is not clickable!");

        log.info("STEP 2: Navigate to the Login Page.");
        homePage.clickOnLoginLink();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1: Verify the Login Page fully loaded successfully.");
        boolean isLoginPageLoaded = loginPage.isURLLoaded(LOGIN_PAGE_PATH);
        Assert.assertTrue(isLoginPageLoaded, "Login Page failed to load!");

        log.info("STEP 3: Verify the Login form header title.");
        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderTitleText();
        Assert.assertEquals(actualLoginFormHeaderText, LOGIN_FORM_HEADER_TITLE,
                "Login form header title is incorrect.");

            log.info("STEP 4: Verify the placeholder text for username field.");
            String actualUsernamePlaceholder = loginPage.verifyUsernameInputFieldPlaceholder();
            Assert.assertEquals(actualUsernamePlaceholder, USERNAME_PLACEHOLDER,
                    "Username input field placeholder text is incorrect.");

        log.info("STEP 5: Verify the placeholder text for password field.");
        String actualPasswordPlaceholder = loginPage.verifyPasswordInputFieldPlaceholder();
        Assert.assertEquals(actualPasswordPlaceholder, PASSWORD_PLACEHOLDER,
                "Password input field placeholder text is incorrect.");

        log.info("STEP 6: Verify the Login form checkbox is visible.");
        boolean isCheckboxVisible = loginPage.isRememberMeCheckboxShown();
        Assert.assertTrue(isCheckboxVisible, "Login form checkbox is not visible.");

        log.info("STEP 6.1: Verify the Login form checkbox is clickable.");
        boolean isCheckboxClickable = loginPage.isCheckBoxClickable();
        Assert.assertTrue(isCheckboxClickable, "Login form checkbox is not clickable.");

        log.info("STEP 6.2: Verify the Login form checkbox label text.");
        String actualCheckboxLabelText = loginPage.getCheckBoxLabelText();
        Assert.assertEquals(actualCheckboxLabelText, CHECKBOX_LABEL_TEXT,
                "Checkbox label text is incorrect.");

        log.info("STEP 7: Verify the Login form submit button is visible.");
        boolean isLoginSubmitButtonVisible = loginPage.isLoginSubmitButtonShown();
        Assert.assertTrue(isLoginSubmitButtonVisible,
                "Login form submit button is not visible.");

        log.info("STEP 7.1: Verify Login form submit button is not clickable without user data.");
        boolean isLoginSubmitButtonClickable = loginPage.isLoginSubmitButtonClickable();
        Assert.assertFalse(isLoginSubmitButtonClickable,
                "Login submit button should not be clickable without user data.");

        log.info("STEP 7.2: Verify Login form submit button text.");
        String actualLoginSubmitButtonText = loginPage.getLoginFormSubmitButtonText();
        Assert.assertEquals(actualLoginSubmitButtonText, SUBMIT_BUTTON_TEXT,
                "Login submit button text is incorrect.");

        log.info("STEP 8: Verify the Login form footer label text.");
        String actualLoginFormFooterLabelText = loginPage.getLoginFormFooterLabelText();
        Assert.assertEquals(actualLoginFormFooterLabelText, FOOTER_LABEL_TEXT,
                "Login form footer label text is incorrect.");

        log.info("Step 9: Verify the Register link is visible.");
        boolean isRegisterLinkVisible = loginPage.isRegisterLinkShown();
        Assert.assertTrue(isRegisterLinkVisible,
                "Register link is not visible on the Login page.");

        log.info("Step 10: Verify the Register link is clickable.");
        boolean isRegisterLinkClickable = loginPage.isRegisterLinkClickable();
        Assert.assertTrue(isRegisterLinkClickable,
                "Register link is not clickable on the Login page.");
    }
}