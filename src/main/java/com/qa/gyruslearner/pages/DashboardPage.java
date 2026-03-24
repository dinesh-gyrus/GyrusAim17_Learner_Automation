package com.qa.gyruslearner.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class DashboardPage extends TestBase {

	ElementUtil eleUtil;

	@FindBy(xpath = "//*[@alt='Gyrus Logo']")
	WebElement companyLogoDashBoard;

	@FindBy(xpath = "//*[text()='Welcome To Gyrus Systems!']")
	WebElement welcomeMsg;

	@FindBy(css = "div.k-scrollview")
	WebElement SliderBanner;

	@FindBy(xpath = "//*[@title='Quick Links  ']")
	WebElement btnQuickLinks;

	@FindBy(xpath = "(//*[@title='Cart'])[1]")
	WebElement cartIcon;

	@FindBy(xpath = "//*[@title='Class Calendar']")
	WebElement calenderIcon;

	@FindBy(xpath = "//*[@title=' Notification']")
	WebElement notificationIcon;

	@FindBy(xpath = "//*[@title='Profile Menu']")
	WebElement profileIcon;

	@FindBy(xpath = "//*[@class='modal-content quick_links_main_popup']")
	WebElement quicklinkPopUp;

	@FindBy(xpath = "//*[@class='modal-content top_header_popup_cart_container']")
	WebElement cartPopUp;

	@FindBy(xpath = "//*[@class='modal-content top_header_popup_notification_container']")
	WebElement notificationPopUp;

	@FindBy(xpath = "//*[@aria-labelledby='profileMenuButton']")
	WebElement profilePopup;

	@FindBy(xpath = "//div[@title='Course Catalog']")
	WebElement quick_links_items_CourseCatalog;

	@FindBy(xpath = "//div[@title='IDP']")
	WebElement quick_links_items_IDP;

	@FindBy(xpath = "//div[@title='Training Transcript']")
	WebElement quick_links_items_TrainingTranscript;

	@FindBy(xpath = "(//*[@title='Close'])[1]")
	WebElement QuickLinksCloseIcon;

	@FindBy(xpath = "(//*[@class='heading'])[1]")
	private WebElement inProgressTrainingsCount;

	@FindBy(xpath = "//*[@title='Back to Dashboard']")
	WebElement btnBackToDashboard;

	/*
	 * @FindBy(xpath ="(//*[@class='LS-card-content'])[1]") private WebElement
	 * inProgressTrainingsCount;
	 */
	@FindBy(xpath = "(//*[@class='heading'])[2]")
	private WebElement completedTrainingsCount;

	@FindBy(xpath = "(//*[@class='heading'])[3]")
	private WebElement earnedBadgesCount;

	@FindBy(xpath = "//*[@aria-label='View statistics for this year']")
	WebElement lnkThisYear;

	@FindBy(xpath = "//*[@aria-label='View statistics for this month']")
	WebElement lnkThisMonth;

	@FindBy(xpath = "//*[@aria-label='View statistics for this week']")
	WebElement lnkThisWeek;

	@FindBy(xpath = "//*[@aria-label='Learning Status In Progress']")
	WebElement lnkLearningStatusInProgress;

	@FindBy(xpath = "//*[@aria-label='Learning Status Not Started']")
	WebElement lnkLearningStatusNotStarted;

	@FindBy(xpath = "//*[@aria-label='View all Schedule data']")
	WebElement lnkViewAllScheduleData;

	@FindBy(xpath = "//h3[contains(@class,'calendar-month')]")
	WebElement txtTodayScheduleDate;

	@FindBy(xpath = "//*[@Class='calendar-dropdown']")
	WebElement mySchedulesDatePicker;

	@FindBy(xpath = "//gyrusaim-my-schedule-widget//kendo-card")
	List<WebElement> MyScheduleList;
	

	@FindBy(xpath = "(//*[@class='col-12 col-lg-4 col-md-4 ng-star-inserted'])[1]")
	WebElement QuicklinkFirst;

	@FindBy(xpath = "(//*[@class='col-12 col-lg-4 col-md-4 ng-star-inserted'])[2]")
	WebElement QuicklinkSecond;

	@FindBy(xpath = "(//*[@class='col-12 col-lg-4 col-md-4 ng-star-inserted'])[3]")
	WebElement QuicklinkThird;

	@FindBy(xpath = "//gyrusaim-learning-status-widget//gyrusaim-cardview")
	List<WebElement> LearningStatuCards;

	@FindBy(xpath = "//gyrusaim-learning-status-widget//div[@title='Card View']")
	List<WebElement> LearnerStatusCardViewList;

	@FindBy(xpath = "//gyrusaim-learning-status-widget//*[contains(@class,'learningstatus_dataempty')]")
	List<WebElement> LearnerStatusCardEmpty;

	@FindBy(xpath = "//p[normalize-space()='Not Started']")
	WebElement lnkNotStartedbutton;

	public DashboardPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getDashBoardPageUrl() {

		String DashboardCurrentUrl = driver.getCurrentUrl();
		return DashboardCurrentUrl;
	}

	public String getDashBoardTitle() {

		String DashBoardTitle = eleUtil.waitForTitleIs(AppConstants.DASHBOARD_PAGE_TITLE,
				AppConstants.DEFAULT_TIME_OUT);
		System.out.println("DashBoard page title==>" + DashBoardTitle);
		return DashBoardTitle;

	}

	public boolean isCompnayLogoInDashBoardDisplayed() {

		return eleUtil.isElementDisplayed(companyLogoDashBoard);
	}

	public boolean isWelcomeTextDisplayed() {

		return eleUtil.isElementDisplayed(welcomeMsg);
	}

	public String getWelcomeText() {

		return eleUtil.doGetElementText(welcomeMsg);
	}

	public boolean isSliderImageDisplay() {

		// WebElement slider = driver.findElement(By.cssSelector("div.k-scrollview"));

		WebElement slider = driver.findElement(By.tagName("kendo-scrollview"));

		return eleUtil.isElementDisplayed(slider);

	}

	public boolean isQuickLinksDisplayed() {

		return eleUtil.isElementDisplayed(btnQuickLinks);
	}

	public boolean isQuickLinkEnabled() {

		return eleUtil.isElementEnable(btnQuickLinks);
	}

	public boolean isCartIconDisplayed() {

		return eleUtil.isElementDisplayed(cartIcon);
	}

	public boolean isCartIconEnabled() {

		return eleUtil.isElementEnable(cartIcon);
	}

	public boolean isCalenderIconDisplayed() {

		return eleUtil.isElementDisplayed(calenderIcon);
	}

	public boolean isCalenderIconEnabled() {

		return eleUtil.isElementEnable(calenderIcon);
	}

	public boolean isNotificationIconDisplayed() {

		return eleUtil.isElementDisplayed(notificationIcon);
	}

	public boolean isNotificationIconEnabled() {

		return eleUtil.isElementEnable(notificationIcon);
	}

	public boolean isProdfileMenuIconDisplayed() {

		return eleUtil.isElementDisplayed(profileIcon);
	}

	public boolean isProfileMenuIconEnabled() {

		return eleUtil.isElementEnable(profileIcon);
	}

	public boolean isQuickLinkItemCourseCataLogDisplayed() {

		return eleUtil.isElementDisplayed(quick_links_items_CourseCatalog);
	}

	public boolean isQuickLinkItemIDPDisplayed() {

		return eleUtil.isElementDisplayed(quick_links_items_IDP);
	}

	public boolean isQuickLinkItemTrainingTranscriptDisplayed() {

		return eleUtil.isElementDisplayed(quick_links_items_TrainingTranscript);
	}

	public boolean isQuickLinkFirstPanelDisplay() {

		return eleUtil.isElementDisplayed(QuicklinkFirst);
	}

	public boolean isQuickLinkSecondPanelDisplay() {

		return eleUtil.isElementDisplayed(QuicklinkSecond);
	}

	public boolean isQuickLinkThirdPaneltDisplay() {

		return eleUtil.isElementDisplayed(QuicklinkThird);
	}

	public void doClickOnFirstQuickLink() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(QuicklinkFirst));
		eleUtil.doClick(QuicklinkFirst);
	}

	public void doClickOnSecondQuickLink() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", QuicklinkSecond);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(QuicklinkSecond));

		try {
			eleUtil.doClick(QuicklinkSecond);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", QuicklinkSecond);
		}
	}

	public void doClickOnThirdQuickLink() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(QuicklinkThird));
		eleUtil.doClick(QuicklinkThird);
	}

	public void doClickOnQuicklinks() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(btnQuickLinks));
		eleUtil.doClick(btnQuickLinks);
	}

	public boolean isQuicklinkPopUpDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		try {
			wait.until(ExpectedConditions.visibilityOf(quicklinkPopUp));
			return eleUtil.isElementDisplayed(quicklinkPopUp);
		} catch (StaleElementReferenceException e) {

			System.out.println("Quick Link Popup went stale.");
			return false;
		}
	}

	public void doClickQuickCloseIcon() {

		eleUtil.doClick(QuickLinksCloseIcon);
	}

	public void doclickOnCarticon() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
		eleUtil.doClick(cartIcon);
	}

	public boolean isCartPopUpDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		try {
			wait.until(ExpectedConditions.visibilityOf(cartPopUp)); //

			return eleUtil.isElementDisplayed(cartPopUp);
		} catch (StaleElementReferenceException e) {

			System.out.println("Cart Popup went stale.");
			return false;
		}
	}

	public void doClickOnCalender() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(calenderIcon));
		eleUtil.doClick(calenderIcon);
	}

	public void doclickNotificationIcon() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(notificationIcon));
		eleUtil.doClick(notificationIcon);
	}

	public boolean isNotificationPopUpDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		try {
			wait.until(ExpectedConditions.visibilityOf(notificationPopUp)); //

			return eleUtil.isElementDisplayed(notificationPopUp);
		} catch (StaleElementReferenceException e) {

			System.out.println("Notification Popup went stale.");
			return false;
		}
	}

	public void doclickProfileIcon() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
		eleUtil.doClick(profileIcon);
	}

	public boolean isProfilePopUpDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		try {
			wait.until(ExpectedConditions.visibilityOf(profilePopup)); //

			return eleUtil.isElementDisplayed(profilePopup);
		} catch (StaleElementReferenceException e) {

			System.out.println("profile Popup went stale.");
			return false;
		}
	}

	public void doPressKeyEscape() {

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}

	public int getInProgressTrainingsCount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(inProgressTrainingsCount));
		String text = inProgressTrainingsCount.getText().trim(); // "0\nIn Progress Trainings"
		String numberOnly = text.split("\n")[0].trim(); // "0"
		return Integer.parseInt(numberOnly);
	}

	public int getCompletedTrainingsCount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(completedTrainingsCount));
		String text = completedTrainingsCount.getText().trim();
		String numberOnly = text.split("\n")[0].trim();
		return Integer.parseInt(numberOnly);
	}

	public int getEarnedBadgesCount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(earnedBadgesCount));
		String text = earnedBadgesCount.getText().trim();
		String numberOnly = text.split("\n")[0].trim();
		return Integer.parseInt(numberOnly);
	}

	public void doclickOnThisYear() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(lnkThisYear));
		eleUtil.doClick(lnkThisYear);
	}

	public void doclickOnThisMonth() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(lnkThisMonth));
		eleUtil.doClick(lnkThisMonth);
	}

	public void doclickOnThisWeek() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(lnkThisWeek));
		eleUtil.doClick(lnkThisWeek);
	}

	public int learningStatusGetCardCount() {

		System.out.println("Learner Status Cards found:" + LearnerStatusCardViewList.size());
		return LearnerStatusCardViewList.size();
	}

	public void doClickOnBrowserBackButton() {

		driver.navigate().back();
	}

	public String isLearningCardsDisplayed() {

		/*
		 * if (!LearnerStatusCardViewList.isEmpty()) {
		 * //System.out.println("Learner Status Cards found: " +
		 * LearnerStatusCardViewList.size()); return true; }
		 * 
		 * if (!LearnerStatusCardEmpty.isEmpty()) {
		 * System.out.println("No Data displayed"); return false; }
		 * 
		 * return false;
		 */

		if (!LearnerStatusCardViewList.isEmpty())
			return "CARDS_PRESENT";
		if (!LearnerStatusCardEmpty.isEmpty())
			return "NO_DATA";

		return "UNKNOWN";

	}

	public void validateLearningInProgressCards() {

		if (LearnerStatusCardViewList.isEmpty()) {
			System.out.println("No Cards Available");
			return;
		}

		System.out.println("Learner Status In-Progress Total Cards: " + LearnerStatusCardViewList.size());

		for (int i = 0; i < LearnerStatusCardViewList.size(); i++) {

			WebElement card = LearnerStatusCardViewList.get(i);

			// Training type
			String Trainingtype = card.findElement(By.xpath(".//p[contains(@aria-label,'Training type')]")).getText();

			// Title
			String title = card.findElement(By.xpath(".//h3")).getText();

			// Status
			String status = card.findElement(By.xpath(".//span[contains(text(),'In progress')]")).getText();
			// .findElement(By.xpath(".//span[contains(text(),'In progress') or
			// contains(text(),'Not Started')]"))

			// Progress
			String progress = card.findElement(By.xpath(".//p[contains(text(),'%')]")).getText();

			// Print

			System.out.println("In-Progress Card: " + (i + 1));
			System.out.println("Training Type: " + Trainingtype);
			System.out.println("Title: " + title);
			System.out.println("Status: " + status);
			System.out.println("Progress: " + progress);

			// Assertions

			if (Trainingtype.trim().isEmpty()) {
				throw new RuntimeException("Training Type is missing in card " + (i + 1));
			}
			if (title.trim().isEmpty()) {
				throw new RuntimeException("Title is missing in card " + (i + 1));
			}

			if (status.trim().isEmpty()) {
				throw new RuntimeException("Status is missing in card " + (i + 1));
			}

			if (progress.trim().isEmpty()) {
				throw new RuntimeException("Progress is missing in card " + (i + 1));
			}

		}
	}

	public boolean isLeanerNotStartedButtonDisplay() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				lnkNotStartedbutton);
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(lnkNotStartedbutton));
		return eleUtil.isElementDisplayed(lnkNotStartedbutton);
	}

	public void doclickLearnerStatusNotStarted() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				lnkNotStartedbutton);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(lnkNotStartedbutton));

		try {
			eleUtil.doClick(lnkNotStartedbutton);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", lnkNotStartedbutton);
		}

	}

	public void validateLearningNotStartedCards() {

		if (LearnerStatusCardViewList.isEmpty()) {
			System.out.println("No Cards Available");
			return;
		}

		System.out.println(" Learner Status Not-Started Total Cards: " + LearnerStatusCardViewList.size());

		for (int i = 0; i < LearnerStatusCardViewList.size(); i++) {

			WebElement card = LearnerStatusCardViewList.get(i);

			// Training type
			String Trainingtype = card.findElement(By.xpath(".//p[contains(@aria-label,'Training type')]")).getText();

			// Title
			String title = card.findElement(By.xpath(".//h3")).getText();

			// Status
			String status = card.findElement(By.xpath(".//span[contains(text(),'Not Started')]")).getText();
			// .findElement(By.xpath(".//span[contains(text(),'In progress') or
			// contains(text(),'Not Started')]"))

			// Progress
			String progress = card.findElement(By.xpath(".//p[contains(text(),'%')]")).getText();

			// Print

			System.out.println("Not Started Card :" + (i + 1));
			System.out.println("Training Type: " + Trainingtype);
			System.out.println("Title: " + title);
			System.out.println("Status: " + status);
			System.out.println("Progress: " + progress);

			// Assertions

			if (Trainingtype.trim().isEmpty()) {
				throw new RuntimeException("Training Type is missing in card " + (i + 1));
			}
			if (title.trim().isEmpty()) {
				throw new RuntimeException("Title is missing in card " + (i + 1));
			}

			if (status.trim().isEmpty()) {
				throw new RuntimeException("Status is missing in card " + (i + 1));
			}

			if (progress.trim().isEmpty()) {
				throw new RuntimeException("Progress is missing in card " + (i + 1));
			}

		}
	}

	public boolean isMySheduleViewDetailsDisplay() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				lnkViewAllScheduleData);
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(lnkViewAllScheduleData));
		return eleUtil.isElementDisplayed(lnkViewAllScheduleData);
	}

	public boolean isMySheduleDatePickerDisplay() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				mySchedulesDatePicker);
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(mySchedulesDatePicker));

		return eleUtil.isElementDisplayed(mySchedulesDatePicker);
	}

	public boolean isMySheduleTodayDateDisplay() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				txtTodayScheduleDate);
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(txtTodayScheduleDate));
		return eleUtil.isElementDisplayed(txtTodayScheduleDate);
	}

	public String getMyScheduleTodayDate() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(txtTodayScheduleDate));
		String text = txtTodayScheduleDate.getText().trim();
		// String numberOnly = text.split("\n")[0].trim();
		return text;
	}

	public String getCurrentdate() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		return LocalDate.now().format(formatter);

	}
	
	public void validateMySheduleTodayList() {

		if (MyScheduleList.isEmpty()) {
			System.out.println("No Cards Available");
			return;
		}

		System.out.println(" My Schedule Total List: " + MyScheduleList.size());

		for (int i = 0; i < MyScheduleList.size(); i++) {

			WebElement card = MyScheduleList.get(i);

			// Training type
			String Trainingtype = card.findElement(By.xpath(".//label")).getText();

			// Title
			 String title = card.findElement(By.xpath(".//span[contains(@class,'timeline_title')]")).getText();

			// Button name 
			 String ButtonText = card.findElement(By.xpath(".//button[contains(@title,'Dive In')]")).getText();
			
			// Time 
			//String Time = card.findElement(By.xpath(".//span[contains(@class,'k-timeline-date')]")).getText();

			// Print

			System.out.println("My Schedule Card :" + (i + 1));
			System.out.println("Training Type: " + Trainingtype);
			System.out.println("Title: " + title);
			System.out.println("Button Name: " + ButtonText);
			

			// Assertions

			if (Trainingtype.trim().isEmpty()) {
				throw new RuntimeException("Training Type is missing in card " + (i + 1));
			}
			
			if (title.trim().isEmpty()) {
				throw new RuntimeException("Title is missing in card " + (i + 1));
			}
			
			
			if (ButtonText.trim().isEmpty()) {
				throw new RuntimeException("ButtonText is missing in card " + (i + 1));
			}
			

		}
	}
	

}
