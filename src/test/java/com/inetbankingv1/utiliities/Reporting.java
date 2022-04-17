package com.inetbankingv1.utiliities;

// it consists of the report of all the test cases
// this is a Listener Class to generate Extent .
//for all the test cases we need to use only one reporting file
// the listerner class and the testcases get integrated in the xml file

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Reporting extends TestListenerAdapter
{

	public WebDriver driver;

	public ExtentSparkReporter sprkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time spamp
		String repName = "Test-Report-" + timeStamp+".html";	// name of the report
		
		sprkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName); // location to store in the folder (test-output
		
		try {
			sprkReporter.loadXMLConfig(System.getProperty("user.dir") +"/extent-config.xml");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
				
		extent = new ExtentReports();
		
		extent.attachReporter(sprkReporter);
// defines the environmet of the session
		extent.setSystemInfo("HostName", "LocalHost"); // user define vales
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Tester Name", "Susheel");
		extent.setSystemInfo("Browser", "chrome");
		
		sprkReporter.config().setDocumentTitle("InetBanking Test Project"); // Title of the report
		sprkReporter.config().setReportName("Fuctional test AutomationReport");  // name of the project
	//	sprkReporter.config().setTestViewChartLocation(ChartLocation.Top);
		sprkReporter.config().setTheme(Theme.DARK);
		
	}

	public void onTestSucess(ITestResult result) throws IOException // this result will hold the result of the prevoiusly exicutred result

	{
		test = extent.createTest(result.getName());		//create new entry in result report
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}
		
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());		//create new entry in result report
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
	
		String screenshortPath = System.getProperty("user.dir")+"/Screenshots/"+ result.getName()+ ".png";           // screen shots in the folder
	    File f = new File(screenshortPath);
	
	if (f.exists())
	{
		
	try {
		test.fail("Screenshort is below:" + test.addScreenCaptureFromPath(screenshortPath));             //the schhort will be attched in the repot
		
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());		//create new entry in result report
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	}
	
	

	public void onFinish(ITestContext testcontext) {
		extent.flush(); 					// important method in the ExtentReports

	}

}


// pending in the code
//1) screenshot is not getting captured.
// 2) scuccesfull testrepot is genetared but not details are not exists in the report