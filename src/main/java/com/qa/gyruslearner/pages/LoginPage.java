package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class LoginPage extends TestBase {

	ElementUtil eleUtil;

	@FindBy(xpath = "//*[@alt='Gyrus Company Logo']")
	WebElement companyLogo;

	@FindBy(xpath = "//*[@class='login-container login-bg login-2']")
	WebElement backGroundImage;

	@FindBy(xpath = "(//*[text()='Login'])[2]")
	WebElement loginHeader;

	@FindBy(id = "username-field")
	WebElement txtUserName;

	@FindBy(id = "password-field")
	WebElement txtPassword;

	@FindBy(xpath = "//*[text()='SIGN IN']")
	WebElement btnSignIn;

	@FindBy(xpath = "//*[text()='Forgot Password ?']")
	WebElement frgotPasswordlink;

	@FindBy(xpath = "//*[text()='Sign Up']")
	WebElement singUplink;
	
	@FindBy(xpath = "//*[text()='Invalid username or password']")
	WebElement InvalidUserNamePasswordToastmsg;
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getLoginPageTitle() {

		String loginPageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login page title==>" + loginPageTitle);
		return loginPageTitle;
	}
	
	public void ensureAtLoginPage() {
        if (!driver.getCurrentUrl().contains("Login")) {
            driver.get("https://vspqa.gyrusaim.net/auth/login"); // 🔹 fallback navigation
        }
    }

	public String getLoginPageUrl() {
		String loginCurrentUrl = driver.getCurrentUrl();
		return loginCurrentUrl;
	}

	public boolean isCompanyLogoExits() {
		return eleUtil.isElementDisplayed(companyLogo);
	}

	public boolean isBackgroundImageExits() {
		return eleUtil.isElementDisplayed(backGroundImage);
	}

	public Boolean isLoginHederTextExits() {
		return eleUtil.isElementDisplayed(loginHeader);
	}

	public String getLoginHeadertext() {

		return eleUtil.doGetElementText(loginHeader);
	}
	
	public boolean isUsernameDisplayed() {
		
		return eleUtil.isElementDisplayed(txtUserName);
	}
	
	public boolean isUsernameEnabled() {
		return eleUtil.isElementEnable(txtUserName);
	}
	
	public boolean isPasswordDisplayed() {
		return eleUtil.isElementDisplayed(txtPassword);
	}
	
	public boolean isPasswordEnabled() {
		
		return eleUtil.isElementEnable(txtPassword);
	}
	
	public boolean isSignInButtonExits() {
		return eleUtil.isElementDisplayed(btnSignIn);
	}

	public boolean isSignInButtonEnable() {
		return eleUtil.isElementEnable(btnSignIn);
	}

	public boolean isForgotPasswordExits() {
		return eleUtil.isElementDisplayed(frgotPasswordlink);
	}
	

	public boolean isForgotPasswordEnable() {
		return eleUtil.isElementEnable(frgotPasswordlink);
	}
	
	public void doclickOnForgotPasswordLinkButton() {
		eleUtil.doClick(singUplink);
	}

	public boolean isSignUpExits() {
		return eleUtil.isElementDisplayed(singUplink);
	}

	public boolean isSignUpEnable() {
		return eleUtil.isElementEnable(singUplink);
	}
	
	public void doclickOnSignUpLinkButton() {
		eleUtil.doClick(singUplink);
	}
	

	public String getPasswordFieldEncrypted() {

		eleUtil.doSendKeys(txtPassword, "123456");
		return eleUtil.doElementGetAttribute(txtPassword, "type");
	}

	public ChangePasswordPage getUserFirstTimeLogin(String username, String pwd) {

		eleUtil.doSendKeys(txtUserName, username);
		eleUtil.doSendKeys(txtPassword, pwd);
		eleUtil.doClick(btnSignIn);
		
		return new ChangePasswordPage();
	}

	public DashboardPage doValidLogin(String username, String pwd) {
		
		eleUtil.doSendKeys(txtUserName, username);
		eleUtil.doSendKeys(txtPassword, pwd);
		eleUtil.doClick(btnSignIn);
		return new DashboardPage();
	}
	
	public void doValidUerNameWrongPass(String username, String pwd) {
		
		eleUtil.doSendKeys(txtUserName, username);
		eleUtil.doSendKeys(txtPassword, pwd);
		eleUtil.doClick(btnSignIn);
	}
	
	public String getToastMessage() {
		return eleUtil.doGetToastMessage();
	}

}
