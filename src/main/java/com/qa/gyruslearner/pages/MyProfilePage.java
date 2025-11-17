package com.qa.gyruslearner.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.util.ElementUtil;

public class MyProfilePage extends TestBase {
	
	
	ElementUtil eleUtil;
	
	@FindBy(xpath = "//*[@title='Profile Menu']")
	WebElement profileIcon;
	
	@FindBy(xpath = "//*[text()='Profile']")
	WebElement openProfilePage;
	
	public MyProfilePage() {
		
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
	}
	
	
	public String getMyProfilePageUrl() {
		
		String MyProfileCurrentUrl = driver.getCurrentUrl();
		return MyProfileCurrentUrl;
	}
	
	
	public void doClickProfileIcon() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
		eleUtil.doClick(profileIcon);
	}
	
	public void doClickProfilePage() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(openProfilePage));
		eleUtil.doClick(openProfilePage);
	}

	

}
