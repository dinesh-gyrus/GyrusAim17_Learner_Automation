package com.qa.gyruslearner.constants;

import java.util.Arrays;
import java.util.List;

import com.qa.gyruslearner.base.TestBase;

public class AppConstants extends TestBase {
	
	
	public final static String BaseUrl=prop.getProperty("baseUrl");

	public final static int DEFAULT_TIME_OUT = 5;
	public final static int SHORT_TIME_OUT = 10;
	public final static int MEDIUM_TIME_OUT = 15;
	public final static int MAX_TIME_OUT = 20;
	public final static int MAX_TO_MAX_TIME_OUT = 30;
	public final static int DEFAULT_TIME = 500;
	
	public final static String LOGIN_PAGE_TITLE ="Login";
	public final static String DASHBOARD_PAGE_TITLE ="Dashboard";
	public final static String CHANGEPASSWORD_PAGE_TITLE ="Change Password";
	public final static String FORGOTPASSWORD_PAGE_TITLE ="Forgot Password";
	public final static String SIGNUP_PAGE_TITLE ="User Registration";
	public final static String MYPROFILE_PAGE_TITLE ="My Profile";
	public final static String IDP_PAGE_TITLE ="IDP";
	public final static String MYENROLLMENT_PAGE_TITLE ="My Enrollments";
	public final static String TRAINING_TRANSCRIPT_PAGE_TITLE ="Training Transcript";
	public final static String GAP_ANALYSIS_PAGE_TITLE ="Gap Analysis";
	public final static String MYASSESSMENTS_PAGE_TITLE ="My Assessments";
	public final static String MYEVALUATIONS_PAGE_TITLE ="My Evaluations";
	
	public final static String COURSE_CATALOG_PAGE_TITLE ="Course Catalog";
	
	
	public final static String LOGIN_PAGE_URL = BaseUrl + "/auth/login";
	public final static String DASHBOARD_PAGE_URL= BaseUrl +"/v2/dashboard";
	public final static String CHANGEPASSWORD_PAGE_URL =BaseUrl +"/Auth/MustChangePassword";
	public final static String FORGOTPASSWORD_PAGE_URL =BaseUrl +"/Auth/ForgotPassword";
	public final static String SIGNUP_PAGE_URL =BaseUrl +"/Registration/UserSelfRegistration";
	public final static String CALENDER_PAGE_URL=BaseUrl + "/v2/classcalendar";
	public final static String MYPROFILE_PAGE_URL=BaseUrl +"/v2/profile";
	public final static String IDP_PAGE_URL=BaseUrl +"/v2/idp";
	public final static String ACTIVITYFEED_PAGE_URL=BaseUrl +"/v2/activityFeed";
	public final static String MYENROLLMENT_PAGE_URL=BaseUrl +"/v2/myenrollment";
	public final static String TRAINING_TRANSCRIPT_PAGE_URL=BaseUrl +"/v2/trainingtranscript";
	public final static String GAP_ANALYSIS_PAGE_URL=BaseUrl +"/v2/gap";
	public final static String MYASSESSMENTS_PAGE_URL=BaseUrl +"/v2/assessment";
	public final static String MYEVALUATIONS_PAGE_URL=BaseUrl +"/v2/evaluation";
	
	public final static String COURSE_CATALOG_PAGE_URL=BaseUrl +"/v2/coursecatalog";
	
	
	
	
						//************Sheet path********/
	          
	public static String LOGIN_DATA_SHEET_PATH = ".\\src\\test\\resources\\testdata\\LoginData.xlsx";
	public static String CHANGEPASSWORD_DATA_SHEET_PATH = ".\\src\\test\\resources\\testdata\\ChangePassword.xlsx";
	public static String MYPROFILE_DATA_SHEET_PATH = ".\\src\\test\\resources\\testdata\\MyProfileData.xlsx";
	public static String IDP_DATA_SHEET_PATH = ".\\src\\test\\resources\\testdata\\IdpData.xlsx";
	
					//************Sheet Name********/
		
		public static final String VALID_LOGIN_SHEET_NAME = "ValidLogin";
		public static final String FIRSTTIME_LOGIN_SHEET_NAME = "FirstTimeLoginRedirectsToChange";
		public static final String WRONGPASS_LOGIN_SHEET_NAME = "ValidUserNameAndWrongPass";
		public static final String WRONGUSERNAME_LOGIN_SHEET_NAME = "WrongUserNameAndValidPass";
		public static final String LOGINATTEMP_LOGIN_SHEET_NAME = "LastLoginAttemptMessage";
		public static final String FAILATTEMPT_LOGIN_SHEET_NAME = "AccountLockAfterFailedAttempt";
		public static final String FIRSTTIME_LOGIN_CHANGEPASSWORD_SHEET_NAME = "FirstTimeLoginUser";
		public static final String WRONGCURRENTPASSOWRD_CHANGEPASSWORD_SHEET_NAME = "WrongCurrentPassword";
		public static final String MISMATCH_NEW_CONFIRM_CHANGEPASSWORD_SHEET_NAME = "MismatchedNewConfirmPasswords";
		public static final String CURRENTANDNEW_SAME_CHANGEPASSWORD_SHEET_NAME = "CurrentAndNewPasswordAreSame";
		public static final String SUCCESSFUL_PASSWORDRESET_CHANGEPASSWORD_SHEET_NAME = "SuccessfulPasswordReset";
		public static final String RESER_PASSWORD_MYPROFILE_SHEET_NAME = "ResetPassword";
		public static final String CFR21_SECURITY_MYPROFILE_SHEET_NAME = "CFR21Security";
		public static final String LOGIN_MYPROFILE_SHEET_NAME ="ValidLogin";
		public static final String RATING_QUICKFILTER_IDPPAGE_SHEET_NAME ="FilterRating";
		public static final String DUEDATE_QUICKFILTER_IDPPAGE_SHEET_NAME ="FilterDueDate";
		public static final String SEARCH_IDPPAGE_SHEET_NAME ="SearchData";
		
		
		
		
		
		
	/*
	public final static String LOGIN_PAGE_URL = "https://nverma.gyrusaim.net/auth/login";
	public final static String DASHBOARD_PAGE_URL="https://nverma.gyrusaim.net/v2/dashboard";
	public final static String CHANGEPASSWORD_PAGE_URL ="https://nverma.gyrusaim.net/Auth/MustChangePassword";
	public final static String FORGOTPASSWORD_PAGE_URL ="https://nverma.gyrusaim.net/Auth/ForgotPassword";
	public final static String SIGNUP_PAGE_URL ="https://nverma.gyrusaim.net/Registration/UserSelfRegistration";
	public final static String CALENDER_PAGE_URL="https://nverma.gyrusaim.net/v2/classcalendar";
	public final static String MYPROFILE_PAGE_URL="https://nverma.gyrusaim.net/v2/profile";
	*/
	
	//*****************************All Pages Idea Coach***********************************//
		
		
		public static final String IDP_PAGE_IDEA_COACH ="IDP - Individual Development Plan - Shows training assignments and their status. And provides quick access to locate the training to meet individuals needs.";
		
		
		//************************************Error and Toast message *************************************
		
		public static final List<String> LOGIN_ERROR_MESSAGES = Arrays.asList(
			    "Invalid username or password",
			    "Invalid username or password. You have one Attempt remaining. Click on forgot password to get your username or password.",
			    "Your Account has been blocked. Please contact system administrator or try after 24 hours."
			);
		
		
		
	
}
