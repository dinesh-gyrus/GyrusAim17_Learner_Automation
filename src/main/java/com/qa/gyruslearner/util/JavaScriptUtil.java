package com.qa.gyruslearner.util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;

public class JavaScriptUtil extends TestBase {
	
	
	public JavaScriptUtil() {
		PageFactory.initElements(driver, this);
	}

	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//red
		for (int i = 0; i < 5; i++) {
			changeColor("rgb(0,200,0)", element);// green
			changeColor(bgcolor, element);// red
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public void goBackByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1)");
	}
	
	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0);");
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}
	
	public void generateConfirmPopUp(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("confirm('" + message + "')");
	}

	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
						 
	}

	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	
	public void scrollPageDownMiddlepage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollIntoViewCenter(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}
	
	public boolean isImageLoaded(WebElement image) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    return (Boolean) js.executeScript(
	        "return arguments[0].complete && arguments[0].naturalWidth > 0;",image);
	}
	
	public  boolean isLoginBackgroundImageLoaded(WebElement element) {
		
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    return (Boolean) js.executeScript(
	            "var style = window.getComputedStyle(arguments[0]);" +
	            "return style.backgroundImage !== 'none';",
	            element);
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
	
	public String toastMessageHandle() {
	
		
		// new Code 18-03-2026 but not Correct 
		/*
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 String toastMsg = wait.until(driver -> {
		        String msg = (String) ((JavascriptExecutor) driver).executeScript(
		            "var el = document.querySelector('.toast, [role=\"alert\"]');" +
		            "return el ? el.innerText : null;"
		        );

		        // Return only when valid message appears (not Loading)
		        if (msg != null && !msg.trim().isEmpty() && !msg.equalsIgnoreCase("Loading")) {
		            return msg;
		        }
		        return null; // keep waiting
		    });
		 
		 	System.out.println("Captured Toast: " + toastMsg);
		    return toastMsg;
		
		*/
		
		// old Code before november2025
		 
		
		 String toastMsg = (String) ((JavascriptExecutor) driver).executeScript(
				    "var el = document.querySelector('.toast, [role=\"alert\"]');" +
				    "return el ? el.innerText : null;"
				);
		 if (toastMsg == null) {
			    System.out.println("No toast message found!");
			   // return (toastMsg != null && !toastMsg.trim().isEmpty()) ? toastMsg : null;
			} else {
			    System.out.println("Toast: " + toastMsg);
			}
		 return toastMsg;
		 		 
	}
	
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
}
