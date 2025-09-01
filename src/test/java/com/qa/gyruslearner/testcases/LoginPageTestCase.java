package com.qa.gyruslearner.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.LoginPage;

public class LoginPageTestCase extends TestBase {
	
	LoginPage loginpage;
	
	public LoginPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
	}
	
	@Test(priority = 1)
	public void LoginPageTitleTest() {
	   String loginPagetitle=loginpage.getLoginPageTitle();
	   Assert.assertEquals(loginPagetitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	@Test(priority = 2)
	public void LoginPageUrlTest() {
		
		 String loginUrl=loginpage.getLoginPageUrl();
		 Assert.assertEquals(loginUrl,AppConstants.LOGIN_PAGE_URL);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
}
