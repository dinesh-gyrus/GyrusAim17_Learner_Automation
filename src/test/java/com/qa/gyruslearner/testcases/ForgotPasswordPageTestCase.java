package com.qa.gyruslearner.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.ChangePasswordPage;
import com.qa.gyruslearner.pages.ForgotPasswordPage;
import com.qa.gyruslearner.pages.LoginPage;

public class ForgotPasswordPageTestCase extends TestBase {

	LoginPage loginpage;
	ForgotPasswordPage forgotpasspage;
	ChangePasswordPage changepassword;

	public ForgotPasswordPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		forgotpasspage = new ForgotPasswordPage();
		changepassword = new ChangePasswordPage();
	}

	@Test(priority = 1)
	public void forgotPasswordPageUrlTest() {

		loginpage.doclickOnForgotPasswordLinkButton();

		String forgotpasswordUrl = forgotpasspage.getForgotPasswordPageUrl();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlToBe(AppConstants.FORGOTPASSWORD_PAGE_URL));

		Assert.assertEquals(forgotpasswordUrl, AppConstants.FORGOTPASSWORD_PAGE_URL);
	}

	@Test(priority = 2)
	public void forgotPasswordPageTitleTest() {

		String forgotPassPagetitle = forgotpasspage.getForgotPasswordPageTitle();
		Assert.assertEquals(forgotPassPagetitle, "ForgotPassword");
	}

	@Test(priority = 3)
	public void verifyForgotPasswordPageVisableAndEnableTest() {

		// Verify radio buttons
		Assert.assertTrue(forgotpasspage.isRadioPasswordExits(), "I don't know my password' option is not displayed!");
		Assert.assertTrue(forgotpasspage.isRadioUserNameExits(), "I don't know my username' option is not displayed!");

		// Verify Back to Login button
		Assert.assertTrue(forgotpasspage.isBackToLoginExits(), "Back To Login button is not displayed!");
		Assert.assertTrue(forgotpasspage.isBackToLoginEnable(), "Back To Login button is not Enable!");

		// Verify Continue button
		Assert.assertTrue(forgotpasspage.isContinueExits(), "Continue button is not displayed!");

	}

	@Test(priority = 4)
	public void verifyContinueButtonDisabledUntilOptionSelectedTest() {

		// Verify Continue button is disabled by default
		Assert.assertFalse(forgotpasspage.isContinueEnable(), "Continue button should be disabled initially!");
	}

	@Test(priority = 5)
	public void verifyBackToLoginButtonNavigatesToLoginPageTest() {

		// Click Back To Login button
		forgotpasspage.doClickOnBackTologin();

		// Verify navigation to Login page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.titleIs(AppConstants.LOGIN_PAGE_TITLE));

		Assert.assertEquals(loginpage.getLoginPageUrl(), AppConstants.LOGIN_PAGE_URL,
				"User was not redirected to Login page!");

		driver.navigate().back();
	}

	@Test(priority = 6)
	public void verifyPasswordOptionDisplaysUsernameAndSecurityQuestionTest() {

		// Click on RadioPassword Button
		forgotpasspage.doClickOnRadioPassword();

		// Verify Username field is displayed
		Assert.assertTrue(forgotpasspage.isUserNameExits(),
				"Username field is not displayed after selecting password option!");

		// Verify Security Question1 field is displayed
		Assert.assertTrue(forgotpasspage.isSecuiryQ1Exits(),
				"Security Question1 field is not displayed after selecting password option!");
		// Verify Security Question2 field is displayed
		Assert.assertTrue(forgotpasspage.isSecuiryQ2Exits(),
				"Security Question2 field is not displayed after selecting password option!");
		// Verify Security Question3 field is displayed
		Assert.assertTrue(forgotpasspage.isSecuiryQ3Exits(),
				"Security Question3 field is not displayed after selecting password option!");

	}

	@Test(priority = 7)
	public void verifySelectedPasswordOptionContinueButtonEnabledTest() {
		// Verify Continue Button Enable Or not
		Assert.assertTrue(forgotpasspage.isContinueEnable(), "Continue Button was not enabled");
	}

	@Test(priority = 8)
	public void verifyValidationForEmptyUsernameTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// driver.navigate().refresh();

		// Click on Password Radio
		forgotpasspage.doClickOnRadioPassword();

		// Fill All Security Questions
		forgotpasspage.getValidationForEmptyUsername();

		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Please enter username");

	}

	@Test(priority = 9)
	public void verifySecurityAnswerEmptyTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// driver.navigate().refresh();

		// Click on Password Radio
		forgotpasspage.doClickOnRadioPassword();

		// Fill All Security Questions
		forgotpasspage.getValidationEmptySecurityQ();

		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Login security answer 3 cannot be null.");

	}

	@Test(priority = 10)
	public void verifyCorrectUsernameWithWrongSecurityAnswerTest() {
		
		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// driver.navigate().refresh();

		// Click on Password Radio
		forgotpasspage.doClickOnRadioPassword();

		// Fill All Security Questions
		forgotpasspage.getWrongSecurityQ();

		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Incorrect answer");

	}

	@Test(priority = 11)
	public void verifyInvalidUsernameShowsErrorTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// driver.navigate().refresh();

		// Click on Password Radio
		forgotpasspage.doClickOnRadioPassword();

		// Fill inValid Username and all Security Questions
		forgotpasspage.getInvalidUsername();

		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Please enter valid username.");
	}

	@Test(priority = 12)
	public void verifyValidUsernameAndCorrectAnswerTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// driver.navigate().refresh();

		// Click on Password Radio
		forgotpasspage.doClickOnRadioPassword();

		// Fill Valid Username and all Security Questions
		forgotpasspage.getUsernameAndCorrectAnswer();

		Assert.assertEquals(changepassword.getChangePasswordPanelTitle(), "Change Password",
				"Panel Title is not Match!");
		
		changepassword.getNewChangePasswordWithValidInputs();
		
		changepassword.doClickOnSignOut();

	}

	@Test(priority = 13)
	public void verifyIDontKnowMyUsernameShowsEmailFieldTest() {

		// Click on forgot Password Link Button
		loginpage.doclickOnForgotPasswordLinkButton();

		// Click on I don't know my username Radio Button
		forgotpasspage.doClickOnRadioUserName();

		// Verify Email input field is visible and enabled
		Assert.assertTrue(forgotpasspage.isEmailExits(),
				"Email input field is not displayed after selecting 'I don't know my username'.");
		Assert.assertTrue(forgotpasspage.isEmailEnable(), "Email input field is not enabled.");
	}

	@Test(priority = 14)
	public void verifySelectedUserNameOptionContinueButtonEnabledTest() {

		// Verify Continue Button Enable Or not
		Assert.assertTrue(forgotpasspage.isContinueEnable(), "Continue Button was not enabled");
	}

	@Test(priority = 15)
	public void verifyValidationForEmptyEmailTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// driver.navigate().refresh();

		// Click on I don't know my username Radio Button
		forgotpasspage.doClickOnRadioUserName();

		// Click on Continue Button
		forgotpasspage.doClickOnContinue();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Verify error message
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Please enter valid eMail.");
	}

	@Test(priority = 16)
	public void verifyUnregisteredEmailTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// Click on I don't know my username Radio Button
		forgotpasspage.doClickOnRadioUserName();

		// Fill Data Unregister
		forgotpasspage.getUnRegisterEmail();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Verify error message
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Please enter valid eMail.");

	}

	@Test(priority = 17)
	public void verifyRegisteredEmailTest() {

		// Refresh Current Page
		forgotpasspage.doRefreshCurrentPage();

		// Click on I don't know my username Radio Button
		forgotpasspage.doClickOnRadioUserName();

		// Fill Data Unregister
		forgotpasspage.getRegisterEmail();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Verify error message
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Username sent to your email address.");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
