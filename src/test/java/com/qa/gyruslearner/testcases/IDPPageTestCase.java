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
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		idp = new IDPPage();
		eleUtil = new ElementUtil();
		excelUtil = new ExcelUtil();
	}

	@BeforeMethod(alwaysRun = true)
	public void pageRefresh() {

		if (driver.getCurrentUrl().contains("login")) {
			// (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL))
			//loginpage.getUserFirstTimeLogin(prop.getProperty("username"), prop.getProperty("password"));
			loginpage.login(prop.getProperty("username"), prop.getProperty("password"));

		} else if (driver.getCurrentUrl().equals(AppConstants.DASHBOARD_PAGE_URL)) {

			System.out.println("Current Page is display");
		}
	}

	@Test(priority = 1,groups = {"regression"})
	public void verifyMyLearnerLinkTest() {
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(idp.isMyLearningMenuDisplay(), "MyLearning Menu icon  not visible");
		softAssert.assertTrue(idp.isMyLearningMenuEnable(), "MyLearning Submenu was not enabled");
		idp.doclickMyLearningSubmenu();
		softAssert.assertAll();
	}

	@Test(priority = 2,groups = {"smoke"}, dependsOnMethods = "verifyMyLearnerLinkTest")
	public void verifyidpUrlTest() {
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(idp.isIDPMenuDisplay(), "IDP SubMenu not visible");
		softAssert.assertTrue(idp.isIDPMenuEnable(), " IDP Submenu was not enabled");
		idp.doclickIDPSubMenu();
		try {
			String idpUrl = idp.getIDPPageUrl();
			softAssert.assertEquals(idpUrl, AppConstants.IDP_PAGE_URL);
			System.out.println("IDP Page URL : " + AppConstants.IDP_PAGE_URL);
		} catch (Exception e) {

			softAssert.fail("IDP URL not loaded properly: " + e.getMessage());
			// throw new SkipException("Skipping test because user could not login.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 3,groups = {"smoke"})
	public void verifyIDPPageTitileTest() {
		
		SoftAssert softAssert = new SoftAssert();
		String idpPagetitle = idp.getIDPPageTitle();
		softAssert.assertEquals(idpPagetitle, AppConstants.IDP_PAGE_TITLE);
		softAssert.assertAll();
	}

	

	@Test(priority = 4,groups = {"smoke"})
	public void verifyIDPHeaderPageTitleTest() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(idp.isIDPHeaderPageTitleDisplay(), "IDP Header Title  not visible");
		String IDPHeaderPageTitle = idp.getIDPHeaderPageTitle();
		softAssert.assertEquals(IDPHeaderPageTitle, "Individual Development Plan");
		System.out.println("IDP Page Header Title : " + IDPHeaderPageTitle);
		softAssert.assertAll();
	}

	@Test(priority = 5,groups = {"smoke"}, enabled = true)
	public void verifyAllCardLoadedAndCountTest() {
		
		SoftAssert softAssert = new SoftAssert();
		// idp.validatAdvancedFilterClear();
		softAssert.assertTrue(idp.isTrainingCountDisplay(), "Trainings Count  not visible");
		System.out.println("------------------Display Number IDP Training Count -------------------");
		// Training Count update
		idp.trainingCountUpdate();
		softAssert.assertAll();

	}

	@Test(priority = 6,groups = {"smoke"}, dependsOnMethods = "verifyAllCardLoadedAndCountTest", enabled = true)
	public void verifyAllCardsStatusAndpercentageTest() {

		SoftAssert softAssert = new SoftAssert();
		System.out.println("------------------Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		softAssert.assertAll();

	}

	@Test(priority = 7,groups = {"regression"},enabled = true)
	public void verifyAllElearningQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(idp.isAllELearningDisplay(), "All ELearning  Quick Filter was not visible");
		softAssert.assertTrue(idp.isAllELearningEnable(), " All ELearning  Quick Filter  was not enabled");
		idp.doclickAllELearningQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();
		softAssert.assertAll();
	}

	

	@Test(priority = 8,groups = {"regression"}, enabled = true)
	public void verifyAssessmentsQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(idp.isAssessmentsDisplay(), "Assessments  Quick Filter was not visible");
		softAssert.assertTrue(idp.isAssessmentsEnable(), " Assessments  Quick Filter  was not enabled");

		idp.doclickAssessmentsQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println(
				"------------------Assessments Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Assessment");

		softAssert.assertAll();

	}

	@Test(priority =9,groups = {"regression"}, enabled = true)
	public void verifyCertificationQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(idp.isCertificationDisplay(), "Certification  Quick Filter was not visible");
		softAssert.assertTrue(idp.isCertificationEnable(), " Certification  Quick Filter  was not enabled");

		idp.doclickCertificationQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Assessments Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Non-Sequence Training", "Sequence Training");

		softAssert.assertAll();

	}

	@Test(priority = 10,groups = {"regression"}, enabled = true)
	public void verifyDocumentQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(idp.isDocumentDisplay(), "Document  Quick Filter was not visible");
		softAssert.assertTrue(idp.isDocumentEnable(), " Document  Quick Filter  was not enabled");

		idp.doclickDocumentQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Document Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Document");
		softAssert.assertAll();

	}

	@Test(priority = 11,groups = {"regression"}, enabled = true)
	public void verifyQuickFilterDailgoBoxTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		// Verify Open
		softAssert.assertTrue(idp.isQuickFilterDialogOpen(), "Quick Filter Dialog is not opened");

		softAssert.assertTrue(idp.isCancelButtonDisplay(), "Cancel button  Quick Filter was not visible");
		softAssert.assertTrue(idp.isCancelButtonEnable(), " Cancel button  Quick Filter  was not enabled");

		idp.doclickCancelButtonQuickFilter();

		softAssert.assertTrue(idp.isQuickFilterDialogClosed(), "Dialog is not closed");
		
		softAssert.assertAll();

	}

	@Test(priority = 12,groups = {"regression"}, enabled = true, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyElearnigQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isElearningDisplay(), "Elearning  Quick Filter was not visible");
		softAssert.assertTrue(idp.isElearningEnable(), " Elearning  Quick Filter  was not enabled");

		idp.doclickElearningQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Elearning Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "eLearning");
		softAssert.assertAll();

	}

	@Test(priority = 13,groups = {"regression"}, enabled = true, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyExternalLinkQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isExternalLinkDisplay(), "ExternalLink  Quick Filter was not visible");
		softAssert.assertTrue(idp.isExternalLinkEnable(), " ExternalLink  Quick Filter  was not enabled");

		idp.doclickExternalLinkQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------ExternalLink Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "External Link");
		softAssert.assertAll();

	}

	@Test(priority = 14,groups = {"regression"}, enabled = true, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyILTQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isILTDisplay(), "ILT  Quick Filter was not visible");
		softAssert.assertTrue(idp.isILTEnable(), " ILT  Quick Filter  was not enabled");

		idp.doclickILTQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out
				.println("------------------ILT Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "ILT");
		softAssert.assertAll();
	}

	@Test(priority = 15,groups = {"regression"}, enabled = true, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyLearningPathQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isLearningPathDisplay(), "LearningPath  Quick Filter was not visible");
		softAssert.assertTrue(idp.isLearningPathEnable(), " LearningPath  Quick Filter  was not enabled");

		idp.doclickLearningPathQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------LearningPath Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "Learning Path");
		softAssert.assertAll();

	}

	@Test(priority = 16,groups = {"regression"}, enabled = true, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyMsTeamQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isMsTeamDisplay(), "MsTeam  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMsTeamEnable(), " MsTeam  Quick Filter  was not enabled");

		idp.doclickMsTeamQuickFilter();

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------MsTeam Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateTrainingTypeMultiple(softAssert, "MSTeams");
		softAssert.assertAll();

	}

	@Test(priority = 17,groups = {"regression"}, enabled = true, dataProvider = "getQuickFilterStatuSheetData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyStatusCompleteQuickFilterTest(String statuse1, String statuse2) {

		SoftAssert softAssert = new SoftAssert();

		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isStatusCompleteDisplay(), "Status Completed in  Quick Filter was not visible");
		softAssert.assertTrue(idp.isStatusCompleteEnable(), " Status Completed in  Quick Filter  was not enabled");

		// idp.doclickStatusCompleteInQuickFilter();
		idp.selectMultipleStatus(statuse1, statuse2);

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Status Completed in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		idp.validateMultipleStatusCards(softAssert, statuse1, statuse2);

		softAssert.assertAll();

	}

	@Test(priority = 18,groups = {"regression"}, enabled = true, dataProvider = "getQucikFilterRatingTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyRatingQuickFilterTest(String Rating) {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isRatingsDisplay(), "Rating  in  Quick Filter was not visible");
		softAssert.assertTrue(idp.isRatingsEnable(), " Rating  in  Quick Filter  was not enabled");

		// idp.doclickRatingsInQuickFilter();
		idp.selectRating(Rating);

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Rating  in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Rating -------------------");
		idp.validateRatingOfCards(softAssert, Rating);

		softAssert.assertAll();

	}

	@Test(priority = 19,groups = {"regression"}, enabled = true, dataProvider = "getQucikFilterDueDateTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyDueDateInQuickFilterTest(String dueDateSelect) {

		SoftAssert softAssert = new SoftAssert();

		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isOverDueDisplay(), "Over Due  in  Quick Filter was not visible");
		softAssert.assertTrue(idp.isOverDueEnable(), " Over Due  in  Quick Filter  was not enabled");

		// idp.doclickOverDueInQuickFilter();
		idp.selectDueAndOverDueDate(dueDateSelect);

		idp.doclickApplyButtonQuickFilter();
		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Due date in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Due and OverDue date -------------------");
		idp.validateDueAndOverdueFilter(softAssert, dueDateSelect);
		softAssert.assertAll();

	}

	@Test(priority = 20,groups = {"regression"}, enabled = true, dataProvider = "getQuickFilterGroupTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyTrainingGroupInQuickFilterTest(String tnGroup1, String tnGroup2) {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isTrainingGroupSelectionDisplay(), "Training Group  in  Quick Filter was not visible");
		softAssert.assertTrue(idp.isTrainingGroupSelectioEnable(), " Training Group  in  Quick Filter  was not enabled");

		idp.selectTrainingGroupMultiValue(tnGroup1, tnGroup2);

		idp.doclickApplyButtonQuickFilter();

		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------TrainingGroup in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Group Name  -------------------");
		idp.validateTrainingGroupFilter(softAssert, tnGroup1, tnGroup2);
		softAssert.assertAll();

	}

	@Test(priority = 21,groups = {"regression"}, enabled = true, dataProvider = "getQuickFilterCetegoryTestData", dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyCategoryInQuickFilterTest(String category) {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isCategorySelectionDisplay(), "Category Selection  in  Quick Filter was not visible");
		softAssert.assertTrue(idp.isCategorySelectionEnable(), " Category Selection  in  Quick Filter  was not enabled");

		idp.selectCategoryValue(category);

		idp.doclickApplyButtonQuickFilter();

		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Category in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verify the All Card Category -------------------");
		idp.validateCategoryFilter(softAssert, category);

		softAssert.assertAll();

	}

	@Test(priority = 22,groups = {"regression"}, enabled = true, dependsOnMethods = "verifyQuickFilterDailgoBoxTest")
	public void verifyFreeInQuickFilterTest() {

		SoftAssert softAssert = new SoftAssert();
		idp.validatAdvancedFilterClear();

		// Verify More button and Click on button
		softAssert.assertTrue(idp.isMoreButtonDisplay(), "More  Quick Filter was not visible");
		softAssert.assertTrue(idp.isMoreButtonEnable(), " More  Quick Filter  was not enabled");
		idp.doclickMoreButtonQuickFilter();

		softAssert.assertTrue(idp.isFreeDisplay(), "Free  in  Quick Filter was not visible");
		softAssert.assertTrue(idp.isFreeEnable(), " Free   in  Quick Filter  was not enabled");

		idp.selecPricingChk("Free");

		idp.doclickApplyButtonQuickFilter();

		// Training count not load thread sleep put
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		idp.trainingCountUpdate();

		System.out.println(
				"------------------Free in Filter Compare the Status and percentage of Cards -------------------");
		idp.validateStatusIDPAllCards(softAssert);
		softAssert.assertAll();
	}

	@Test(priority = 23,groups = {"regression"}, enabled = true)
	public void VerifyIdealCoachTextTest() {
		
		SoftAssert softAssert = new SoftAssert();
		// Verify More button and Click on button
		softAssert.assertTrue(idp.isIdeaCouchButtonDisplay(), "Idea Coach Button was not visible");
		softAssert.assertTrue(idp.isIdeaCouchButtonEnable(), " Idea Coach Button  was not enabled");
		System.out.println("------------------Verifing Idea Coach Text -------------------");
		softAssert.assertTrue(idp.isIdeaGotItbuttonDisplay(), "Got it  Button was not visible");
		softAssert.assertTrue(idp.isIdeaGotItbuttonEnable(), " Got it  Button  was not enabled");
		//softAssert.assertTrue(idp.isIdeaCouchIconDisplay(), " Idea Coach Icon  was not Visible");
		idp.doclickIdeaCouchButton();

		String actualIdea = idp.getIdeaCoachText();
		String expectedIdea = AppConstants.IDP_PAGE_IDEA_COACH;
		System.out.println("IDP Idea Coach Text is : " + actualIdea);
		softAssert.assertEquals(actualIdea, expectedIdea, "Mismatch in Idea Coach!");
		softAssert.assertAll();
	}

	@Test(priority = 24,groups = {"smoke"}, enabled = true, dataProvider = "getSearchTestData")
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
		idp.trainingCountUpdate();
		idp.validateStatusIDPAllCards(softAssert);
		System.out.println("------------------Verifing InValid Search Data  -------------------");
		idp.doSearch(searchInValid);
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		idp.trainingCountUpdate();
		System.out.println("------------------Verifing Partial Search Data  -------------------");
		idp.doSearch(searchPartial);
		// Training count not load thread sleep put
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		idp.trainingCountUpdate();
		//idp.validateStatusIDPAllCards(softAssert);
		softAssert.assertAll();

	}
	
	@Ignore
	@Test(priority = 25,groups = {"smoke"})
	public void verifyBackToDashBoardTest() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(idp.isBackToDashboardDisplay(), "Back to Dashboard button not visible");
		idp.doclickBackToDashboard();
		try {
			String DashBaordURL = dashboard.getDashBoardPageUrl();
			softAssert.assertEquals(DashBaordURL, AppConstants.DASHBOARD_PAGE_URL);
			System.out.println("DashBaord Page URL : " + AppConstants.DASHBOARD_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
		verifyMyLearnerLinkTest();
		softAssert.assertTrue(idp.isIDPMenuDisplay(), "IDP SubMenu not visible");
		softAssert.assertTrue(idp.isIDPMenuEnable(), " IDP Submenu was not enabled");
		idp.doclickIDPSubMenu();
		softAssert.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
