package com.qa.gyruslearner.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class MyProfilePage extends TestBase {

	ElementUtil eleUtil;

	@FindBy(xpath = "//*[@title='Profile Menu']")
	WebElement profileIcon;

	@FindBy(xpath = "//*[text()='Profile']")
	WebElement openProfilePage;
	
	
	@FindBy(xpath ="(//*[@class='my_profile_account_details_left_container_user_profile_text']//h4)[1]" )
	WebElement profileName;
	
	@FindBy(xpath = "(//*[@class='my_profile_account_details_left_container_user_profile_text']//p)")
	WebElement jobCode;
	
	@FindBy(xpath = "//*[@aria-label='organizationLabel']")
	WebElement organizationLabel;
	
	@FindBy(xpath ="//*[@aria-label='addressLabel']")
	WebElement addressLabel;
	
	@FindBy(xpath = "//*[@aria-label='emailLabel']")
	WebElement emailLabel;
	
	@FindBy(xpath = "//*[@aria-label='phoneLabel']")
	WebElement phoneLabel;
	
	@FindBy(xpath ="//*[@alt='profileMenu']")
	WebElement profileImage;

	@FindBy(xpath ="//*[@alt='Profile QR']")
	WebElement profileQR;
	
	@FindBy(xpath = "//*[@title='Back to Dashboard']")
	WebElement backToDashBoardlnk;
	
	
	
	
	
	

	

	public MyProfilePage() {

		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public void doClickProfileIcon() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
		eleUtil.doClick(profileIcon);
	}

	public void doClickProfilePage() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(openProfilePage));
		eleUtil.doClick(openProfilePage);
	}

	public String getMyProfilePageUrl() {

		String MyProfileCurrentUrl = driver.getCurrentUrl();
		return MyProfileCurrentUrl;
	}

	public String getMyProfileTitle() {

		String myprofileTitle = eleUtil.waitForTitleIs(AppConstants.MYPROFILE_PAGE_TITLE,
				AppConstants.DEFAULT_TIME_OUT);
		System.out.println("My Profile page title==>" + myprofileTitle);
		return myprofileTitle;
	}

	public boolean isLearnerNameDisplayed() {

		return eleUtil.isElementDisplayed(profileName);
	}
	
	public boolean isJobCodeDisplayed() {

		return eleUtil.isElementDisplayed(jobCode);
	}
	
	public boolean isOrganizationNameDisplayed() {

		return eleUtil.isElementDisplayed(organizationLabel);
	}
	
	public boolean isEmailIdDisplayed() {

		return eleUtil.isElementDisplayed(emailLabel);
	}
	
	public boolean isAddressDisplayed() {

		return eleUtil.isElementDisplayed(addressLabel);
	}
	
	public boolean isPhoneDisplayed() {

		return eleUtil.isElementDisplayed(phoneLabel);
	}
	
	public boolean isProfileImageDisplayed() {

		return eleUtil.isElementDisplayed(profileImage);
	}
	
	public boolean isProfileQRDisplayed() {
		
		return eleUtil.isElementDisplayed(profileQR);		
	}
	
	public boolean isBackToDashBoardDisplayed() {
		
		return eleUtil.isElementDisplayed(backToDashBoardlnk);		
	}
	
	public void doclickOnBackToDashBoardLinkButton() {
		eleUtil.doClick(backToDashBoardlnk);
	}
	
}
