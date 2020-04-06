package org.kissflow.qa.process;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.qameta.allure.Step;


public class NewItemPage extends org.kissflow.qa.utils.KissflowPage {
	@FindBy(xpath = "(//button[text()='Save'])[last()]")
	WebElement SaveButton;

	@FindBy(xpath = "(//label[@class='punnai-label']/..//small)[last()]")
	WebElement ErrorMessageText;

	@FindBy(xpath = "(//button[text()='Submit'])[last()]")
	WebElement SubmitButton;

	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DateAndTimeDone;

	@FindBy(xpath = "(//button[text()='Next'])[last()]")
	WebElement NextBtn;

	@FindBy(xpath = "(//button[text()='Replace Image'])[last()]")
	WebElement ReplaceImageBtn;
	
	@FindBy(xpath = "//div[text()='100% Done']")
	WebElement CheckAttachComplete;
	
	@FindBy(xpath = "//button[contains(text(),'Add New Row')]")
	WebElement AddNewRow;
	
	// Initializing the Page Objects:
	public NewItemPage() {
		PageFactory.initElements(driver, this);
	}

	@Step("Enter value to the field")
	public void enterValueToField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/..//input)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).sendKeys(InputValue);
		takeScreenshot();
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}

	@Step("Enter value to the Date and Time field")
	public void enterValueToDateTimeField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/..//input)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		WebElement element = driver.findElement(By.xpath(finalpart));
		element.sendKeys(Keys.PAGE_DOWN);
		element.sendKeys(InputValue);
		takeScreenshot();
		Utils.isElementLoaded(driver, DateAndTimeDone);
		DateAndTimeDone.click();
		takeScreenshot();

	}

	@Step("Enter value to the Text Area field")
	public void enterValueToTetAreaField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/../div/div[1]/div)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).sendKeys(InputValue);
		takeScreenshot();

	}

	@Step("Select Yes/No value to the field")
	public void selectYesNoValueToField(String InputValue, String FieldName) throws InterruptedException {
		if (InputValue.matches("Yes")) {
			String firstpart = "(//label[@class='punnai-label' and text() = '";
			String lastpart = "']/..//div)[last()]";
			String finalpart = firstpart + FieldName + lastpart;
			Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
			driver.findElement(By.xpath(finalpart)).click();
		} else {

		}
		takeScreenshot();

	}

	@Step("Upload the image for image field")
	public void uploadImage(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/..//div/button)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));

		((JavascriptExecutor) driver)
				.executeScript("HTMLInputElement.prototype.click = function() {                     "
						+ "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  "
						+ "};                                                                  ");
		// trigger the upload
		driver.findElement(By.xpath(finalpart)).click();

		// assign the file to the `<input>`
		String currentDirectory = System.getProperty("user.dir");
		String NewInput = currentDirectory+InputValue;
		driver.findElement(By.cssSelector("input[type=file]")).sendKeys(NewInput);
		Utils.isElementLoaded(driver, NextBtn);
		NextBtn.click();
		Utils.isElementLoaded(driver, DateAndTimeDone);
		DateAndTimeDone.click();
		takeScreenshot();
		Utils.isElementLoaded(driver, ReplaceImageBtn);

	}

	public void Save() throws InterruptedException {
		Utils.isElementLoaded(driver, SaveButton);
		SaveButton.click();
	}

	public void verifyErrorMessage(String ErrorMess) throws InterruptedException {
		Utils.isElementLoaded(driver, ErrorMessageText);
		String ErrorMessage = ErrorMessageText.getText();
		Assert.assertEquals(ErrorMessage, ErrorMess);
		System.out.println("Error message " + ErrorMessage + " is verified");
	}

	public ProcessHomePage Submit() throws InterruptedException {
		Utils.isElementLoaded(driver, SubmitButton);
		SubmitButton.click();
		return new ProcessHomePage();
	}
	
	@Step("Enter value to the Rating field")
	public void enterValueToRatingField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String nextpart = "']/../div/span[";
		String lastpart = "])[last()]";
		String finalpart = firstpart + FieldName + nextpart + InputValue + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		takeScreenshot();
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}
	
	@Step("Enter value to the Dropdown field")
	public void enterValueToCheckboxAndDropdownField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/../div)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		driver.findElement(By.xpath(finalpart)).sendKeys(InputValue);
		String value1 = "// p[text() = '";
		String value2 = "']";
		String finalvalue = value1+InputValue+value2;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalvalue)));
		driver.findElement(By.xpath(finalvalue)).click();
		takeScreenshot();
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}
	@Step("Select user from User field")
	public void selectUserFromUserField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/../div)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		String value1 = "// p[text() = '";
		String value2 = "']";
		String finalvalue = value1+InputValue+value2;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalvalue)));
		driver.findElement(By.xpath(finalvalue)).click();
		takeScreenshot();
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}
	
	@Step("Select value from Checklist field")
	public void selectValueFromChecklistField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String nextpart = "']/..//*[text()='";
		String lastpart = "'])[last()]";
		String finalpart = firstpart + FieldName + nextpart + InputValue + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		takeScreenshot();
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}
	
	@Step("Upload attachment for Attachment field")
	public void uploadAttachment(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/..//div/button)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));

		((JavascriptExecutor) driver)
				.executeScript("HTMLInputElement.prototype.click = function() {                     "
						+ "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  "
						+ "};                                                                  ");
		// trigger the upload
		driver.findElement(By.xpath(finalpart)).click();

		// assign the file to the `<input>`
		String currentDirectory = System.getProperty("user.dir");
		String NewInput = currentDirectory+InputValue;
		driver.findElement(By.cssSelector("input[type=file]")).sendKeys(NewInput);
		Utils.isElementLoaded(driver, CheckAttachComplete);
		Utils.isElementLoaded(driver, DateAndTimeDone);
		DateAndTimeDone.click();
		takeScreenshot();
	}
	
	@Step("Sign to the Signature field")
	public void signToSignatureField(String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/../div/canvas)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));		
		WebElement element = driver.findElement(By.xpath(finalpart));
	    Actions builder = new Actions(driver);
	    Action drawAction = builder.moveToElement(element,0,0) //start points x axis and y axis. 
	              .click()
	              .moveByOffset(15,1 ) // 2nd points (x1,y1)
	              .click()
	              .moveByOffset(15,2 ) // 2nd points (x1,y1)
	              .click()
	              .moveByOffset(15,3 ) // 2nd points (x1,y1)
	              .click()
	              .moveByOffset(8, 1)// 3rd points (x2,y2)
	              .moveByOffset(8, 2)// 3rd points (x2,y2)
	              .moveByOffset(8, 3)// 3rd points (x2,y2)
	              .doubleClick()
	              .build();
	    drawAction.perform();
		takeScreenshot();
	}
	
	@Step("Select value for Lookup field")
	public void selectValueForLookUpField(String InputValue, String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/../div)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		String value1 = "// div[text() = 'Request for ";
		String value2 = "']";
		String finalvalue = value1+InputValue+value2;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalvalue)));
		driver.findElement(By.xpath(finalvalue)).click();
		takeScreenshot();
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}
	@Step("Get value for Remotelookup field")
	public void getdataForRemoteLookUpField(String FieldName) throws InterruptedException {
		String firstpart = "(//label[@class='punnai-label' and text() = '";
		String lastpart = "']/../div/button)[last()]";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		String thirdpart = "']/../div[1]/input)[last()]";
		String finalvalue = firstpart+FieldName+thirdpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalvalue)));
		takeScreenshot();
		Actions act = new Actions(driver);
		act.release();
	}
	
	@Step("Set value for Slider field")
	public void setValueForSliderField(String InputValue,String FieldName) throws InterruptedException {
		String firstpart = "//label[@class='punnai-label' and text() = '";
		String secondpart = "']/..//*[text()='";
		String thirdpart = "']";
		String finalpart = firstpart + FieldName +secondpart+InputValue+thirdpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		takeScreenshot();
	}
	
	public void addNewRownTable() {
		AddNewRow.click();
	}
}
