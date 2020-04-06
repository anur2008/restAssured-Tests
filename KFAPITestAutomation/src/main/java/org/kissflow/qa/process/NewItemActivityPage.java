package org.kissflow.qa.process;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.qameta.allure.Step;


public class NewItemActivityPage extends org.kissflow.qa.utils.KissflowPage {
	
	@FindBy(xpath = "(//button[text()='Send Back'])[last()]")
	WebElement SendBackBtn;
	
	@FindBy(xpath = "//div[text()='Send back to']/../div//div[2]")
	WebElement SendBackToInput;
	
	@FindBy(xpath = "//*[@name='sendBackComments']")
	WebElement SendBackCommentInput;
	
	@FindBy(xpath = "(//button[text()='Reject'])[last()]")
	WebElement RejectBtn;
	
	@FindBy(xpath = "//*[@name='rejectComments']")
	WebElement RejectCommentInput;
	
	
	@FindBy(xpath = "(//button[text()='Reassign'])[last()]")
	WebElement ReassignBtn;
	
	@FindBy(xpath = "//div[text()='Reassign to']/../div//div[2]")
	WebElement ReassignToInput;
	
	
	@FindBy(xpath = "//*[@name='reassignComments']")
	WebElement ReassignCommentInput;
	
	
	@FindBy(xpath = "(//button[text()='Restart'])[last()]")
	WebElement RestartBtn;
	
	@FindBy(xpath = "(//button[text()='Withdraw'])[last()]")
	WebElement WithdrawBtn;
	
	@FindBy(xpath = "//*[@name='withdrawComments']")
	WebElement WithdrawCommentInput;
	
	@FindBy(xpath = "(//button[text()='Submit'])[last()]")
	WebElement SubmitButton;
	
	public NewItemActivityPage() {
		PageFactory.initElements(driver, this);
	}
@Step("Check field created successfully")
	public void checkCreatedField(String FieldName) throws InterruptedException {
		String firstpart = "//label[@class ='punnai-label' and text()='";
		String lastpart = "']";
		String finalpart = firstpart + FieldName + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		String fieldName = driver.findElement(By.xpath(finalpart)).getText();
		if (fieldName.contains(FieldName)) {
			System.out.println(fieldName + " field is created successfully");
		}
		takeScreenshot();

	}
@Step("Verify pending approver name")
	public void verifyApprovername(String Approver) {
		String firstpart = "//div[text()='";
		String lastpart = "']";
		String finalpart = firstpart + Approver + lastpart;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		String fieldName = driver.findElement(By.xpath(finalpart)).getText();
		Assert.assertEquals(fieldName, Approver);
		System.out.println("The current step is pending with "+fieldName);
		takeScreenshot();

	}

@Step("Check created field value")
public void checkCreatedFieldValue(String FieldName, String InputValue) throws InterruptedException {
	String firstpart = "(//label[@class='punnai-label' and text() = '";
	String lastpart = "']/../div)[last()]";
	String finalpart = firstpart + FieldName + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	String fieldValue = driver.findElement(By.xpath(finalpart)).getText();
	Assert.assertEquals(fieldValue, InputValue);
	System.out.println(fieldValue + "  value is verified successfully in the field of "+FieldName);
	
	takeScreenshot();

}

@Step("Mouseover and find the current field value")
public void overAndFindCurrentFieldValue(String FieldName, String InputValue) throws InterruptedException {
	String firstpart = "(//label[@class='punnai-label' and text() = '";
	String lastpart = "']/../div)[last()]";
	String finalpart = firstpart + FieldName + lastpart;
	WebElement element1 = driver.findElement(By.xpath(finalpart));
	Utils.isElementLoaded(driver, element1);
	Actions act = new Actions(driver);
	act.moveToElement(element1).build().perform(); 
	act.release();
	String partA = "//*[text()='";
	String partB = "']/../..";
	String newPart = partA+InputValue+partB;
	WebElement element3 = driver.findElement(By.xpath(newPart));
	String value = element3.getText();
	if(value.contains(InputValue)) {
		System.out.println("The current time stamp is "+value);
	}
	takeScreenshot();
	
}

@Step("Check created field value contains")
public void checkCreatedFieldContainsValue(String FieldName, String InputValue) throws InterruptedException {
	String firstpart = "(//label[@class='punnai-label' and text() = '";
	String lastpart = "']/../div)[last()]";
	String finalpart = firstpart + FieldName + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	String fieldValue = driver.findElement(By.xpath(finalpart)).getText();
	if(fieldValue.contains(InputValue)) {
		System.out.println(fieldValue + "  value is verified successfully in the field of "+FieldName);

	}
	
	
	takeScreenshot();

}
@Step("Check the uploaded image in Image field")
public void checkUploadedImage(String FieldName) throws InterruptedException {
	String firstpart = "(//label[@class='punnai-label' and text() = '";
	String lastpart = "']/..//div/img)[last()]";
	String finalpart = firstpart + FieldName + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	System.out.println("Image is verified successfully in the field of "+FieldName);
	takeScreenshot();
	
}

@Step("Check the given rating in Rating field")
public void checkRatingInRatingField(String FieldName, String InputValue ) throws InterruptedException {
	String firstpart = "(//label[@class='punnai-label' and text() = '";
	String nextpart = "']/../div/span[";
	String lastpart = "])[last()]";
	String finalpart = firstpart + FieldName + nextpart + InputValue + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	System.out.println("Image is verified successfully in the field of "+FieldName);
	takeScreenshot();
	
}
@Step("Close the New Item activity page")
public void closeNewItemActivityOfProcess(String ProcessName) throws InterruptedException {
	
	String firstpart = "//div[text()='Request for ";
	String lastpart = "']/../div[last()]";
	String finalpart = firstpart + ProcessName + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	driver.findElement(By.xpath(finalpart)).click();
	takeScreenshot();
	
}

@Step("Close the New Item activity for Project")
public void closeNewItemActivityOfProject(String ProcessName) throws InterruptedException {
	
	String firstpart = "//div[text()='";
	String lastpart = "']/../div[last()]";
	String finalpart = firstpart + ProcessName + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	driver.findElement(By.xpath(finalpart)).click();
	takeScreenshot();
	
}

@Step("Sendback the process")
public void sendbackTo(String ApprovalLevel, String SendbackComment) throws InterruptedException {
	Utils.isElementLoaded(driver, SendBackBtn);
	SendBackBtn.click();
	Utils.isElementLoaded(driver, SendBackToInput);
	SendBackToInput.click();
	String firstpart = "//p[text()='";
	String lastpart = "']";
	String finalpart = firstpart + ApprovalLevel + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	driver.findElement(By.xpath(finalpart)).click();
	Utils.isElementLoaded(driver, SendBackCommentInput);
	SendBackCommentInput.sendKeys(SendbackComment);
	Utils.isElementLoaded(driver, SendBackBtn);
	SendBackBtn.click();
	takeScreenshot();
	
}
@Step("Verify the Sendback process")
public void verifySendBackCommentFromOther(String SenderName, String SendbackComment) throws InterruptedException {
	String firstpart = "//div[text()='";
	String lastpart = "']/..";
	String finalpart = firstpart + SendbackComment + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	String comment = driver.findElement(By.xpath(finalpart)).getText();
	if(comment.contains(SenderName)) {
		System.out.println("Process is sent back with the comment of "+comment);
	}
	takeScreenshot();
	
}
@Step("Reject the process")
public void rejectProcess(String RejectComment) throws InterruptedException {
	Utils.isElementLoaded(driver, RejectBtn);
	RejectBtn.click();
	Utils.isElementLoaded(driver, RejectCommentInput);
	RejectCommentInput.sendKeys(RejectComment);
	Utils.isElementLoaded(driver, RejectBtn);
	RejectBtn.click();
	takeScreenshot();
	
}
@Step("Verify the Rejected process")
public void verifyRejectedCommentFromOther(String RejectorName, String RejectComment) throws InterruptedException {
	String firstpart = "//div[text()='";
	String lastpart = "']/..";
	String finalpart = firstpart + RejectComment + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	String comment = driver.findElement(By.xpath(finalpart)).getText();
	if(comment.contains(RejectorName)) {
		System.out.println("Process is rejected with the comment of "+comment);
	}
	takeScreenshot();
	
}
@Step("Reassign the process")
public void reassignTo(String ReassignTo, String ReassignComment) throws InterruptedException {
	Utils.isElementLoaded(driver, ReassignBtn);
	ReassignBtn.click();
	Utils.isElementLoaded(driver, ReassignToInput);
	ReassignToInput.click();
	String firstpart = "//p[text()='";
	String lastpart = "']";
	String finalpart = firstpart + ReassignTo + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	driver.findElement(By.xpath(finalpart)).click();
	Utils.isElementLoaded(driver, ReassignCommentInput);
	ReassignCommentInput.sendKeys(ReassignComment);
	Utils.isElementLoaded(driver, ReassignBtn);
	ReassignBtn.click();
	takeScreenshot();
	
}
@Step("Verify the Reassigned process")
public void verifyReassignCommentFromOther(String ReassignerName, String ReassignComment) throws InterruptedException {
	String firstpart = "//div[text()='";
	String lastpart = "']/..";
	String finalpart = firstpart + ReassignComment + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	String comment = driver.findElement(By.xpath(finalpart)).getText();
	if(comment.contains(ReassignerName)) {
		System.out.println("Process is reassigned with the comment of "+comment);
	}
	takeScreenshot();
	
}
@Step("Restart the process")
public void restartProcess() throws InterruptedException {
	Utils.isElementLoaded(driver, RestartBtn);
	RestartBtn.click();
	takeScreenshot();
}
@Step("Withdraw the process")
public void withdrawProcess(String WithDrawNotes) throws InterruptedException {
	Utils.isElementLoaded(driver, WithdrawBtn);
	WithdrawBtn.click();
	Utils.isElementLoaded(driver, WithdrawCommentInput);
	WithdrawCommentInput.sendKeys(WithDrawNotes);
	Utils.isElementLoaded(driver, WithdrawBtn);
	WithdrawBtn.click();
	takeScreenshot();
}
@Step("Verify the withdrawn process")
public void verifyWithdrawCommentFromOther(String WithDrawerName, String WithdrawComment) throws InterruptedException {
	String firstpart = "//div[text()='";
	String lastpart = "']/..";
	String finalpart = firstpart + WithdrawComment + lastpart;
	Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
	String comment = driver.findElement(By.xpath(finalpart)).getText();
	if(comment.contains(WithDrawerName)) {
		System.out.println("Process is withdraw with the comment of "+comment);
	}
	takeScreenshot();
	
}

public void Submit() throws InterruptedException {
	Utils.isElementLoaded(driver, SubmitButton);
	SubmitButton.click();
}
}
