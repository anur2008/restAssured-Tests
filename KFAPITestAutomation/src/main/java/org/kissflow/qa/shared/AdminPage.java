package org.kissflow.qa.shared;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class AdminPage extends org.kissflow.qa.utils.KissflowPage{

	@FindBy(xpath = "//label[text() = 'Account time zone']/../../div[2]")
	WebElement timeZoneDropBox;
	
	@FindBy(xpath = "//label[text() = 'Account locale']/../../div[2]")
	WebElement LocaleDropBox;
	
	@FindBy(xpath = "(//button[text() = 'Save'])[2]")
	WebElement localeAndTimeZoneSave;
	 
	@FindBy(xpath = "(//div[text()='Account settings updated']/../../../span)[last()]")
	WebElement SuccessMessPop;
	 
	
	
	// Initializing the Page Objects:
	public AdminPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void saveLocaleAndTimezoneChange() throws Exception {
		Utils.isElementLoaded(driver, localeAndTimeZoneSave);
		localeAndTimeZoneSave.click();
		try {
			Utils.isElementLoaded(driver, SuccessMessPop);
			SuccessMessPop.click();
		}
		catch(Exception e) {
			System.out.println("No Popup message");

		}
	}
	@Step("Changing Time zone")
	public void timeZoneChange(String TimeZone) throws Exception {
		Utils.isElementLoaded(driver, timeZoneDropBox);
		timeZoneDropBox.click();
		timeZoneDropBox.sendKeys(TimeZone);
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart+TimeZone+lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		takeScreenshot();
	}
	@Step("Changing Locale")
	public void localeChange(String Locale) throws Exception {
		Utils.isElementLoaded(driver, LocaleDropBox);
		LocaleDropBox.click();
		LocaleDropBox.sendKeys(Locale);
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart+Locale+lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		takeScreenshot();

	}
}
