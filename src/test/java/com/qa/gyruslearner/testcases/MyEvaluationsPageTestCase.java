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
import com.qa.gyruslearner.pages.MyEvaluationsPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class MyEvaluationsPageTestCase extends TestBase {
	
	LoginPage loginpage;
	DashboardPage dashboard;
	MyEvaluationsPage myEval;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;
	
	public MyEvaluationsPageTestCase() {
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		myEval = new MyEvaluationsPage();
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
	public void verifyMyEvaluationsUrlTest() {

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(myEval.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		softAssert.assertTrue(myEval.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		myEval.doclickMyLearningSubmenu();

		softAssert.assertTrue(myEval.isMyEvaluationsMenuDisplay(), "My Evaluations SubMenu not visible");
		softAssert.assertTrue(myEval.isMyEvaluationsMenuEnable(), " My Evaluations Submenu was not enabled");
		myEval.doclickMyEvaluationsSubMenu();
		
		try {
			String myEvaluationsPageUrl = myEval.getMyEvaluationsPageUrl();
			softAssert.assertEquals(myEvaluationsPageUrl, AppConstants.MYEVALUATIONS_PAGE_URL);
			System.out.println("My Evaluations Page URL : " + AppConstants.MYEVALUATIONS_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("My Evaluations URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"})
	public void verifyMyEvaluationsTitileTest() {

		SoftAssert softAssert = new SoftAssert();
		String myEvaluationsPagetitle = myEval.getMyEvaluationsPageTitle();
		softAssert.assertEquals(myEvaluationsPagetitle, AppConstants.MYEVALUATIONS_PAGE_TITLE);
		softAssert.assertAll();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
