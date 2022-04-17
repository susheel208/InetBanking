package com.inetbankingv1.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbankingv1.pageObjects.AddCustomerPage;
import com.inetbankingv1.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, Exception{
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		LoginPage lp = new LoginPage(driver); 							// this driver is coming frpm base class

																		//	Step 1 : login in the website
		driver.get(baseUrl); 
		lp.setUserName(username); 										// this variables are from base class
		lp.setPassword(password);
		lp.clickSubmit();
		Thread.sleep(2000);
		//driver.get(" https://demo.guru99.com/v4/manager/addcustomerpage.php");																//  step 2: after login we need to move to the add custemer page na dfill the details so 
																	// call all the methods from the "AddCustomerPageObject"
		

		System.out.println(driver.getCurrentUrl());
	//	logger.info("entering into Add customer");
		 addcust.clickAddNewCustomer();
		
     	System.out.println("entering data");
		addcust.custName("susheel");
		Thread.sleep(2000);
		System.out.println("entering name");
		addcust.custgender("Male");
		addcust.custdob("01", "06", "1994");
		Thread.sleep(2000); // putting sleep here bez of the dob has 3 cell to fill which will take time
		addcust.custaddress("INDIA");
		addcust.custstate("Karnataka");
		addcust.custcity("Bengalure");
		addcust.custpinno("500017");
		addcust.custtelephoneno("9898987474");
		System.out.println("entering email id");
		String email = randomstring() + "@gmail.com"; // crates dynamic and random email id.

		addcust.custemailid(email); // pass the dynamically generated email id here.
		addcust.custpassword("gsk1243");
		addcust.custsubmit();
		System.out.println("Submit btn");
		Thread.sleep(2000);
	//	addcust.custAlertButton();
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (res == true) // if res contains above data then our test case is successfull
		{
			Assert.assertTrue(true); // this statement says that this line is successfull
		} else { // tc failed so we need to take the screen shot and need to declare that the tc
					// is failed
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false); // this declares that the tc has failed
			System.out.println("hi");

		}
}

	// we would like to send some "random test data" in email id instead of sending
	// hard coded value

	// as these methods may be use full in other pageobjectclasses so we need to
	// keep them into baseClass

	/*
	 * public String randomstring() { String generatestring =
	 * RandomStringUtils.randomAlphabetic(8); // generates total STRING WITH 8 CHARs
	 * return(generatestring);
	 * 
	 * }
	 */

	/*
	 * public String randomsNum() { String generatestring2 =
	 * RandomStringUtils.randomNumeric(4); // generates total STRING WITH 8 CHARs
	 * return(generatestring2);
	 * 
	 * 
	 * }
	 */

}
