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
import com.qa.gyruslearner.pages.TrainingTranscriptPage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class TrainingTranscriptPageTestCase extends TestBase {
	
	
	LoginPage loginpage;
	DashboardPage dashboard;
	TrainingTranscriptPage trainingTrans;
	ElementUtil eleUtil;
	ExcelUtil excelUtil;
	
	public TrainingTranscriptPageTestCase() {
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		trainingTrans = new TrainingTranscriptPage();
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
	public void verifyTrainingTranscriptUrlTest() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(trainingTrans.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		softAssert.assertTrue(trainingTrans.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		trainingTrans.doclickMyLearningSubmenu();

		softAssert.assertTrue(trainingTrans.isTrainingTranscriptMenuDisplay(), "Training Transcript SubMenu not visible");
		softAssert.assertTrue(trainingTrans.isTrainingTranscriptMenuEnable(), " Training Transcript Submenu was not enabled");
		trainingTrans.doclickTrainingTranscriptSubMenu();
		try {
			String TrainingTranscriptUrl = trainingTrans.getTrainingTranscriptPageUrl();
			softAssert.assertEquals(TrainingTranscriptUrl, AppConstants.TRAINING_TRANSCRIPT_PAGE_URL);
			System.out.println("Training Transcript Page URL : " + AppConstants.TRAINING_TRANSCRIPT_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("Training Transcript URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"})
	public void verifyTrainingTranscriptTitileTest() {

		SoftAssert softAssert = new SoftAssert();
		String TrainingTranscriptPagetitle = trainingTrans.getTrainingTranscriptPageTitle();
		softAssert.assertEquals(TrainingTranscriptPagetitle, AppConstants.TRAINING_TRANSCRIPT_PAGE_TITLE);
		softAssert.assertAll();
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
