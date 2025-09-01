package com.qa.gyruslearner.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class LoginPage extends TestBase {

	ElementUtil eleUtil;

	public LoginPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}

	public String getLoginPageTitle() {

		String loginPageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login page title==>" + loginPageTitle);
		return loginPageTitle;

	}

	public String getLoginPageUrl() {
		String loginCurrentUrl= driver.getCurrentUrl();
	    return loginCurrentUrl;
	}
		
}
