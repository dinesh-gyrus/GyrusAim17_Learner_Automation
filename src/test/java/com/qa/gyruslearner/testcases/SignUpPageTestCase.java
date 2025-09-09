package com.qa.gyruslearner.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.pages.SignUpPage;

public class SignUpPageTestCase extends TestBase {

	LoginPage loginpage;
	SignUpPage signuppage;

	public SignUpPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		signuppage = new SignUpPage();
	}

	@Test(priority = 1)
	public void verifySignUpUrlTest() {

		loginpage.doclickOnSignUpLinkButton();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlToBe(AppConstants.SIGNUP_PAGE_URL));
		String signUpPageUrl = signuppage.getSignUpPageUrl();
		Assert.assertEquals(signUpPageUrl, AppConstants.SIGNUP_PAGE_URL);

	}

	@Test(priority = 2)
	public void verifySignUpPageTitle() {

		String signUpPageTitle = signuppage.getSignUpPageTitle();
		Assert.assertEquals(signUpPageTitle, AppConstants.SIGNUP_PAGE_TITLE);
		
	}

	@Test(priority = 3)
	public void verifyCompnayLogoAndHeadingTextTest() {

		Assert.assertTrue(signuppage.isComoanyLogoDisplayed(), "Company Logo is not displayed!");
		Assert.assertTrue(signuppage.isRegisterTextDisplayed(), "Register text is not displayed!");
	}

	@Test(priority = 4)
	public void verifyMandatoryFieldsDisplayedTest() {

		Assert.assertTrue(signuppage.isFirstNameDisplayed(), "First Name field is not displayed!");
		Assert.assertTrue(signuppage.isLastNameDisplayed(), "Last Name field is not displayed!");
		Assert.assertTrue(signuppage.isEMailDisplayed(), "Email field is not displayed!");
	}

	@Test(priority = 5)
	public void verifyCaptchaDisplayedTest() {

		Assert.assertTrue(signuppage.isCaptchaDisplayed(), "reCAPTCHA checkbox is not displayed!");
		driver.switchTo().defaultContent();
	}

	// Ignore This Test case captcha doesn't fill Auto

	@Test(priority = 6)
	public void verifyRegisterButtonEnableTest() {

		System.out.println("Register Button is display : " + signuppage.isRegisterTextDisplayed());
		// Verify button is Enable initially
		Assert.assertTrue(signuppage.isRegisterButtonDisplayed(),
				"Register button should be displayed on the signup page!");

		System.out.println("Register Button is Disable : " + signuppage.isRegisterButtonEnable());
		// Verify button is disabled initially
		Assert.assertFalse(signuppage.isRegisterButtonEnable(),
				"Register button should be disabled before filling fields!");

		signuppage.fillMandatoryFields("test6", "team6", "test6gyrus@yopmail.com");

		// Handle Recaptcha
		signuppage.getHandleRecaptchaCheckBox();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(driver -> signuppage.isRegisterButtonEnable());

		Assert.assertTrue(signuppage.isRegisterButtonEnable(), "Register button did not enable after filling fields!");

	}

	@Test(priority = 7)
	public void verifyBackToLoginButtonTest() {

		// Verify Back to Login button is displayed
		Assert.assertTrue(signuppage.isBackToLoginDisplayed(), "Back to Login button is not displayed!");

		// Verify Back to Login button is clickable
		Assert.assertTrue(signuppage.isBackToLoginEnable(), "Back to Login button is not clickable!");

		signuppage.doClickOnBackToLogin();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlToBe(AppConstants.LOGIN_PAGE_URL));

		Assert.assertEquals(loginpage.getLoginPageUrl(), AppConstants.LOGIN_PAGE_URL,
				"Did not navigate to Login Page after clicking Back to Login!");

	}

	// This Test Case Depend on ReCaptcha

	@Test(priority = 8)
	public void verifyValidationErrorOnEmptyRegisterTest() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		loginpage.doclickOnSignUpLinkButton();

		// Do not enter anything in First Name, Last Name, Email fields.
		signuppage.fillMandatoryFields("", "", "");
		// Handle Re-captcha
		signuppage.getHandleRecaptchaCheckBox();

		// Click on Register button
		signuppage.doClickOnRegisterButton();

		// Verify validations are shown
		wait.until(driver -> signuppage.isFirstNameErrorMsgDisplayed() && signuppage.isLastNameErrorMsgDisplayed()
				&& signuppage.isEmailErrorMsgDisplayed());

		Assert.assertTrue(signuppage.isFirstNameErrorMsgDisplayed(), "First Name validation not displayed!");
		Assert.assertTrue(signuppage.isLastNameErrorMsgDisplayed(), "Last Name validation not displayed!");
		Assert.assertTrue(signuppage.isEmailErrorMsgDisplayed(), "Email validation not displayed!");

	}

	@Test(priority = 9)
	public void verifyInvalidEmailFormatTest() {

		if (signuppage.getSignUpPageUrl().equals(AppConstants.SIGNUP_PAGE_URL)) {

			driver.navigate().refresh();
		} else {
			loginpage.doclickOnSignUpLinkButton();
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.navigate().refresh();
		signuppage.fillMandatoryFields("test1", "team1", "testuser@invalid");
		// (e.g., "testuser@invalid", "testuser.com", "@gmail.com")

		wait.until(driver -> signuppage.isEmailErrorMsgDisplayed());
		Assert.assertTrue(signuppage.isEmailErrorMsgDisplayed(), "Email validation not displayed!");

	}

	@Test(priority = 10)
	public void verifyAlreadyRegisteredEmailTest() {

		if (signuppage.getSignUpPageUrl().equals(AppConstants.SIGNUP_PAGE_URL)) {

			driver.navigate().refresh();
		} else {
			loginpage.doclickOnSignUpLinkButton();
		}

		signuppage.fillMandatoryFields("test5", "test5", "test5gyrus@yopmail.com");

		// Handle Re-captcha
		signuppage.getHandleRecaptchaCheckBox();

		// Click on Register button
		signuppage.doClickOnRegisterButton();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Verify toast message
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "System cannot have duplicate eMail address.", "Toast message mismatch!");

		// Change E-mail, duplicate found.
	}

	@Test(priority = 11)
	public void verifySuccessfulRegistrationWithValidDataTest() {

		if (signuppage.getSignUpPageUrl().equals(AppConstants.SIGNUP_PAGE_URL)) {
			driver.navigate().refresh();
		} else {
			loginpage.doclickOnSignUpLinkButton();
		}

		signuppage.fillMandatoryFields("test6", "test6", "test6gyrus@yopmail.com");

		// Handle Re-captcha
		signuppage.getHandleRecaptchaCheckBox();

		// Click on Register button
		signuppage.doClickOnRegisterButton();
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Verify toast message
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Registration complete, approval required for system access.", "Toast message mismatch!");
	
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
