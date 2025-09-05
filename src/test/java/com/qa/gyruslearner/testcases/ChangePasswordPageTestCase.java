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
import com.qa.gyruslearner.pages.ChangePasswordPage;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;

public class ChangePasswordPageTestCase extends TestBase {

	LoginPage loginpage;
	ChangePasswordPage chanagepassword;
	DashboardPage dashboard;

	public ChangePasswordPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		chanagepassword = new ChangePasswordPage();
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void changePasswordUrlTest() {

		// Login with First Time User
		loginpage.getUserFirstTimeLogin("TTeam4", "123456");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		try {
			wait.until(ExpectedConditions.or(ExpectedConditions.urlToBe(AppConstants.CHANGEPASSWORD_PAGE_URL),
					ExpectedConditions.urlToBe(AppConstants.DASHBOARD_PAGE_URL)));

			String changePassUrl = chanagepassword.getChangedPassPageUrl();
			
			if (changePassUrl.equals(AppConstants.CHANGEPASSWORD_PAGE_URL)) {
				
				System.out.println("User redirected to Change Password page (First Time Login).");
				 Assert.assertEquals(changePassUrl, AppConstants.CHANGEPASSWORD_PAGE_URL);
				
			}
			else if(changePassUrl.equals(AppConstants.DASHBOARD_PAGE_URL)) {
				
				System.out.println("User redirected to Dashboard (Not First Time Login).");
				
				Assert.assertEquals(dashboard.getDashBoardPageUrl(), AppConstants.DASHBOARD_PAGE_URL,"Dashboard Url mismatch!");
			}
			

		} catch (Exception e) {
			
			 Assert.fail("FAIL: Neither Change Password nor Dashboard page loaded after login!");
		}

	}

	@Test(priority = 2, enabled = false,dependsOnMethods="changePasswordUrlTest" )
	public void ChangePasswordTitleTest() {

		String changePasswordTitle = chanagepassword.getChangedPassPageTitle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleIs(AppConstants.CHANGEPASSWORD_PAGE_TITLE));
		Assert.assertEquals(changePasswordTitle, AppConstants.CHANGEPASSWORD_PAGE_TITLE);
	}

	@Test(priority = 3,dependsOnMethods = "changePasswordUrlTest")
	public void verifyChangePasswordPanelTitleTest() {

		String panelTitle = chanagepassword.getChangePasswordPanelTitle();
		Assert.assertEquals(panelTitle, "Change Password");
	}

	@Test(priority = 4,dependsOnMethods = "changePasswordUrlTest")
	public void verifyChangePasswordPageUIElementsTest() {

		// Verify fields and buttons
		Assert.assertTrue(chanagepassword.isCurrentPasswordDisplayed(), "Current Password field not displayed!");
		Assert.assertTrue(chanagepassword.isNewPasswordDisplayed(), "New Password field not displayed!");
		Assert.assertTrue(chanagepassword.isConfirmPasswordDisplayed(), "Confirm Password field not displayed!");
		Assert.assertTrue(chanagepassword.isResetPasswordDisplayed(), "Reset Password button not displayed!");
		Assert.assertTrue(chanagepassword.isSignOutDisplayed(), "Sign Out button not displayed!");
	}

	// Now Skip test Case no : 5

	@Test(priority = 6,dependsOnMethods = "changePasswordUrlTest")
	public void VerifyWrongCurrentPasswordTest() {

		chanagepassword.doChangePassword("1259", "123456", "123456");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String toastMsg = loginpage.getToastMessage();
		System.out.println("Toast Message is :" + toastMsg);
		Assert.assertEquals(toastMsg, "Current password does not match.");
	}

	@Test(priority = 7,dependsOnMethods = "changePasswordUrlTest")
	public void verifyErrorForMismatchedPasswordsTest() {

		chanagepassword.doChangePassword("123", "123456", "123654");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String toastMsg = loginpage.getToastMessage();
		System.out.println("Toast Message is :" + toastMsg);
		Assert.assertEquals(toastMsg, "Passwords must match.");
	}

	@Test(priority = 8,dependsOnMethods = "changePasswordUrlTest")
	public void verifyCurrentAndNewPasswordAreSameTest() {

		chanagepassword.doChangePassword("123", "123", "123");
		String actualError = chanagepassword.getSamepasswordErrormessage();
		Assert.assertEquals(actualError, "New password must be different from your Current password.",
				"Same password error message not displayed!");
	}

	@Test(priority = 9,dependsOnMethods ="changePasswordUrlTest")
	public void verifySignOutFunctionalityTest() {

		driver.navigate().refresh();

		chanagepassword.doClickOnSignOut();

		String loginUrl = loginpage.getLoginPageUrl();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlToBe(AppConstants.LOGIN_PAGE_URL));
		Assert.assertEquals(loginUrl, AppConstants.LOGIN_PAGE_URL);
	}

	@Ignore
	@Test(priority = 10,dependsOnMethods = "changePasswordUrlTest")
	public void verifySuccessfulPasswordResetTest() {

		loginpage.getUserFirstTimeLogin("TTeam4", "123");

		chanagepassword.doChangePassword("123", "123456", "123456");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String toastMsg = loginpage.getToastMessage();
		System.out.println("Toast Message is :" + toastMsg);
		Assert.assertEquals(toastMsg, "Password updated.");

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
