package org.kissflow.qa.form;

import java.util.HashMap;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KissflowFieldDefn extends org.kissflow.qa.utils.KissflowPage {
	@FindBy(xpath = "//input[@name='DefaultValue']")
	WebElement DefaultValueInput;
	@FindBy(xpath = "//div[@data-type ='select-icon']")
	WebElement lookUpField;
	@FindBy(xpath = "//label[text()='Required?']/../div")
	WebElement IsRequiredBtn;

	@FindBy(xpath = "//label[text()='Which list to use?']/../div[1]")
	WebElement DataSetPick;

	@FindBy(xpath = "//label[text()='Choose a flow to look up']/../div")
	WebElement ChooseLookup;

	@FindBy(xpath = "//label[text()='Select all dataset fields that will be used by this process']/../div")
	WebElement ChooseLookupField;

	@FindBy(xpath = "//label[text()='URL']/../div/div")
	WebElement RemoteLookupURL;
	
	@FindBy(xpath = "//label[text()='JSON path of value in result']/../div/div")
	WebElement RemoteLookupResult;

	@FindBy(xpath = "//label[text()='Select a field to aggregate']/../div/div")
	WebElement AggField;

	@FindBy(xpath = "//label[text()='Aggregation type']/../div/div")
	WebElement AggType;

	@FindBy(xpath = "//label[text()='Minimum Value']/../input")
	WebElement MinValueInput;

	@FindBy(xpath = "//label[text()='Maximum Value']/../input")
	WebElement MaxValueInput;

	@FindBy(xpath = "//label[text()='Interval size']/../input")
	WebElement IntervalsizeInput;

	public KissflowFieldDefn() {
		PageFactory.initElements(driver, this);
	}

	public KissflowFieldDefn setDefaultValue(String defaultValue) {
		Utils.isElementLoaded(driver, DefaultValueInput);
		DefaultValueInput.sendKeys(defaultValue);
		return this;
	}

	public void setExtraParams(HashMap<String, String> map) {
		map.entrySet().forEach(action -> {
			try {
				this.setExtraParam(action.getKey(), action.getValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public void setExtraParam(String key, String val) throws InterruptedException {
		switch (key) {
		case "Default":
			this.setDefaultValue(val);
			break;
		case "Required":
			this.setRequired(val);
			break;
		case "Datalist":
			this.setDatalist(val);
			break;
		case "Aadharam":
			this.setLookupSource(val);
			
			break;
		case "Paguthi":
			this.setLookupField(val);
			break;
		case "RemoteLookupUrl":
			this.setRemoteLookupSource(val);
			
			break;
		case "RemoteLookUpJsonPath":
			this.setRemoteLookupResult(val);
			break;
		case "AggFieldSelect":
			this.setAggregateField(val);
			break;
		case "AggTypeSelect":
			this.setAggregateType(val);
			break;
		case "Min":
			this.setMinRange(val);
			break;
		case "Max":
			this.setMaxRange(val);
			break;
		case "IntervalSize":
			this.setIntervalRange(val);
			break;
		default:
			break;
		}

	}

	protected void setRequired(String Yes) {
		Utils.isElementLoaded(driver, IsRequiredBtn);
		IsRequiredBtn.click();
	}

	protected void setDatalist(String DatalistName) {
		Utils.isElementLoaded(driver, DataSetPick);
		DataSetPick.click();
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart + DatalistName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
	}

	protected void setLookupSource(String LookupName) {
		Utils.isElementLoaded(driver, ChooseLookup);
		ChooseLookup.click();
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart + LookupName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
	}


	protected void setLookupField(String LookupField){
		Utils.isElementLoaded(driver, ChooseLookupField);
		ChooseLookupField.click();
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart + LookupField + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
	}

	protected void setRemoteLookupSource(String RemoteLookupUrl){
//		Utils.isElementLoaded(driver, RemoteLookupURL);
//		new Actions(driver).moveToElement(RemoteLookupURL).perform();
		Utils.isElementLoaded(driver, RemoteLookupURL);
		RemoteLookupURL.sendKeys(RemoteLookupUrl);


		//Thread.sleep(4000);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",RemoteLookupURL);
		//RemoteLookupURL.isEnabled();
		//RemoteLookupURL.click();
//		Actions actions = new Actions(driver);
//		actions.doubleClick(RemoteLookupURL).perform();
//		actions.sendKeys(RemoteLookupUrl);
//		Utils.isElementLoaded(driver, RemoteLookupURL);
		//RemoteLookupURL.sendKeys(RemoteLookupUrl);
//		
	}

	protected void setRemoteLookupResult(String RemoteLookupPath) {
		Utils.isElementLoaded(driver, RemoteLookupResult);
		RemoteLookupResult.sendKeys(RemoteLookupPath);
		

	}

	protected void setAggregateField(String AggFieldName) {
		Utils.isElementLoaded(driver, AggField);
		AggField.click();
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart + AggFieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
	}

	protected void setAggregateType(String AggregationType) throws InterruptedException {
		Utils.isElementLoaded(driver, AggType);
		AggType.click();
		String firstpart = "//p[text()='";
		String lastpart = "']";
		String finalpart = firstpart + AggregationType + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
	}

	protected void setMinRange(String MinVal) {
		Utils.isElementLoaded(driver, MinValueInput);
		MinValueInput.sendKeys(MinVal);
		;

	}

	protected void setMaxRange(String MaxVal) {
		Utils.isElementLoaded(driver, MaxValueInput);
		MaxValueInput.sendKeys(MaxVal);
		;

	}

	protected void setIntervalRange(String IntervalSize) {
		Utils.isElementLoaded(driver, IntervalsizeInput);
		IntervalsizeInput.sendKeys(IntervalSize);
		;

	}
}
