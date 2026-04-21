package com.qa.gyruslearner.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class IDPPage extends TestBase {

	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;

	public IDPPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}

	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuIDP')]")
	WebElement lnkIDP;

	@FindBy(xpath = "//p[normalize-space()='Back to Dashboard']")
	WebElement lnkBackToDashboard;

	@FindBy(xpath = "//*[normalize-space()='Individual Development Plan']")
	WebElement idpHeaderPageTitle;

	@FindBy(xpath = "//div[contains(@class,'IDE-header')]//p")
	WebElement trainingcount;

	@FindBy(xpath = "//gyrusaim-cardview//div[@title='Card View']")
	List<WebElement> idpCardViewList;

	@FindBy(id = "lbl_156")
	WebElement btnQFAllELearning;

	@FindBy(id = "lbl_58")
	WebElement btnQFAssessments;

	@FindBy(id = "lbl_111")
	WebElement btnQFCertification;

	@FindBy(id = "lbl_57")
	WebElement btnQFDocument;

	@FindBy(id = "lbl_52")
	WebElement btnQFElearning;

	@FindBy(id = "lbl_56")
	WebElement btnQFExteanalLink;

	@FindBy(id = "lbl_59")
	WebElement btnQFILT;

	@FindBy(id = "lbl_117")
	WebElement btnQFlearningPath;

	@FindBy(id = "lbl_122")
	WebElement btnQFMSTeam;

	@FindBy(id = "lbl_116")
	WebElement chkcompleteStatus;

	@FindBy(id = "lbl_142")
	WebElement chk5starsRating;

	@FindBy(id = "lbl_135")
	WebElement chkOverdue;

	@FindBy(id = "lbl_0")
	WebElement chkFree;
	

	@FindBy(xpath = "//div[contains(@title,'Add To Favorites')]")
	WebElement favoritesicon;

	@FindBy(xpath = "//div[contains(@title,'Add To Playlist')]")
	WebElement addToPlaylistIcon;

	@FindBy(xpath = "//div[contains(@title,'Like')]")
	WebElement likeIcon;

	@FindBy(xpath = "//div[contains(@title,'Dislike')]")
	WebElement DislikeIcon;

	@FindBy(xpath = "//span[@class='rating_count']")
	WebElement RatingIncard;

	@FindBy(xpath = "//button[normalize-space()='See More']")
	List<WebElement> btnSeeMore;

	@FindBy(xpath = "(//kendo-badge[contains(@themecolor,'error')])[1]")
	WebElement AdvanceFilterApplyIcon;

	@FindBy(xpath = "(//kendo-badge[contains(@themecolor,'error')])[2]")
	WebElement SortingFilterApplyIcon;

	@FindBy(xpath = "//button[normalize-space()='More']")
	WebElement btnMore;

	@FindBy(xpath = "//button[@title='Apply']")
	WebElement btnApply;

	@FindBy(xpath = " //button[contains(@title,'Clear All')]")
	WebElement btnClearAll;

	@FindBy(xpath = "//div[contains(@class,'k-window k-dialog')]")
	WebElement QuickFilterDiallog;

	@FindBy(xpath = "//button[contains(@title,'Cancel')]")
	WebElement btnCancel;

	@FindBy(xpath = "//button[@title='Advanced Filter']")
	WebElement btnAdvancedFilter;

	@FindBy(xpath = "//div[@title='Sorting']")
	WebElement btnsorting;

	@FindBy(xpath = "//div[@title='List View']")
	WebElement btnListView;

	@FindBy(xpath = "//div[@aria-label='Card View']")
	WebElement btnCardView;

	@FindBy(xpath = "//div[@id='left-toggle-btn']")
	WebElement btnViewToggle;

	@FindBy(xpath = "//*[@placeholder='Search Trainings']")
	WebElement txtSearchTraining;

	@FindBy(xpath = "//div[@title='Idea Couch']")
	WebElement btnidealCouch;

	@FindBy(xpath = "//button[contains(@class,'idea-hint-popup_got_it_button')]")
	WebElement btnGotIt;

	@FindBy(xpath = "//i[contains(@class,'k-i-l-idea-gi text-primary')]")
	WebElement idealIcon;

	@FindBy(xpath = "//div[contains(@class,'idea-hint-popup-box')]//p")
	WebElement idealCoachMsg;

	@FindBy(xpath = "//kendo-multiselect[@name='trainingGroup']")
	WebElement multiSelectionTrainingGroup;

	@FindBy(xpath = "//kendo-multiselect[@name='trainingGroup']//input")
	WebElement txtTraingGroupSelection;

	@FindBy(xpath = "//kendo-multiselect[@name='category']")
	WebElement multiSelectionCategory;

	@FindBy(xpath = "//kendo-multiselect[@name='category']//input")
	WebElement txtCategorySelection;
	
	@FindBy(xpath = "//kendo-icon[contains(@class,'k-i-close')]")
	WebElement advanceFilterCloseicon;
	
	@FindBy(xpath = "//span[contains(text(),'Applied Filters')]")
	WebElement appliedFiltersCount;

	public boolean isMyLearningMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkMyLearning);
		return eleUtil.visibleElementWhenReady(lnkMyLearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMyLearningMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkMyLearning);
		return eleUtil.isElementEnable(lnkMyLearning);
	}

	public void doclickMyLearningSubmenu() {

		jsUtil.scrollIntoViewCenter(lnkMyLearning);
		eleUtil.clickElementWhenReady(lnkMyLearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public String getIDPPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.IDP_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String idpCurrentUrl = driver.getCurrentUrl();
		return idpCurrentUrl;
	}

	public String getIDPPageTitle() {

		String IDPPageTitle = eleUtil.waitForTitleIs(AppConstants.IDP_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Individual Development Plan page title==>" + IDPPageTitle);

		return IDPPageTitle;

	}

	public boolean isIDPMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkIDP);
		return eleUtil.visibleElementWhenReady(lnkIDP, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isIDPMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkIDP);
		return eleUtil.isElementEnable(lnkIDP);
	}

	public void doclickIDPSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkIDP);
		eleUtil.clickElementWhenReady(lnkIDP, AppConstants.MAX_TIME_OUT);
	}

	public boolean isBackToDashboardDisplay() {

		jsUtil.scrollIntoViewCenter(lnkBackToDashboard);
		return eleUtil.visibleElementWhenReady(lnkBackToDashboard, AppConstants.MEDIUM_TIME_OUT);
	}

	public void doclickBackToDashboard() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(lnkBackToDashboard);
		eleUtil.clickElementWhenReady(lnkBackToDashboard, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isIDPHeaderPageTitleDisplay() {

		jsUtil.scrollIntoViewCenter(idpHeaderPageTitle);
		return eleUtil.visibleElementWhenReady(idpHeaderPageTitle, AppConstants.MEDIUM_TIME_OUT);
	}

	public String getIDPHeaderPageTitle() {

		return eleUtil.doGetElementText(idpHeaderPageTitle);
	}
	
	public boolean isIdeaCouchButtonDisplay() {

		jsUtil.scrollIntoViewCenter(btnidealCouch);
		return eleUtil.visibleElementWhenReady(btnidealCouch, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isIdeaCouchButtonEnable() {

		jsUtil.scrollIntoViewCenter(btnidealCouch);
		return eleUtil.isElementEnable(btnidealCouch);
	}
	public boolean isIdeaCouchIconDisplay() {
		
		jsUtil.scrollIntoViewCenter(idealIcon);
		return eleUtil.visibleElementWhenReady(idealIcon, AppConstants.MAX_TIME_OUT);
	}
	public boolean isIdeaGotItbuttonDisplay() {
		jsUtil.scrollIntoViewCenter(btnidealCouch);
		return eleUtil.visibleElementWhenReady(btnidealCouch, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isIdeaGotItbuttonEnable() {
		jsUtil.scrollIntoViewCenter(btnidealCouch);
		return eleUtil.isElementEnable(btnidealCouch);
	}
	

	public void doclickIdeaCouchButton() {

		try {
			eleUtil.clickElementWhenReady(btnidealCouch, AppConstants.MAX_TIME_OUT);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnidealCouch);
		}
		
	}
	

	public String getIdeaCoachText() {
		eleUtil.visibleElementWhenReady(idealCoachMsg, AppConstants.MAX_TIME_OUT);
		String text = eleUtil.doGetElementText(idealCoachMsg);
		return text;
	}
	
	
	
	public boolean isTrainingCountDisplay() {

		jsUtil.scrollIntoViewCenter(trainingcount);
		return eleUtil.visibleElementWhenReady(trainingcount, AppConstants.MEDIUM_TIME_OUT);
	}

	public int getTotalTrainingCount() {

		eleUtil.waitForLoaderToDisappear();
		eleUtil.visibleElementWhenReady(trainingcount, AppConstants.MAX_TIME_OUT);

		String text = eleUtil.doGetElementText(trainingcount);

		return Integer.parseInt(text.replaceAll("\\D+", ""));

	}
	
	public void trainingCountUpdate() {

		SoftAssert softAssert = new SoftAssert();
		eleUtil.waitForLoaderToDisappear();
		int expectedCount = getTotalTrainingCount();
		System.out.println("Trainings Count : " + expectedCount);
		loadAllCards();
		eleUtil.waitForLoaderToDisappear();
		int actualCount =getCardCount();

		System.out.println("Expected: " + expectedCount);
		System.out.println("Actual: " + actualCount);

		softAssert.assertEquals(actualCount, expectedCount, "Mismatch in training cards!");
		
		softAssert.assertAll();
	}

	public boolean isAllELearningDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAllELearning);
		return eleUtil.visibleElementWhenReady(btnQFAllELearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isAllELearningEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAllELearning);
		return eleUtil.isElementEnable(btnQFAllELearning);
	}

	public void doclickAllELearningQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAllELearning);

		String isChecked = eleUtil.doElementGetAttribute(btnQFAllELearning, "aria-checked");
		// btnQFAllELearning.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("AllELearning is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFAllELearning, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFAllELearning);
			}
		}

		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isAssessmentsDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAssessments);
		return eleUtil.visibleElementWhenReady(btnQFAssessments, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isAssessmentsEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAssessments);
		return eleUtil.isElementEnable(btnQFAssessments);
	}

	public void doclickAssessmentsQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAssessments);

		String isChecked = eleUtil.doElementGetAttribute(btnQFAssessments, "aria-checked");
		// btnQFAssessments.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("Assessments is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFAssessments, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFAssessments);
			}
		}

		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isCertificationDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFCertification);
		return eleUtil.visibleElementWhenReady(btnQFCertification, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isCertificationEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFCertification);
		return eleUtil.isElementEnable(btnQFCertification);
	}

	public void doclickCertificationQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFCertification);

		String isChecked = eleUtil.doElementGetAttribute(btnQFCertification, "aria-checked");
		// btnQFCertification.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("Certification is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFCertification, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFCertification);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isDocumentDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFDocument);
		return eleUtil.visibleElementWhenReady(btnQFDocument, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isDocumentEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFDocument);
		return eleUtil.isElementEnable(btnQFDocument);
	}

	public void doclickDocumentQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFDocument);

		String isChecked = eleUtil.doElementGetAttribute(btnQFDocument, "aria-checked");
		// btnQFDocument.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("Document is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFDocument, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFDocument);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isMoreButtonDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnMore);
		return eleUtil.visibleElementWhenReady(btnMore, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMoreButtonEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnMore);
		return eleUtil.isElementEnable(btnMore);
	}

	public void doclickMoreButtonQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnMore);

		String isChecked = eleUtil.doElementGetAttribute(btnMore, "aria-checked");
		// btnMore.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("More is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnMore, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnMore);
			}
		}

	}

	public boolean isApplyButtonDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnApply);
		return eleUtil.visibleElementWhenReady(btnApply, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isApplyButtonEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnApply);
		return eleUtil.isElementEnable(btnApply);
	}

	public void doclickApplyButtonQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnApply);

		try {
			eleUtil.clickElementWhenReady(btnApply, AppConstants.MEDIUM_TIME_OUT);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnApply);
		}

		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isQuickFilterDialogOpen() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.SHORT_TIME_OUT));
		try {
			return wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(@class,'k-window k-dialog')]")))
					.isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isQuickFilterDialogClosed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.SHORT_TIME_OUT));
		return wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'k-window k-dialog')]")));
	}

	public boolean isCancelButtonDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnCancel);
		return eleUtil.visibleElementWhenReady(btnCancel, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isCancelButtonEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnCancel);
		return eleUtil.isElementEnable(btnCancel);
	}

	public void doclickCancelButtonQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnCancel);

		try {
			eleUtil.clickElementWhenReady(btnCancel, AppConstants.MEDIUM_TIME_OUT);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCancel);
		}

		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isElearningDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFElearning);
		return eleUtil.visibleElementWhenReady(btnQFElearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isElearningEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFElearning);
		return eleUtil.isElementEnable(btnQFElearning);
	}

	public void doclickElearningQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFElearning);

		String isChecked = eleUtil.doElementGetAttribute(btnQFElearning, "aria-checked");
		// btnQFElearning.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("Elearnig is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFElearning, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFElearning);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isExternalLinkDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFExteanalLink);
		return eleUtil.visibleElementWhenReady(btnQFExteanalLink, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isExternalLinkEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFExteanalLink);
		return eleUtil.isElementEnable(btnQFExteanalLink);
	}

	public void doclickExternalLinkQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFExteanalLink);

		String isChecked = eleUtil.doElementGetAttribute(btnQFExteanalLink, "aria-checked");
		// btnQFExteanalLink.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("ExteanalLink is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFExteanalLink, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFExteanalLink);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isILTDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFILT);
		return eleUtil.visibleElementWhenReady(btnQFILT, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isILTEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFILT);
		return eleUtil.isElementEnable(btnQFILT);
	}

	public void doclickILTQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFILT);

		String isChecked = eleUtil.doElementGetAttribute(btnQFILT, "aria-checked");
		// btnQFILT.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("ILT is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFILT, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFILT);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isLearningPathDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFlearningPath);
		return eleUtil.visibleElementWhenReady(btnQFlearningPath, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isLearningPathEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFlearningPath);
		return eleUtil.isElementEnable(btnQFlearningPath);
	}

	public void doclickLearningPathQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFlearningPath);

		String isChecked = eleUtil.doElementGetAttribute(btnQFlearningPath, "aria-checked");
		// btnQFlearningPath.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("Learning path is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFlearningPath, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFlearningPath);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isMsTeamDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFMSTeam);
		return eleUtil.visibleElementWhenReady(btnQFMSTeam, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMsTeamEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFMSTeam);
		return eleUtil.isElementEnable(btnQFMSTeam);
	}

	public void doclickMsTeamQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFMSTeam);

		String isChecked = eleUtil.doElementGetAttribute(btnQFMSTeam, "aria-checked");
		// btnQFMSTeam.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("MSTeam is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(btnQFMSTeam, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFMSTeam);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}

	public boolean isStatusCompleteDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkcompleteStatus);
		return eleUtil.visibleElementWhenReady(chkcompleteStatus, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isStatusCompleteEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkcompleteStatus);
		return eleUtil.isElementEnable(chkcompleteStatus);
	}


	public void selectMultipleStatus(String... statuses) {

		eleUtil.selectMultipleStatusCheckedBox(statuses);
	}

	public void selectRating(String Rating) {

		eleUtil.selectSingleCheckedBox(Rating);
	}

	public boolean isRatingsDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chk5starsRating);
		return eleUtil.visibleElementWhenReady(chk5starsRating, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isRatingsEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chk5starsRating);
		return eleUtil.isElementEnable(chk5starsRating);
	}


	public boolean isOverDueDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkOverdue);
		return eleUtil.visibleElementWhenReady(chkOverdue, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isOverDueEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkOverdue);
		return eleUtil.isElementEnable(chkOverdue);
	}

	
	public void selectDueAndOverDueDate(String Duedate) {
		eleUtil.selectSingleCheckedBox(Duedate);
	}

	public boolean isTrainingGroupSelectionDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(multiSelectionTrainingGroup);
		return eleUtil.visibleElementWhenReady(multiSelectionTrainingGroup, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isTrainingGroupSelectioEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(multiSelectionTrainingGroup);
		return eleUtil.isElementEnable(multiSelectionTrainingGroup);
	}

	public void selectTrainingGroupMultiValue(String... values) {
		eleUtil.selectKendoMultiSelect(txtTraingGroupSelection, values);
	}

	public boolean isCategorySelectionDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(multiSelectionCategory);
		return eleUtil.visibleElementWhenReady(multiSelectionCategory, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isCategorySelectionEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(multiSelectionCategory);
		return eleUtil.isElementEnable(multiSelectionCategory);
	}

	public void selectCategoryValue(String values) {

		eleUtil.selectKendoMultiSelect(txtCategorySelection, values);
	}

	public boolean isFreeDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkFree);
		return eleUtil.visibleElementWhenReady(chkFree, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isFreeEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkFree);
		return eleUtil.isElementEnable(chkOverdue);
	}
	
	public void selecPricingChk(String Pricing ) {
		
		eleUtil.selectSingleCheckedBox(Pricing);
	}

	// Sample DoClick Event for this
	public void doclickFreeInQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(chkFree);

		String isChecked = eleUtil.doElementGetAttribute(chkFree, "aria-checked");
		// chkFree.getDomAttribute("aria-checked");

		if ("true".equals(isChecked)) {
			System.out.println("Free  is Selected");
		} else {

			try {
				eleUtil.clickElementWhenReady(chkFree, AppConstants.MEDIUM_TIME_OUT);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", chkFree);
			}
		}
		eleUtil.waitForLoaderToDisappear();
	}
	
	public boolean isSearchFieldDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(txtSearchTraining);
		return eleUtil.visibleElementWhenReady(txtSearchTraining, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isSearchFieldEnable() {

		jsUtil.scrollIntoViewCenter(txtSearchTraining);
		return eleUtil.isElementEnable(txtSearchTraining);
	}
	
	public void doSearch(String Search) {
		
		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(txtSearchTraining);
		eleUtil.doSendKeys(txtSearchTraining, Search);
	}
	

	public boolean isAdvanceFilterBtnDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnAdvancedFilter);
		return eleUtil.visibleElementWhenReady(btnAdvancedFilter, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isAdvanceFilterBtnEnable() {

		jsUtil.scrollIntoViewCenter(btnAdvancedFilter);
		return eleUtil.isElementEnable(btnAdvancedFilter);
	}

	public boolean isClearAllBtnDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnClearAll);
		return eleUtil.visibleElementWhenReady(btnClearAll, AppConstants.MEDIUM_TIME_OUT);
	}

	public void doclickClearAllbtnForAdvanceFiter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnClearAll);

		try {
			eleUtil.clickElementWhenReady(btnClearAll, AppConstants.MEDIUM_TIME_OUT);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnClearAll);
		}
	}

	public void doclickAdvanceFilterBtnQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnAdvancedFilter);

		try {
			eleUtil.clickElementWhenReady(btnAdvancedFilter, AppConstants.MEDIUM_TIME_OUT);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnAdvancedFilter);
		}
	}

	public void validatAdvancedFilterClear() {
		
		Assert.assertTrue(isAdvanceFilterBtnDisplay(), "Advance Filter button  Advance Filter was not visible");
		Assert.assertTrue(isAdvanceFilterBtnEnable(), " Advance Filter button  Advance Filter  was not enabled");

		doclickAdvanceFilterBtnQuickFilter();
		
		// Phase-2 New Funcationlity  added Cover Scanarios 
		
		//String filtersCount = appliedFiltersCount.getText();
		//filtersCount = filtersCount.replaceAll("[^0-9.]", "");
		//int count = Integer.parseInt(filtersCount);
		
		//if(count>0) {
			
			Assert.assertTrue(isClearAllBtnDisplay(), "Clear All button  Advance Filter was not visible");

			doclickClearAllbtnForAdvanceFiter();

			doclickApplyButtonQuickFilter();

			eleUtil.waitForLoaderToDisappear();
			
		//}else {
			
		//	eleUtil.doClick(advanceFilterCloseicon);
		//}

	}

	public int getCardCount() {

		eleUtil.waitForLoaderToDisappear();
		// Wait until new cards loaded
		new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT))
				.until(driver -> idpCardViewList.size());
		return idpCardViewList.size();
	}

	public void waitForCardsToLoad(int previousCount) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));

		wait.until(driver -> {
			int current = idpCardViewList.size();
			return current > previousCount;
		});
	}

	public void loadAllCards() {

		eleUtil.waitForLoaderToDisappear();

		int previousCount = 0;

		while (true) {

			int currentCount = idpCardViewList.size();

			if (currentCount == previousCount) {
				break;
			}

			previousCount = currentCount;

			if (btnSeeMore.isEmpty())
				break;

			WebElement btn = btnSeeMore.get(0);

			try {
				eleUtil.waitForLoaderToDisappear();
				// jsUtil.scrollIntoViewCenter(btn);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
				try {
					eleUtil.waitForLoaderToDisappear();
					btn.click();
				} catch (Exception e) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
				}

				eleUtil.waitForLoaderToDisappear();

				waitForCardsToLoad(previousCount);

				// Wait until new cards loaded
				new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT))
						.until(driver -> idpCardViewList.size() > currentCount);
				eleUtil.waitForLoaderToDisappear();

			} catch (Exception e) {
				System.out.println("See More click failed, retrying...");
				break;
			}

		}
	}

	public void validateStatusIDPAllCards(SoftAssert softAssert) {

		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No IDP All Cards Available");
			return;
		}

		// SoftAssert softAssert = new SoftAssert();

		System.out.println("IDP ALL Total  Cards: " + idpCardViewList.size());

		for (int i = 0; i < idpCardViewList.size(); i++) {

			WebElement card = idpCardViewList.get(i);

			// CardsImage
			Boolean cardsImage = jsUtil.isImageLoaded(card.findElement(By.xpath(".//img[contains(@alt,'training')]")));

			// Training type
			String Trainingtype = card.findElement(By.xpath(".//p[contains(@aria-label,'Training type')]")).getText();

			// Training Title
			String TrainingName = card.findElement(By.xpath(".//h3")).getText();

			// Status
			String status = card.findElement(By.xpath(
					".//span[contains(text(),'Not started') or contains(text(),'In progress') or contains(text(),'Completed') or contains(text(),'Not Certified')  or contains(text(),'Recertified') or  contains(text(),'Certified') or contains(text(),'Expired')]"))
					.getText();

			// Progress
			String progress = card.findElement(By.xpath(".//p[contains(text(),'%')]")).getText();
			Boolean like = card.findElement(By.xpath(".//div[contains(@title,'Like')]")).isDisplayed();
			Boolean dislike = card.findElement(By.xpath(".//div[contains(@title,'Dislike')]")).isDisplayed();
			//Boolean viewTrainingbutton=card.findElement(By.xpath(".//button[@title='View Training Details']")).isDisplayed();
					

			// Print
			System.out.println("IDP All Cards :" + (i + 1));
			System.out.println("Training Image Display : " + cardsImage);
			System.out.println("Training Type : " + Trainingtype);
			System.out.println("Training Name : " + TrainingName);
			System.out.println("status : " + status);
			System.out.println("Progress: " + progress);

			// Assertions
			if (cardsImage == false) {
				softAssert.fail("❌ Training  Image is missing in card  " + (i + 1) + " : " + TrainingName);
			}
			if (Trainingtype.trim().isEmpty()) {
				softAssert.fail("❌ Training Type is missing in card  " + (i + 1) + " : " + TrainingName);
			}
			if (TrainingName.trim().isEmpty()) {
				softAssert.fail("❌ Training Name  is missing in card  " + (i + 1) + " : " + Trainingtype);
			}
			if (status.trim().isEmpty()) {
				softAssert.fail("❌ status is missing in card  " + (i + 1) + " : " + TrainingName);
			}
			if (progress.trim().isEmpty()) {
				softAssert.fail("❌ progress is missing in card  " + (i + 1) + " : " + TrainingName);
			}

			if (!Trainingtype.equals("Non-Sequence Training") && !Trainingtype.equals("Sequence Training")) {

				Boolean addToFavorites = card.findElement(By.xpath(".//div[contains(@title,'Favorites')]"))
						.isDisplayed();
				Boolean addToPlaylist = card.findElement(By.xpath(".//div[contains(@title,'Add To Playlist')]"))
						.isDisplayed();

				if (addToFavorites == false) {
					softAssert.fail("❌ Add To Favorites is missing in card  " + (i + 1) + " : " + TrainingName);
				}
				if (addToPlaylist == false) {
					softAssert.fail("❌ add To Playlist is missing in card  " + (i + 1) + " : " + TrainingName);
				}
			}

			if (like == false) {
				softAssert.fail("❌ like icon is missing in card  " + (i + 1) + " : " + TrainingName);
			}
			if (dislike == false) {
				softAssert.fail("❌ Dislike icon is missing in card  " + (i + 1) + " : " + TrainingName);
			}
			
			

			
			
			if (status.contains("Not started")) {
				if (progress.equals("100%")) {
					softAssert.fail("❌ Not Started but " + progress + " : " + TrainingName);
				}
			} else if (status.contains("In progress")) {
				
				boolean isILTorMSTeam = Trainingtype.equalsIgnoreCase("ILT") || Trainingtype.equalsIgnoreCase("MSTeams");

				boolean isInProgressZero = status.contains("In progress") && progress.equals("0%");
				
				if (isILTorMSTeam && isInProgressZero) {
					System.out.println("Skipping validation for ILT/MSTeam with 0% progress: "+ progress + ": " + TrainingName);
					continue;
				}
				
				if (progress.equals("100%") || (progress.equals("0%"))) {
					
					softAssert.fail("❌ In progress but " + progress + " : " + TrainingName);
				}
				
			} else if (status.contains("Completed")) {
				if (!progress.equals("100%")) {
					softAssert.fail("❌ Completed but " + progress + " : " + TrainingName);
				}
			} else if (status.contains("Not Certified")) {
				if (progress.equals("100%")) {
					softAssert.fail("❌ Not Certified but " + progress + " : " + TrainingName);
				}
			} else if (status.contains("Recertified")) {
				if (!progress.equals("0%")) {
					softAssert.fail("❌ Recertified but " + progress + " : " + TrainingName);
				}
			} else if (status.contains("Certified")) {
				if (!progress.equals("100%")) {
					softAssert.fail("❌ Certified but " + progress + " : " + TrainingName);
				}

			}

		}
		// softAssert.assertAll();
	}

	public void validateMultipleStatusCards(SoftAssert softAssert, String... expectedStatuses) {

		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No status wise IDP  Cards Available");
			return;
		}

		// SoftAssert softAssert = new SoftAssert();

		for (WebElement card : idpCardViewList) {

			String status = card.findElement(By.xpath(".//span[contains(@class,'index_in_progress_badge')]")).getText();
			String trainingName = card.findElement(By.xpath(".//h3")).getText();

			boolean match = false;

			for (String expected : expectedStatuses) {
				if (status.equalsIgnoreCase(expected)) {
					match = true;
					break;
				}
			}

			if (!match) {
				softAssert.fail("❌ Unexpected status: " + status + " | Training: " + trainingName);
			}
		}

		// softAssert.assertAll();
	}

	public void validateRatingOfCards(SoftAssert softAssert, String expectedRatingText) {

		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No Rating wise IDP  Cards Available");
			return;
		}

		expectedRatingText = expectedRatingText.replaceAll("[^0-9.]", "");
		double expectedRating = Double.parseDouble(expectedRatingText);

		for (WebElement card : idpCardViewList) {

			String ratingText = card.findElement(By.xpath(".//span[@class='rating_count']")).getText();
			ratingText = ratingText.replaceAll("[^0-9.]", "");
			double rating = Double.parseDouble(ratingText);
			String trainingName = card.findElement(By.xpath(".//h3")).getText();

			if (rating > expectedRating) {
				softAssert.fail("❌ Unexpected status: " + rating + " | Training: " + trainingName);
			}
			System.out.println("Current Cards Rating: " + rating);
		}
	}

	public LocalDate convertToDate(String dateText) {

		// String DateFormate = myprofile.getDataFormateName();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
			// "MM/dd/yyyy"
			return LocalDate.parse(dateText, formatter);

		} catch (Exception e) {
			return null; // invalid format
		}
	}

	public void validateDueAndOverdueFilter(SoftAssert softAssert, String filterType) {

		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No Due date wise IDP  Cards Available");
			return;
		}

		LocalDate today = LocalDate.now();

		for (WebElement card : idpCardViewList) {

			String trainingName = card.findElement(By.xpath(".//h3")).getText();
			String dueDateText = eleUtil.getFieldValue(card, "Due Date");
			// getDueDate(card);

			if ("MISSING".equals(dueDateText)) {
				softAssert.fail("❌ Due Date Label missing: " + trainingName);
				continue;

			} else if ("EMPTY".equals(dueDateText)) {
				softAssert.fail("❌ Due date  value missing: " + trainingName);
				continue;
			}

			LocalDate dueDate = convertToDate(dueDateText);

			if (dueDate == null) {
				softAssert.fail("❌ Invalid date format: " + trainingName);
				continue;
			}

			// Due Filter Logic
			if (filterType.equalsIgnoreCase("DUE")) {

				if (dueDate.isBefore(today)) {
					softAssert.fail("❌ Overdue card found in Due filter: " + trainingName + " | " + dueDateText);
				}
			}

			// Overdue Filter Logic
			else if (filterType.equalsIgnoreCase("OVERDUE")) {

				if (!dueDate.isBefore(today)) {
					softAssert.fail("❌ Non-overdue card found: " + trainingName + " | " + dueDateText);
				}
			}
		}
	}

	public void validateTrainingGroupFilter(SoftAssert softAssert, String... filterType) {

		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No Training Group Filter wise IDP  Cards Available");
			return;
		}

		for (WebElement card : idpCardViewList) {

			String trainingName = card.findElement(By.xpath(".//h3")).getText();
			String groupText = eleUtil.getFieldValue(card, "Group");
			// getDueDate(card);

			if ("MISSING".equals(groupText)) {
				softAssert.fail("❌ Group Label missing: " + trainingName);
				continue;

			} else if ("EMPTY".equals(groupText)) {
				softAssert.fail("❌ Group  value missing: " + trainingName);
				continue;
			}
			
			boolean match = false;

			for (String expected : filterType) {
				if (groupText.equalsIgnoreCase(expected)) {
					match = true;
					break;
				}
			}

			if (!match) {
	            softAssert.fail("❌ Group mismatch in filter. Expected: " 
	                + filterType + " but found: " + groupText 
	                + " | Training: " + trainingName);
	        }	
		}
	}
	public void validateCategoryFilter(SoftAssert softAssert, String... filterType) {

		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No Category Filter wise IDP  Cards Available");
			return;
		}

		for (WebElement card : idpCardViewList) {

			String trainingName = card.findElement(By.xpath(".//h3")).getText();
			String CategoryText = eleUtil.getFieldValue(card, "Category");
			// getDueDate(card);

			if ("MISSING".equals(CategoryText)) {
				softAssert.fail("❌ Category Label missing: " + trainingName);
				continue;

			} else if ("EMPTY".equals(CategoryText)) {
				softAssert.fail("❌ Category  value missing: " + trainingName);
				continue;
			}
			
			boolean match = false;

			for (String expected : filterType) {
				if (CategoryText.equalsIgnoreCase(expected)) {
					match = true;
					break;
				}
			}

			if (!match) {
	            softAssert.fail("❌ Category mismatch in filter. Expected: " 
	                + filterType + " but found: " + CategoryText 
	                + " | Training: " + trainingName);
	        }	
		}
	}

	public void validateTrainingTypeMultiple(SoftAssert softAssert, String... expectedTypes) {

		eleUtil.waitForLoaderToDisappear();
		
		for (WebElement card : idpCardViewList) {

			String trainingType = card.findElement(By.xpath(".//p[contains(@aria-label,'Training type')]")).getText()
					.trim();

			String trainingName = card.findElement(By.xpath(".//h3")).getText();

			boolean match = false;

			for (String type : expectedTypes) {
				if (trainingType.equalsIgnoreCase(type)) {
					match = true;
					break;
				}
			}

			if (!match) {
				softAssert.fail("❌ Unexpected Type: " + trainingType + " | Training: " + trainingName);
			}

			eleUtil.validateFieldsByType(card, trainingType, trainingName, softAssert);

		}
	}
	
	
	

}
