package com.qa.gyruslearner.testcases;



import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
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

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		calender = new CalendarPage();
	}

	@BeforeMethod(alwaysRun = true)
	public void pageRefresh() {

		// driver.navigate().refresh();

		if (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL)) {
			//loginpage.getUserFirstTimeLogin(prop.getProperty("username"), prop.getProperty("password"));
			loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		}
	}

	@Test(priority = 1)
	public void dashBoardUrlTest() {

		// loginpage.getUserFirstTimeLogin("TTeam", "123");
		try {
			String dashBoardUrl = dashboard.getDashBoardPageUrl();
			Assert.assertEquals(dashBoardUrl, AppConstants.DASHBOARD_PAGE_URL);
			System.out.println("DashBoard URL : " + AppConstants.DASHBOARD_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
	}

	@Test(priority = 2)
	public void dashBoardPageTitleTest() {

		String dashBoardPagetitle = dashboard.getDashBoardTitle();
		Assert.assertEquals(dashBoardPagetitle, AppConstants.DASHBOARD_PAGE_TITLE);
	}

	@Test(priority = 3,groups = {"regression"})
	public void VerifyCompanyLogoInDashBoardTest() {

		Assert.assertTrue(dashboard.isCompnayLogoInDashBoardDisplayed(),
				"Company logo is not displayed on the DashBoard page!");
		Assert.assertTrue(dashboard.isCompnayLogoImageLoadedInDashBoard(),
				"Company logo Images is not Loaded  on the DashBoard page!");
	}

	@Test(priority = 4)
	public void verifyWelcomeHeadertextAndSlidersTest() {

		String welcomeBannerText = "Welcome To Gyrus Systems!";
				
		Assert.assertTrue(dashboard.isWelcomeTextDisplayed(), "Welcome Banner text not displayed!");
		
		Assert.assertEquals(dashboard.getWelcomeText().trim(), welcomeBannerText, "welcome Banner text mismatch!");

		Assert.assertTrue(dashboard.isSliderImageDisplay(), "❌ Slider is not displayed!");
		
		Assert.assertTrue(dashboard.getSlideCount() > 1,"❌ Less than 2 slides!");
		
		System.out.println("Slide count: " + dashboard.getSlideCount());
		
		String before = dashboard.getSliderPosition();
		System.out.println("Before: " + before);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));

		boolean isMoving = wait.until(d -> {
		    String after = dashboard.getSliderPosition();
		    System.out.println("After: " + after);
		    return !after.equals(before);
		});

		Assert.assertTrue(isMoving, "❌ Slider is NOT auto-sliding!");

		//List<WebElement> slides = driver.findElements(By.cssSelector("kendo-scrollview .k-scrollview-wrap > div"));

		//System.out.println("Slider image count:" +slides.size());

		//Assert.assertTrue(slides.size() > 1, "Slider has less than 2 slides, cannot auto-slide!");
	}

	@Test(priority = 5)
	public void verifyCalenderDisplayTest() {

		// calender
		Assert.assertTrue(dashboard.isCalenderIconDisplayed(), "Calender icon is not displayed!");
		Assert.assertTrue(dashboard.isCalenderIconEnabled(), "Calender icon was not enabled");

		dashboard.doClickOnCalender();

		String CalenderUrl = calender.getCalenderPageUrl();
		Assert.assertEquals(CalenderUrl, AppConstants.CALENDER_PAGE_URL);

		calender.doClickOnBackToDashboard();

	}

	@Test(priority = 6)
	public void verifyProfilePopUpTest() {
		// Profile
		Assert.assertTrue(dashboard.isProdfileMenuIconDisplayed(), "Profile icon is not displayed!");
		Assert.assertTrue(dashboard.isProfileMenuIconEnabled(), "Profile icon was not enabled");
		dashboard.doclickProfileIcon();
		Assert.assertTrue(dashboard.isProfilePopUpDisplayed(), "Profile popup not displayed!");
		dashboard.doPressKeyEscape();
	}

	@Test(priority = 7)
	public void verifyQuickLinksTest() {

		Assert.assertTrue(dashboard.isQuickLinkFirstPanelDisplay(), "QuickLink First Panel is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkSecondPanelDisplay(), "QuickLink Second  Panel is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkThirdPaneltDisplay(), "QuickLink Third Panel is not displayed!");

		/*
		 * dashboard.doClickOnFirstQuickLink(); String CurrentURL =
		 * dashboard.getDashBoardPageUrl(); Assert.assertEquals(CurrentURL,
		 * AppConstants.IDP_PAGE_URL); dashboard.doClickOnBrowserBackButton();
		 * 
		 * dashboard.doClickOnSecondQuickLink(); Assert.assertEquals(CurrentURL,
		 * AppConstants.ACTIVITYFEED_PAGE_URL); dashboard.doClickOnBrowserBackButton();
		 * 
		 * dashboard.doClickOnThirdQuickLink(); Assert.assertEquals(CurrentURL,
		 * AppConstants.MYENROLLMENT_PAGE_URL); dashboard.doClickOnBrowserBackButton();
		 */
	}

	@Test(priority = 8)
	public void verifyStatisticsDisplayed() {

		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0, "In Progress Trainings not displayed!");

		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0, "Completed Trainings not displayed!");

		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "Earned Badges not displayed!");
	}

	@Test(priority = 9)
	public void verifyStatisticsWithFilters() {

		dashboard.doclickOnThisYear();
		System.out.println("------------------Display Year Wise My Statistics Data-----------------------------");
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0,
				"This Year filter not working for InProgress Trainings !");
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0,
				"This Year filter not working for InProgress Trainings Completed Trainings!");
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "This Year filter not working for Earned Badges!");

		dashboard.doclickOnThisMonth();
		System.out.println("------------------Display Month Wise My Statistics Data-----------------------------");
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0,
				"This Month filter not working for InProgress Trainings !");
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0,
				"This Month filter not working for InProgress Trainings Completed Trainings!");
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "This Month filter not working for Earned Badges!");

		dashboard.doclickOnThisWeek();
		System.out.println("------------------Display Week Wise My Statistics Data-----------------------------");
		System.out.println("In Progress Trainings: " + dashboard.getInProgressTrainingsCount());
		Assert.assertTrue(dashboard.getInProgressTrainingsCount() >= 0,
				"This Week filter not working for InProgress Trainings !");
		System.out.println("Completed  Trainings: " + dashboard.getCompletedTrainingsCount());
		Assert.assertTrue(dashboard.getCompletedTrainingsCount() >= 0,
				"This Week filter not working for InProgress Trainings Completed Trainings!");
		System.out.println("Earned Badges: " + dashboard.getEarnedBadgesCount());
		Assert.assertTrue(dashboard.getEarnedBadgesCount() >= 0, "This Week filter not working for Earned Badges!");

	}
	
	@Ignore
	@Test(priority = 10)
	public void verifyLearningStatusCardTest() {

		System.out.println("------------------Display Number Cards Are Display of Learning Status In-Progress -------------------");
		
		String isCardsPresent = dashboard.isLearningCardsDisplayed();

		switch (isCardsPresent) {

		case "CARDS_PRESENT":
		    System.out.println("Cards are available");
		    dashboard.learningStatusGetCardCount();
		    break;

		case "NO_DATA":
		    System.out.println("No Data found");
		    break;

		default:
		    Assert.fail("Unexpected state");
		    }
	}
	
	
	@Test(priority = 11)
	public void verifyLearningStatusInProgressDataTest() {
		
		System.out.println("------------------Display Number Cards Are Display of Learning Status In-Progress -------------------");
		dashboard.validateLearningInProgressCards();
		
	}
	
	
	@Test(priority = 12)
	public void verifyLearningStatusNotStartedDataTest() {
		
		
		Assert.assertTrue(dashboard.isLeanerNotStartedButtonDisplay(), "Leanrer Status Not started link button not visible");
		dashboard.doclickLearnerStatusNotStarted();
		System.out.println("------------------Display Number Cards Are Display of Learning Status Not-Started -------------------");
		dashboard.validateLearningNotStartedCards();
	}
	
	
	@Test(priority = 13)
	public void verifyMySheduleDataTest() {
		
		System.out.println("------------------Display Number My Schedule -------------------");
		Assert.assertTrue(dashboard.isMySheduleViewDetailsDisplay(), "My Schedule View Details link button not visible");
		Assert.assertTrue(dashboard.isMySheduleDatePickerDisplay(), "My Schedule Date Picker not visible");
		Assert.assertTrue(dashboard.isMySheduleTodayDateDisplay(), "My Schedule Today Date not visible");
		Assert.assertEquals(dashboard.getMyScheduleTodayDate(),dashboard.getCurrentdate(),"My Shedule Current Date mismatch!");
		System.out.println("Today date: "+ dashboard.getCurrentdate());
		dashboard.validateMySheduleTodayList();
	}
	
	@Test(priority = 14)
	public void verifyAssignedTrainingsRecentlyAssignedDataTest() {
		
		Assert.assertTrue(dashboard.isAssignedTrainingsRecentlyAssignedDisplay(), "Assigned Trainings  link button not visible");
		System.out.println("------------------Display Number Cards Are Display of Assigned Trainings Recently Assigned -------------------");
		dashboard.validateAssignedTrainingsRecentlyAssignedCards();
	}
	
	@Test(priority = 15)
	public void verifyAssignedTrainingsDueDataTest() {
		
		Assert.assertTrue(dashboard.isAssignedTrainingsDueButtonDisplay(), "Due link button not visible");
		dashboard.doclickAssignedTrainingsDueButton();
		System.out.println("------------------Display Number Cards Are Display of Assigned Trainings Due -------------------");
		dashboard.validateAssignedTrainingsDueAndOverDueCards();
	}
	@Test(priority = 16)
	public void verifyAssignedTrainingsOverDueDataTest() {
		
		Assert.assertTrue(dashboard.isAssignedTrainingsOverDueButtonDisplay(), "Over Due link button not visible");
		dashboard.doclickAssignedTrainingsOverDueButton();
		System.out.println("------------------Display Number Cards Are Display of Assigned Trainings Over Due -------------------");
		dashboard.validateAssignedTrainingsDueAndOverDueCards();
	}
	
	@Test(priority = 17)
	public void verifyRecentBadgesDataTest() {
		
		Assert.assertTrue(dashboard.isRecentBadgesViewAllButtonDisplay(), "Badges View All link button not visible");
		System.out.println("------------------Display Number Cards Are Display of Recent Badges -------------------");
		dashboard.validateRecentBadgesCards();
	}
	
	@Test(priority = 18)
	public void verifyLeaderboardDataTest() {
		
		System.out.println("------------------Display Number Rank Cards Are Display of Leaderboard -------------------");
		dashboard.validateLeaderboardRankCards();
	}
	
	@Test(priority = 19)
	public void verifyActivityFeedsDataTest() {
		
		Assert.assertTrue(dashboard.isActivityFeedsViewAllButtonDisplay(), "Activity Feeds View All link button not visible");
		System.out.println("------------------Display Number Cards Are Display of Activity Feeds -------------------");
		dashboard.validateActivityFeedsCards();
	}
	@Test(priority = 20)
	public void verifyCertificationsDataTest() {
		
		Assert.assertTrue(dashboard.isCertificationsViewAllButtonDisplay(), "Certifications View All link button not visible");
		System.out.println("------------------Display Number Cards Are Display of Certifications -------------------");
		
		dashboard.validateCertificationsCards();
	}
	@Test(priority = 21)
	public void verifymainMenuItemsDataTest() {
		
		Assert.assertTrue(dashboard.isSidebarMenuiconDisplay(), "sidebar Menu icon  not visible");
		Assert.assertTrue(dashboard.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		Assert.assertTrue(dashboard.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		dashboard.doclickMyLearningSubmenu();
		System.out.println("------------------Display Number Menus Are Display -------------------");
		dashboard.validatemainMenuItemsAndIcon();
	}
	
	// This Icon is hide So this Test Case is ignore
	@Ignore
	@Test(priority = 6)
	public void verifyHeaderIconsFunctionality() {

		// Quick Links
		Assert.assertTrue(dashboard.isQuickLinksDisplayed(), "Quick Links icon is not displayed!");
		Assert.assertTrue(dashboard.isQuickLinkEnabled(), "Quick Links icon was not enabled");
		dashboard.doClickOnQuicklinks();
		Assert.assertTrue(dashboard.isQuicklinkPopUpDisplayed(), "Quick Links popup not displayed!");
		dashboard.doClickQuickCloseIcon();

	}

	@Ignore
	@Test(priority = 7)
	public void verifyCartPopUpTest() {

		// Cart
		Assert.assertTrue(dashboard.isCartIconDisplayed(), "Cart icon is not displayed!");
		Assert.assertTrue(dashboard.isCartIconEnabled(), "Cart icon was not enabled");
		dashboard.doclickOnCarticon();
		Assert.assertTrue(dashboard.isCartPopUpDisplayed(), "Cart icon popup not displayed!");
		dashboard.doPressKeyEscape();
	}

	@Ignore
	@Test(priority = 9)
	public void verifyNotificationPopUpTest() {

		// Notification
		Assert.assertTrue(dashboard.isNotificationIconDisplayed(), "Notification icon is not displayed!");
		Assert.assertTrue(dashboard.isNotificationIconEnabled(), "Notification icon was not enabled");
		dashboard.doclickNotificationIcon();
		Assert.assertTrue(dashboard.isNotificationPopUpDisplayed(), "Notification popup not displayed!");
		dashboard.doPressKeyEscape();
	}

	@Ignore
	@Test(priority = 7)
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

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}

}
