package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class SignUpPage extends TestBase {

	ElementUtil eleUtil;

	@FindBy(xpath = "//*[@src='/Content/img/gyruslogo/img_gyruslogo_large.png']")
	WebElement inSignupcompnayLogo;

	@FindBy(id = "reg")
	WebElement registerText;

	@FindBy(id = "txtFirstName")
	WebElement txtFirstName;

	@FindBy(id = "txtLastName")
	WebElement txtLastName;

	@FindBy(id = "txteMail")
	WebElement txteMail;

	@FindBy(xpath = "//*[@title='Back to Login']")
	WebElement btnBackToLogin;

	@FindBy(xpath = "//*[@title='reCAPTCHA']")
	WebElement iframeCaptcha;

	@FindBy(id = "recaptcha-anchor")
	WebElement reCaptchaCheckBox;

	@FindBy(id = "btnselfregistration")
	WebElement btnRegister;

	@FindBy(xpath = "//label[normalize-space(text())='First Name is required.']") // label[contains(@ng-show,'txtFirstName')]
	WebElement firstNameErrorMsg;

	@FindBy(xpath = "//label[contains(@ng-show,'txtLastName')]")
	WebElement lastNameErrorMsg;

	@FindBy(xpath = "//label[contains(@ng-show,'txteMail')]")
	WebElement emailErrorMsg;
	
	@FindBy(id = "txtPhone")
	WebElement txtphoneNumber;
	
	@FindBy(id = "txtCompany")
	WebElement txtcompanyName;
	
	@FindBy(id = "txtRoleWithinCompany")
	WebElement txtRoleWithinCompany;
	
	
	
	

	public SignUpPage() {

		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getSignUpPageUrl() {

		String SignUpPageUrl = driver.getCurrentUrl();
		return SignUpPageUrl;
	}

	public String getSignUpPageTitle() {

		String SignUpPageTitle = eleUtil.waitForTitleIs(AppConstants.SIGNUP_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("SignUpPage page title==>" + SignUpPageTitle);

		return SignUpPageTitle;
	}

	public boolean iscompanyLogoDisplayed() {

		return eleUtil.isElementDisplayed(inSignupcompnayLogo);
	}

	public boolean isRegisterTextDisplayed() {

		return eleUtil.isElementDisplayed(registerText);
	}

	public boolean isFirstNameDisplayed() {

		return eleUtil.isElementDisplayed(txtFirstName);
	}

	public boolean isLastNameDisplayed() {

		return eleUtil.isElementDisplayed(txtLastName);
	}

	public boolean isEMailDisplayed() {

		return eleUtil.isElementDisplayed(txteMail);
	}
	
	public boolean isPhoneDisplayed() {

		return eleUtil.isElementDisplayed(txtphoneNumber);
	}
	
	public boolean isCompanyDisplayed() {

		return eleUtil.isElementDisplayed(txtcompanyName);
	}
	
	public boolean isRoleWithinCompanyDisplayed() {

		return eleUtil.isElementDisplayed(txtRoleWithinCompany);
	}

	public boolean isCaptchaDisplayed() {

		driver.switchTo().frame(iframeCaptcha);
		return eleUtil.isElementDisplayed(reCaptchaCheckBox);

	}

	public boolean isRegisterButtonDisplayed() {

		return eleUtil.isElementDisplayed(btnRegister);
	}

	public boolean isRegisterButtonEnable() {

		return eleUtil.isElementEnable(btnRegister);
	}

	public boolean isBackToLoginDisplayed() {

		return eleUtil.isElementDisplayed(btnBackToLogin);
	}

	public boolean isBackToLoginEnable() {

		return eleUtil.isElementEnable(btnBackToLogin);
	}

	public LoginPage doClickOnBackToLogin() {

		eleUtil.doClick(btnBackToLogin);

		return new LoginPage();
	}

	public boolean isFirstNameErrorMsgDisplayed() {

		return eleUtil.isElementDisplayed(firstNameErrorMsg);
	}

	public boolean isLastNameErrorMsgDisplayed() {

		return eleUtil.isElementDisplayed(lastNameErrorMsg);
	}

	public boolean isEmailErrorMsgDisplayed() {

		return eleUtil.isElementDisplayed(emailErrorMsg);
	}

	public void doClickOnRegisterButton() {

		eleUtil.clickElementWhenReady(btnRegister,1);
	}

	public void fillMandatoryFields(String firstName, String lastName, String Email,String PhoneNumber,String company,String RoleWithinCompany) {

		eleUtil.doSendKeys(txtFirstName, firstName);
		eleUtil.doSendKeys(txtLastName, lastName);
		eleUtil.doSendKeys(txteMail, Email);
		eleUtil.doSendKeys(txtphoneNumber, PhoneNumber);
		eleUtil.doSendKeys(txtcompanyName, company);
		eleUtil.doSendKeys(txtRoleWithinCompany, RoleWithinCompany);
	}

	public void getHandleRecaptchaCheckBox() {
	
		driver.switchTo().frame(iframeCaptcha);
		eleUtil.doClick(reCaptchaCheckBox);
		driver.switchTo().defaultContent();
	}

	

}
