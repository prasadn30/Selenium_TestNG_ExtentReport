package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utility.BaseClass;
import Utility.TestNGListener;


public class TestCase1 extends BaseClass{

	@Test(priority=1, enabled = false)
	public void mthd1()
	{
		test.log(Status.INFO, "Step-1");
		test.log(Status.INFO, "Step-2");
		Assert.assertEquals(false, true);
	}
	@Test(priority=2, enabled = true)
	public void mthd2()
	{
		test.log(Status.INFO, "Step-1");
		test.log(Status.INFO, "Step-2");
		test.log(Status.INFO, "Step-3");
		test.log(Status.INFO, "Step-4");
		test.log(Status.INFO, "Step-5");
		test.log(Status.INFO, "Step-6");
		
	}@Test(priority=3, enabled = false)
	public void mthd3()
	{
		test.log(Status.INFO, "Step-1");
		test.log(Status.INFO, "Step-2");
		test.log(Status.INFO, "Step-3");
		test.log(Status.INFO, "Step-4");
		Assert.assertEquals(false, true);
		
	}@Test(priority=4, enabled = false)
	public void mthd4()
	{
		test.log(Status.INFO, "Step-1");
		test.log(Status.INFO, "Step-2");
		test.log(Status.INFO, "Step-3");
		test.log(Status.INFO, "Step-4");
		test.log(Status.INFO, "Step-5");
		
	}
	
	@Test(priority=5)
	public void verifyLoginHdr() throws Throwable
	{
		test.log(Status.INFO, loginObj.getLoginHdrText()+" is the available Header..");
		Assert.assertEquals(loginObj.getLoginHdrText(), "OrangeHRM");
		
	}
	
	@Test(priority=6, enabled = false)
	public void loginToApp() throws Throwable
	{
		
		loginObj.setUserName();
		loginObj.setPassword();
		loginObj.clickLoginBtn();
		
	}

}
