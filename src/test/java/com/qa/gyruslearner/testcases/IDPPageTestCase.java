package com.qa.gyruslearner.testcases;

import org.testng.Assert;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		return ExcelUtil.getTestData(AppConstants.LOGIN_DATA_SHEET_PATH, AppConstants.VALID_LOGIN_SHEET_NAME);
	}

	@DataProvider
	public Object[][] getQuickFilterStatuSheetData() {
		return new Object[][] { { "Completed", "In Progress" } };
	}

	@DataProvider
	public Object[][] getQucikFilterRatingTestData() {
		return ExcelUtil.getTestData(AppConstants.IDP_DATA_SHEET_PATH,
				AppConstants.RATING_QUICKFILTER_IDPPAGE_SHEET_NAME);
		// return new Object[][] { { "2 stars" } };
	}

	@DataProvider
	public Object[][] getQucikFilterDueDateTestData() {
		return ExcelUtil.getTestData(AppConstants.IDP_DATA_SHEET_PATH,
				AppConstants.DUEDATE_QUICKFILTER_IDPPAGE_SHEET_NAME);
	}

	@DataProvider
	public Object[][] getQuickFilterGroupTestData() {
		return new Object[][] { { "E-Learning", "External Link" } };
	}

	@DataProvider
	public Object[][] getQuickFilterCetegoryTestData() {
		return new Object[][] { { "Business Skills" } };
	}

	@DataProvider
	public Object[][] getSearchTestData() {
		return ExcelUtil.getTestData(AppConstants.IDP_DATA_SHEET_PATH,
				AppConstants.SEARCH_IDPPAGE_SHEET_NAME);
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

			Assert.fail("IDP URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
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

	@Test(priority = 6, enabled = false)
	public void verifyAllCardLoadedAndCountTest() {

		// idp.validatAdvancedFilterClear();
		Assert.assertTrue(idp.isTrainingCountDisplay(), "Trainings Count  not visible");
		System.out.println("------------------Display Number IDP Training Count -------------------");
		// Training Count update
		TrainingCountUpdate();

	}

	@Test(priority = 7, dependsOnMethods = "verifyAllCardLoadedAndCountTest", enabled = false)
	public void verifyAllCardsStatusAndpercentageTest() {

		SoftAssert softAssert = new SoftAssert();
		System.out.println("------------------Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		softAssert.assertAll();

	}

	@Test(priority = 8, enabled = false)
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

	@Test(priority = 9, enabled = false)
	public void verifyAssessmentsQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();

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
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println(
				"------------------Assessments Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Assessment");

		softAssert.assertAll();

	}

	@Test(priority = 10, enabled = false)
	public void verifyCertificationQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();

		Assert.assertTrue(idp.isCertificationDisplay(), "Certification  Quick Filter was not visible");
		Assert.assertTrue(idp.isCertificationEnable(), " Certification  Quick Filter  was not enabled");

		idp.doclickCertificationQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Assessments Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Non-Sequence Training", "Sequence Training");

		softAssert.assertAll();

	}

	@Test(priority = 11, enabled = false)
	public void verifyDocumentQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		Assert.assertTrue(idp.isDocumentDisplay(), "Document  Quick Filter was not visible");
		Assert.assertTrue(idp.isDocumentEnable(), " Document  Quick Filter  was not enabled");

		idp.doclickDocumentQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Document Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Document");
		softAssert.assertAll();

	}

	@Test(priority = 12, enabled = true)
	public void verifyQuickFilterDailgoBoxTest() {

		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		// Verify Open
		Assert.assertTrue(idp.isQuickFilterDialogOpen(), "Quick Filter Dialog is not opened");

		Assert.assertTrue(idp.isCancelButtonDisplay(), "Cancel button  Quick Filter was not visible");
		Assert.assertTrue(idp.isCancelButtonEnable(), " Cancel button  Quick Filter  was not enabled");

		idp.doclickCancelButtonQuickFilter();

		Assert.assertTrue(idp.isQuickFilterDialogClosed(), "Dialog is not closed");

	}

	@Test(priority = 13, enabled = false, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyElearnigQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isElearningDisplay(), "Elearning  Quick Filter was not visible");
		Assert.assertTrue(idp.isElearningEnable(), " Elearning  Quick Filter  was not enabled");

		idp.doclickElearningQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Elearning Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "eLearning");
		softAssert.assertAll();

	}

	@Test(priority = 14, enabled = false, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyExternalLinkQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isExternalLinkDisplay(), "ExternalLink  Quick Filter was not visible");
		Assert.assertTrue(idp.isExternalLinkEnable(), " ExternalLink  Quick Filter  was not enabled");

		idp.doclickExternalLinkQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------ExternalLink Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "External Link");
		softAssert.assertAll();

	}

	@Test(priority = 15, enabled = false, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyILTQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isILTDisplay(), "ILT  Quick Filter was not visible");
		Assert.assertTrue(idp.isILTEnable(), " ILT  Quick Filter  was not enabled");

		idp.doclickILTQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out
				.println("------------------ILT Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "ILT");
		softAssert.assertAll();
	}

	@Test(priority = 16, enabled = false, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyLearningPathQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isLearningPathDisplay(), "LearningPath  Quick Filter was not visible");
		Assert.assertTrue(idp.isLearningPathEnable(), " LearningPath  Quick Filter  was not enabled");

		idp.doclickLearningPathQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------LearningPath Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Learning Path");
		softAssert.assertAll();

	}

	@Test(priority = 17, enabled = false, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyMsTeamQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isMsTeamDisplay(), "MsTeam  Quick Filter was not visible");
		Assert.assertTrue(idp.isMsTeamEnable(), " MsTeam  Quick Filter  was not enabled");

		idp.doclickMsTeamQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------MsTeam Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "MSTeams");
		softAssert.assertAll();

	}

	@Test(priority = 18, enabled = false, dataProvider = "getQuickFilterStatuSheetData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyStatusCompleteQuickFilterTest(String statuse1, String statuse2) {

		SoftAssert softAssert = new SoftAssert();

		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isStatusCompleteDisplay(), "Status Completed in  Quick Filter was not visible");
		Assert.assertTrue(idp.isStatusCompleteEnable(), " Status Completed in  Quick Filter  was not enabled");

		// idp.doclickStatusCompleteInQuickFilter();
		idp.selectMultipleStatus(statuse1, statuse2);

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Status Completed in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateMultipleStatusCards(softAssert, statuse1, statuse2);

		softAssert.assertAll();

	}

	@Test(priority = 19, enabled = false, dataProvider = "getQucikFilterRatingTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyRatingQuickFilterTest(String Rating) {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isRatingsDisplay(), "Rating  in  Quick Filter was not visible");
		Assert.assertTrue(idp.isRatingsEnable(), " Rating  in  Quick Filter  was not enabled");

		// idp.doclickRatingsInQuickFilter();
		idp.selectRating(Rating);

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Rating  in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Rating -------------------");
		idp.validateRatingOfCards(softAssert, Rating);

		softAssert.assertAll();

	}

	@Test(priority = 20, enabled = false, dataProvider = "getQucikFilterDueDateTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyDueDateInQuickFilterTest(String dueDateSelect) {

		SoftAssert softAssert = new SoftAssert();

		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isOverDueDisplay(), "Over Due  in  Quick Filter was not visible");
		Assert.assertTrue(idp.isOverDueEnable(), " Over Due  in  Quick Filter  was not enabled");

		// idp.doclickOverDueInQuickFilter();
		idp.selectDueAndOverDueDate(dueDateSelect);

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Due date in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Due and OverDue date -------------------");
		idp.validateDueAndOverdueFilter(softAssert, dueDateSelect);
		softAssert.assertAll();

	}

	@Test(priority = 21, enabled = false, dataProvider = "getQuickFilterGroupTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyTrainingGroupInQuickFilterTest(String tnGroup1, String tnGroup2) {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isTrainingGroupSelectionDisplay(), "Training Group  in  Quick Filter was not visible");
		Assert.assertTrue(idp.isTrainingGroupSelectioEnable(), " Training Group  in  Quick Filter  was not enabled");

		idp.selectTrainingGroupMultiValue(tnGroup1, tnGroup2);

		idp.doclickApplyButtonQuickFilter();

		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------TrainingGroup in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Group Name  -------------------");
		idp.validateTrainingGroupFilter(softAssert, tnGroup1, tnGroup2);
		softAssert.assertAll();

	}

	@Test(priority = 22, enabled = false, dataProvider = "getQuickFilterCetegoryTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyCategoryInQuickFilterTest(String category) {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();
		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isCategorySelectionDisplay(), "Category Selection  in  Quick Filter was not visible");
		Assert.assertTrue(idp.isCategorySelectionEnable(), " Category Selection  in  Quick Filter  was not enabled");

		idp.selectCategoryValue(category);

		idp.doclickApplyButtonQuickFilter();

		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Category in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Category -------------------");
		idp.validateCategoryFilter(softAssert, category);

		softAssert.assertAll();

	}

	@Test(priority = 22, enabled = false, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyFreeInQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		Assert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		Assert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		Assert.assertTrue(idp.isFreeDisplay(), "Free  in  Quick Filter was not visible");
		Assert.assertTrue(idp.isFreeEnable(), " Free   in  Quick Filter  was not enabled");

		idp.selecPricingChk("Free");

		idp.doclickApplyButtonQuickFilter();

		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TrainingCountUpdate();

		System.out.println(
				"------------------Free in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		softAssert.assertAll();
	}

	@Test(priority = 23, enabled = false)
	public void VerifyIdealCoachTextTest() {

		// Verify More button and Click on button
		Assert.assertTrue(idp.isIdeaCouchButtonDisplay(), "Idea Coach Button was not visible");
		Assert.assertTrue(idp.isIdeaCouchButtonEnable(), " Idea Coach Button  was not enabled");
		System.out.println("------------------Verifing Idea Coach Text -------------------");
		Assert.assertTrue(idp.isIdeaGotItbuttonDisplay(), "Got it  Button was not visible");
		Assert.assertTrue(idp.isIdeaCouchButtonEnable(), " Got it  Button  was not enabled");
		Assert.assertTrue(idp.isIdeaCouchIconDisplay(), " Idea Coach Icon  was not Visible");
		idp.doclickIdeaCouchButton();

		String actualIdea = idp.getIdeaCoachText();
		String expectedIdea = AppConstants.IDP_PAGE_IDEA_COACH;
		Assert.assertEquals(actualIdea, expectedIdea, "Mismatch in Idea Coach!");
	}

	@Test(priority = 24, enabled = true, dataProvider = "getSearchTestData")
	public void verifySearchFunctionality(String searchValid,String searchInValid, String searchPartial) {

		SoftAssert softAssert = new SoftAssert();
		eleUtil.waitForCoachNotificationPanelToDisappear();
		idp.validatAdvancedFilterClear();
		System.out.println("------------------Verifing Valid Search Data  -------------------");
		Assert.assertTrue(idp.isSearchFieldDisplay(), "Search Field was not visible");
		Assert.assertTrue(idp.isSearchFieldEnable(), " Search Field  was not enabled");
		idp.doSearch(searchValid);
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		TrainingCountUpdate();
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verifing InValid Search Data  -------------------");
		idp.doSearch(searchInValid);
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		TrainingCountUpdate();
		
		System.out.println("------------------Verifing Partial Search Data  -------------------");
		idp.doSearch(searchPartial);
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		TrainingCountUpdate();
		//idp.validateStatusIDPAllCards(softAssert);
		softAssert.assertAll();

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
