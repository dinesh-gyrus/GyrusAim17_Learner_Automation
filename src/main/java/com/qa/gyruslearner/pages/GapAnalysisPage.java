package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class GapAnalysisPage extends TestBase {
	
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;
	
	public GapAnalysisPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}
	
	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuGap')]")
	WebElement lnkGapAnalysis;
	
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
	
	public String getGapAnalysisPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.GAP_ANALYSIS_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String gapAnalysisCurrentUrl = driver.getCurrentUrl();
		return gapAnalysisCurrentUrl;
	}

	public String getGapAnalysisPageTitle() {

		String gapAnalysisTitle = eleUtil.waitForTitleIs(AppConstants.GAP_ANALYSIS_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Gap Analysis page title==>" + gapAnalysisTitle);
		return gapAnalysisTitle;

	}
	
	
	public boolean isGapAnalysisMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkGapAnalysis);
		return eleUtil.visibleElementWhenReady(lnkGapAnalysis, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isGapAnalysisMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkGapAnalysis);
		return eleUtil.isElementEnable(lnkGapAnalysis);
	}

	public void doclickGapAnalysisSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkGapAnalysis);
		eleUtil.clickElementWhenReady(lnkGapAnalysis, AppConstants.MAX_TIME_OUT);
	}

}
