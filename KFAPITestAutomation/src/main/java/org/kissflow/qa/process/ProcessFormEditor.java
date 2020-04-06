package org.kissflow.qa.process;

import java.util.HashMap;

import org.kissflow.qa.form.TextFieldDefn;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Step;

public class ProcessFormEditor extends org.kissflow.qa.utils.KissflowPage {

	@FindBy(xpath = "(//label[text()='Text'])[last()]")
	WebElement TextFieldButton;

	@FindBy(xpath = "//p[text()='Text']")
	WebElement FieldTypeText;
	@FindBy(xpath = "//button[contains(text(),'Add New Row')]")
	WebElement addNewRowBtn;

	@FindBy(xpath = "(//input[@name = 'Name'])[last()]")
	WebElement TextFieldName;

	@FindBy(xpath = "//input[@placeholder='Field Id']")
	WebElement FieldId;

	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DoneButton;

	@FindBy(xpath = "//label[text()='Untitled field']")
	WebElement UntitledField;

	@FindBy(xpath = "(//button[text()='Delete'])[last()]")
	WebElement DeleteButton;
	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DateAndTimeDone;
	@FindBy(xpath = "//div[text()='100% Done']")
	WebElement CheckAttachComplete;
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	WebElement NextBtn;
	@FindBy(xpath="//span[contains(.,'Unmapped')]")
	WebElement Map1;
	@FindBy(xpath="//ul[@id='optionListContainer']/li/div/span")
	WebElement option1;
	@FindBy(xpath="//ul[@id='optionListContainer']/li[2]/div")
	WebElement option2;
	@FindBy(xpath="//div[contains(text(),'Data import was successfully completed')]")
	WebElement successMsg;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement CancelButton;

	@FindBy(xpath = "//button[contains(text(),'Add table')]")
	WebElement AddTable;

	@FindBy(xpath = "(//div[@class='relative']//div)[3]")
	WebElement AddChildTableColumn;
	
	@FindBy(css = "#newColumn path")
	WebElement DSTableColumn;
	@FindBy(xpath ="//button[contains(text(),'Import CSV')]")
			WebElement DSimport;
	@FindBy(xpath = "(//input[@placeholder='Section title'])[2]")
	WebElement childTableTitle;
	
	@FindBy(xpath = "(//textarea[@placeholder='Section description'])[2]")
	WebElement childTableDescription;
	
	final String tableId = "ThisisAchildTAble";

	public ProcessFormEditor() {
		PageFactory.initElements(driver, this);
	}

	public TextFieldDefn createCommonField(String FiledType, String FieldName) throws InterruptedException {
		TextFieldDefn tfd = new TextFieldDefn();
		Utils.isElementLoaded(driver, TextFieldButton);
		TextFieldButton.click();
		Utils.isElementLoaded(driver, FieldTypeText);
		FieldTypeText.click();
		String firstpart = "//label[@class ='punnai-label' and text()='";
		String lastpart = "']";
		String finalpart = firstpart + FiledType + lastpart;
		System.out.println(finalpart);
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		Utils.isElementLoaded(driver, TextFieldName);
		TextFieldName.click();
		Actions actions = new Actions(driver);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		TextFieldName.sendKeys(FieldName);

		return tfd;

		/*
		 * TextFieldButton.click(); Utils.isElementLoaded(driver, FieldTypeText);
		 * FieldTypeText.click(); String firstpart =
		 * "//label[@class ='punnai-label' and text()='"; String lastpart = "']"; String
		 * finalpart = firstpart + FiledType + lastpart; System.out.println(finalpart);
		 * Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		 * driver.findElement(By.xpath(finalpart)).click();
		 * Utils.isElementLoaded(driver, TextFieldName); TextFieldName.click(); Actions
		 * actions = new Actions(driver); actions.doubleClick(TextFieldName).perform();
		 * actions.sendKeys(Keys.BACK_SPACE);
		 * actions.doubleClick(TextFieldName).perform();
		 * actions.sendKeys(Keys.BACK_SPACE);
		 * actions.doubleClick(TextFieldName).perform();
		 * actions.sendKeys(Keys.BACK_SPACE); actions.doubleClick(FieldId).perform();
		 * actions.sendKeys(Keys.BACK_SPACE); actions.doubleClick(FieldId).perform();
		 * actions.sendKeys(Keys.BACK_SPACE); TextFieldName.sendKeys(FieldName);
		 * FieldId.sendKeys(FieldName); return tfd;
		 */
	}

	public TextFieldDefn createCommonFieldForAttach(String FiledType, String FieldName) throws InterruptedException {
		TextFieldDefn tfd = new TextFieldDefn();
		Utils.isElementLoaded(driver, TextFieldButton);
		TextFieldButton.click();
		Utils.isElementLoaded(driver, FieldTypeText);
		FieldTypeText.click();
		String firstpart = "//label[@class ='punnai-label' and text()='";
		String lastpart = "']";
		String finalpart = firstpart + FiledType + lastpart;
		System.out.println(finalpart);
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		Utils.isElementLoaded(driver, TextFieldName);
		TextFieldName.click();
		Actions actions = new Actions(driver);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		TextFieldName.sendKeys(FieldName);
		FieldId.sendKeys(FieldName);

		return tfd;
	}

	@Step("Create field")
	public void createField(String FiledType, String FieldName, HashMap<String, String> map) throws Exception {
		switch (FiledType) {
		case "Text":
			TextFieldDefn field1 = this.createCommonField(FiledType, FieldName);
			field1.setExtraParams(map);
			break;
		case "Text Area":
			TextFieldDefn textAreafield1 = this.createCommonFieldForAttach(FiledType, FieldName);
			textAreafield1.setExtraParams(map);
			break;
		case "Number":
			TextFieldDefn field2 = this.createCommonField(FiledType, FieldName);
			field2.setExtraParams(map);
			break;
		case "Currency":
			TextFieldDefn currencyfield2 = this.createCommonField(FiledType, FieldName);
			currencyfield2.setExtraParams(map);
			break;
		case "Date":
			TextFieldDefn field3 = this.createCommonField(FiledType, FieldName);
			field3.setExtraParams(map);
			break;
		case "Date & Time":
			TextFieldDefn fieldDateTime = this.createCommonField(FiledType, FieldName);
			fieldDateTime.setExtraParams(map);
			break;

		case "Yes/No":
			TextFieldDefn fieldYesNo = this.createCommonField(FiledType, FieldName);
			fieldYesNo.setExtraParams(map);
			break;

		case "Image":
			TextFieldDefn fieldImage = this.createCommonFieldForAttach(FiledType, FieldName);
			fieldImage.setExtraParams(map);
			break;

		case "Rating":
			TextFieldDefn fieldRating = this.createCommonField(FiledType, FieldName);
			fieldRating.setExtraParams(map);
			break;

		case "Dropdown":
			TextFieldDefn fieldDropdown = this.createCommonField(FiledType, FieldName);
			fieldDropdown.setExtraParams(map);
			break;

		case "Checkbox / Multi-select":
			TextFieldDefn fieldcheckbox = this.createCommonField(FiledType, FieldName);
			fieldcheckbox.setExtraParams(map);
			break;

		case "Slider":
			TextFieldDefn fieldSlider = this.createCommonField(FiledType, FieldName);
			fieldSlider.setExtraParams(map);
			break;

		case "User":
			TextFieldDefn fieldUser = this.createCommonField(FiledType, FieldName);
			fieldUser.setExtraParams(map);
			break;

		case "Checklist":
			TextFieldDefn fieldChecklist = this.createCommonField(FiledType, FieldName);
			fieldChecklist.setExtraParams(map);
			break;

		case "Attachment":
			TextFieldDefn fieldAttachment = this.createCommonFieldForAttach(FiledType, FieldName);
			fieldAttachment.setExtraParams(map);
			break;

		case "Signature":
			TextFieldDefn fieldSignature = this.createCommonField(FiledType, FieldName);
			fieldSignature.setExtraParams(map);
			break;

		case "Lookup":
			TextFieldDefn fieldLookup = this.createCommonField(FiledType, FieldName);
			fieldLookup.setExtraParams(map);
			break;

		case "Remote Lookup":
			TextFieldDefn fieldRemoteLookup = this.createCommonField(FiledType, FieldName);
			fieldRemoteLookup.setExtraParams(map);
			break;

		case "Aggregation":
			TextFieldDefn fieldAgg = this.createCommonField(FiledType, FieldName);
			fieldAgg.setExtraParams(map);
			break;
		default:
			break;
		}
		takeScreenshot();

		String attValue = FieldId.getAttribute("value");

		System.out.println("id:" + FieldId.getAttribute("value"));
		/*
		 * if(FieldId.getAttribute("value").contains("Untitled_Field")) {
		 * FieldId.click(); System.out.println("***Entering if loop*****");
		 * waitForElementTextEqualsString(FieldId,FieldName,driver);
		 * 
		 * if(attValue==FieldName){ }
		 */

		if (!attValue.contains("Untitled_Field"))
			Utils.isElementLoaded(driver, DoneButton);
		DoneButton.click();

	}

	public void waitForElementTextEqualsString(WebElement element, String expectedString, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> element.getAttribute("value")
				.equals(expectedString);
		wait.until(elementTextEqualsString);
	}

	public void clearUntitledField() {
		try {
			if (UntitledField.isDisplayed()) {
				UntitledField.click();
				DeleteButton.click();
				Utils.isElementLoadedSec(driver, CancelButton);
				DeleteButton.click();
			}

			else {
				System.out.println("");
			}
		}

		catch (Exception e) {
			System.out.println("");
		}
	}

	public void createChildTable() throws Exception

	{
		Utils.isElementLoaded(driver, AddTable);
		AddTable.click();
		Actions actions = new Actions(driver);
		actions.doubleClick(childTableTitle).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(childTableTitle).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		childTableTitle.sendKeys(tableId);
		childTableDescription.sendKeys("For Testing");
	}

	public void createChildTableColumn() throws Exception {

		Utils.isElementLoaded(driver, AddChildTableColumn);
		AddChildTableColumn.click();
	}

	public void createDSTableColumn() throws Exception {

		Utils.isElementLoaded(driver, DSTableColumn);
		
		
		Actions actions = new Actions(driver);
		actions.moveToElement(DSTableColumn).click().build().perform();
		//DSTableColumn.click();
	}
	public void addNewRowDS() throws Exception {

		Utils.isElementLoaded(driver, addNewRowBtn);
		
		
//		Actions actions = new Actions(driver);
//		actions.moveToElement(addNewRowBtn).click().build().perform();
		addNewRowBtn.click();
	}
	
	
	public void importCsvData(String InputValue, String FieldName) {
		Utils.isElementLoaded(driver, DSimport);
		DSimport.click();
		
//			String firstpart = "(//label[@class='punnai-label' and text() = '";
//			String lastpart = "']/..//div/button)[last()]";
//			String finalpart = firstpart + FieldName + lastpart;
//			Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
//
//			((JavascriptExecutor) driver)
//					.executeScript("HTMLInputElement.prototype.click = function() {                     "
//							+ "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  "
//							+ "};                                                                  ");
//			// trigger the upload
//			driver.findElement(By.xpath(finalpart)).click();

			// assign the file to the `<input>`
			String currentDirectory = System.getProperty("user.dir");
			String NewInput = currentDirectory+InputValue;
			driver.findElement(By.cssSelector("input[type=file]")).sendKeys(NewInput);
			Utils.isElementLoaded(driver, CheckAttachComplete);
			Utils.isElementLoaded(driver, DateAndTimeDone);
			DateAndTimeDone.click();
			Utils.isElementLoadedClickable(driver,NextBtn);
			NextBtn.click();
			Map1.click();
			option1.click();
			Map1.click();
			option2.click();
			NextBtn.click();
			NextBtn.click();
			Utils.isElementLoadedClickable(driver,successMsg);
			System.out.println("SuccessMsg:"+successMsg.getText());
			Assert.assertEquals(successMsg.getText(), "Data import was successfully completed");
			
			
			
			
			
	}

	public TextFieldDefn createCommonFieldforChild(String FiledType, String FieldName) {
		TextFieldDefn tfd = new TextFieldDefn();
		Utils.isElementLoaded(driver, FieldTypeText);
		FieldTypeText.click();
		String firstpart = "//label[@class ='punnai-label' and text()='";
		String lastpart = "']";
		String finalpart = firstpart + FiledType + lastpart;
		System.out.println(finalpart);
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		Utils.isElementLoaded(driver, TextFieldName);
		TextFieldName.click();
		Actions actions = new Actions(driver);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		TextFieldName.sendKeys(FieldName);
		FieldId.sendKeys(FieldName);
		// TextFieldName.sendKeys(FieldName);
		return tfd;
	}

	@Step("Create field in child table")
	public void createFieldForChild(String FiledType, String FieldName, HashMap<String, String> map) throws Exception {
		switch (FiledType) {
		case "Text":
			TextFieldDefn field1 = this.createCommonFieldforChild(FiledType, FieldName);
			field1.setExtraParams(map);
			break;
		case "Text Area":
			TextFieldDefn textAreafield1 = this.createCommonFieldforChild(FiledType, FieldName);
			textAreafield1.setExtraParams(map);
			break;
		case "Number":
			TextFieldDefn field2 = this.createCommonFieldforChild(FiledType, FieldName);
			field2.setExtraParams(map);
			break;
		case "Currency":
			TextFieldDefn currencyfield2 = this.createCommonFieldforChild(FiledType, FieldName);
			currencyfield2.setExtraParams(map);
			break;
		case "Date":
			TextFieldDefn field3 = this.createCommonFieldforChild(FiledType, FieldName);
			field3.setExtraParams(map);
			break;
		case "Date & Time":
			TextFieldDefn fieldDateTime = this.createCommonFieldforChild(FiledType, FieldName);
			fieldDateTime.setExtraParams(map);
			break;
		case "Yes/No":
			TextFieldDefn fieldYesNo = this.createCommonFieldforChild(FiledType, FieldName);
			fieldYesNo.setExtraParams(map);
			break;
		case "Image":
			TextFieldDefn fieldImage = this.createCommonFieldforChild(FiledType, FieldName);
			fieldImage.setExtraParams(map);
			break;
		case "Rating":
			TextFieldDefn fieldRating = this.createCommonFieldforChild(FiledType, FieldName);
			fieldRating.setExtraParams(map);
			break;
		case "Dropdown":
			TextFieldDefn fieldDropdown = this.createCommonFieldforChild(FiledType, FieldName);
			fieldDropdown.setExtraParams(map);
			break;
		case "Checkbox / Multi-select":
			TextFieldDefn fieldcheckbox = this.createCommonFieldforChild(FiledType, FieldName);
			fieldcheckbox.setExtraParams(map);
			break;
		case "Slider":
			TextFieldDefn fieldSlider = this.createCommonFieldforChild(FiledType, FieldName);
			fieldSlider.setExtraParams(map);
			break;
		case "User":
			TextFieldDefn fieldUser = this.createCommonFieldforChild(FiledType, FieldName);
			fieldUser.setExtraParams(map);
			break;
		case "Checklist":
			TextFieldDefn fieldChecklist = this.createCommonFieldforChild(FiledType, FieldName);
			fieldChecklist.setExtraParams(map);
			break;
		case "Attachment":
			TextFieldDefn fieldAttachment = this.createCommonFieldforChild(FiledType, FieldName);
			fieldAttachment.setExtraParams(map);
			break;
		case "Signature":
			TextFieldDefn fieldSignature = this.createCommonFieldforChild(FiledType, FieldName);
			fieldSignature.setExtraParams(map);
			break;
		case "Lookup":
			TextFieldDefn fieldLookup = this.createCommonFieldforChild(FiledType, FieldName);
			fieldLookup.setExtraParams(map);
			break;
		case "Remote Lookup":
			TextFieldDefn fieldRemoteLookup = this.createCommonFieldforChild(FiledType, FieldName);
			fieldRemoteLookup.setExtraParams(map);
			break;
		case "Aggregation":
			TextFieldDefn fieldAgg = this.createCommonFieldforChild(FiledType, FieldName);
			fieldAgg.setExtraParams(map);
			break;
		default:
			break;
		}
		takeScreenshot();
		Utils.isElementLoaded(driver, DoneButton);
		DoneButton.click();
	}
}
