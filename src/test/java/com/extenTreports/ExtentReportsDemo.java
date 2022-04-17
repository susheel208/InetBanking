package com.extenTreports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsDemo {

	public WebDriver driver;

	public ExtentSparkReporter sprkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setExtent() {
		sprkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/myReport.html"); // location to store in the folder (test-output
	
		sprkReporter.config().setDocumentTitle("Automation Report"); // Title of the report
		sprkReporter.config().setReportName("Fuctional Report");
		sprkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sprkReporter);

		extent.setSystemInfo("HostName", "LocalHost"); // user define vales
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Tester Name", "Susheel");
		extent.setSystemInfo("Browser", "chrome");

	}

	@AfterTest
	public void endReport() {
		extent.flush(); // inportant method in the ExtentReports

	}

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");

	}

	@Test
	public void googleLoogo() {
		test = extent.createTest("googleLoogo"); // test ia the object of ExtendTest

		Boolean status = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/img")).isDisplayed();
		Assert.assertTrue(status);

	}

	public void tearDown(ITestResult result) throws IOException // this result will hold the result of the prevoiusly exicutred result

	{
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // To ADD TestCase NAME into ExtentReport
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // Tadd Error/ Exception in the
																					// report

			String screenshotPath = ExtentReportsDemo.getScreenShot(driver, result.getName());   	//result.getName()  --> This will give the failked testcade name

			test.addScreenCaptureFromPath(screenshotPath); // add screen shot

		} else if (result.getStatus() == ITestResult.SKIP) {

			test.log(Status.SKIP, "TEST CASE FAILED IS " + result.getName());

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(Status.PASS, "TEST CASE FAILED IS " + result.getName());

		}
	}

	// crating a method for taking screenshot
	public static String getScreenShot(WebDriver driver, String screenshortName) throws IOException{						//screenshortName = result.getName() 
		 
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	
	//after execution, you could see a "Failed TestsScreenshots" undr src folder
	String destination = System.getProperty("user.dir")+"/Screenshots/"+ screenshortName + dateName + ".png";
	File finlaDestination = new File(destination);
	FileUtils.copyFile(source, finlaDestination);
	return destination;
	
		
	}

}
