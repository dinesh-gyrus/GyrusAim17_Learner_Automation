package com.qa.gyruslearner.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "//*[@title='Notification']")
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
	
	@FindBy(xpath ="(//*[@class='heading'])[1]")
	private WebElement  inProgressTrainingsCount;
	
	/*
	@FindBy(xpath ="(//*[@class='LS-card-content'])[1]")
	private WebElement  inProgressTrainingsCount; 
	*/
	@FindBy(xpath ="(//*[@class='heading'])[2]")
	private WebElement   completedTrainingsCount;
	
	@FindBy(xpath ="(//*[@class='heading'])[3]")
	private WebElement   earnedBadgesCount;
	
	@FindBy(xpath ="//*[@aria-label='View statistics for this year']")
	WebElement lnkThisYear;
	
	@FindBy(xpath ="//*[@aria-label='View statistics for this month']")
	WebElement lnkThisMonth;
	
	@FindBy(xpath ="//*[@aria-label='View statistics for this week']")
	WebElement lnkThisWeek;
	
	@FindBy(xpath ="//*[@aria-label='Learning Status In Progress']")
	WebElement lnkLearningStatusInProgress;
	
	@FindBy(xpath ="//*[@aria-label='Learning Status Not Started']")
	WebElement lnkLearningStatusNotStarted;
	
	@FindBy(xpath="//*[@aria-label='View all Schedule data']")
	WebElement lnkViewAllScheduleData;
	
	@FindBy(xpath = "//*[@class='calendar-month m-0']")
	WebElement txtTodayScheduleDate;
	
	@FindBy(id = "curDate")
	WebElement mySchedulesDatePicker;
	
	
	//@FindBy(xpath = "(//gyrusaim-cardview)[1]")
	//List<WebElement> learningCards;
	
	
	
	

	
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
		
		 return	eleUtil.isElementDisplayed(quick_links_items_CourseCatalog);
	}

	public boolean  isQuickLinkItemIDPDisplayed() {
			
	   return eleUtil.isElementDisplayed(quick_links_items_IDP);
	}

	public boolean isQuickLinkItemTrainingTranscriptDisplayed() {

		return	eleUtil.isElementDisplayed(quick_links_items_TrainingTranscript);
	}

	public void doClickOnQuicklinks() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
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
		    String numberOnly = text.split("\n")[0].trim();          //  "0"
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
		
		List<WebElement> cards = driver.findElements(By.xpath("//gyrusaim-cardview"));
		//System.out.println("Total cards found: " + cards.size());
		return cards.size();
	}
	
}
