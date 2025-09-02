package com.qa.gyruslearner.testcases;

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
		//driver.get("https://vspqa.gyrusaim.net/Auth/Login");
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
		Assert.assertEquals(loginUrl, AppConstants.LOGIN_PAGE_URL);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void isCompanyLogoExitsTest() {
		Assert.assertTrue(loginpage.isCompanyLogoExits());
	}

	@Test(priority = 4)
	public void isBackGroundImageExitsTest() {
		Assert.assertTrue(loginpage.isBackgroundImageExits());
	}

	@Test(priority = 5)
	public void verifyLoginHeadertextTest() {
		Assert.assertTrue(loginpage.isLoginHederTextExits(), "Login header not displayed!");
		Assert.assertEquals(loginpage.getLoginHeadertext().trim(), "Login", "Login header text mismatch!");
	}

	@Test(priority = 6)
	public void isSingInbuttonExitsAndEnableTest() {
		Assert.assertTrue(loginpage.isSignInButtonExits(), "Sign In Button not displayed!");
		Assert.assertTrue(loginpage.isSignInButtonEnable(), "Sing In Button not Enable!");
	}

	@Test(priority = 7)
	public void verifyForgotPasswordTest() {

		Assert.assertTrue(loginpage.isForgotPasswordExits(), "Forgot Password link Button not displayed!");
		Assert.assertTrue(loginpage.isForgotPasswordEnable(), "Forgot Password link not Enable!");
	}

	@Test(priority = 8)
	public void verifySignUplinkButtonTest() {

		Assert.assertTrue(loginpage.isSignUpExits(), "SignUp link Button not displayed!");
		Assert.assertTrue(loginpage.isSignUpEnable(), "SignUp link not Enable!");
	}

	@Test(priority = 9)
	public void verifyPasswordFieldEncryptedTest() {
		Assert.assertEquals(loginpage.getPasswordFieldEncrypted(), "password",
				"Password field is not masked properly!");
	}

	@Ignore
	@Test(priority = 10)
	public void verifyFirstTimeLoginRedirectsToChangePasswordTest() {
		loginpage.getUserFirstTimeLogin("TTeam3", "123");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(chanPassword.getLoginPageUrl(), AppConstants.CHANGEPASSWORD_PAGE_URL,
				"User was not redirected to Change Password page!");
	}

	// 
	@Test(priority = 11)
	public void verifyValidLoginTest() {
		loginpage.doValidLogin("TTeam", "123");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(dashboard.getDashBoardTitle(), AppConstants.DASHBOARD_PAGE_TITLE);
	}

	
	@Test(priority = 12)
	public void verifyValidUserNameAndWrongPassTest() {
		loginpage.doValidUerNameWrongPass("TTeam2","123");
		Assert.assertTrue(driver.getPageSource().contains("Invalid username or password"), "Error message not displayed!");
		
	}
	
	
	/*
	 * public void verifyUsernameAndPasswordFields() { // Locate Username field
	 * WebElement usernameField = driver.findElement(By.id("username")); // 🔹
	 * Replace locator Assert.assertTrue(usernameField.isDisplayed(),
	 * "Username field is not visible");
	 * Assert.assertTrue(usernameField.isEnabled(),
	 * "Username field is not enabled");
	 * 
	 * // Enter a sample username usernameField.sendKeys("testUser");
	 * 
	 * // Locate Password field WebElement passwordField =
	 * driver.findElement(By.id("password")); // 🔹 Replace locator
	 * Assert.assertTrue(passwordField.isDisplayed(),
	 * "Password field is not visible");
	 * Assert.assertTrue(passwordField.isEnabled(),
	 * "Password field is not enabled");
	 * 
	 * // Enter a sample password passwordField.sendKeys("Test@123");
	 * 
	 * // Verify password field is masked String fieldType =
	 * passwordField.getAttribute("type"); Assert.assertEquals(fieldType,
	 * "password", "Password field is not masked properly!"); }
	 */

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
