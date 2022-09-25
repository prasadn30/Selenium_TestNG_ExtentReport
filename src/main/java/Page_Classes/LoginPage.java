package Page_Classes;


import com.aventstack.extentreports.Status;

import Utility.BaseClass;
import Utility.WebDriver_CommonFunctions;

public class LoginPage extends BaseClass {
	
	public static WebDriver_CommonFunctions commonObj = new WebDriver_CommonFunctions();
	private static final LoginPage instance = new LoginPage();

	public static LoginPage getInstance() {
		return instance;
	}

	
	private static String txtUserName = "//input[@name='username']"; 
	private static String txtPassword = "//input[@name='password']"; 
	private static String btnLogin = "//button[@type='submit']"; 
	private static String hdrLogin = "//h5[text()='Login']";

	public void setUserName() throws Throwable { 
		
		commonObj.sendInputText(txtUserName, configPropFile.getProperty("username"));
		test.log(Status.PASS, "Entered input text as : "+ configPropFile.getProperty("username"));
	}

	public void setPassword() throws Throwable { 
		commonObj.sendInputText(txtPassword, configPropFile.getProperty("password"));
		test.log(Status.PASS, "Entered password text successfully!!");
		String path = screenShotCapture("Password", driver);
		test.addScreenCaptureFromPath(path,"Password");
		
	}

	public void clickLoginBtn() throws Throwable 
	{ 
		commonObj.click(btnLogin);
		test.log(Status.PASS, "Clicked on Login button successfully");
	}
	
	public String getLoginHdrText()
	{
		return	commonObj.getText(hdrLogin);
	}

}
