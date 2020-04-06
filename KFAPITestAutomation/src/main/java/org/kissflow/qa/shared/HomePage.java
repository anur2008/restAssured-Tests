package org.kissflow.qa.shared;


import org.kissflow.qa.process.ProcessHomePage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Step;


public class HomePage extends org.kissflow.qa.utils.KissflowPage{


	@FindBy(xpath = "(//button[text() = 'Create Flow'])[last()]")
	WebElement createFlow;
	
	@FindBy(xpath = "//a[text() = 'Admin']")
	WebElement adminLink;
	
	@FindBy(xpath = "//a[text()='View all flows']")
	WebElement ViewAllBtn;
	
	@FindBy(xpath = "//input[@placeholder='Search' and @type='text']")
	WebElement GlobalSearch;

	@FindBy(xpath = "(//button[text()='Edit Process'])[last()]")
	WebElement EditProcessButton;

	@FindBy(xpath = "//a[text() = 'Home']")
	WebElement HomeLink;
	
	@FindBy(xpath = "//a[text() = 'Home']/../../li[last()]")
	WebElement UserProfile;
	
	@FindBy(xpath = "//li[text() = 'Sign out']")
	WebElement SignOutlink;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	@Step("Create new flow")
	public ProcessHomePage createFlowNew(String flow_type, String flow_name) throws InterruptedException {
		Utils.isElementLoaded(driver, HomeLink);
		HomeLink.click();
		Utils.isElementLoadedSec(driver, createFlow);
		createFlow.click();
		MarketPlace mk =  new MarketPlace();
		String mkTitle = mk.verifyMarketPageTitle();
		Assert.assertEquals(mkTitle, "Kissflow");
		mk.createFlow(flow_type, flow_name);
		takeScreenshot();
		return new ProcessHomePage();
	}
	
	public String getHomePageTitle(){
		return driver.getTitle();
	}
	
	public AdminPage clickOnAdminLink() throws InterruptedException{
		Utils.isElementLoaded(driver, adminLink);
		adminLink.click();
		return new AdminPage();
	}

	public void clickOnAdminLink1() throws InterruptedException{
		Thread.sleep(5000);
		Utils.isElementLoaded(driver, adminLink);
		adminLink.click();
	}
	public void clickHome() {
		WebDriverWait wait=new WebDriverWait(driver, 120);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text() = 'Home']")));
		Utils.isElementLoadedSec(driver, HomeLink);
		HomeLink.click();
		
	}

	public void getBacktoProcess(String flow_name) throws InterruptedException {
		Utils.isElementLoaded(driver, HomeLink);
		Thread.sleep(4000);
		HomeLink.click();
		Utils.isElementLoaded(driver, ViewAllBtn);
		ViewAllBtn.click();
		Utils.isElementLoaded(driver, GlobalSearch);
		GlobalSearch.click();
		GlobalSearch.sendKeys(flow_name);
		
		String InitalPath = "(//h4[text()='";
		String LastPath = "'])[last()]";
		String FinalPath =InitalPath+flow_name+LastPath;

	
		WebElement flow = driver.findElement(By.xpath(FinalPath));
		WebDriverWait waitCreateButton = new WebDriverWait(driver, 1);
		waitCreateButton.until(ExpectedConditions.visibilityOf(flow));
		driver.findElement(By.xpath(FinalPath)).click();
		
	}
	@Step("Logout from current user")
	public void logout() throws InterruptedException {
		Thread.sleep(3000);
		Utils.isElementLoadedSec(driver, UserProfile);
		UserProfile.click();
		Utils.isElementLoadedSec(driver, SignOutlink);
		SignOutlink.click();
		
	}
		}
	 
		
	
