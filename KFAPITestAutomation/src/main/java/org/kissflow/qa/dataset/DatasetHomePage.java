package org.kissflow.qa.dataset;

import java.util.HashMap;

import org.kissflow.qa.form.TextFieldDefn;
import org.kissflow.qa.process.NewItemActivityPage;
import org.kissflow.qa.utils.KissflowPage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class DatasetHomePage extends KissflowPage {
	@FindBy(xpath = "//input[@placeholder='Enter name']")
	WebElement DatasetName;
	@FindBy(xpath = "(//label[text()='Text'])[last()]")
	WebElement TextFieldButton;

	@FindBy(xpath = "//p[text()='Text']")
	WebElement FieldTypeText;

	@FindBy(xpath = "(//input[@name = 'Name'])[last()]")
	WebElement TextFieldName;

	@FindBy(xpath = "//input[@placeholder='Field Id']")
	WebElement FieldId;

	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DoneButton;
	public DatasetHomePage() {
		PageFactory.initElements(driver, this);
	}

	NewItemActivityPage nia = new NewItemActivityPage();

	public String getDsName() {
		
		Utils.isElementLoaded(driver, DatasetName);
		String CreatedDatasetName = DatasetName.getText();
		return CreatedDatasetName;
	}
	@Step("Add fields to DS")
	
		public TextFieldDefn createCommonFieldDS(String FiledType, String FieldName) throws InterruptedException {
			TextFieldDefn tfd = new TextFieldDefn();
			Utils.isElementLoaded(driver, TextFieldButton);
			TextFieldButton.click();
			Utils.isElementLoaded(driver, FieldTypeText);
			FieldTypeText.click();
			String firstpart = "//label[@class ='punnai-label' and text()='";
			String lastpart = "']";
			String finalpart = firstpart+FiledType+lastpart;
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
	}
			@Step("Create field")
			public void createField(String FiledType, String FieldName, HashMap<String, String> map) throws Exception {
				switch (FiledType) {
				case "Text":
					TextFieldDefn field1 = this.createCommonFieldDS(FiledType, FieldName);
					field1.setExtraParams(map);
					break;
				case "Text Area":
					TextFieldDefn textAreafield1 = this.createCommonFieldDS(FiledType, FieldName);
					textAreafield1.setExtraParams(map);
					break;
				case "Number":
					TextFieldDefn field2 = this.createCommonFieldDS(FiledType, FieldName);
					field2.setExtraParams(map);
					break;
				case "Currency":
					TextFieldDefn currencyfield2 = this.createCommonFieldDS(FiledType, FieldName);
					currencyfield2.setExtraParams(map);
					break;
				case "Date":
					TextFieldDefn field3 = this.createCommonFieldDS(FiledType, FieldName);
					field3.setExtraParams(map);
					break;
				case "Date & Time":
					TextFieldDefn fieldDateTime = this.createCommonFieldDS(FiledType, FieldName);
					fieldDateTime.setExtraParams(map);
					break;

				case "Yes/No":
					TextFieldDefn fieldYesNo = this.createCommonFieldDS(FiledType, FieldName);
					fieldYesNo.setExtraParams(map);
					break;

				case "Image":
					TextFieldDefn fieldImage = this.createCommonFieldDS(FiledType, FieldName);
					fieldImage.setExtraParams(map);
					break;

				case "Rating":
					TextFieldDefn fieldRating = this.createCommonFieldDS(FiledType, FieldName);
					fieldRating.setExtraParams(map);
					break;

				case "Dropdown":
					TextFieldDefn fieldDropdown = this.createCommonFieldDS(FiledType, FieldName);
					fieldDropdown.setExtraParams(map);
					break;

				case "Checkbox / Multi-select":
					TextFieldDefn fieldcheckbox = this.createCommonFieldDS(FiledType, FieldName);
					fieldcheckbox.setExtraParams(map);
					break;

				case "Slider":
					TextFieldDefn fieldSlider = this.createCommonFieldDS(FiledType, FieldName);
					fieldSlider.setExtraParams(map);
					break;

				case "User":
					TextFieldDefn fieldUser = this.createCommonFieldDS(FiledType, FieldName);
					fieldUser.setExtraParams(map);
					break;

				case "Checklist":
					TextFieldDefn fieldChecklist = this.createCommonFieldDS(FiledType, FieldName);
					fieldChecklist.setExtraParams(map);
					break;

				case "Attachment":
					TextFieldDefn fieldAttachment = this.createCommonFieldDS(FiledType, FieldName);
					fieldAttachment.setExtraParams(map);
					break;

				case "Signature":
					TextFieldDefn fieldSignature = this.createCommonFieldDS(FiledType, FieldName);
					fieldSignature.setExtraParams(map);
					break;

				case "Lookup":
					TextFieldDefn fieldLookup = this.createCommonFieldDS(FiledType, FieldName);
					fieldLookup.setExtraParams(map);
					break;

				case "Remote Lookup":
					TextFieldDefn fieldRemoteLookup = this.createCommonFieldDS(FiledType, FieldName);
					fieldRemoteLookup.setExtraParams(map);
					break;

				case "Aggregation":
					TextFieldDefn fieldAgg = this.createCommonFieldDS(FiledType, FieldName);
					fieldAgg.setExtraParams(map);
					break;
				default:
					break;
				}

	}
}
