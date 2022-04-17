//this consisted of the 2 methods
//testmethd  --> based on the num of inputs we have the test method will be executed those many times

// dataprovider method


package com.inetbankingv1.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;

import org.testng.annotations.Test;

import com.inetbankingv1.pageObjects.LoginPage;


public class TC_LoginDDT_002 extends BaseClass {

	
	// this is the test case  --->this is a method
	
	@Test //(dataProvider = "LoginData")      		//this refers to the name of the data provider from which, test method has to get the data 
//	public void loginDDt(String user1, String pswd1) throws InterruptedException {
//		
//	    LoginPage lp = new LoginPage(driver);
//		
//	    
//		driver.get(baseUrl); 
//		lp.setUserName(user1); 										// this variables are from base class
//		lp.setPassword(pswd1);
//		lp.clickSubmit();
//		Thread.sleep(2000);
		
	public void loginTest() throws InterruptedException, IOException {
		driver.get(baseUrl); 							// baseUrl is variable mentioned in Base class
		
		// to access the methods from LoginPage class we need to crayte an object of
		// that class
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username); 					    // username is var mentioned in the base class
		lp.setPassword(password); 						// password is var mentioned in the base class
	
		lp.clickSubmit();	
	
	
		if(IsALertPresent() ==true)               // if alert is present means the given details were wrong so the test method is failed
		{
			driver.switchTo().alert().accept();    // close alert
			driver.switchTo().defaultContent();		// after closing the alert we are switching to the main page
			
			Assert.assertTrue(false);			// assert = false means that the TEST method has failed
		
	
		}
		else {
			Assert.assertTrue(true); 			// as Assert = true, test method is successfull that mean s the deatals are correct 
			lp.clickLogout();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();  // close the logout alert
			driver.switchTo().defaultContent();			// after closing the alert we are switching to the main page
		
		}
	}
	
// used defined method to check weather alert is present =t or not
	
	public boolean IsALertPresent()
	{
		try 
		{
			driver.switchTo().alert();			// if alert is present then it returns "true".
				return true;
			
		} catch (NoAlertPresentException e) 
		{
			return false;						// if alert is present not then it returns "false".
		}
	}
	
	
	// method 2 is actually a dataprovider
	
	//this iwll collect the data from the xl and store it in the 
	// 2 dimentional array
	
	
	/*
	 * @DataProvider (name = "LoginData") String[][] getData() throws IOException //
	 * String[][] came from the return type { String path =
	 * System.getProperty("user.dir")+
	 * "src\\test\\java\\com\\inetbankingv1\\testData\\LoginData.xlsx"; // xlfile
	 * location
	 * 
	 * int rownum = XLUtils.getRowCount(path, "Sheet1"); // reading the exl file
	 * from its location int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
	 * 
	 * String logindata[][] = new String [rownum][colcount]; // 2 dimentional array
	 * 
	 * 
	 * // the row, col value is{1,0}, equals to {0,0} in 2 dimentional arrray
	 * 
	 * 
	 * for (int i = 1; i <= rownum; i++ ) { // here row = 0 we want to neglect for
	 * (int j= 0; j<colcount; j++) { logindata[i-1][j] = XLUtils.getCellData(path,
	 * "Sheet1", i , j); //[0,0] for row=1,col=1: [1,1 } // to send the data to
	 * actual testmethod
	 * 
	 * }
	 * 
	 * return logindata; //returns 2 dimentional array.
	 * 
	 * }
	 */
	
	
	
	
}
