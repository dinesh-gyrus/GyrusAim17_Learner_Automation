package com.qa.gyruslearner.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.GapAnalysisPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class GapAnalysisPageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	GapAnalysisPage gapAnalysis;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;
	
	public GapAnalysisPageTestCase() {
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		gapAnalysis = new GapAnalysisPage();
		eleUtil = new ElementUtil();
		excelUtil = new ExcelUtil();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void pageRefresh() {

		if (driver.getCurrentUrl().contains("login")) {
			// (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL))
			// loginpage.getUserFirstTimeLogin(prop.getProperty("username"),
			// prop.getProperty("password"));
			loginpage.login(prop.getProperty("username"), prop.getProperty("password"));

		} else if (driver.getCurrentUrl().equals(AppConstants.DASHBOARD_PAGE_URL)) {

			System.out.println("Current Page is display");
		}
	}
	
	@Test(priority = 1, groups = { "regression" })
	public void verifyGapAnalysisUrlTest() {

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(gapAnalysis.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		softAssert.assertTrue(gapAnalysis.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		gapAnalysis.doclickMyLearningSubmenu();

		softAssert.assertTrue(gapAnalysis.isGapAnalysisMenuDisplay(), "Gap Analysis SubMenu not visible");
		softAssert.assertTrue(gapAnalysis.isGapAnalysisMenuEnable(), " Gap Analysis Submenu was not enabled");
		gapAnalysis.doclickGapAnalysisSubMenu();
		
		try {
			String gapAnalysistUrl = gapAnalysis.getGapAnalysisPageUrl();
			softAssert.assertEquals(gapAnalysistUrl, AppConstants.GAP_ANALYSIS_PAGE_URL);
			System.out.println("Gap Analysis Page URL : " + AppConstants.GAP_ANALYSIS_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("Gap Analysis URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"})
	public void verifyGapAnalysisTitileTest() {

		SoftAssert softAssert = new SoftAssert();
		String gapAnalysisPagetitle = gapAnalysis.getGapAnalysisPageTitle();
		softAssert.assertEquals(gapAnalysisPagetitle, AppConstants.GAP_ANALYSIS_PAGE_TITLE);
		softAssert.assertAll();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
