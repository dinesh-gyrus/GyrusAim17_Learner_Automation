package com.qa.gyruslearner.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class ForgotPasswordPage extends TestBase  {
		
	ElementUtil eleUtil;
	
	public ForgotPasswordPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}
	
	
	public String getForgotPasswordPageUrl() {
		
		String ForgotPassCurrentUrl = driver.getCurrentUrl();
		return ForgotPassCurrentUrl;
	}
	
	public String getForgotPasswordPageTitle() {

		String ForgotPassPageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("ForgotPassword page title==>" + ForgotPassPageTitle);
		return ForgotPassPageTitle;
	}
	
	
}
