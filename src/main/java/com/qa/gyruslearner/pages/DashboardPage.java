package com.qa.gyruslearner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	@FindBy(xpath = "//*[@title='Quick Links ']")
	WebElement btnQuickLinks;

	@FindBy(xpath = "(//*[@title='Cart'])[1]")
	WebElement cartIcon;

	@FindBy(xpath = "//*[@title='Class Calendar']")
	WebElement calenderIcon;

	@FindBy(xpath = "//*[@title='Notification']")
	WebElement notificationIcon;

	@FindBy(xpath = "//*[@class='user_profile_img']")
	WebElement profileIcon;
	
	@FindBy(xpath = "//*[@class='modal-content quick_links_main_popup']")
	WebElement quicklinkPopUp;
	
	// WebElement slider = driver.findElement(By.cssSelector("div.k-scrollview"));

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
		System.out.println("Login page title==>" + DashBoardTitle);
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
	
	public void doClickOnQuicklinks() {
		
		eleUtil.clickElementWhenReady(btnQuickLinks, AppConstants.MEDIUM_TIME_OUT);
	}
	
	public boolean isQuicklinkPopUpDisplayed() {
		
	   return eleUtil.isElementDisplayed(quicklinkPopUp);
	}

}
