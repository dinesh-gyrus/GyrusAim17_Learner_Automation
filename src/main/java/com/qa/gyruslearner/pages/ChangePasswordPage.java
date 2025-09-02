package com.qa.gyruslearner.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.util.ElementUtil;

public class ChangePasswordPage extends TestBase {
	
	ElementUtil eleUtil;
	
	
	public ChangePasswordPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}
	
	
	public String getLoginPageUrl() {
		
		String changepPasswordCurrentUrl = driver.getCurrentUrl();
		return changepPasswordCurrentUrl;
	}
	
	
	
}
