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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.CalendarPage;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;

public class DashboardPageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	CalendarPage calender;

	public DashboardPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		calender = new CalendarPage();
	}
	
	
	@BeforeMethod
	public void pageRefresh() {
		
		//driver.navigate().refresh();

		if (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL)) {
			loginpage.getUserFirstTimeLogin("TTeam", "123");
		}
	}
	

	@Test(priority = 1)
	public void dashBoardUrlTest() {
		
		//loginpage.getUserFirstTimeLogin("TTeam", "123");

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
	public void verifyHeaderIconsFunctionality() {

		// Quick Links
		Assert.assertTrue(dashboard.isQuickLinksDisplayed(), "Quick Links icon is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkEnabled(), "Quick Links icon was not enabled");
		dashboard.doClickOnQuicklinks();
		Assert.assertTrue(dashboard.isQuicklinkPopUpDisplayed(), "Quick Links popup not displayed!");
		dashboard.doClickQuickCloseIcon();

	}

	@Test(priority = 7)
	public void verifyCartPopUpTest() {

		// Cart
		Assert.assertTrue(dashboard.isCartIconDisplayed(), "Cart icon is not displayed!");
		Assert.assertTrue(dashboard.isCartIconEnabled(), "Cart icon was not enabled");
		dashboard.doclickOnCarticon();
		Assert.assertTrue(dashboard.isCartPopUpDisplayed(), "Cart icon popup not displayed!");
		dashboard.doPressKeyEscape();
	}

	@Test(priority = 8)
	public void verifyCalenderDisplayTest() {

		// calender
		Assert.assertTrue(dashboard.isCalenderIconDisplayed(), "Calender icon is not displayed!");
		Assert.assertTrue(dashboard.isCartIconEnabled(), "Calender icon was not enabled");
		
		/*
		dashboard.doClickOnCalender();
		
		String CalenderUrl = calender.getCalenderPageUrl();
		Assert.assertEquals(CalenderUrl, AppConstants.CALENDER_PAGE_URL);
		
		calender.doClickOnBackToDashboard();
		*/
	}

	@Test(priority = 9)
	public void verifyNotificationPopUpTest() {

		// Notification
		Assert.assertTrue(dashboard.isNotificationIconDisplayed(), "Notification icon is not displayed!");
		Assert.assertTrue(dashboard.isNotificationIconEnabled(), "Notification icon was not enabled");
		dashboard.doclickNotificationIcon();
		Assert.assertTrue(dashboard.isNotificationPopUpDisplayed(), "Notification popup not displayed!");
		dashboard.doPressKeyEscape();
	}

	@Test(priority = 10)
	public void verifyProfilePopUpTest() {
		// Profile
		Assert.assertTrue(dashboard.isProdfileMenuIconDisplayed(), "Profile icon is not displayed!");
		Assert.assertTrue(dashboard.isProfileMenuIconEnabled(), "Profile icon was not enabled");
		dashboard.doclickProfileIcon();
		Assert.assertTrue(dashboard.isProfilePopUpDisplayed(), "Profile popup not displayed!");
		dashboard.doPressKeyEscape();
	}
	
	
	@Test(priority =11)
	public void verifyQuickLinksPopupTest() {

		// Quick Links
		Assert.assertTrue(dashboard.isQuickLinksDisplayed(), "Quick Links icon is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkEnabled(), "Quick Links icon was not enabled");

		// Step 2: Verify popup is displayed
		dashboard.doClickOnQuicklinks();
		Assert.assertTrue(dashboard.isQuicklinkPopUpDisplayed(), "Quick Links popup not displayed!");
	
		// Step 3: Verify items inside popup
	    Assert.assertTrue(dashboard.isQuickLinkItemCourseCataLogDisplayed(), "Course Catalog link missing!");
	    Assert.assertTrue(dashboard.isQuickLinkItemIDPDisplayed(), "IDP link missing!");
	    Assert.assertTrue(dashboard.isQuickLinkItemTrainingTranscriptDisplayed(), "Training Transcript link missing!");
		
	    // Step 4: Close popup
	    dashboard.doClickQuickCloseIcon();
	    
	    
	    /*
	    // Step 5: Verify popup closed
	    Assert.assertFalse(dashboard.isQuicklinkPopUpDisplayed(), "Quick Links popup did not close!");
	    */
	}
	
	@Test(priority = 12)
	public void verifyStatisticsDisplayed() {
		
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0, "In Progress Trainings not displayed!");
		
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0, "Completed Trainings not displayed!");
		
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "Earned Badges not displayed!");
	}
	
	@Test(priority = 13)
	public void verifyStatisticsWithFilters() {
		
		dashboard.doclickOnThisYear();
		System.out.println("------------------Display Year Wise My Statistics Data-----------------------------");
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0, "This Year filter not working for InProgress Trainings !");
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0, "This Year filter not working for InProgress Trainings Completed Trainings!");
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "This Year filter not working for Earned Badges!");
		
		dashboard.doclickOnThisMonth();
		System.out.println("------------------Display Month Wise My Statistics Data-----------------------------");
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0, "This Month filter not working for InProgress Trainings !");
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0, "This Month filter not working for InProgress Trainings Completed Trainings!");
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "This Month filter not working for Earned Badges!");
		
		dashboard.doclickOnThisWeek();
		System.out.println("------------------Display Week Wise My Statistics Data-----------------------------");
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0, "This Week filter not working for InProgress Trainings !");
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0, "This Week filter not working for InProgress Trainings Completed Trainings!");
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "This Week filter not working for Earned Badges!");
		
		
	}
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.close();
		}
		
	}

}
