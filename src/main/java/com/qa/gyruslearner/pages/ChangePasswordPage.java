package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class ChangePasswordPage extends TestBase {

	ElementUtil eleUtil;

	@FindBy(xpath = "//*[@type='button']")
	WebElement btnSignOut;

	@FindBy(xpath = "//*[text()='Change Password']")
	WebElement changePasswordPanelTitle;

	@FindBy(id = "CurrentPassword")
	WebElement txtCurrentPassword;

	@FindBy(id = "NewPassword")
	WebElement txtNewPassword;

	@FindBy(id = "CPassword")
	WebElement txtConfirmPassword;

	@FindBy(id = "btnSubmit")
	WebElement btnResetpassword;

	@FindBy(xpath = "//*[@ng-show='ChangePasswordModel.NewPassword_SameAsCurrent == 1']")
	WebElement validationMessageForSamePwd;

	public ChangePasswordPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getChangedPassPageUrl() {

		String changepPasswordCurrentUrl = driver.getCurrentUrl();
		return changepPasswordCurrentUrl;
	}

	public String getChangedPassPageTitle() {

		String changePasswordTitle = eleUtil.waitForTitleIs("ChangePassword", AppConstants.DEFAULT_TIME_OUT);
		System.out.println("ChangePassword page title==>" + changePasswordTitle);
		return changePasswordTitle;
	}

	public boolean isCurrentPasswordDisplayed() {

		return eleUtil.isElementDisplayed(txtCurrentPassword);
	}

	public boolean isCurrentPasswordEnable() {

		return eleUtil.isElementEnable(txtCurrentPassword);
	}

	public boolean isNewPasswordDisplayed() {

		return eleUtil.isElementDisplayed(txtNewPassword);
	}

	public boolean isNewPasswordEnable() {

		return eleUtil.isElementEnable(txtNewPassword);
	}

	public boolean isConfirmPasswordDisplayed() {

		return eleUtil.isElementDisplayed(txtConfirmPassword);
	}

	public boolean isConfirmPasswordEnable() {

		return eleUtil.isElementEnable(txtConfirmPassword);

	}

	public boolean isResetPasswordDisplayed() {

		return eleUtil.isElementDisplayed(btnResetpassword);
	}

	public boolean isResetPasswordEnable() {

		return eleUtil.isElementEnable(btnResetpassword);
	}

	public boolean isSignOutDisplayed() {

		return eleUtil.isElementDisplayed(btnSignOut);
	}

	public boolean isSignOutEnable() {

		return eleUtil.isElementEnable(btnSignOut);
	}

	public void doClickOnSignOut() {

		eleUtil.waitForElementVisible(btnSignOut, 15).click();
		// eleUtil.doClick(btnSignOut);
	}

	public String getChangePasswordPanelTitle() {

		return changePasswordPanelTitle.getText();
	}

	public String getSamepasswordErrormessage() {

		return validationMessageForSamePwd.getText();
	}

	// After Forgot Password With UserName and Security Question.
	public void getNewChangePasswordWithValidInputs(String newpwd, String confirmPwd) {

		eleUtil.doSendKeys(txtNewPassword, newpwd);
		eleUtil.doSendKeys(txtConfirmPassword, confirmPwd);
		eleUtil.doClick(btnResetpassword);

	}

	public void doChangePassword(String currentpwd, String newpwd, String confirmpwd) {
		
		
		txtCurrentPassword.clear();
		txtNewPassword.clear();
		txtConfirmPassword.clear();
		eleUtil.doSendKeys(txtCurrentPassword, currentpwd);
		eleUtil.doSendKeys(txtNewPassword, newpwd);
		eleUtil.doSendKeys(txtConfirmPassword, confirmpwd);
		eleUtil.doClick(btnResetpassword);
	
	}

}
