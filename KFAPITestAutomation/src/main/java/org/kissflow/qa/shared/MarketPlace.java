package org.kissflow.qa.shared;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketPlace extends org.kissflow.qa.utils.KissflowPage {
	@FindBy(xpath = "//p[text() = 'Create from scratch']/../div")
	WebElement createFromScratch;

	@FindBy(xpath = "//button[text() = 'Create Process']")
	WebElement createProcess;

	@FindBy(name = "name")
	WebElement flowName;

	@FindBy(name = "Name")
	WebElement flowChannelName;
	
	@FindBy(xpath = "(//button[text() = 'Create'])[last()-0]")
	WebElement createBtn;

	public MarketPlace() {
		PageFactory.initElements(driver, this);
	}

	public String verifyMarketPageTitle() {
		return driver.getTitle();
	}

	public void createFlow(String flow_type, String flow_name) throws InterruptedException {
		Utils.isElementLoaded(driver, createFromScratch);
		Thread.sleep(2000);
		createFromScratch.click();
		
		String InitalPath = "//button[text() = 'Create ";
		String LastPath = "']";
		String FinalPath =InitalPath+flow_type+LastPath;
		WebElement flow = driver.findElement(By.xpath(FinalPath));
		Utils.isElementLoaded(driver, flow);
		flow.click();
		if(flow_type.equals("Channel")) {
			Utils.isElementLoaded(driver, flowChannelName);
			flowChannelName.sendKeys(flow_name);
		}
		else {
			Utils.isElementLoaded(driver, flowName);
			flowName.sendKeys(flow_name);
		}
		
		takeScreenshot();
		Utils.isElementLoaded(driver, createBtn);
		createBtn.click();

	}
	
	
}
