package com.qa.gyruslearner.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;

public class DashboardPage extends TestBase {

	ElementUtil eleUtil;
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}
	
	public String getDashBoardPageUrl() {
		String DashboardCurrentUrl = driver.getCurrentUrl();
		return DashboardCurrentUrl;
	}
	
	public String getDashBoardTitle() {

		String DashBoardTitle = eleUtil.waitForTitleIs(AppConstants.DASHBOARD_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login page title==>" + DashBoardTitle);
		return DashBoardTitle;

	}
	
	
	
	
}
