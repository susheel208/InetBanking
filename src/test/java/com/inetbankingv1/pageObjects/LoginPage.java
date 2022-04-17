//every page is classified as class in page object model.
// every page class consits of these three parts
// 1) constructor initialization
// 2) Locating of webelemets
// 3)user action  methods


package com.inetbankingv1.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;				// webDriver initialization

//1) initializing constructor.
	
	public LoginPage(WebDriver d) {
		
		driver = d;
		PageFactory.initElements(d, this);
	}
//2)  locating the webElement
	
	@FindBy(name ="uid")			// this is locator
	WebElement txtUserName;				//locator is identified by this name give by us
	
	
	@FindBy(name ="password")
	WebElement txtPassword;


	@FindBy(name ="btnLogin")
	WebElement loginbtn;
	
	@FindBy(xpath ="/html/body/div[3]/div/ul/li[15]/a")
	WebElement logoutbtn;					
	
//3) creating the user action method....................
	
	public void setUserName(String uname)      //uname is a varible we will send the value from another class 
	{	
		txtUserName.sendKeys(uname);		   // same variable should be entered here
		
	}
	public void setPassword(String pswd)      //pswd is a varible 
	{	
		txtPassword.sendKeys(pswd);		   // same variable should be entered here
		
	}
	
	public void clickSubmit()
	{	
		loginbtn.click();
	}
	
	
	public void clickLogout()
	{	
		logoutbtn.click();
	}
	
}
