package com.qa.gyruslearner.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.ChangePasswordPage;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;

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

		String loginUrl = loginpage.getLoginPageUrl();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.urlToBe(AppConstants.LOGIN_PAGE_URL));

		Assert.assertEquals(loginUrl, AppConstants.LOGIN_PAGE_URL);

	}

	@Test(priority = 3, description = "TC_LOGIN_003 : Verify company  logo presence")
	public void isCompanyLogoExitsTest() {
		Assert.assertTrue(loginpage.isCompanyLogoExits());
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

	@Ignore
	@Test(priority = 11)
	public void verifyFirstTimeLoginRedirectsToChangePasswordTest() {
		// This Login First Time Login User
		// loginpage.getUserFirstTimeLogin("TTeam3", "123");
		loginpage.getUserFirstTimeLogin("TTeam4", "123");

		// Verify navigation to Login page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.urlToBe(AppConstants.CHANGEPASSWORD_PAGE_URL));

		Assert.assertEquals(chanPassword.getChangedPassPageUrl(), AppConstants.CHANGEPASSWORD_PAGE_URL,
				"User was not redirected to Change Password page!");

		chanPassword.doClickOnSignOut();
	}

	@Test(priority = 12)
	public void verifyValidUserNameAndWrongPassTest1() {
		loginpage.doValidUerNameWrongPass("TTeam2", "123");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Invalid username or password");
	}

	@Test(priority = 13)
	public void verifyWrongUserNameAndValidPassTest() {
		loginpage.doValidUerNameWrongPass("TTeam2156", "123456");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg, "Invalid username or password");
	}

	@Ignore
	@Test(priority = 14)
	public void verifyLastLoginAttemptMessage() {

		for (int i = 1; i < 2; i++) {

			loginpage.doValidUerNameWrongPass("TTeam2", "Test123");

		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String toastMsg = loginpage.getToastMessage();
		Assert.assertEquals(toastMsg,
				"Invalid username or password. You have one Attempt remaining. Click on forgot password to get your username or password");

	}

	@Ignore
	@Test(priority = 15)
	public void verifyAccountLockAfterFailedAttemptTest() {
		loginpage.doValidUerNameWrongPass("TTeam2", "123");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String toastMsg = loginpage.getToastMessage();
		System.out.println("Toast Message is :" + toastMsg);
		Assert.assertEquals(toastMsg,
				"Your Account has been blocked. Please contact system administrator or try after 24 hours.");
	}

	@Test(priority = 16)
	public void verifyValidLoginTest() {

		loginpage.doValidLogin("TTeam", "123");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.titleIs(AppConstants.DASHBOARD_PAGE_TITLE));

		Assert.assertEquals(dashboard.getDashBoardTitle(), AppConstants.DASHBOARD_PAGE_TITLE);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
