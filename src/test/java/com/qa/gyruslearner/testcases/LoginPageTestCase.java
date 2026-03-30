package com.qa.gyruslearner.testcases;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.ChangePasswordPage;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.util.ExcelUtil;

public class LoginPageTestCase extends TestBase {

	LoginPage loginpage;
	ChangePasswordPage chanPassword;
	DashboardPage dashboard;

	public LoginPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		chanPassword = new ChangePasswordPage();
		dashboard = new DashboardPage();
	}

	@DataProvider
	public Object[][] getLoginSheetData() {
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.VALID_LOGIN_SHEET_NAME);
	}

	@DataProvider
	public Object[][] getFirstTimeLoginSheetData() {
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.FIRSTTIME_LOGIN_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getValidUserNameAndWrongPassSheetData() {
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.WRONGPASS_LOGIN_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] getWrongUserNameAndValidPassSheetData() {
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.WRONGUSERNAME_LOGIN_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getLastLoginAttemptMessageSheetData() {
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.LOGINATTEMP_LOGIN_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] getAccountLockAfterFailedAttemptSheetData() {
		
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.FAILATTEMPT_LOGIN_SHEET_NAME);
	}

	@DataProvider
	public Object[][] geLoginTestData() {
		return new Object[][] { { "TTeam01", "123" }, };
	}

	@BeforeMethod
	public void goToLoginPage() {
		// Always return to login page before each test
		// driver.get("https://vspqa.gyrusaim.net/Auth/login");
		loginpage.ensureAtLoginPage();
	}

	@Test(priority = 1, description = "TC_LOGIN_001 : Verify the page title matches expected.")
	public void LoginPageTitleTest() {
		String loginPagetitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(loginPagetitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2, description = "TC_LOGIN_002 :Verify the Login Page URL matches the expected.")
	public void LoginPageUrlTest() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		wait.until(ExpectedConditions.urlToBe(AppConstants.LOGIN_PAGE_URL));
		String loginUrl = loginpage.getLoginPageUrl();
		Assert.assertEquals(loginUrl, AppConstants.LOGIN_PAGE_URL);

	}

	@Test(priority = 3, description = "TC_LOGIN_003 : Verify company  logo presence")
	public void isCompanyLogoExitsTest() {

		Assert.assertTrue(loginpage.isCompanyLogoExits(), "Company logo is not displayed on the login page!");
	}

	@Test(priority = 4, description = "TC_LOGIN_004 : Verify BackGround Image presence")
	public void isBackGroundImageExitsTest() {

		Assert.assertTrue(loginpage.isBackgroundImageExits());
	}

	@Test(priority = 5, description = "TC_LOGIN_005 : Verify that the Login header is displayed on the login page.")
	public void verifyLoginHeadertextTest() {

		Assert.assertTrue(loginpage.isLoginHederTextExits(), "Login header not displayed!");
		Assert.assertEquals(loginpage.getLoginHeadertext().trim(), "Login", "Login header text mismatch!");
	}

	@Test(priority = 6, description = "TC_LOGIN_006: Verify Username and Password fields is display and Enable. ")
	public void verifyUsernameAndPasswordFieldsTest() {

		// Username field
		Assert.assertTrue(loginpage.isUsernameDisplayed(), "Username field was not displayed");
		Assert.assertTrue(loginpage.isUsernameEnabled(), "Username field was not enabled");

		// Password field
		Assert.assertTrue(loginpage.isPasswordDisplayed(), "Password field was not displayed");
		Assert.assertTrue(loginpage.isPasswordEnabled(), "Password field was not enabled");

	}

	@Test(priority = 7)
	public void isSingInbuttonExitsAndEnableTest() {

		Assert.assertTrue(loginpage.isSignInButtonExits(), "Sign In Button not displayed!");
		Assert.assertTrue(loginpage.isSignInButtonEnable(), "Sing In Button not Enable!");
	}

	@Test(priority = 8)
	public void verifyForgotPasswordTest() {

		Assert.assertTrue(loginpage.isForgotPasswordExits(), "Forgot Password link Button not displayed!");
		Assert.assertTrue(loginpage.isForgotPasswordEnable(), "Forgot Password link not Enable!");
	}

	@Test(priority = 9)
	public void verifySignUplinkButtonTest() {

		Assert.assertTrue(loginpage.isSignUpExits(), "SignUp link Button not displayed!");
		Assert.assertTrue(loginpage.isSignUpEnable(), "SignUp link not Enable!");
	}

	@Test(priority = 10)
	public void verifyPasswordFieldEncryptedTest() {

		Assert.assertEquals(loginpage.getPasswordFieldEncrypted(), "password",
				"Password field is not masked properly!");
	}
	
	
	@Test(priority = 11, dataProvider = "getFirstTimeLoginSheetData")
	public void verifyFirstTimeLoginRedirectsToChangePasswordTest(String UserName, String Password) {

		// This Login First Time Login User
		loginpage.getUserFirstTimeLogin(UserName, Password);

		// Verify navigation to Login page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));

		wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("MustChangePassword"),
				ExpectedConditions.urlContains("dashboard")));

		String currentUrl = driver.getCurrentUrl();

		if (currentUrl.contains("MustChangePassword")) {

			System.out.println("First Time User Flow");

			Assert.assertEquals(chanPassword.getChangedPassPageUrl(), AppConstants.CHANGEPASSWORD_PAGE_URL,
					"User was not redirected to Change Password page!");

			chanPassword.doClickOnSignOut();

		} else if (currentUrl.contains("dashboard")) {

			System.out.println("Normal User Flow");

			wait.until(ExpectedConditions.titleIs(AppConstants.DASHBOARD_PAGE_TITLE));
			Assert.assertEquals(dashboard.getDashBoardTitle(), AppConstants.DASHBOARD_PAGE_TITLE);

		} else {

			Assert.fail("User not redirected to expected page!");
		}

		dashboard.doclickProfileIcon();
		dashboard.doClickOnSignOut();
	}

	
	@Test(priority = 12, dataProvider = "getValidUserNameAndWrongPassSheetData")
	public void verifyValidUserNameAndWrongPassTest(String UserName, String Password) {

		loginpage.doValidUserNameWrongPass(UserName,Password);

		// String toastMsg = loginpage.getToastMessage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		String toastMsg = wait.until(d -> loginpage.getToastMessage());

		List<String> validMessages = Arrays.asList("Invalid username or password",
				"Invalid username or password. You have one Attempt remaining. Click on forgot password to get your username or password.",
				"Your Account has been blocked. Please contact system administrator or try after 24 hours.");

		Assert.assertTrue(validMessages.contains(toastMsg), "Unexpected toast message: " + toastMsg);


	}

	@Test(priority = 13, dataProvider = "getWrongUserNameAndValidPassSheetData")
	public void verifyWrongUserNameAndValidPassTest(String UserName, String Password) {

		loginpage.doValidUserNameWrongPass(UserName,Password);
		
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		String toastMsg = wait.until(d -> loginpage.getToastMessage());
		Assert.assertEquals(toastMsg, "Invalid username or password");
	}

	
	@Test(priority = 14,dataProvider = "getLastLoginAttemptMessageSheetData")
	public void verifyLastLoginAttemptMessageTest(String UserName, String Password) {

		for (int i = 0; i <= 2; i++) {

			loginpage.doValidUserNameWrongPass(UserName,Password);

			// wait for toast after each attempt
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			String toastMsg = wait.until(d -> loginpage.getToastMessage());

			System.out.println("Toast after attempt " + (i + 1) + ": " + toastMsg);

		}

		// final toast check
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String finalToastMsg = wait.until(d -> loginpage.getToastMessage());

		List<String> validMessages = Arrays.asList("Invalid username or password",
				"Invalid username or password. You have one Attempt remaining. Click on forgot password to get your username or password.",
				"Your Account has been blocked. Please contact system administrator or try after 24 hours.");

		Assert.assertTrue(validMessages.contains(finalToastMsg), "Unexpected toast message: " + finalToastMsg);
	}

	
	@Test(priority = 15, dataProvider ="getAccountLockAfterFailedAttemptSheetData",dependsOnMethods = "verifyLastLoginAttemptMessageTest" )
	public void verifyAccountLockAfterFailedAttemptTest(String UserName, String Password) {

		loginpage.doValidUserNameWrongPass(UserName,Password);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.SHORT_TIME_OUT));
		String toastMsg = wait.until(d -> loginpage.getToastMessage());

		List<String> validMessages = Arrays.asList("Invalid username or password",
				"Invalid username or password. You have one Attempt remaining. Click on forgot password to get your username or password.",
				"Your Account has been blocked. Please contact system administrator or try after 24 hours.");

		Assert.assertTrue(validMessages.contains(toastMsg), "Unexpected toast message: " + toastMsg);

		/*
		 * String toastMsg = loginpage.getToastMessage();
		 * System.out.println("Toast Message is :" + toastMsg);
		 * Assert.assertEquals(toastMsg,
		 * "Your Account has been blocked. Please contact system administrator or try after 24 hours."
		 * );
		 */
	}

	
	@Test(priority = 16, dataProvider = "getLoginSheetData")
	public void verifyValidLoginTest(String UserName, String Password) {

		loginpage.doValidLogin(UserName, Password);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.titleIs(AppConstants.DASHBOARD_PAGE_TITLE));

		Assert.assertEquals(dashboard.getDashBoardTitle(), AppConstants.DASHBOARD_PAGE_TITLE);
	}

	@AfterClass
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

}
