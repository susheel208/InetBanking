//when ever we crate the new test case it should extend the base class
//test cases consists of only test cases
//testcase class should follow the "test cases name"
package com.inetbankingv1.testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbankingv1.pageObjects.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_LoginTest_001{ // extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		Properties pro = new Properties();
		
		String baseUrl = pro.getProperty("baseUrl");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");
		
		
		
		driver.get(baseUrl); 							// baseUrl is variable mentioned in Base class
		
		// to access the methods from LoginPage class we need to crayte an object of
		// that class
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username); 					    // username is var mentioned in the base class
		lp.setPassword(password); 						// password is var mentioned in the base class
	
		lp.clickSubmit();
		
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) // how to verfiy tile ? --> inspect > console> type( documet.tile
		{
			Assert.assertTrue(true); 				   // aseert is ffrom TESTNG
			System.out.println("testcase 1 succesful");
		}    
		/*
		 * else { captureScreen(driver,"loginTest"); // driverObject and test method
		 * name Assert.assertTrue(false); // aseert is ffrom TESTNG
		 * 
		 * }
		 */

	}
}
