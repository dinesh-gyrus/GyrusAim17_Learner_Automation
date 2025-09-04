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
	
	@FindBy(id ="NewPassword")
	WebElement txtNewPassword;
	
	@FindBy(id ="CPassword")
	WebElement txtConfirmPassword;
	
	@FindBy(id="btnSubmit")
	WebElement btnResetpassword;
	
	
	
	
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
	
	
	public void doClickOnSignOut() {
		
		eleUtil.waitForElementVisible(btnSignOut,15).click();
		//eleUtil.doClick(btnSignOut);
	}
	
	public String getChangePasswordPanelTitle() {
		
		return changePasswordPanelTitle.getText();
	}
	
	public void getNewChangePasswordWithValidInputs() {
		
		eleUtil.doSendKeys(txtNewPassword, "123456");
		eleUtil.doSendKeys(txtConfirmPassword,"123456");
		eleUtil.doClick(btnResetpassword);
		
	}
	
}
