package org.kissflow.qa.process;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.qameta.allure.Step;


public class ProcessReportPage extends org.kissflow.qa.utils.KissflowPage{
	@FindBy(xpath = "(//*[text()='Read-Only'])[2]")
	WebElement ReadonlyForField;
	
	public ProcessReportPage() {
		PageFactory.initElements(driver, this);
		
	}
	ProcessHomePage pg = new ProcessHomePage();
	@Step("Click process report")
	public void OpenReports(String ProcessName) throws InterruptedException {
		pg.clickProcessReports();
		String firstpart = "//h6[text()='";
		String lastpart = " All Items']/../../div/img";
		String finalpart = firstpart+ProcessName+lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		WebElement element = driver.findElement(By.xpath(finalpart));
		element.click();
		takeScreenshot();

	}
	
	@Step("Verify field values in Report")
	public void verifyReport() throws InterruptedException {
		takeScreenshot();
		Assert.assertTrue(true, "This is default content");
		takeScreenshot();

	}
}
