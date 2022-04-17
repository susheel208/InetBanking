//when ever we crate the new test case it should extend the base class
//test cases consists of only test cases
//testcase class should follow the "test cases name"
package com.inetbankingv1.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbankingv1.pageObjects.LoginPage;
import com.inetbankingv1.utiliities.XLUtils;

public class TC_LoginTest_2 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginTest(String useer, String pswwwd) throws InterruptedException, IOException {
		driver.get(baseUrl); // baseUrl is variable mentioned in Base class

		// to access the methods from LoginPage class we need to crayte an object of
		// that class
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(useer); 						// username is var mentioned in the base class
		lp.setPassword(pswwwd); 					// password is var mentioned in the base class

		lp.clickSubmit();

		if (IsALertPresent() == true) // if alert is present means the given details were wrong so the test method is
										// failed
		{
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent(); // after closing the alert we are switching to the main page

			Assert.assertTrue(false); // assert = false means that the TEST method has failed

		} else {
			Assert.assertTrue(true); // as Assert = true, test method is successfull that mean s the deatals are
										// correct
			lp.clickLogout();
			Thread.sleep(2000);
			driver.switchTo().alert().accept(); // close the logout alert
			driver.switchTo().defaultContent(); // after closing the alert we are switching to the main page

		}
	}

// used defined method to check weather alert is present =t or not

	public boolean IsALertPresent() {
		try {
			driver.switchTo().alert(); // if alert is present then it returns "true".
			return true;

		} catch (NoAlertPresentException e) {
			return false; // if alert is present not then it returns "false".
		}
	}

	
	  
	  @DataProvider (name = "LoginData") 
			  String[][] getData() throws IOException {										//  String[][] came from the return type { String path =
			  String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\inetbankingv1\\testData\\LoginData.xlsx"; // xlfile location
			  
			  int rownum = XLUtils.getRowCount(path, "Sheet1"); // reading the exl file from its location
			  int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
			  
	  String logintestdata[][] = new String [rownum][colcount]; // 2 dimentional array
	  
	  
	  		// the row, col value is{1,0}, equals to {0,0} in 2 dimentional arrray
	  
	  
				  for (int i = 1; i <= rownum; i++ ) { // here row = 0 we want to neglect 
					  for (int j= 0; j<colcount; j++) { 
						  logintestdata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i , j); //[0,0] for row=1,col=1: [1,1 } // to send the data toactual testmethod
				  
					  }
				  }
				  
	  return logintestdata; //returns 2 dimentional array.
	  
	  }
	
	 

    }

//class reflection in java.

