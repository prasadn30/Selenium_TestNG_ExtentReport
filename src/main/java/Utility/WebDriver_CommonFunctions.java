package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class WebDriver_CommonFunctions extends BaseClass{

	private static final WebDriver_CommonFunctions instance = new WebDriver_CommonFunctions();
	public static WebDriver_CommonFunctions getInstance() 
	{
		return instance;
	}

	//public WebElement elementObject;
	
	public WebElement createWebElement(String locatorXpath)
	{
		return driver.findElement(By.xpath(locatorXpath));
	}
	public void getURL(WebDriver driverObj, String appURL) throws Throwable
	{
		driverObj.manage().window().maximize();
		driverObj.get(appURL);
		Thread.sleep(7000);
		WebDriverWait wait = new WebDriverWait(driverObj, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		test.log(Status.INFO, "URL is launched");
	}
	
	public boolean isElementExist(String locatorXpath)
	{
		boolean isObjExist = false;
		if( createWebElement(locatorXpath).isDisplayed())
		{
			isObjExist =true;
		}
		return isObjExist;
	}
	
	public void sendInputText(String locatorXpath, String inputText)
	{
		WebElement elementObj = createWebElement(locatorXpath);
		if(isElementExist(locatorXpath))
		{
			elementObj.sendKeys(inputText);
		}
		
	}
	
	public void click(String locatorXpath)
	{
		WebElement elementObj = createWebElement(locatorXpath);
		if(isElementExist(locatorXpath))
		{
			elementObj.click();
		}
		
	}
	
	public String getText(String locatorXpath)
	{
		String elementText=null;
		WebElement elementObj = createWebElement(locatorXpath);
		if(isElementExist(locatorXpath))
		{
			elementText =  elementObj.getText();
		}
		return elementText;
	}
	/*
	 * public void screenshotCapture() throws Throwable { Date dt = new Date();
	 * String dtLng = String.valueOf(dt.getTime()); File file = ((TakesScreenshot)
	 * driver).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(file, new
	 * File(screenShotsPath+"Screenshot-"+dtLng+".png")); }
	 */
	
	
	
	
}
