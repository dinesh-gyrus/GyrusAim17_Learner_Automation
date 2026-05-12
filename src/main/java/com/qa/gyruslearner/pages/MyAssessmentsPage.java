package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class MyAssessmentsPage extends TestBase {

	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;
	
	public MyAssessmentsPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}
	
	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuTAKE_ASSESSMENT')]")
	WebElement lnkMyAssessments;
	
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
	
	public String getMyAssessmentsPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.MYASSESSMENTS_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String myAssessmentsCurrentUrl = driver.getCurrentUrl();
		return myAssessmentsCurrentUrl;
	}

	public String getMyAssessmentsPageTitle() {

		String myAssessmentsTitle = eleUtil.waitForTitleIs(AppConstants.MYASSESSMENTS_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("My Assessments page title==>" + myAssessmentsTitle);
		return myAssessmentsTitle;

	}
	
	public boolean isMyAssessmentsMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkMyAssessments);
		return eleUtil.visibleElementWhenReady(lnkMyAssessments, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMyAssessmentsMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkMyAssessments);
		return eleUtil.isElementEnable(lnkMyAssessments);
	}

	public void doclickMyAssessmentsSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkMyAssessments);
		eleUtil.clickElementWhenReady(lnkMyAssessments, AppConstants.MAX_TIME_OUT);
	}
	
}
