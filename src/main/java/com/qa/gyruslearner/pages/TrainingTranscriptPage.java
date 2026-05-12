package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class TrainingTranscriptPage extends TestBase {
	
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;
	
	public TrainingTranscriptPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}
	
	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuTRAINING_TRANSCRIPT')]")
	WebElement lnkTrainingTranscript;
	
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
	
	public String getTrainingTranscriptPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.TRAINING_TRANSCRIPT_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String TrainingTranscriptCurrentUrl = driver.getCurrentUrl();
		return TrainingTranscriptCurrentUrl;
	}

	public String getTrainingTranscriptPageTitle() {

		String trainingTranscriptPageTitle = eleUtil.waitForTitleIs(AppConstants.TRAINING_TRANSCRIPT_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Training Transcript page title==>" + trainingTranscriptPageTitle);

		return trainingTranscriptPageTitle;

	}
	
	public boolean isTrainingTranscriptMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkTrainingTranscript);
		return eleUtil.visibleElementWhenReady(lnkTrainingTranscript, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isTrainingTranscriptMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkTrainingTranscript);
		return eleUtil.isElementEnable(lnkTrainingTranscript);
	}

	public void doclickTrainingTranscriptSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkTrainingTranscript);
		eleUtil.clickElementWhenReady(lnkTrainingTranscript, AppConstants.MAX_TIME_OUT);
	}
	

}
