package com.qa.gyruslearner.testcases;


import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.IDPPage;
import com.qa.gyruslearner.pages.LoginPage;

public class IDPPageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	IDPPage idp;
	
	public IDPPageTestCase() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();	
		idp = new IDPPage();
	}
	@BeforeMethod
	public void pageRefresh() {

		
		if (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL)) {
			loginpage.getUserFirstTimeLogin("TTeam01", "123");
				
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
	
	@Test(priority = 2)
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
	public void verifyIDPHeaderPageTitleAndTrainigCountTest() {
		
		Assert.assertTrue(idp.isIDPHeaderPageTitleDisplay(), "IDP Header Title  not visible");
		String IDPHeaderPageTitle=idp.getIDPHeaderPageTitle();
		Assert.assertEquals(IDPHeaderPageTitle,"Individual Development Plan");
		System.out.println("IDP Page Header Title : " + IDPHeaderPageTitle);
		Assert.assertTrue(idp.isTrainingCountDisplay(), "Trainings Count  not visible");
		System.out.println("Trainings Count : " + idp.getIDPTrainingCount());
		
	}
	@Test(priority = 6)
	public void verifyQuickFilterTest() {
		
		
	}
	
	
	
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.close();
		}

	}
	
	
}
