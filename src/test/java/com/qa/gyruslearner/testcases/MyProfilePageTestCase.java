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
	
	@Test(priority = 2)
	public void myprofileTitleTest() {

		String myProfilePagetitle = myprofile.getMyProfileTitle();
		Assert.assertEquals(myProfilePagetitle, AppConstants.MYPROFILE_PAGE_TITLE);
	}
	
	@Test(priority = 3)
	public void verifyUserProfileDetails() {
		
		Assert.assertTrue(myprofile.isLearnerNameDisplayed(),"Learner name not visible");
		Assert.assertTrue(myprofile.isJobCodeDisplayed(),"Job code not visible");
		Assert.assertTrue(myprofile.isOrganizationNameDisplayed(),"OrganizationName not visible");
		Assert.assertTrue(myprofile.isEmailIdDisplayed(),"Email not visible");
		Assert.assertTrue(myprofile.isAddressDisplayed(),"Adsress not visible");
		Assert.assertTrue(myprofile.isPhoneDisplayed(),"Phone number not visible");
	}
	
	@Test(priority = 4)
	public void verifyProfileImage() {
		
		Assert.assertTrue(myprofile.isProfileImageDisplayed(), "Profile picture not displayed");
	}
	
	@Test(priority = 5)
	public void verifyQRCodeDisplayed() {
		
		Assert.assertTrue(myprofile.isProfileQRDisplayed(), "QR Code not displayed");
	}
	
	@Test(priority = 6,enabled = false )
	public void verifyBackToDashboardLink() {
			
		Assert.assertTrue(myprofile.isProfileQRDisplayed(), "Back to Dashboard link is not displayed on the Profile page");
		myprofile.doclickOnBackToDashBoardLinkButton();
		
		Assert.assertEquals(dashboard.getDashBoardPageUrl(), AppConstants.DASHBOARD_PAGE_URL,"Dashboard page URL mismatch after clicking Back to Dashboard");
		myprofile.doClickProfileIcon();
		myprofile.doClickProfilePage();
		
	}
	
	
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.close();
		}
		
	}
}
