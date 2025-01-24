package register;

import base.BaseTest;
import com.spp.POM.HomePage;
import com.spp.POM.LoginPage;
import com.spp.POM.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

public class RegistrationHappyPathTest extends BaseTest {
    private static final String HOME_PAGE_PATH = "/posts/all";
    private static final String LOGIN_PAGE_PATH = "/users/login";
    private static final String REGISTRATION_PAGE_PATH = "/users/register";

    private static final String REGISTRATION_FORM_HEADER_TITLE = "Sign up";
    private static final String TEST_USERNAME = RegistrationDataGenerator.createUsername();
    private static final String TEST_EMAIL = RegistrationDataGenerator.createEmail();
    private static final String TEST_PASSWORD = RegistrationDataGenerator.createPassword();
    private static final String BIRTHDATE = "01012001";
    private static final String PUBLIC_INFO = "Testing registration with valid credentials";
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = "Successful register!";
    private static final String SUCCESSFUL_SIGN_OUT_MESSAGE = "Successful logout!";

    @Test
    public void verifySuccessfulRegistrationWithValidCredentials() {
        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Open the ISkillo Home Page as a guest user.");
        homePage.navigateToHomePage();

        log.info("STEP 1.1: Verify the Home Page loaded successfully.");
        boolean isHomePageLoaded = homePage.isURLLoaded(HOME_PAGE_PATH);
        Assert.assertTrue(isHomePageLoaded, "Home Page failed to load!");

        log.info("STEP 1.2: Verify the Login link in the navigation bar is visible.");
        boolean isLoginLinkVisible = homePage.isLoginLinkShown();
        Assert.assertTrue(isLoginLinkVisible,
                "Login link is not visible in the navigation bar.");

        log.info("STEP 1.3: Verify the Login link in the navigation bar is clickable.");
        boolean isLoginLinkClickable = homePage.isLoginLinkClickable();
        Assert.assertTrue(isLoginLinkClickable, "Login link is not clickable!");

        log.info("STEP 2: Navigate to the Login Page via the Login link.");
        homePage.clickOnLoginLink();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1: Verify the Login Page loaded successfully.");
        boolean isLoginPageLoaded = loginPage.isURLLoaded(LOGIN_PAGE_PATH);
        Assert.assertTrue(isLoginPageLoaded, "Login Page failed to load!");

        log.info("STEP 3: Verify the Register link is visible on the Login Page.");
        boolean isRegisterLinkVisible = loginPage.isRegisterLinkShown();
        Assert.assertTrue(isRegisterLinkVisible,
                "Register link is not visible on the Login Page.");

        log.info("STEP 3.1: Verify the Register link is clickable.");
        boolean isRegisterLinkClickable = loginPage.isRegisterLinkClickable();
        Assert.assertTrue(isRegisterLinkClickable,
                "Register link is not clickable.");

        log.info("STEP 4: Navigate to the Registration Page via the Register link.");
        loginPage.clickOnRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        log.info("STEP 5: Verify the Registration Page loaded successfully.");
        boolean isRegistrationPageLoaded = registrationPage.isURLLoaded(REGISTRATION_PAGE_PATH);
        Assert.assertTrue(isRegistrationPageLoaded,
                "Registration Page failed to load!");

        log.info("STEP 5.1: Verify the Registration form header title matches the expected value.");
        String actualRegistrationFormHeaderTitle = registrationPage.getRegistrationFormHeaderTitleText();
        Assert.assertEquals(actualRegistrationFormHeaderTitle, REGISTRATION_FORM_HEADER_TITLE,
                "Registration form header title is incorrect.");

        log.info("STEP 5.2: Verify Registration form submit button is visible.");
        boolean isRegSubmitButtonVisible = registrationPage.isRegistrationSubmitButtonShown();
        Assert.assertTrue(isRegSubmitButtonVisible,
                "Registration form submit button is not visible!");

        log.info("STEP 6: Provide valid user credentials.");
        registrationPage.provideUserCredentials(TEST_USERNAME, TEST_EMAIL, BIRTHDATE, TEST_PASSWORD, TEST_PASSWORD, PUBLIC_INFO);

        log.info("STEP 7: Verify Registration form submit button is clickable.");
        boolean isRegSubmitButtonClickable = registrationPage.isRegistrationSubmitButtonClickable();
        Assert.assertTrue(isRegSubmitButtonClickable,
                "Registration form submit button is not clickable!");

        log.info("STEP 7.1: Click on the Registration form submit button.");
        registrationPage.clickOnRegistrationFormSubmitButton();

        log.info("STEP 8: Verify successful registration message.");
        String actualRegisterActionMessage = registrationPage.getActionMessageText();
        Assert.assertEquals(actualRegisterActionMessage, SUCCESSFUL_REGISTRATION_MESSAGE,
                "Successful registration message is incorrect!");

        log.info("STEP 9: Verify that the user is redirected to Home Page after registration.");
        boolean isUserRedirectedToHomePage = homePage.isURLLoaded(HOME_PAGE_PATH);
        Assert.assertTrue(isUserRedirectedToHomePage,
                "User was not redirected to the Home Page after registration.");

        log.info("STEP 10: Verify Profile link is visible.");
        boolean isProfileLinkVisible = homePage.isProfileLinkShown();
        Assert.assertTrue(isProfileLinkVisible,
                "Profile link is not visible in the navigation bar.");

        log.info("STEP 11: Verify Sign out button is visible.");
        boolean isLogOutButtonVisible = homePage.isSignOutButtonShown();
        Assert.assertTrue(isLogOutButtonVisible,
                "Sign out button is not visible in the navigation bar.");

        log.info("STEP 11.1. Verify Sign out button is clickable.");
        boolean isLogOutButtonClickable = homePage.isSignOutButtonClickable();
        Assert.assertTrue(isLogOutButtonClickable, "Sign out button is not clickable.");

        log.info("STEP 11.2: Click the Sign out button.");
        homePage.clickOnSignOutButton();

        log.info("STEP 11.3: Verify successful Sign out message.");
        String actualLogOutActionMessage = loginPage.getSignOutActionMessageText();
        Assert.assertEquals(actualLogOutActionMessage, SUCCESSFUL_SIGN_OUT_MESSAGE,
                "Successful Sign out message is incorrect!");

        log.info("STEP 12: Verify user is redirected back to Login Page.");
        boolean isUserRedirectedToSignInPage = loginPage.isURLLoaded(LOGIN_PAGE_PATH);
        Assert.assertTrue(isUserRedirectedToSignInPage,
                "User was not redirected to the Login Page!");
    }
}