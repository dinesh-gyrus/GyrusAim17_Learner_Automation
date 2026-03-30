package com.qa.gyruslearner.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	@FindBy(id = "lbl_58")
	WebElement btnQFAssessments;

	@FindBy(id = "lbl_111")
	WebElement btnQFCertification;

	@FindBy(id = "lbl_57")
	WebElement btnQFDocument;

	@FindBy(xpath = "//button[normalize-space()='See More']")
	List<WebElement> btnSeeMore;

	@FindBy(xpath = "(//kendo-badge[contains(@themecolor,'error')])[1]")
	WebElement AdvanceFilterApplyIcon;

	@FindBy(xpath = "(//kendo-badge[contains(@themecolor,'error')])[2]")
	WebElement SortingFilterApplyIcon;

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
		try {
			eleUtil.clickElementWhenReady(btnQFAllELearning, AppConstants.MEDIUM_TIME_OUT);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnQFAllELearning);
		}
		
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
				 eleUtil.waitForLoaderToDisappear();
				 // jsUtil.scrollIntoViewCenter(btn);
				 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
				 try {
					    eleUtil.waitForLoaderToDisappear();
						btn.click();
					} catch (Exception e) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
					}
				
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

	public void validateStatusIDPAllCards() {
		
		eleUtil.waitForLoaderToDisappear();

		if (idpCardViewList.isEmpty()) {
			System.out.println("No IDP All Cards Available");
			return;
		}

		System.out.println("IDP ALL Total  Cards: " + idpCardViewList.size());

		for (int i = 0; i < idpCardViewList.size(); i++) {

			WebElement card = idpCardViewList.get(i);

			// CardsImage
			Boolean cardsImage = jsUtil.isImageLoaded(card.findElement(By.xpath(".//img[contains(@alt,'training')]")));

			// Training type
			String Trainingtype = card.findElement(By.xpath(".//p[contains(@aria-label,'Training type')]")).getText();

			// Training Title
			String TrainingName = card.findElement(By.xpath(".//h3")).getText();

			// Status
			String status = card
					.findElement(By.xpath(".//span[contains(text(),'Not started') or contains(text(),'In progress') or contains(text(),'Completed') or contains(text(),'Not Certified')  or contains(text(),'Recertified') or  contains(text(),'Certified') or contains(text(),'Expired')]")).getText();

			// Progress
			String progress = card.findElement(By.xpath(".//p[contains(text(),'%')]")).getText();

			// Print
			System.out.println("IDP All Cards :" + (i + 1));
			System.out.println("Training Image Display : " + cardsImage);
			System.out.println("Training Type : " + Trainingtype);
			System.out.println("Training Name : " + TrainingName);
			System.out.println("status : " + status);
			System.out.println("Progress: " + progress);

			// Assertions
			if (cardsImage == false) {
				throw new RuntimeException("Training  Image is missing in card " + (i + 1) + ":" + TrainingName);
			}
			if (Trainingtype.trim().isEmpty()) {
				throw new RuntimeException("Training Type is missing in card " + (i + 1) + ":" + TrainingName);
			}
			if (TrainingName.trim().isEmpty()) {
				throw new RuntimeException(" Training Name  is missing in card " + (i + 1) + ":" + Trainingtype);
			}
			if (status.trim().isEmpty()) {
				throw new RuntimeException(" status is missing in card " + (i + 1) + ":" + TrainingName);
			}
			if (progress.trim().isEmpty()) {
				throw new RuntimeException(" progress is missing in card " + (i + 1) + ":" + TrainingName);
			}

			if (status.contains("Not Started")) {
				if (progress.equals("100%")) {
					throw new RuntimeException("Status Not Started  but percentage Wrong Display: " + TrainingName);
				}
			} else if (status.contains("In progress")) {
				if (progress.equals("100%")|| (progress.equals("0%"))) {
					throw new RuntimeException("Status In progress but percentage Wrong Display: " + TrainingName);
				} 
			} else if (status.contains("Completed")) {
				if (!progress.equals("100%")) {
					throw new RuntimeException("Status Completed  but percentage Wrong Display: " + TrainingName);
				}
			}else if(status.contains("Not Certified")) {
				if (progress.equals("100%")) {

					throw new RuntimeException("Status Not Certified  but percentage Wrong Display: " + TrainingName);
				}
			}else if(status.contains("Recertified")) {
				if (!progress.equals("0%")) {

					throw new RuntimeException("Status Recertified  but percentage Wrong Display :" + TrainingName);
				}
			} else if(status.contains("Certified")) {
				if (!progress.equals("100%")) {

					throw new RuntimeException("Status Certified  but percentage Wrong Display : " + TrainingName);
				}

			}
		}
	}

}
