package com.qa.gyruslearner.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.ChangePasswordPage;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.util.ExcelUtil;

public class ChangePasswordPageTestCase extends TestBase {

	LoginPage loginpage;
	ChangePasswordPage chanagepassword;
	DashboardPage dashboard;

	public ChangePasswordPageTestCase() {
		super();
	}
	
	@DataProvider
	public Object[][] getFirstTimeLoginSheetData() {
		return ExcelUtil.getTestData(AppConstants.CHANGEPASSWORD_DATA_SHEET_PATH,AppConstants.FIRSTTIME_LOGIN_CHANGEPASSWORD_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getWrongCurentPasswordSheetData() {
		return ExcelUtil.getTestData(AppConstants.CHANGEPASSWORD_DATA_SHEET_PATH,AppConstants.WRONGCURRENTPASSOWRD_CHANGEPASSWORD_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getErrorForMismatchedPasswordsSheetData() {
		return ExcelUtil.getTestData(AppConstants.CHANGEPASSWORD_DATA_SHEET_PATH,AppConstants.MISMATCH_NEW_CONFIRM_CHANGEPASSWORD_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getCurrentAndNewPasswordAreSameSheetData() {
		return ExcelUtil.getTestData(AppConstants.CHANGEPASSWORD_DATA_SHEET_PATH,AppConstants.CURRENTANDNEW_SAME_CHANGEPASSWORD_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] getSuccessfulPasswordResetSheetData() {
		return ExcelUtil.getTestData(AppConstants.CHANGEPASSWORD_DATA_SHEET_PATH,AppConstants.SUCCESSFUL_PASSWORDRESET_CHANGEPASSWORD_SHEET_NAME);
	}
	
	

	@BeforeClass
	public void setUp() {
		initialazation();
		chanagepassword = new ChangePasswordPage();
		loginpage = new LoginPage();
	}

	@Test(priority = 1,dataProvider = "getFirstTimeLoginSheetData")
	public void changePasswordUrlTest(String UserName, String Password) {

		// Login with First Time User
		//loginpage.getUserFirstTimeLogin(UserName,Password);
		loginpage.login(UserName, Password);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		
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

	@Test(priority = 2,dependsOnMethods="changePasswordUrlTest" )
	public void ChangePasswordTitleTest() {

		String changePasswordTitle = chanagepassword.getChangedPassPageTitle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
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

	@Test(priority = 6,dependsOnMethods = "changePasswordUrlTest",dataProvider = "getWrongCurentPasswordSheetData")
	public void VerifyWrongCurrentPasswordTest(String Currentpwd, String newPwd,String confirmPwd) {

		chanagepassword.doChangePassword(Currentpwd, newPwd, confirmPwd);
		/*
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		String toastMsg = wait.until(d -> loginpage.getToastMessage());

		//String toastMsg = loginpage.getToastMessage();
		System.out.println("Toast Message is :" + toastMsg);
		Assert.assertEquals(toastMsg, "Current password does not match");
	}

	@Test(priority = 7,dependsOnMethods = "changePasswordUrlTest",dataProvider ="getErrorForMismatchedPasswordsSheetData")
	public void verifyErrorForMismatchedPasswordsTest(String Currentpwd, String newPwd,String confirmPwd) {

		chanagepassword.doChangePassword(Currentpwd, newPwd, confirmPwd);
		
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

	@Test(priority = 8,dependsOnMethods = "changePasswordUrlTest",dataProvider = "getCurrentAndNewPasswordAreSameSheetData")
	public void verifyCurrentAndNewPasswordAreSameTest(String Currentpwd, String newPwd,String confirmPwd) {

		chanagepassword.doChangePassword(Currentpwd,newPwd, confirmPwd);
		String actualError = chanagepassword.getSamepasswordErrormessage();
		Assert.assertEquals(actualError, "New password must be different from your Current password.",
				"Same password error message not displayed!");
	}

	@Test(priority = 9,dependsOnMethods ="changePasswordUrlTest")
	public void verifySignOutFunctionalityTest() {

		driver.navigate().refresh();

		chanagepassword.doClickOnSignOut();

		String loginUrl = loginpage.getLoginPageUrl();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		wait.until(ExpectedConditions.urlToBe(AppConstants.LOGIN_PAGE_URL));
		Assert.assertEquals(loginUrl, AppConstants.LOGIN_PAGE_URL);
	}

	
	@Test(priority = 10,dependsOnMethods = "changePasswordUrlTest",dataProvider ="getSuccessfulPasswordResetSheetData")
	public void verifySuccessfulPasswordResetTest(String UerName,String Currentpwd, String newPwd,String confirmPwd) {

		//loginpage.getUserFirstTimeLogin(UerName,Currentpwd);
		loginpage.login(UerName, Currentpwd);

		chanagepassword.doChangePassword(Currentpwd, newPwd, confirmPwd);
		
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
		if (driver != null) {
			driver.quit();
		}	
	}

}
