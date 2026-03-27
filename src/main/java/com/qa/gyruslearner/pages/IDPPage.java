package com.qa.gyruslearner.pages;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class IDPPage extends TestBase {

	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;

	public IDPPage() {
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
	}

	@FindBy(xpath = "//span[contains(@data-key,'navMyLearning')]")
	WebElement lnkMyLearning;

	@FindBy(xpath = "//span[contains(@data-key,'menuIDP')]")
	WebElement lnkIDP;

	@FindBy(xpath = "//p[normalize-space()='Back to Dashboard']")
	WebElement lnkBackToDashboard;

	@FindBy(xpath = "//*[normalize-space()='Individual Development Plan']")
	WebElement idpHeaderPageTitle;

	@FindBy(xpath = "//div[contains(@class,'IDE-header')]//p")
	WebElement trainingcount;

	@FindBy(xpath = "//gyrusaim-cardview//div[@title='Card View']")
	List<WebElement> idpCardViewList;

	@FindBy(id = "lbl_156")
	WebElement btnQFAllELearning;

	@FindBy(xpath = "//button[normalize-space()='See More']")
	List<WebElement> btnSeeMore;

	public boolean isMyLearningMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkMyLearning);
		return eleUtil.visibleElementWhenReady(lnkMyLearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isMyLearningMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkMyLearning);
		return eleUtil.isElementEnable(lnkMyLearning);
	}

	public void doclickMyLearningSubmenu() {

		jsUtil.scrollIntoViewCenter(lnkMyLearning);
		eleUtil.clickElementWhenReady(lnkMyLearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public String getIDPPageUrl() {

		eleUtil.waitForUrlToBe(AppConstants.IDP_PAGE_URL, AppConstants.MAX_TIME_OUT);
		String idpCurrentUrl = driver.getCurrentUrl();
		return idpCurrentUrl;
	}

	public String getIDPPageTitle() {

		String IDPPageTitle = eleUtil.waitForTitleIs(AppConstants.IDP_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Individual Development Plan page title==>" + IDPPageTitle);

		return IDPPageTitle;

	}

	public boolean isIDPMenuDisplay() {

		jsUtil.scrollIntoViewCenter(lnkIDP);
		return eleUtil.visibleElementWhenReady(lnkIDP, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isIDPMenuEnable() {

		jsUtil.scrollIntoViewCenter(lnkIDP);
		return eleUtil.isElementEnable(lnkIDP);
	}

	public void doclickIDPSubMenu() {

		jsUtil.scrollIntoViewCenter(lnkIDP);
		eleUtil.clickElementWhenReady(lnkIDP, AppConstants.MAX_TIME_OUT);
	}

	public boolean isBackToDashboardDisplay() {

		jsUtil.scrollIntoViewCenter(lnkBackToDashboard);
		return eleUtil.visibleElementWhenReady(lnkBackToDashboard, AppConstants.MEDIUM_TIME_OUT);
	}

	public void doclickBackToDashboard() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(lnkBackToDashboard);
		eleUtil.clickElementWhenReady(lnkBackToDashboard, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isIDPHeaderPageTitleDisplay() {

		jsUtil.scrollIntoViewCenter(idpHeaderPageTitle);
		return eleUtil.visibleElementWhenReady(idpHeaderPageTitle, AppConstants.MEDIUM_TIME_OUT);
	}

	public String getIDPHeaderPageTitle() {

		return eleUtil.doGetElementText(idpHeaderPageTitle);
	}

	public boolean isTrainingCountDisplay() {

		jsUtil.scrollIntoViewCenter(trainingcount);
		return eleUtil.visibleElementWhenReady(trainingcount, AppConstants.MEDIUM_TIME_OUT);
	}

	public int getTotalTrainingCount() {

		eleUtil.waitForLoaderToDisappear();
		eleUtil.visibleElementWhenReady(trainingcount, AppConstants.MAX_TIME_OUT);
		
		String text = eleUtil.doGetElementText(trainingcount);
		
		return Integer.parseInt(text.replaceAll("\\D+", ""));
		
		/*
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	       
		// Wait until text is updated (not empty / not old)
	    wait.until(driver -> {
	        String text = driver.findElement(By.xpath("//div[contains(@class,'IDE-header')]//p")).getText();
	        return text != null && !text.trim().isEmpty();
	    });

	    String text = driver.findElement(By.xpath("//div[contains(@class,'IDE-header')]//p")).getText();
		*/
	}

	public boolean isAllELearningDisplay() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAllELearning);
		return eleUtil.visibleElementWhenReady(btnQFAllELearning, AppConstants.MEDIUM_TIME_OUT);
	}

	public boolean isAllELearningEnable() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAllELearning);
		return eleUtil.isElementEnable(btnQFAllELearning);
	}

	public void doclickAllELearningQuickFilter() {

		eleUtil.waitForLoaderToDisappear();
		jsUtil.scrollIntoViewCenter(btnQFAllELearning);
		eleUtil.clickElementWhenReady(btnQFAllELearning, AppConstants.MEDIUM_TIME_OUT);
		eleUtil.waitForLoaderToDisappear();
	}

	public int getCardCount() {

		eleUtil.waitForLoaderToDisappear();
		// Wait until new cards loaded
		new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT))
				.until(driver -> idpCardViewList.size());
		return idpCardViewList.size();
	}

	public void waitForCardsToLoad(int previousCount) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));

		wait.until(driver -> {
			int current = idpCardViewList.size();
			return current > previousCount;
		});
	}

	public void loadAllCards() {

		eleUtil.waitForLoaderToDisappear();

		int previousCount = 0;

		while (true) {

			int currentCount = idpCardViewList.size();

			if (currentCount == previousCount) {
				break;
			}

			previousCount = currentCount;

			if (btnSeeMore.isEmpty())
				break;

			WebElement btn = btnSeeMore.get(0);
			try {
				jsUtil.scrollIntoViewCenter(btn);
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
				eleUtil.waitForLoaderToDisappear();
				btn.click();
				eleUtil.waitForLoaderToDisappear();

				waitForCardsToLoad(previousCount);
				// Wait until new cards loaded
				new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT))
						.until(driver -> idpCardViewList.size() > currentCount);
				eleUtil.waitForLoaderToDisappear();

			} catch (Exception e) {
				System.out.println("See More click failed, retrying...");
				break;
			}

		}
	}

}
