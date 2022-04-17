//in  creating terms utility files are similar to the page object model.
//step 1> initalization of properties object
//step *** creating  constructor
//Step2> creating the file object with file path location
//step3> creating the object to read the "file object"
//step4> load that "Fileinput object" with the properties object 

package com.inetbankingv1.utiliities;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

public class ReadConfig {

	Properties pro; 											// initialization o fthe properties object

	public ReadConfig() 															// ...........craeting the constructor
	{	
	File src = new File("./configarations\\confilg.properties "); 		// "./" --> represent the the project path
	
	try {
		FileInputStream fis = new FileInputStream(src);			// creting an object which will read the File OBject
		pro = new Properties();
		pro.load(fis);											//
	}
	catch (Exception e) {
		System.out.println("Exception is " + e.getMessage());
	}
	}

	// while creating the method : each method should contain reading the value frpm properties file and 
	// return the value
	// always methods are created outside of the constructor.
	public String getApplicationURL() 
	{		
		String url = pro.getProperty("baseUrl");		// loadin gthe key value, there should be same key values
		return url;										// 
	}
	public String getUsername()
	{		
		String username = pro.getProperty("username");		// loadin gthe key value,there should be same key values
		return username;
	}

	public String getPassword()
	{		
		String password = pro.getProperty("password");		// loadin gthe key value
		return password;
	}


}

