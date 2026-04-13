package com.qa.gyruslearner.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class LoginPage extends TestBase {

	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;

	@FindBy(xpath = "//*[@alt='Gyrus Company Logo']")
	WebElement companyLogo;

	@FindBy(xpath = "//div[contains(@style,'background-image')]")
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
	WebElement forgotPasswordlink;

	@FindBy(xpath = "//*[text()='Sign Up']")
	WebElement singUplink;

	@FindBy(xpath = "//*[text()='Invalid username or password']")
	WebElement InvalidUserNamePasswordToastmsg;

	public LoginPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
	}

	public String getLoginPageTitle() {

		String loginPageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login page title==>" + loginPageTitle);
		return loginPageTitle;
	}

	public void ensureAtLoginPage() {

		if (!driver.getCurrentUrl().contains("Login")) {
			driver.get(AppConstants.LOGIN_PAGE_URL);
			// driver.get(prop.getProperty("goliveUrl"));
			// //("https://nverma.gyrusaim.net/auth/login"); // 🔹 fallback navigation
		}
	}

	public String getLoginPageUrl() {
		String loginCurrentUrl = driver.getCurrentUrl();
		return loginCurrentUrl;
	}

	public boolean isCompanyLogoExits() {
		return eleUtil.isElementDisplayed(companyLogo);
	}
	public boolean isCompanyLogoImageLoaded() {
		
		return jsUtil.isImageLoaded(companyLogo);
	}

	public boolean isBackgroundImageExits() {
		return eleUtil.isElementDisplayed(backGroundImage);
	}

	public boolean isBackgroundImageLoadedInLogin() {
		return jsUtil.isLoginBackgroundImageLoaded(backGroundImage);
	}

	public void waitForLoaderToDisappear() {

		eleUtil.waitForLoaderToDisappear();
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
		return eleUtil.isElementDisplayed(forgotPasswordlink);
	}

	public boolean isForgotPasswordEnable() {
		return eleUtil.isElementEnable(forgotPasswordlink);
	}

	public void doclickOnForgotPasswordLinkButton() {
		eleUtil.doClick(forgotPasswordlink);
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

	public void login(String username, String password) {

		eleUtil.doSendKeys(txtUserName, username);
		eleUtil.doSendKeys(txtPassword, password);
		eleUtil.doClick(btnSignIn);
	}

	public String getToastMessage() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		return wait.until(d -> eleUtil.doGetToastMessage());

		// return eleUtil.doGetToastMessage();
	}

}
