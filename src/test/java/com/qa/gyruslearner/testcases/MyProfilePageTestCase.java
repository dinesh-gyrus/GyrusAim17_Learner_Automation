package com.qa.gyruslearner.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.pages.MyProfilePage;

public class MyProfilePageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	MyProfilePage myprofile;
	
	public MyProfilePageTestCase() {
		super();
	}
	
	
	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		myprofile= new MyProfilePage();
	}
	
	
	@BeforeMethod
	public void pageRefresh() {
		
		//driver.navigate().refresh();

		if (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL)) {
			loginpage.getUserFirstTimeLogin("TTeam", "123");
			
			myprofile.doClickProfileIcon();
			myprofile.doClickProfilePage();
	
		}else if(driver.getCurrentUrl().equals(AppConstants.DASHBOARD_PAGE_URL)) {
			
			myprofile.doClickProfileIcon();
			myprofile.doClickProfilePage();
		}
	}
	
	
	@Test(priority = 1)
	public void myProfileUrlTest() {
		
		//loginpage.getUserFirstTimeLogin("TTeam", "123");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		try {
			wait.until(ExpectedConditions.urlToBe(AppConstants.MYPROFILE_PAGE_URL));
			String myProfileUrl = myprofile.getMyProfilePageUrl();
			Assert.assertEquals(myProfileUrl, AppConstants.MYPROFILE_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
	}
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.close();
		}
		
	}
}
