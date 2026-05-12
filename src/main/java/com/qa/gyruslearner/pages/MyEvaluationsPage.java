package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class MyEvaluationsPage extends TestBase {

	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;
	
	public MyEvaluationsPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}
	
	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuTAKE_EVALUATION')]")
	WebElement lnkMyEvaluations;
	
	
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
	
	public String getMyEvaluationsPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.MYEVALUATIONS_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String myEvaluationsCurrentUrl = driver.getCurrentUrl();
		return myEvaluationsCurrentUrl;
	}

	public String getMyEvaluationsPageTitle() {

		String myEvaluationsTitle = eleUtil.waitForTitleIs(AppConstants.MYEVALUATIONS_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("My Evaluations page title==>" + myEvaluationsTitle);
		return myEvaluationsTitle;

	}
	
	public boolean isMyEvaluationsMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkMyEvaluations);
		return eleUtil.visibleElementWhenReady(lnkMyEvaluations, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMyEvaluationsMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkMyEvaluations);
		return eleUtil.isElementEnable(lnkMyEvaluations);
	}

	public void doclickMyEvaluationsSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkMyEvaluations);
		eleUtil.clickElementWhenReady(lnkMyEvaluations, AppConstants.MAX_TIME_OUT);
	}
	
}
