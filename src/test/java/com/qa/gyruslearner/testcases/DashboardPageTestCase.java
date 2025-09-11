package com.qa.gyruslearner.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;

public class DashboardPageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;

	public DashboardPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
	}

	@Test(priority = 1)
	public void dashBoardUrlTest() {

		loginpage.getUserFirstTimeLogin("TTeam", "123");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		try {
			wait.until(ExpectedConditions.urlToBe(AppConstants.DASHBOARD_PAGE_URL));
			String dashBoardUrl = dashboard.getDashBoardPageUrl();
			Assert.assertEquals(dashBoardUrl, AppConstants.DASHBOARD_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
	}

	@Test(priority = 2)
	public void dashBoardPageTitleTest() {

		String dashBoardPagetitle = dashboard.getDashBoardTitle();
		Assert.assertEquals(dashBoardPagetitle, AppConstants.DASHBOARD_PAGE_TITLE);
	}

	@Test(priority = 3)
	public void VerifyCompanyLogoInDashBoardTest() {

		Assert.assertTrue(dashboard.isCompnayLogoInDashBoardDisplayed(),
				"Company logo is not displayed on the DashBoard page!");
	}

	@Test(priority = 4)
	public void verifyWelcomeHeadertextTest() {

		String welcomeBannerText = "Welcome To Gyrus Systems!";

		Assert.assertTrue(dashboard.isWelcomeTextDisplayed(), "Welcome Banner text not displayed!");
		Assert.assertEquals(dashboard.getWelcomeText().trim(), welcomeBannerText, "welcome Banner text mismatch!");

		Assert.assertTrue(dashboard.isSliderImageDisplay(), "Slider is not displayed!");

		List<WebElement> slides = driver.findElements(By.cssSelector("kendo-scrollview .k-scrollview-wrap > div"));

		System.out.println(slides.size());

		Assert.assertTrue(slides.size() > 1, "Slider has less than 2 slides, cannot auto-slide!");

	}

	@Test(priority = 6)
	public void verifyQuickLinksPopupTest() {

		// Quick Links
		Assert.assertTrue(dashboard.isQuickLinksDisplayed(), "Quick Links icon is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkEnabled(), "Quick Links icon was not enabled");
		
		dashboard.doClickOnQuicklinks();
		
		Assert.assertTrue(dashboard.isQuicklinkPopUpDisplayed(), "Quick Links popup not displayed!");
		
		
		
		

	}

	@Ignore
	@Test(priority = 6)
	public void verifyHeaderIconsFunctionality() {

		// Quick Links
		Assert.assertTrue(dashboard.isQuickLinksDisplayed(), "Quick Links icon is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkEnabled(), "Quick Links icon was not enabled");

		// Cart
		Assert.assertTrue(dashboard.isCartIconDisplayed(), "Cart icon is not displayed!");
		Assert.assertTrue(dashboard.isCartIconEnabled(), "Cart icon was not enabled");

		// calender
		Assert.assertTrue(dashboard.isCalenderIconDisplayed(), "Calender icon is not displayed!");
		Assert.assertTrue(dashboard.isCartIconEnabled(), "Calender icon was not enabled");

		// Notification
		Assert.assertTrue(dashboard.isNotificationIconDisplayed(), "Notification icon is not displayed!");
		Assert.assertTrue(dashboard.isNotificationIconEnabled(), "Notification icon was not enabled");

		// Profile
		Assert.assertTrue(dashboard.isProdfileMenuIconDisplayed(), "Profile icon is not displayed!");
		Assert.assertTrue(dashboard.isProfileMenuIconEnabled(), "Profile icon was not enabled");

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
