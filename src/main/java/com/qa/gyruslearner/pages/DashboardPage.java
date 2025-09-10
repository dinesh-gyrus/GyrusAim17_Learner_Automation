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
	
	@FindBy(css ="div.k-scrollview")
	WebElement SliderBanner;
	
	
	//WebElement slider = driver.findElement(By.cssSelector("div.k-scrollview"));
	
	
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}
	
	public String getDashBoardPageUrl() {
		String DashboardCurrentUrl = driver.getCurrentUrl();
		return DashboardCurrentUrl;
	}
	
	public String getDashBoardTitle() {

		String DashBoardTitle = eleUtil.waitForTitleIs(AppConstants.DASHBOARD_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
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
		
		return  eleUtil.doGetElementText(welcomeMsg);
	}
	
	public boolean isSliderImageDisplay() {
		
		//WebElement slider = driver.findElement(By.cssSelector("div.k-scrollview"));
		
		WebElement slider = driver.findElement(By.tagName("kendo-scrollview"));
		
		return eleUtil.isElementDisplayed(slider);
		
	}

}
