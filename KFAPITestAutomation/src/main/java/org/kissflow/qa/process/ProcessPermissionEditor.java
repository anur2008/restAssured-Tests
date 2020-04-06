package org.kissflow.qa.process;

import org.kissflow.qa.utils.KissflowPage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;


public class ProcessPermissionEditor extends KissflowPage{
	
	@FindBy(xpath = "(//*[text()='Read-Only'])[2]")
	WebElement ReadonlyForField;
	
	public ProcessPermissionEditor() {
		PageFactory.initElements(driver, this);
	}
	@Step("Set permission to the field")
	public void setPermission(String fieldName,String Permission) throws InterruptedException {
		String firstpart = "(//label[text() = '";
		String lastpart = "'])[last()]";
		String finalpart = firstpart+fieldName+lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		WebElement element = driver.findElement(By.xpath(finalpart));
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		Utils.isElementLoaded(driver, ReadonlyForField);
		ReadonlyForField.click();
		takeScreenshot();

	}
}
