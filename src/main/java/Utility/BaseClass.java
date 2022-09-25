package Utility;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Page_Classes.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {	
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter reporter;
	public static ExtentTest test;
	public static Properties configPropFile;
	public static String configFilePath = System.getProperty("user.dir")+ "/Configuration_Files/config.properties";
//	public static ThreadLocal<ExtentTest> extentTest  = new ThreadLocal<ExtentTest>();; //for preventing parallel test obj
	
	public static LoginPage loginObj = new LoginPage().getInstance();
	public static WebDriver_CommonFunctions commonObj = new WebDriver_CommonFunctions().getInstance();
	
	
	@BeforeSuite
	public static ExtentReports getExtentTestObj()
	{
		loadConfigFile(configFilePath);
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Extent_Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	

	@BeforeMethod
	  public void driverinitialization() throws Throwable 
		{ 
		switch(configPropFile.getProperty("browser")) 
		{
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
		  case "edge":
			  	WebDriverManager.edgedriver().setup(); 
			  	driver = new EdgeDriver();
			  	break;
		  case "chrome":
		  default:
				  ChromeOptions chromeOptions = new ChromeOptions();
				  WebDriverManager.chromedriver().setup(); 
				  driver = new ChromeDriver(chromeOptions); 
				  break;
		  }
		
		 	 driver.get(configPropFile.getProperty("appURL"));
			 driver.manage().window().maximize();
			 Thread.sleep(8000);
		}
		
	
	  @AfterMethod 
	  public void teardown()
	  { 
		  driver.quit(); 
	  }
	 
	
	  public String screenShotCapture(String testCaseName, WebDriver driver) throws Throwable
	  {
		  File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  String destinationPath = System.getProperty("user.dir")+"/Screenshots/"+testCaseName+".jpg";
		  FileUtils.copyFile(srcFile, new File(destinationPath));
		  return destinationPath;
	  }
	  
	  public static void loadConfigFile(String filepath)
		{
			try {
				Properties property = new Properties();
				FileInputStream ip = new FileInputStream(filepath);
				property.load(ip);
				configPropFile =property;		//	report.attachReporter(spark);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 

}
