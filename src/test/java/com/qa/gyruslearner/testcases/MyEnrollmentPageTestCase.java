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
import com.qa.gyruslearner.pages.MyEnrollmentPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class MyEnrollmentPageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	MyEnrollmentPage myEndroll;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;

	public MyEnrollmentPageTestCase() {
		super();
	}

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		myEndroll = new MyEnrollmentPage();
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
	public void verifyMyEnrollmentUrlTest() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(myEndroll.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		softAssert.assertTrue(myEndroll.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		myEndroll.doclickMyLearningSubmenu();

		softAssert.assertTrue(myEndroll.isMyEndrollmentMenuDisplay(), "My Endrollment SubMenu not visible");
		softAssert.assertTrue(myEndroll.isMyEndrollmentMenuEnable(), " My sEndrollment Submenu was not enabled");
		myEndroll.doclickMyEndrollmentSubMenu();
		try {
			String MyEndrollmentUrl = myEndroll.getIDPPageUrl();
			softAssert.assertEquals(MyEndrollmentUrl, AppConstants.MYENROLLMENT_PAGE_URL);
			System.out.println("My Endrollment Page URL : " + AppConstants.MYENROLLMENT_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("My Endrollment URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"})
	public void verifyMyEndrollmentTitileTest() {

		SoftAssert softAssert = new SoftAssert();
		String idpPagetitle = myEndroll.getIDPPageTitle();
		softAssert.assertEquals(idpPagetitle, AppConstants.MYENROLLMENT_PAGE_TITLE);
		softAssert.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
