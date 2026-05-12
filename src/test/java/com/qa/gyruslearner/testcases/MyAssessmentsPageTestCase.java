package com.qa.gyruslearner.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.pages.MyAssessmentsPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class MyAssessmentsPageTestCase extends TestBase {

	
	LoginPage loginpage;
	DashboardPage dashboard;
	MyAssessmentsPage myAssessment;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;
	
	public MyAssessmentsPageTestCase(){
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		myAssessment = new MyAssessmentsPage();
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
		
		softAssert.assertTrue(myAssessment.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		softAssert.assertTrue(myAssessment.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		myAssessment.doclickMyLearningSubmenu();

		softAssert.assertTrue(myAssessment.isMyAssessmentsMenuDisplay(), "My Assessment SubMenu not visible");
		softAssert.assertTrue(myAssessment.isMyAssessmentsMenuEnable(), " My Assessment Submenu was not enabled");
		myAssessment.doclickMyAssessmentsSubMenu();
		
		try {
			String myAssessmentUrl = myAssessment.getMyAssessmentsPageUrl();
			softAssert.assertEquals(myAssessmentUrl, AppConstants.MYASSESSMENTS_PAGE_URL);
			System.out.println("My Assessment Page URL : " + AppConstants.MYASSESSMENTS_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("My Assessment URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"})
	public void verifyMyAssessmentTitileTest() {

		SoftAssert softAssert = new SoftAssert();
		String myAssessmentPagetitle = myAssessment.getMyAssessmentsPageTitle();
		softAssert.assertEquals(myAssessmentPagetitle, AppConstants.MYASSESSMENTS_PAGE_TITLE);
		softAssert.assertAll();
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
