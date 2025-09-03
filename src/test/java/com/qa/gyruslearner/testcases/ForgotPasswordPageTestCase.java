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
import com.qa.gyruslearner.pages.ForgotPasswordPage;
import com.qa.gyruslearner.pages.LoginPage;

public class ForgotPasswordPageTestCase extends TestBase {

	LoginPage loginpage;
	ForgotPasswordPage forgotpasspage;

	public ForgotPasswordPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		forgotpasspage = new ForgotPasswordPage();
	}

	@Test(priority = 1)
	public void forgotPasswordPageUrlTest() {
		loginpage.doclickOnForgotPasswordLinkButton();

		String forgotpasswordUrl=forgotpasspage.getForgotPasswordPageUrl();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlToBe(AppConstants.FORGOTPASSWORD_PAGE_URL));
	
		Assert.assertEquals(forgotpasswordUrl, AppConstants.FORGOTPASSWORD_PAGE_URL);
	}
	
	@Test(priority = 2)
	public void forgotPasswordPageTitleTest() {
		
		String forgotPassPagetitle = forgotpasspage.getForgotPasswordPageTitle();
		Assert.assertEquals(forgotPassPagetitle,"ForgotPassword");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
