package com.qa.gyruslearner.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
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
		String singupPageUrl = signuppage.getSignUpPageUrl();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlToBe(AppConstants.SIGNUP_PAGE_URL));
		Assert.assertEquals(singupPageUrl, AppConstants.SIGNUP_PAGE_URL);

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
	@Ignore 
	@Test(priority = 6)
	public void verifyRegisterButtonEnableTest() {

		System.out.println("Register Button is display : "+signuppage.isRegisterTextDisplayed());
		// Verify button is Enable  initially
		Assert.assertTrue(signuppage.isRegisterButtonDisplayed(),
				"Register button should be displayed on the signup page!");

		System.out.println("Register Button is Disable : "+signuppage.isRegisterButtonEnable());
		// Verify button is disabled initially
		Assert.assertFalse(signuppage.isRegisterButtonEnable(),"Register button should be disabled before filling fields!");
		
		
		
		signuppage.fillMandatoryFields("test6","team6","test6gyrus@yopmail.com");
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(signuppage.isRegisterButtonEnable(), "Register button did not enable after filling fields!");
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
