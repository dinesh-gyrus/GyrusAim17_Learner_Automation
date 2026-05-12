package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class MyEnrollmentPage extends TestBase {
	
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;
	
	
	public MyEnrollmentPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}
	
	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuMyENROLLMENT')]")
	WebElement lnkMyEndrolment;
	
	
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

	public String getMyEndrolmentPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.MYENROLLMENT_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String myEnrollmentsCurrentUrl = driver.getCurrentUrl();
		return myEnrollmentsCurrentUrl;
	}

	public String getMyEndrolmentPageTitle() {

		String myEnrollmentsPageTitle = eleUtil.waitForTitleIs(AppConstants.MYENROLLMENT_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("My Enrollments page title==>" + myEnrollmentsPageTitle);

		return myEnrollmentsPageTitle;

	}

	public boolean isMyEndrollmentMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkMyEndrolment);
		return eleUtil.visibleElementWhenReady(lnkMyEndrolment, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMyEndrollmentMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkMyEndrolment);
		return eleUtil.isElementEnable(lnkMyEndrolment);
	}

	public void doclickMyEndrollmentSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkMyEndrolment);
		eleUtil.clickElementWhenReady(lnkMyEndrolment, AppConstants.MAX_TIME_OUT);
	}
	

}
