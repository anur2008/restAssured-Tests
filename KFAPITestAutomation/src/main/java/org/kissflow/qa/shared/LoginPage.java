package org.kissflow.qa.shared;

import java.io.IOException;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class LoginPage extends org.kissflow.qa.utils.KissflowPage{
	//Page Factory - OR:
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(className="marutham-body")
	WebElement signInBtn;

	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	@Step("Login to kissflow")
	public HomePage login(String Username) throws IOException{
		String GmailUserName = getJsonData("LoginDetails1.json","GmailUserName");
		String GmailPassword = getJsonData("LoginDetails1.json","GmailPassword");
		String YahooUserName = getJsonData("LoginDetails1.json","YahooUserName");
		String YahooPassword = getJsonData("LoginDetails1.json","YahooPassword");
		String OutlookUserName = getJsonData("LoginDetails1.json","OutlookUserName");
		String OutlookPassword = getJsonData("LoginDetails1.json","OutlookPassword");

		switch (Username){
		case "QAGmail":
			username.sendKeys(GmailUserName);
			password.sendKeys(GmailPassword);
			break;
		case "QAYahoo":
			Utils.isElementLoaded(driver, username);
			username.sendKeys(YahooUserName);
			password.sendKeys(YahooPassword);
			break;	
		case "QAOutlook":
			Utils.isElementLoaded(driver, username);
			username.sendKeys(OutlookUserName);
			password.sendKeys(OutlookPassword);
			break;	
		}
		
		takeScreenshot();
		signInBtn.click();   	    	
		return new HomePage();
	}
	
}

