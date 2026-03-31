package com.qa.gyruslearner.testcases;

import org.testng.Assert;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.IDPPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class IDPPageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	IDPPage idp;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;

	
	public IDPPageTestCase() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		idp = new IDPPage();
		eleUtil = new ElementUtil();
		excelUtil = new ExcelUtil();
	}

	@DataProvider
	public Object[][] getLoginSheetData() {
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH,AppConstants.VALID_LOGIN_SHEET_NAME);
	}

	@DataProvider
	public Object[][] geSearchTestData() {
		return new Object[][] { { "Test20" }, };
	}

	@BeforeMethod()
	public void pageRefresh() {

		if (driver.getCurrentUrl().contains("login")) {
			// (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL))
			loginpage.getUserFirstTimeLogin(prop.getProperty("username"), prop.getProperty("password"));

		} else if (driver.getCurrentUrl().equals(AppConstants.DASHBOARD_PAGE_URL)) {

			System.out.println("Current Page is display");
		}
	}

	@Test(priority = 1)
	public void verifyMyLearnerLinkTest() {

		Assert.assertTrue(idp.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		Assert.assertTrue(idp.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		idp.doclickMyLearningSubmenu();
	}

	@Test(priority = 2, dependsOnMethods = "verifyMyLearnerLinkTest")
	public void verifyidpUrlTest() {

		Assert.assertTrue(idp.isIDPMenuDisplay(), "IDP SubMenu not visible");
		Assert.assertTrue(idp.isIDPMenuEnable(), " IDP Submenu was not enabled");
		idp.doclickIDPSubMenu();
		try {
			String idpUrl = idp.getIDPPageUrl();
			Assert.assertEquals(idpUrl, AppConstants.IDP_PAGE_URL);
			System.out.println("IDP Page URL : " + AppConstants.IDP_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
	}

	@Test(priority = 3)
	public void verifyIDPPageTitileTest() {

		String idpPagetitle = idp.getIDPPageTitle();
		Assert.assertEquals(idpPagetitle, AppConstants.IDP_PAGE_TITLE);
	}
	@Ignore
	@Test(priority = 4)
	public void verifyBackToDashBoardTest() {

		Assert.assertTrue(idp.isBackToDashboardDisplay(), "Back to Dashboard button not visible");
		idp.doclickBackToDashboard();
		try {
			String DashBaordURL = dashboard.getDashBoardPageUrl();
			Assert.assertEquals(DashBaordURL, AppConstants.DASHBOARD_PAGE_URL);
			System.out.println("DashBaord Page URL : " + AppConstants.DASHBOARD_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
		verifyMyLearnerLinkTest();
		Assert.assertTrue(idp.isIDPMenuDisplay(), "IDP SubMenu not visible");
		Assert.assertTrue(idp.isIDPMenuEnable(), " IDP Submenu was not enabled");
		idp.doclickIDPSubMenu();
	}

	@Test(priority = 5)
	public void verifyIDPHeaderPageTitleTest() {

		Assert.assertTrue(idp.isIDPHeaderPageTitleDisplay(), "IDP Header Title  not visible");
		String IDPHeaderPageTitle = idp.getIDPHeaderPageTitle();
		Assert.assertEquals(IDPHeaderPageTitle, "Individual Development Plan");
		System.out.println("IDP Page Header Title : " + IDPHeaderPageTitle);

	}
	
	@Test(priority = 6,enabled = true)
	public void verifyAllCardLoadedAndCountTest() {

		Assert.assertTrue(idp.isTrainingCountDisplay(), "Trainings Count  not visible");
		System.out.println("------------------Display Number IDP Training Count -------------------");
		// Training Count update
		TrainingCountUpdate();

	}
	
	@Test(priority = 7, dependsOnMethods ="verifyAllCardLoadedAndCountTest", enabled = true)
	public void verifyAllCardsStatusAndpercentageTest() {
		
		System.out.println("------------------Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards();
		
	}

	
	@Test(priority = 8,enabled = true)
	public void verifyAllElearningQuickFilterTest() {

		Assert.assertTrue(idp.isAllELearningDisplay(), "All ELearning  Quick Filter was not visible");
		Assert.assertTrue(idp.isAllELearningEnable(), " All ELearning  Quick Filter  was not enabled");
		idp.doclickAllELearningQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();
	}

	public void TrainingCountUpdate() {

		int expectedCount = idp.getTotalTrainingCount();
		System.out.println("Trainings Count : " + expectedCount);
		idp.loadAllCards();
		int actualCount = idp.getCardCount();

		System.out.println("Expected: " + expectedCount);
		System.out.println("Actual: " + actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Mismatch in training cards!");
	}
	
	@Test(priority = 9)
	public void verifyAssessmentsQuickFilterTest() {

		Assert.assertTrue(idp.isAssessmentsDisplay(), "Assessments  Quick Filter was not visible");
		Assert.assertTrue(idp.isAssessmentsEnable(), " Assessments  Quick Filter  was not enabled");
		
		idp.doclickAssessmentsQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();
		
		System.out.println("------------------Assessments Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards();
		
	}
	

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	

}
