package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class ForgotPasswordPage extends TestBase {

	ElementUtil eleUtil;

	@FindBy(xpath = "//*[@title='Back to Login']")
	WebElement btnBackToLogin;

	@FindBy(xpath = "//*[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//*[@value='getPassword']")
	WebElement rdPassword;

	@FindBy(xpath = "//*[@value='getUserName']")
	WebElement rdUserName;

	@FindBy(id = "Username")
	WebElement txtUserName;

	@FindBy(xpath = "//*[@ng-model='txtSecurityAns1']")
	WebElement txtSecurityQue1;

	@FindBy(xpath = "//*[@ng-model='txtSecurityAns2']")
	WebElement txtSecurityQue2;

	@FindBy(xpath = "//*[@ng-model='txtSecurityAns3']")
	WebElement txtSecurityQue3;
	
	@FindBy(id = "Email")
	WebElement txtEmail;

	public ForgotPasswordPage() {

		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getForgotPasswordPageUrl() {

		String ForgotPassCurrentUrl = driver.getCurrentUrl();
		return ForgotPassCurrentUrl;
	}

	public String getForgotPasswordPageTitle() {

		String ForgotPassPageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,
				AppConstants.DEFAULT_TIME_OUT);
		System.out.println("ForgotPassword page title==>" + ForgotPassPageTitle);
		return ForgotPassPageTitle;
	}

	public boolean isBackToLoginExits() {

		return eleUtil.isElementDisplayed(btnBackToLogin);
	}

	public boolean isBackToLoginEnable() {

		return eleUtil.isElementEnable(btnBackToLogin);
	}

	public boolean isContinueExits() {

		return eleUtil.isElementDisplayed(btnContinue);
	}

	public boolean isContinueEnable() {

		return eleUtil.isElementEnable(btnContinue);
		// eleUtil.doElementGetAttribute(btnContinue, getForgotPasswordPageTitle())
	}

	public boolean isRadioPasswordExits() {

		return eleUtil.isElementDisplayed(rdPassword);
	}

	public boolean isRadioUserNameExits() {

		return eleUtil.isElementDisplayed(rdUserName);
	}

	public void doClickOnBackTologin() {

		eleUtil.doClick(btnBackToLogin);
	}

	public void doClickOnRadioPassword() {
		eleUtil.doClick(rdPassword);
	}

	public void doClickOnRadioUserName() {
		eleUtil.doClick(rdUserName);
	}

	public boolean isUserNameExits() {

		return eleUtil.isElementDisplayed(txtUserName);
	}

	public boolean isSecuiryQ1Exits() {

		return eleUtil.isElementDisplayed(txtSecurityQue1);
	}
	public boolean isSecuiryQ2Exits() {

		return eleUtil.isElementDisplayed(txtSecurityQue2);
	}
	public boolean isSecuiryQ3Exits() {

		return eleUtil.isElementDisplayed(txtSecurityQue3);
	}
	
	public boolean isEmailExits() {
		
		return eleUtil.isElementDisplayed(txtEmail);
	}
	
	public boolean  isEmailEnable() {
		
		return eleUtil.isElementEnable(txtEmail);
	}
	
	public void doClickOnContinue() {
		
		eleUtil.doClick(btnContinue);
	}
	
	public void doRefreshCurrentPage() {
		
		driver.navigate().refresh();
	}
	
	public void getValidationForEmptyUsername() {
		eleUtil.doSendKeys(txtSecurityQue1, "Test1");
		eleUtil.doSendKeys(txtSecurityQue2, "Test2");
		eleUtil.doSendKeys(txtSecurityQue3, "Test3");
		eleUtil.doClick(btnContinue);
	}
	
	public void getInvalidUsername() {
		eleUtil.doSendKeys(txtUserName, "TTeam59999");
		eleUtil.doSendKeys(txtSecurityQue1, "Test1");
		eleUtil.doSendKeys(txtSecurityQue2, "Test2");
		eleUtil.doSendKeys(txtSecurityQue3, "Test3");
		eleUtil.doClick(btnContinue);
	}
	
	public void getValidationEmptySecurityQ(){
		
		eleUtil.doSendKeys(txtUserName, "TTeam");
		eleUtil.doSendKeys(txtSecurityQue1, "test1");
		eleUtil.doSendKeys(txtSecurityQue2, "test2");
		eleUtil.doClick(btnContinue);
	}
	public void getWrongSecurityQ() {
		
		eleUtil.doSendKeys(txtUserName,"TTeam");
		eleUtil.doSendKeys(txtSecurityQue1,"test1");
		eleUtil.doSendKeys(txtSecurityQue2,"test2");
		eleUtil.doSendKeys(txtSecurityQue3,"WrongAnsw");
		eleUtil.doClick(btnContinue);
	}
	
	public ChangePasswordPage getUsernameAndCorrectAnswer() {
		
		eleUtil.doSendKeys(txtUserName,"TTeam");
		eleUtil.doSendKeys(txtSecurityQue1,"test1");
		eleUtil.doSendKeys(txtSecurityQue2,"test2");
		eleUtil.doSendKeys(txtSecurityQue3,"test3");
		eleUtil.doClick(btnContinue);
		
		return new ChangePasswordPage();
	}
	
	public void getUnRegisterEmail() {
		eleUtil.doSendKeys(txtEmail, "test@gmail.com");
		eleUtil.doClick(btnContinue);
	}
	
	public void getRegisterEmail() {
		eleUtil.doSendKeys(txtEmail, "test1gyrus@yopmail.com");
		eleUtil.doClick(btnContinue);
	}
	
	
	
	
	

}
