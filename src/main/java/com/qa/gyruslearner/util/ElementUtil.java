package com.qa.gyruslearner.util;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;

public class ElementUtil extends TestBase {

	private JavaScriptUtil jsUtil;

	public ElementUtil() {
		PageFactory.initElements(driver, this);
		jsUtil = new JavaScriptUtil();
	}

	private void nullBlankCheck(String value) {
		if (value == null || value.length() == 0) {
			System.out.println("Element Value is  :" + value);
		}
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			break;
		}

		return locator;

	}

	private void checkHighlight(WebElement element) {
		if (Boolean.parseBoolean(prop.getProperty("highlight"))) {
			jsUtil.flash(element);
		}
	}

	public WebElement getElement(WebElement btnloginLink) {
		WebElement element = null;

		try {
			element = btnloginLink;
			checkHighlight(element);
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page");
			e.printStackTrace();
		}

		return element;
	}

	public WebElement waitForElementVisible(WebElement locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
		// WebElement element =
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By) locator));
		checkHighlight(element);
		return element;
	}

	public void doClick(WebElement btnloginLink) {
		getElement(btnloginLink).click();
	}

	public void doSendKeys(WebElement locator, String value) {
		nullBlankCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("title is not found within : " + timeOut);
		}
		return driver.getTitle();
	}

	public boolean isElementDisplayed(WebElement locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("element is not present on the page using : " + locator);
			return false;
		}
	}

	public boolean isElementEnable(WebElement locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			System.out.println("element is not Enable on the page using : " + locator);
			return false;
		}
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		checkHighlight(element);
		return element;
	}

	public void clickElementWhenReady(WebElement locator, long timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public boolean visibleElementWhenReady(WebElement locator, long timeOut) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOf(locator)).isDisplayed();

		} catch (NoSuchElementException e) {
			System.out.println("element is not present on the page using : " + locator);
			return false;
		}
	}

	public boolean waitForUrlToBe(String expectedUrl, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		return wait.until(ExpectedConditions.urlToBe(expectedUrl));
	}

	public void waitForLoaderToDisappear() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));

		By loader = By.xpath("//gyrusaim-loader | //*[@id='loader']");

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		} catch (Exception e) {
			System.out.println("Loader not found or already disappeared");
		}
	}

	public String doGetElementText(WebElement loginHeader) {

		return getElement(loginHeader).getText();
	}

	public String doElementGetAttribute(WebElement locator, String attrName) {
		return getElement(locator).getDomAttribute(attrName);
	}
	
	public void selectKendoMultiSelect(WebElement inputLocator, String... values) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_TIME_OUT));

	    WebElement input = inputLocator;

	    for (String val : values) {

	        input.click();
	        input.clear();
	        input.sendKeys(val);

	        By option = By.xpath("//kendo-popup//li[normalize-space()='" + val + "']");

	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }
	}
	
	public String getFieldValue(WebElement card, String label) {

	    List<WebElement> elements = card.findElements(
	        By.xpath(".//*[contains(text(),'" + label + "')]"));

	    if (elements.isEmpty()) return "MISSING";

	    List<WebElement> valueEle = card.findElements(
	            By.xpath(".//*[contains(text(),'" + label + "')]/following-sibling::p[1]"));
	    
	    if(valueEle.isEmpty()) return "EMPTY";
	    
	    String value = valueEle.get(0).getText().trim();
	    
	    return value.isEmpty() ? "EMPTY" : value;
	}
	

	public boolean isElementPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}

	public String doGetToastMessage() {

		return jsUtil.toastMessageHandle();
	}

}
