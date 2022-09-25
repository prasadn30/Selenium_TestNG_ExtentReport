package Utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestNGListener extends BaseClass implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test is started");
		test = extent.createTest(result.getMethod().getMethodName());
	//	extentTest.set(test);
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test is successful");
	//	extentTest.get().log(Status.INFO, result.getMethod().getMethodName()+" is successful");
		test.log(Status.INFO, result.getMethod().getMethodName()+" is successful");
		
	}

	@Override
	public void onTestFailure(ITestResult result)  {
		// TODO Auto-generated method stub
		try {
			System.out.println("Test is failed..");
		//	extentTest.get().fail(result.getThrowable());
			test.fail(result.getThrowable());
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			String screenshotPath = screenShotCapture(result.getMethod().getMethodName(), driver);
			/*
			 * extentTest.get().addScreenCaptureFromBase64String(screenshotPath,
			 * result.getMethod().getMethodName()); extentTest.get().log(Status.INFO,
			 * result.getMethod().getMethodName()+" is Failed");
			 */	
			test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
			test.log(Status.FAIL, result.getMethod().getMethodName()+" is Failed");
		
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test is skipped");
	//	extentTest.get().log(Status.INFO, result.getMethod().getMethodName()+" is SKIPPED");
		test.log(Status.INFO, result.getMethod().getMethodName()+" is SKIPPED");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test is Finished");
	
		extent.flush();
	}
	

}
