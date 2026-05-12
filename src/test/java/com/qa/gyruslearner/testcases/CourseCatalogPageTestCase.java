package com.qa.gyruslearner.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.CourseCatalogPage;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class CourseCatalogPageTestCase extends TestBase {
	
	LoginPage loginpage;
	DashboardPage dashboard;
	CourseCatalogPage courseCatalog;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;
	
	public CourseCatalogPageTestCase() {
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		courseCatalog = new CourseCatalogPage();
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
	public void verifyCourseCatalogUrlTest() {

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(courseCatalog.isCourseCatalogMenuDisplay(), "Course Catalog Menu not visible");
		softAssert.assertTrue(courseCatalog.isCourseCatalogMenuEnable(), " Course Catalog menu was not enabled");
		courseCatalog.doclickCourseCatalogMenu();
		try {
			String CourseCatalogUrl = courseCatalog.getCourseCatalogPageUrl();
			softAssert.assertEquals(CourseCatalogUrl, AppConstants.COURSE_CATALOG_PAGE_URL);
			System.out.println("Course Catalog Page URL : " + AppConstants.COURSE_CATALOG_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("Course Catalog URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"})
	public void verifyCourseCatalogTitileTest() {

		SoftAssert softAssert = new SoftAssert();
		String CourseCatalogPagetitle = courseCatalog.getCourseCatalogPageTitle();
		softAssert.assertEquals(CourseCatalogPagetitle, AppConstants.COURSE_CATALOG_PAGE_TITLE);
		softAssert.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
