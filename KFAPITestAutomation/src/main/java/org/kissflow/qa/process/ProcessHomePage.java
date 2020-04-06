package org.kissflow.qa.process;

import org.kissflow.qa.utils.KissflowPage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;

public class ProcessHomePage extends KissflowPage {
	@FindBy(xpath = "//*[@id='studioOverlay']/..//h5")
	WebElement processName;
	@FindBy(xpath = "//input[@placeholder='Enter name']")
	WebElement DatasetName;
	@FindBy(xpath = "(//button[text()='New Item'])[last()]")
	WebElement NewItemButton;

	@FindBy(xpath = "//li[text()='Validation']")
	WebElement ValidationButton;

	@FindBy(xpath = "//span[text() = 'Add Validation Rule']/..")
	WebElement AddRulesButton;

	@FindBy(xpath = "//a[text() = 'Admin']")
	WebElement adminLink;

	@FindBy(xpath = "//button[text()='Reports']/../div//button")
	WebElement MoreOptionBtn;

	@FindBy(xpath = "//li[text()='Archive process']")
	WebElement ArchiveLink;

	@FindBy(xpath = "//button[text()='Archive Process']")
	WebElement ArchiveBtn;

	@FindBy(xpath = "//li[contains(text(),'Share settings')]")
	WebElement ShareSettingsBtn;

	@FindBy(xpath = "(//button[text()='Add User/Group'])[1]")
	WebElement AddUserGrpAdmin;

	@FindBy(xpath = "(//button[text()='Add User/Group'])[last()]")
	WebElement AddUserGrp;

	@FindBy(xpath = "(//div[@data-type ='select-icon'])[last()]")
	WebElement AssignedToField;
	@FindBy(xpath = "//div[@data-type = 'select-icon']/../div/input")
	WebElement SharedToInput;
	@FindBy(xpath = "//span[contains(text(),'Everyone')]")
	WebElement EveryOneOption;
	@FindBy(xpath = "//label[1]//div[1]//span[1]")
	WebElement InitiateBtn;
	@FindBy(xpath = "//label[2]//div[1]//span[1]")
	WebElement EditBtn;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement SaveBtn;

	@FindBy(xpath = "//button[text()='Unarchive']")
	WebElement UnarchiveBtn;

	@FindBy(xpath = "//button[text()='Delete']")
	WebElement DeleteBtn;

	@FindBy(xpath = "//button[text()='Delete Process']")
	WebElement DeleteProcessBtn;

	@FindBy(xpath = "(//div[text()='This process was archived successfully']/../../../span)[last()]")
	WebElement ArchiveSuccessMess;

	@FindBy(xpath = "//button[text()='Reports']")
	WebElement ReportsBtn;

	// Initializing the Page Objects:
	public ProcessHomePage() {
		PageFactory.initElements(driver, this);
	}

	public String getProcessName() {
		Utils.isElementLoaded(driver, processName);
		String CreatedProcessName = processName.getText();
		return CreatedProcessName;
	}
public String getDsName() {
		
		Utils.isElementLoaded(driver, DatasetName);
		String CreatedDatasetName = DatasetName.getAttribute("value");
		return CreatedDatasetName;
}
	public NewItemActivityPage openMyItemProcess(String flowName) throws Exception {

		String InitalPath = "//div[text() ='Request for ";
		String LastPath = "']";
		String FinalPath = InitalPath + flowName + LastPath;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath)));
		driver.findElement(By.xpath(FinalPath)).click();
		takeScreenshot();
		return new NewItemActivityPage();
	}

	@Step("Open process new item after withdrawn")
	public void openMyItemProcessAfterWithdrawn(String flowName) throws Exception {

		String InitalPath = "//span[text() =' was withdrawn']/../../..//div[text()='Request for ";
		String LastPath = "']";
		String FinalPath = InitalPath + flowName + LastPath;
		Thread.sleep(3000);
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath)));
		driver.findElement(By.xpath(FinalPath)).click();
		takeScreenshot();
	}

	public void newItemClickOfProcess(String ProcessName) throws InterruptedException {
		Utils.isElementLoaded(driver, NewItemButton);
		NewItemButton.click();
		String InitalPath = "//div[text() ='Request for ";
		String LastPath = "']";
		String FinalPath = InitalPath + ProcessName + LastPath;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath)));
	}

	@Step("Archieve the process")
	public void archiveProcess() throws InterruptedException {
		Utils.isElementLoaded(driver, MoreOptionBtn);
		MoreOptionBtn.click();
		Utils.isElementLoaded(driver, ArchiveLink);
		ArchiveLink.click();
		Utils.isElementLoaded(driver, ArchiveBtn);
		ArchiveBtn.click();
		Utils.isElementLoaded(driver, ArchiveSuccessMess);
		ArchiveSuccessMess.click();

	}

	@Step("Share the process")
	public void shareProcess() throws InterruptedException {
		Utils.isElementLoaded(driver, MoreOptionBtn);
		MoreOptionBtn.click();
		Utils.isElementLoaded(driver, ShareSettingsBtn);
		ShareSettingsBtn.click();
		Thread.sleep(5000);
		Utils.isElementLoaded(driver, AddUserGrpAdmin);
		AddUserGrpAdmin.click();
		AssignedToField.click();
		SharedToInput.sendKeys(Keys.PAGE_DOWN);
		SharedToInput.sendKeys(Keys.PAGE_DOWN);
		SharedToInput.sendKeys("kfqatest@gmail.com");
		Thread.sleep(2000);
		String finalpart = "#optionListContainer";
		driver.findElement(By.cssSelector(finalpart)).click();
		Utils.isElementLoaded(driver, AddUserGrp);

		AddUserGrp.click();
		Thread.sleep(2000);
		AssignedToField.click();
		SharedToInput.sendKeys(Keys.PAGE_DOWN);
		SharedToInput.sendKeys(Keys.PAGE_DOWN);
		// Utils.isElementLoaded(driver, SharedToInput);
		SharedToInput.sendKeys("Everyone");
		Thread.sleep(2000);
		// String finalpart = "#optionListContainer";
		// Utils.isElementLoaded(driver, driver.findElement(By.cssSelector(finalpart)));
		driver.findElement(By.cssSelector(finalpart)).click();
		// Utils.isElementLoaded(driver, EveryOneOption);
		InitiateBtn.click();
		EditBtn.click();
		Utils.isElementLoaded(driver, SaveBtn);
		Thread.sleep(2000);
		SaveBtn.click();

	

	}

	@Step("Delete the process")
	public void deleteProcess() throws InterruptedException {
		Utils.isElementLoaded(driver, UnarchiveBtn);
		Utils.isElementLoaded(driver, DeleteBtn);
		DeleteBtn.click();
		Utils.isElementLoaded(driver, DeleteProcessBtn);
		DeleteProcessBtn.click();
	}

	public void clickProcessReports() throws InterruptedException {
		Utils.isElementLoaded(driver, ReportsBtn);
		ReportsBtn.click();
	}
}
