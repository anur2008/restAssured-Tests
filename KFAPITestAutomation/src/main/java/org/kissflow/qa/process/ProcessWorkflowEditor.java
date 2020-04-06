package org.kissflow.qa.process;

import org.kissflow.qa.utils.KissflowPage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;


public class ProcessWorkflowEditor extends KissflowPage {
	

	@FindBy(xpath = "(// input[ @ type = 'checkbox'] /..)[last()]")
	WebElement AddPlusButton;
	
	
	@FindBy(xpath = "//span[text()='Add new step']")
	WebElement AddNewButton;
	
	@FindBy(xpath = "//input[@id='Name']")
	WebElement StepName;
	@FindBy(xpath = "//div/a[contains(text(),'Change')]")
	WebElement multiAssigneeConfig;
	@FindBy(xpath = "//span[contains(text(),'All assignees')]")
	WebElement AllAssigneesBtn;
	@FindBy(xpath = "//div[contains(@class,'advancedAssignment')]//button[text()='Save']")
	WebElement SaveAssigneeBtn;
	@FindBy(xpath = "(//div[@data-type ='select-icon'])[last()]")
	WebElement AssignedToDropBox;
	
	@FindBy(xpath = "//div[@data-type = 'select-icon']/../div/input")
	WebElement AssignedToInput;
	
	@FindBy(xpath = "(//a[text()='Change'])[last()]")
	WebElement FormulaChangeButton;
	
	@FindBy(xpath = "(//input[@type='text'])[last()]")
	WebElement FormulaInput;
	
	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DoneButton;
	
	@FindBy(xpath = "(//button[text()='Save'])[last()]")
	WebElement SaveButton;
	
public ProcessWorkflowEditor() {
	PageFactory.initElements(driver, this);
}
	@Step("Create workflow to define approval stages")
	public void createWorkflow(String WorkFlowStepName,String Approver1,String Approver2, String Formula) throws InterruptedException {
		if(AddPlusButton.isDisplayed()) {
			Utils.isElementLoaded(driver, AddPlusButton);
			AddPlusButton.click();
			Utils.isElementLoaded(driver, AddNewButton);
			AddNewButton.click();
		}
		
		else {
			Utils.isElementLoaded(driver, AddNewButton);
			AddNewButton.click();
		}
		
		Utils.isElementLoaded(driver, StepName);
		StepName.clear();
		StepName.sendKeys(WorkFlowStepName);
		Utils.isElementLoaded(driver, AssignedToDropBox);
		Thread.sleep(2000);
		AssignedToDropBox.click();
		AssignedToInput.sendKeys(Keys.PAGE_DOWN);
		AssignedToInput.sendKeys(Keys.PAGE_DOWN);
		Utils.isElementLoaded(driver, AssignedToInput);
		AssignedToInput.sendKeys(Approver1);
		
		String firstpart = "// p[text() = '";
		String lastpart = "']";
		String finalpart = firstpart+Approver1+lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		AssignedToInput.sendKeys(Keys.PAGE_DOWN);
		AssignedToInput.sendKeys(Keys.PAGE_DOWN);
		Utils.isElementLoaded(driver, AssignedToInput);
		AssignedToInput.sendKeys(Approver1);
		
		String firstpart1 = "// p[text() = '";
		String lastpart1 = "']";
		String finalpart1 = firstpart+Approver2+lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart1)));
		driver.findElement(By.xpath(finalpart1)).click();
		multiAssigneeConfig.click();
		AllAssigneesBtn.click();
		Utils.isElementLoaded(driver, SaveAssigneeBtn);
		SaveAssigneeBtn.click();
		if(Formula.isEmpty()) {
			System.out.println("Formula is not applied in "+WorkFlowStepName);
		}
		else {
		Utils.isElementLoaded(driver, FormulaChangeButton);
		FormulaChangeButton.click();	
		Utils.isElementLoaded(driver, FormulaInput);
		FormulaInput.sendKeys(Formula);
		Utils.isElementLoaded(driver, DoneButton);
		DoneButton.click();
		}
		Utils.isElementLoaded(driver, SaveButton);
		SaveButton.click();
		takeScreenshot();
	}
	
}
