package com.qa.gyruslearner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class CourseCatalogPage extends TestBase {
	
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	MyProfilePage myprofile;
	
	public CourseCatalogPage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
		myprofile = new MyProfilePage();
	}
	
	@FindBy(xpath = "//span[contains(@data-key,'menuCOURSE_CATALOG')]")
	WebElement lnkCourseCatalog;
	
	
	public String getCourseCatalogPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.COURSE_CATALOG_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String CourseCatalogCurrentUrl = driver.getCurrentUrl();
		return CourseCatalogCurrentUrl;
	}

	public String getCourseCatalogPageTitle() {

		String CourseCatalogPageTitle = eleUtil.waitForTitleIs(AppConstants.COURSE_CATALOG_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Course Catalog page title==>" + CourseCatalogPageTitle);

		return CourseCatalogPageTitle;

	}
	
	public boolean isCourseCatalogMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkCourseCatalog);
		return eleUtil.visibleElementWhenReady(lnkCourseCatalog, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isCourseCatalogMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkCourseCatalog);
		return eleUtil.isElementEnable(lnkCourseCatalog);
	}

	public void doclickCourseCatalogMenu() {

		jsUtil.scrollIntoViewCenter(lnkCourseCatalog);
		eleUtil.clickElementWhenReady(lnkCourseCatalog, AppConstants.MAX_TIME_OUT);
	}

}
