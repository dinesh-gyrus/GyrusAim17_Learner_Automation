package com.qa.gyruslearner.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.JavaScriptUtil;

public class MyProfilePage extends TestBase {

	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	

	@FindBy(xpath = "//*[@title='Profile Menu']")
	WebElement profileIcon;

	@FindBy(xpath = "//*[@aria-label='Upload Photo']")
	WebElement btnUploadPhoto;

	@FindBy(xpath = "//*[contains(@class,'my_profile_account_user_profile_icon')]")
	WebElement imgProfileIcon;

	@FindBy(xpath = "//input[@type='file']")
	WebElement imagesChoose;

	@FindBy(xpath = "//*[@title='Upload']")
	WebElement btnUpload;

	@FindBy(xpath = "//*[text()='Profile']")
	WebElement openProfilePage;

	@FindBy(xpath = "(//*[@class='my_profile_account_details_left_container_user_profile_text']//h4)[1]")
	WebElement profileName;

	@FindBy(xpath = "(//*[@class='my_profile_account_details_left_container_user_profile_text']//p)")
	WebElement jobName;

	@FindBy(xpath = "//*[@aria-label='organizationLabel']")
	WebElement organizationLabel;

	@FindBy(xpath = "//*[@aria-label='addressLabel']")
	WebElement addressLabel;

	@FindBy(xpath = "//*[@aria-label='emailLabel']")
	WebElement emailLabel;

	@FindBy(xpath = "//*[@aria-label='phoneLabel']")
	WebElement phoneLabel;

	@FindBy(xpath = "//*[@alt='profileMenu']")
	WebElement profileImage;

	@FindBy(xpath = "//*[@alt='Profile QR']")
	WebElement profileQR;

	@FindBy(xpath = "//*[@title='Back to Dashboard']")
	WebElement backToDashBoardlnk;

	@FindBy(xpath = "//*[@title='Change Language']")
	WebElement changeLanguage;

	@FindBy(xpath = "//*[@id='flush-heading-editProfile']")
	WebElement editProfilePanel;

	@FindBy(xpath = "//*[@id='flush-collapse-editProfile']")
	WebElement editprofilePanelShow;

	@FindBy(xpath = "//*[@id='flush-heading-security']")
	WebElement securityPanel;

	@FindBy(xpath = "//*[@id='flush-collapse-security']")
	WebElement securityPanelShow;

	@FindBy(xpath = "//*[@id='flush-heading-cfrPin']")
	WebElement cfr21SecurityPanel;

	@FindBy(xpath = "//*[@id='flush-collapse-cfrPin']")
	WebElement cfr21SecurityPanelShow;

	@FindBy(xpath = "//*[@id='flush-heading-timeZone']")
	WebElement timeZoneSettingsPanel;

	@FindBy(xpath = "//*[@id='flush-collapse-timeZone']")
	WebElement timeZoneSettingsPanelShow;

	@FindBy(xpath = "//*[@id='flush-heading-theme']")
	WebElement themePanel;

	@FindBy(xpath = "//*[@id='flush-collapse-theme']")
	WebElement themePanelShow;

	@FindBy(xpath = "(//kendo-dropdownlist//button[@aria-label='Select'])[1]")
	WebElement drpChangeLanguage;

	@FindBy(xpath = "(//kendo-dropdownlist//span[contains(@class,'k-input-inner')])[1]")
	WebElement drpLanguageSelectedName;

	@FindBy(xpath = "(//kendo-dropdownlist//button[@aria-label='Select'])[2]")
	WebElement drpeDateFormat;

	@FindBy(xpath = "(//kendo-dropdownlist//span[contains(@class,'k-input-inner')])[2]")
	WebElement drpSelectedDateformat;

	@FindBy(xpath = "//kendo-dropdownlist[contains(@class,'profile_account_edit_profile_accordion_input')]")
	WebElement drpUserTimeZone;

	@FindBy(xpath = "//label[normalize-space()='User Time Zone']/parent::div//kendo-dropdownlist")
	WebElement drpSelectedUserTimezone;

	@FindBy(id = "ClientWorkingDateTime_RO")
	WebElement txtSystemDateAndTime;

	@FindBy(id = "UTCDateTime_RO")
	WebElement txtUTCDateTime;

	@FindBy(id = "allusertime")
	WebElement txtAllUsersUseSystemTimeZone;

	@FindBy(id = "PersonWorkingDateTime_RO")
	WebElement txtUserDateAndTime;

	@FindBy(xpath = "(//button[contains(@class,'my_profile_edit_profile_save_button')])[5]")
	WebElement btnTimezoneSettingSaveButton;

	@FindBy(id = "CurrentPassword")
	WebElement txtCurrentPassword;

	@FindBy(id = "NewPassword")
	WebElement txtNewPassword;

	@FindBy(id = "ConfirmPassword")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "(//*[@class='my_profile_edit_profile_save_button mt-3'])[1]")
	WebElement btnResetPasswordSave;

	@FindBy(id = "ExistingPIN")
	WebElement txtCurrentPin;

	@FindBy(id = "NewPIN")
	WebElement txtNewPin;

	@FindBy(id = "ConfirmPin")
	WebElement txtConfirmPin;

	@FindBy(xpath = "(//*[@class='my_profile_edit_profile_save_button mt-3'])[3]")
	WebElement btnCFR21SecuritySave;

	@FindBy(xpath = "//*[@class='get_color_code profile_account_theme_6_c']")
	WebElement RadiobuttonTheme8;

	@FindBy(xpath = "(//button[contains(@class,'my_profile_edit_profile_save_button')])[6]")
	WebElement btnThemeSaveButton;

	@FindBy(xpath = "//*[@role='alert']//span")
	WebElement toastMessage;
	
	List<WebElement> toastElements = driver.findElements(By.xpath("//*[@role='alert']//span"));
	
	public MyProfilePage() {

		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil();
		jsUtil = new JavaScriptUtil();
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

	public String getMyProfilePageUrl() {

		String MyProfileCurrentUrl = driver.getCurrentUrl();
		return MyProfileCurrentUrl;
	}

	public String getMyProfileTitle() {

		String myprofileTitle = eleUtil.waitForTitleIs(AppConstants.MYPROFILE_PAGE_TITLE,
				AppConstants.DEFAULT_TIME_OUT);
		System.out.println("My Profile page title==>" + myprofileTitle);
		return myprofileTitle;
	}

	public void uploadImage(String filePath) {

		imagesChoose.sendKeys(filePath);
	}

	public void doclickOnUploadPhotoButton() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});",
				backToDashBoardlnk);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(btnUploadPhoto));

		try {
			eleUtil.doClick(btnUploadPhoto);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnUploadPhoto);
		}
	}

	public void doValidUploadPhoto(String Filepath) {

		uploadImage(Filepath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MAX_TIME_OUT));
		wait.until(ExpectedConditions.elementToBeClickable(btnUpload));
		btnUpload.click();

	}

	public boolean isLearnerNameDisplayed() {

		return eleUtil.isElementDisplayed(profileName);
	}

	public boolean isJobCodeDisplayed() {

		return eleUtil.isElementDisplayed(jobName);
	}

	public boolean isOrganizationNameDisplayed() {

		return eleUtil.isElementDisplayed(organizationLabel);
	}

	public boolean isEmailIdDisplayed() {

		return eleUtil.isElementDisplayed(emailLabel);
	}

	public boolean isAddressDisplayed() {

		return eleUtil.isElementDisplayed(addressLabel);
	}

	public boolean isPhoneDisplayed() {

		return eleUtil.isElementDisplayed(phoneLabel);
	}

	public boolean isProfileImageDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		wait.until(ExpectedConditions.visibilityOf(profileImage));
		
		
		return  eleUtil.isElementDisplayed(profileImage);
	}

	public boolean isProfileQRDisplayed() {

		return eleUtil.isElementDisplayed(profileQR);
	}

	public boolean isBackToDashBoardDisplayed() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});",
				backToDashBoardlnk);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.visibilityOf(backToDashBoardlnk));
		return eleUtil.isElementDisplayed(backToDashBoardlnk);
	}

	public void doclickOnBackToDashBoardLinkButton() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});",
				backToDashBoardlnk);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.elementToBeClickable(backToDashBoardlnk));

		try {
			eleUtil.doClick(backToDashBoardlnk);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", backToDashBoardlnk);
		}

	}

	public void doClickonEditProfilePanel() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				editProfilePanel);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.elementToBeClickable(editProfilePanel));

		try {
			eleUtil.doClick(editProfilePanel);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", editProfilePanel);
		}

	}

	public boolean isEditProfilePanelDisplay() {

		return eleUtil.isElementDisplayed(editprofilePanelShow);
	}

	public void doClickonSecurityPanel() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", securityPanel);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(securityPanel));

		try {
			eleUtil.doClick(securityPanel);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", securityPanel);
		}
	}

	public boolean isSecurityPanelDisplay() {

		return eleUtil.isElementDisplayed(securityPanelShow);
	}

	public void doClickonCFR21SecurityPanel() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				cfr21SecurityPanel);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(cfr21SecurityPanel));

		try {
			eleUtil.doClick(cfr21SecurityPanel);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", cfr21SecurityPanel);
		}

	}

	public boolean isCFR21SecurityPanelDisplay() {
	
		return eleUtil.isElementDisplayed(cfr21SecurityPanelShow);
	}

	public void doClickonTimeZoneSettingsPanel() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				timeZoneSettingsPanel);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		wait.until(ExpectedConditions.elementToBeClickable(timeZoneSettingsPanel));
		wait.until(ExpectedConditions.visibilityOf(timeZoneSettingsPanel));

		try {
			eleUtil.doClick(timeZoneSettingsPanel);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", timeZoneSettingsPanel);
		}

	}

	public boolean isTimeZoneSettingsPanelDisplay() {

		return eleUtil.isElementDisplayed(timeZoneSettingsPanelShow);
	}

	public void doClickonThemePanel() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", themePanel);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(themePanel));

		try {
			eleUtil.doClick(themePanel);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", themePanel);
		}

	}

	public boolean isThemePanelDisplay() {
		return eleUtil.isElementDisplayed(themePanelShow);
	}

	public boolean isChangeLanguageDisplay() {

		return eleUtil.isElementDisplayed(drpChangeLanguage);
	}

	public String getLangaugeName() {

		return eleUtil.doGetElementText(drpLanguageSelectedName);
	}

	public boolean isDataFormateDisplay() {

		return eleUtil.isElementDisplayed(drpeDateFormat);
	}

	public String getDataFormateName() {

		return eleUtil.doGetElementText(drpSelectedDateformat);
	}

	public boolean isCurrentPasswordDisplay() {

		return eleUtil.isElementDisplayed(txtCurrentPassword);
	}

	public boolean isNewPasswordDisplay() {

		return eleUtil.isElementDisplayed(txtNewPassword);
	}

	public boolean isConfirmPasswordDisplay() {

		return eleUtil.isElementDisplayed(txtConfirmPassword);
	}

	public boolean isResetPasswordSaveButtonDisplay() {

		return eleUtil.isElementDisplayed(btnResetPasswordSave);
	}

	public boolean isResetPasswordSaveButtonEnable() {

		return eleUtil.isElementEnable(btnResetPasswordSave);
	}

	public boolean isCFR21NewPinDisplay() {

		return eleUtil.isElementDisplayed(txtNewPin);
	}

	public boolean isCFR21ConfirmPinDisplay() {

		return eleUtil.isElementDisplayed(txtConfirmPin);
	}

	public boolean isCFR21CurrentPinDisplay() {

		try {
			return eleUtil.isElementDisplayed(txtCurrentPin);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isCfr21SecuritySaveButtonDisplay() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				btnCFR21SecuritySave);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(btnCFR21SecuritySave));
		return eleUtil.isElementDisplayed(btnCFR21SecuritySave);
	}

	public boolean isCfr21SecuritySaveButtonEnable() {

		return eleUtil.isElementEnable(btnCFR21SecuritySave);
	}

	public void doValidResetPassword(String currentPwd, String newPwd, String confirmPwd) {

		eleUtil.doSendKeys(txtCurrentPassword, currentPwd);

		eleUtil.doSendKeys(txtNewPassword, newPwd);
		eleUtil.doSendKeys(txtConfirmPassword, confirmPwd);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				btnResetPasswordSave);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(btnResetPasswordSave));

		try {
			eleUtil.doClick(btnResetPasswordSave);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnResetPasswordSave);
		}
	}

	public void doValidaCFR21CurrentPin(String currentPin1) {

		if (txtCurrentPin.isDisplayed()) {
			txtCurrentPin.sendKeys(currentPin1);
		}

	}

	public void doValidCFR21Secuirty(String newPin, String confirmPin) {

		eleUtil.doSendKeys(txtNewPin, newPin);
		eleUtil.doSendKeys(txtConfirmPin, confirmPin);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				btnCFR21SecuritySave);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(btnCFR21SecuritySave));

		try {
			eleUtil.doClick(btnCFR21SecuritySave);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCFR21SecuritySave);
		}

	}

	public boolean isUserTimeZoneDisplay() {

		return eleUtil.isElementDisplayed(drpUserTimeZone);
	}

	public String getUserTimeZone() {

		return eleUtil.doGetElementText(drpSelectedUserTimezone);
	}

	public boolean isSystemDateAndTimeDisplay() {

		return eleUtil.isElementDisplayed(txtSystemDateAndTime);
	}

	public boolean isUTCDateTimeDisplay() {

		return eleUtil.isElementDisplayed(txtUTCDateTime);
	}

	public boolean isAllUsersUseSystemTimeZoneDisplay() {

		return eleUtil.isElementDisplayed(txtAllUsersUseSystemTimeZone);
	}

	public boolean isUserDateAndTimeDisplay() {

		return eleUtil.isElementDisplayed(txtUserDateAndTime);
	}

	public void selectUserTimeZone(String partialValue) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		drpUserTimeZone.click();

		By option = By.xpath("//kendo-popup[contains(@class,'k-animation-container-shown')],'" + partialValue + "')]");

		wait.until(ExpectedConditions.elementToBeClickable(option)).click();

	}

	public void doValidTimezoneSettingSave() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				btnTimezoneSettingSaveButton);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(btnTimezoneSettingSaveButton));

		try {
			eleUtil.doClick(btnTimezoneSettingSaveButton);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnTimezoneSettingSaveButton);
		}

	}

	public void dovalidThemeSave() {

		RadiobuttonTheme8.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				btnThemeSaveButton);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(btnThemeSaveButton));

		try {
			eleUtil.doClick(btnThemeSaveButton);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnThemeSaveButton);
		}
	}

	public String getToastMessage1() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		return wait.until(driver -> {

			List<WebElement> elements = driver.findElements(By.xpath("//*[@role='alert']//span"));

			for (int i = elements.size() - 1; i >= 0; i--) {

				WebElement el = elements.get(i);

				if (el.isDisplayed()) {
					String text = el.getText();

					if (text != null && !text.trim().isEmpty() && !text.contains("Loading")) {
						return text;
					}
				}
			}
			return null;
		});

	}

	public void waitForToastDisappear() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfAllElements(toastElements));
	}

	public String getToastMessage() {
		return eleUtil.doGetToastMessage();
	}

}
