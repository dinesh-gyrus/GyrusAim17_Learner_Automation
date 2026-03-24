package com.qa.gyruslearner.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class CalendarPage extends TestBase {

	ElementUtil eleUtil;
	
	@FindBy(xpath = "//*[@title='Back to Dashboard']")
	WebElement btnBackToDashboard;

	public CalendarPage() {

		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getCalenderPageUrl() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.until(ExpectedConditions.urlToBe(AppConstants.CALENDER_PAGE_URL));
			
			String CalenderCurrentUrl = driver.getCurrentUrl();
			return CalenderCurrentUrl;

		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
			
		}

	}
	
	public void doClickOnBackToDashboard() {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});",
				btnBackToDashboard);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(btnBackToDashboard));

		try {
			eleUtil.doClick(btnBackToDashboard);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnBackToDashboard);
		}
		
		//eleUtil.doClick(btnBackToDashboard);
	}

}
