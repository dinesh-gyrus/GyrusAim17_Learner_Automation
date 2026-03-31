package com.qa.gyruslearner.testcases;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gyruslearner.base.TestBase;
import com.qa.gyruslearner.constants.AppConstants;
import com.qa.gyruslearner.pages.DashboardPage;
import com.qa.gyruslearner.pages.LoginPage;
import com.qa.gyruslearner.pages.MyProfilePage;
import com.qa.gyruslearner.util.ElementUtil;
import com.qa.gyruslearner.util.ExcelUtil;

public class MyProfilePageTestCase extends TestBase {

	LoginPage loginpage;
	DashboardPage dashboard;
	MyProfilePage myprofile;
	ElementUtil eleUtil;

	public MyProfilePageTestCase() {
		super();
	}
	
	@DataProvider
	public Object[][] getResetPasswordSheetData() {
		return ExcelUtil.getTestData(AppConstants.MYPROFILE_DATA_SHEET_PATH,AppConstants.RESER_PASSWORD_MYPROFILE_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getCFR21SecuritySheetData() {
		return ExcelUtil.getTestData(AppConstants.MYPROFILE_DATA_SHEET_PATH,AppConstants.CFR21_SECURITY_MYPROFILE_SHEET_NAME);
	}

	@BeforeClass
	public void setUp() {
		initialazation();
		loginpage = new LoginPage();
		dashboard = new DashboardPage();
		myprofile = new MyProfilePage();
		eleUtil = new ElementUtil();
	}

	@BeforeMethod
	public void pageRefresh() {

		// driver.navigate().refresh();

		if (driver.getCurrentUrl().equals(AppConstants.LOGIN_PAGE_URL)) {
			loginpage.getUserFirstTimeLogin(prop.getProperty("username"), prop.getProperty("password"));

			myprofile.doClickProfileIcon();
			myprofile.doClickProfilePage();

		} else if (driver.getCurrentUrl().equals(AppConstants.DASHBOARD_PAGE_URL)) {

			myprofile.doClickProfileIcon();
			myprofile.doClickProfilePage();
		}
	}

	@Test(priority = 1)
	public void myProfileUrlTest() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		try {
			wait.until(ExpectedConditions.urlToBe(AppConstants.MYPROFILE_PAGE_URL));
			String myProfileUrl = myprofile.getMyProfilePageUrl();
			Assert.assertEquals(myProfileUrl, AppConstants.MYPROFILE_PAGE_URL);
		} catch (Exception e) {
			throw new SkipException("Skipping test because user could not login.");
		}
	}

	@Test(priority = 2)
	public void myprofileTitleTest() {

		String myProfilePagetitle = myprofile.getMyProfileTitle();
		Assert.assertEquals(myProfilePagetitle, AppConstants.MYPROFILE_PAGE_TITLE);
	}

	@Test(priority = 3, enabled = true)
	public void verifyUserProfileDetails() {

		Assert.assertTrue(myprofile.isLearnerNameDisplayed(), "Learner name not visible");
		Assert.assertTrue(myprofile.isJobCodeDisplayed(), "Job code not visible");
		Assert.assertTrue(myprofile.isOrganizationNameDisplayed(), "OrganizationName not visible");
		Assert.assertTrue(myprofile.isEmailIdDisplayed(), "Email not visible");
		Assert.assertTrue(myprofile.isAddressDisplayed(), "Adsress not visible");
		Assert.assertTrue(myprofile.isPhoneDisplayed(), "Phone number not visible");
	}

	@Test(priority = 4, enabled = true)
	public void verifyProfileImage() {

		Assert.assertTrue(myprofile.isProfileImageDisplayed(), "Profile picture not displayed");
	}

	@Test(priority = 5, enabled = true)
	public void verifyQRCodeDisplayed() {

		Assert.assertTrue(myprofile.isProfileQRDisplayed(), "QR Code not displayed");
	}

	@Test(priority = 6, enabled = true)
	public void verifyEditProfilePanelExpandCollapse() {

		// Click to Expand
		myprofile.doClickonEditProfilePanel();
		Assert.assertTrue(myprofile.isEditProfilePanelDisplay(), "Edit Profile section did not expand");

		// Click again to Collapse
		myprofile.doClickonEditProfilePanel();
		Assert.assertFalse(myprofile.isEditProfilePanelDisplay(), "Edit Profile section did not collapse");

	}

	@Test(priority = 7, enabled = true)
	public void verifySecurityPanelExpandCollapse() {

		// Click to Expand
		myprofile.doClickonSecurityPanel();
		Assert.assertTrue(myprofile.isSecurityPanelDisplay(), "Security section did not expand");

		// Click again to Collapse
		myprofile.doClickonSecurityPanel();
		Assert.assertFalse(myprofile.isSecurityPanelDisplay(), "Security section did not collapse");

	}

	@Test(priority = 8, enabled = true)
	public void verifyCFR21SecurityPanelExpandCollapse() {

		// Click to Expand
		myprofile.doClickonCFR21SecurityPanel();
		Assert.assertTrue(myprofile.isCFR21SecurityPanelDisplay(), "CFR 21 Security section did not expand");

		// Click again to Collapse
		myprofile.doClickonCFR21SecurityPanel();
		Assert.assertFalse(myprofile.isSecurityPanelDisplay(), "CFR 21 Security section did not collapse");

	}

	@Test(priority = 9, enabled = true)
	public void verifyTimeZoneSettingsPanelExpandCollapse() {

		// Click to Expand
		myprofile.doClickonTimeZoneSettingsPanel();
		Assert.assertTrue(myprofile.isTimeZoneSettingsPanelDisplay(), "Time Zone Settings section did not expand");

		// Click again to Collapse
		myprofile.doClickonTimeZoneSettingsPanel();
		Assert.assertFalse(myprofile.isTimeZoneSettingsPanelDisplay(), "Time Zone Settings section did not collapse");
	}

	@Test(priority = 10, enabled = true)
	public void verifyThemePanelExpandCollapse() {

		// Click to Expand
		myprofile.doClickonThemePanel();
		Assert.assertTrue(myprofile.isThemePanelDisplay(), "Theme section did not expand");

		// Click again to Collapse
		myprofile.doClickonThemePanel();
		Assert.assertFalse(myprofile.isThemePanelDisplay(), "Theme section did not collapse");

	}

	@Test(priority = 11, enabled = true)
	public void verifyLanguageDropdown() {

		Assert.assertTrue(myprofile.isChangeLanguageDisplay(), "Change Language did not display");
		String actual = myprofile.getLangaugeName();
		Assert.assertEquals(actual, "English (United States)", "Dropdown value not selected properly");

	}

	@Test(priority = 12, enabled = true)
	public void verifyDateFormaDropdown() {

		Assert.assertTrue(myprofile.isDataFormateDisplay(), "Change Language did not display");
		String actual = myprofile.getDataFormateName();
		Assert.assertEquals(actual, "MM/DD/YYYY", "Dropdown value not selected properly");

	}

	@Test(priority = 13, enabled = true,dataProvider = "getResetPasswordSheetData")
	public void verifyResetPasswordTest(String CurrentPassword,String NewPassword, String ConfirmPassword) {

		// Click to Expand
		myprofile.doClickonSecurityPanel();
		Assert.assertTrue(myprofile.isSecurityPanelDisplay(), "Security section did not expand");

		Assert.assertTrue(myprofile.isCurrentPasswordDisplay(), "CurrentPassword did not display");
		Assert.assertTrue(myprofile.isNewPasswordDisplay(), "New Password did not display");
		Assert.assertTrue(myprofile.isConfirmPasswordDisplay(), "Confirm Password did not display");
		// Assert.assertTrue(myprofile.isResetPasswordSaveButtonDisplay(), "Reset
		// password save Button not displayed!");
		myprofile.doValidResetPassword(CurrentPassword, NewPassword, ConfirmPassword);
		// Assert.assertTrue(myprofile.isResetPasswordSaveButtonEnable(), "Reset
		// password save button not Enable!");
		eleUtil.waitForLoaderToDisappear();
		String toastMsg = myprofile.getToastMessage1();
		System.out.println("My Password Save==>" + toastMsg);
		Assert.assertEquals(toastMsg, "Password updated");
		myprofile.waitForToastDisappear();
	
	}

	// when we CurrentTextbox Visible after this Test case Will Pass
	@Test(priority = 14, enabled = true, dataProvider = "getCFR21SecuritySheetData")
	public void verifyCFR21SecurityTest(String CurrentPin,String NewPin, String ConfirmNewPin) {

		// Click to Expand
		myprofile.doClickonCFR21SecurityPanel();
		Assert.assertTrue(myprofile.isCFR21SecurityPanelDisplay(), "CFR 21 Security section did not expand");

		Assert.assertTrue(myprofile.isCFR21NewPinDisplay(), "new pin did not display");
		Assert.assertTrue(myprofile.isCFR21ConfirmPinDisplay(), "Confirm did not display");
		Assert.assertTrue(myprofile.isCfr21SecuritySaveButtonDisplay(), "CFR 21 security save Button not displayed!");
		// Assert.assertTrue(myprofile.isCfr21SecuritySaveButtonEnable(), "CFR 21 save
		// button not Enable!");

		if (myprofile.isCFR21CurrentPinDisplay()) {
			myprofile.doValidaCFR21CurrentPin(CurrentPin);
			myprofile.doValidCFR21Secuirty(NewPin, ConfirmNewPin);
		} else {
			myprofile.doValidCFR21Secuirty(NewPin, ConfirmNewPin);
		}
		eleUtil.waitForLoaderToDisappear();
		// In Toast message Read the Loader Text name like "Loading"
		String toastMsg = myprofile.getToastMessage1();
		System.out.println("My Password Save==>" + toastMsg);
		Assert.assertEquals(toastMsg, "PIN Updated Successfully");
		myprofile.waitForToastDisappear();

	}

	@Test(priority = 15, enabled = true)
	public void verifyTimeZoneSettingsDisplayTest() {

		// Click to Expand
		myprofile.doClickonTimeZoneSettingsPanel();
		Assert.assertTrue(myprofile.isTimeZoneSettingsPanelDisplay(), "Time Zone Settings section did not expand");

		Assert.assertTrue(myprofile.isUserTimeZoneDisplay(), "User Time Zone did not display");
		Assert.assertTrue(myprofile.isSystemDateAndTimeDisplay(), "System Date And Time did not display");
		Assert.assertTrue(myprofile.isUTCDateTimeDisplay(), "UTC Date Time did not display");
		Assert.assertTrue(myprofile.isAllUsersUseSystemTimeZoneDisplay(),
				"All Users Use System Time Zone did not display");
		Assert.assertTrue(myprofile.isUserDateAndTimeDisplay(), "User Date And Time did not display");

		String actual = myprofile.getUserTimeZone();
		Assert.assertEquals(actual, "(UTC-04:00) Atlantic Time (Canada)",
				"User TIme Zone Dropdown value not selected properly");
		/*
		// Click again to Collapse
		myprofile.doClickonTimeZoneSettingsPanel();
		Assert.assertFalse(myprofile.isTimeZoneSettingsPanelDisplay(), "Time Zone Settings section did not collapse");
		*/
	}

	@Test(priority = 16,dependsOnMethods = "verifyTimeZoneSettingsDisplayTest",enabled = true)
	public void verifyTimeZoneSettingsTest() {
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			
		}
		myprofile.waitForToastDisappear();
		myprofile.doValidTimezoneSettingSave();
		eleUtil.waitForLoaderToDisappear();
		// In Toast message Read the Loader Text name like "Loading"
		String toastMsg = myprofile.getToastMessage1();
		System.out.println("My Password Save==>" + toastMsg);
		Assert.assertEquals(toastMsg, "Profile setting updated.");
		myprofile.waitForToastDisappear();
	}

	@Test(priority = 17, enabled = true)
	public void verifyThemeTest() {

		// Click to Expand
		myprofile.doClickonThemePanel();
		Assert.assertTrue(myprofile.isThemePanelDisplay(), "Theme section did not expand");

		myprofile.dovalidThemeSave();
		eleUtil.waitForLoaderToDisappear();
		// In Toast message Read the Loader Text name like "Loading"
		String toastMsg = myprofile.getToastMessage1();
		System.out.println("My Password Save==>" + toastMsg);
		Assert.assertEquals(toastMsg, "Theme updated successfully");
		myprofile.waitForToastDisappear();

	}

	@Test(priority = 18, enabled = true)
	public void verifyBackToDashboardLink() {

		Assert.assertTrue(myprofile.isBackToDashBoardDisplayed(),
				"Back to Dashboard link is not displayed on the Profile page");
		myprofile.doclickOnBackToDashBoardLinkButton();

		Assert.assertEquals(dashboard.getDashBoardPageUrl(), AppConstants.DASHBOARD_PAGE_URL,
				"Dashboard page URL mismatch after clicking Back to Dashboard");
		myprofile.doClickProfileIcon();
		myprofile.doClickProfilePage();
	}

	@Test(priority = 19, enabled = true)
	public void VerifyUploadhphotoTest() {

		myprofile.doclickOnUploadPhotoButton();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\imagesUpload\\2025-08-27.png";
		myprofile.doValidUploadPhoto(filePath);
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			
		}
		eleUtil.waitForLoaderToDisappear();
		// In Toast message Read the Loader Text name like "Loading"
		String toastMsg = myprofile.getToastMessage();
		System.out.println("My Password Save==>" + toastMsg);
		Assert.assertEquals(toastMsg, "Profile setting updated.");
		//myprofile.waitForToastDisappear();
		Assert.assertTrue(myprofile.isProfileImageDisplayed(), "Profile picture not displayed");

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}
}
