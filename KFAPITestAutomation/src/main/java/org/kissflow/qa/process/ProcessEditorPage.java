	package org.kissflow.qa.process;

import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class ProcessEditorPage extends org.kissflow.qa.utils.KissflowPage {
	@FindBy(xpath = "(//div[text()='Form'])[last()]")
	WebElement FormBtn;
	
	
	@FindBy(xpath = "(//div[text()='Workflow'])[last()]")
	WebElement WorkflowButton;
	
	
	@FindBy(xpath = "(//div[text()='Permissions'])[last()]")
	WebElement PermissionButton;
	
	@FindBy(xpath = "(//button[text()='Publish'])[last()]")
	WebElement PublishButton;
	
	public ProcessEditorPage() {
		PageFactory.initElements(driver, this);
	}
	public ProcessFormEditor navToFormEditor() {
		Utils.isElementLoaded(driver, FormBtn);
		FormBtn.click();
		takeScreenshot();
		return new ProcessFormEditor();
	}
	
	public ProcessWorkflowEditor navToWorkflowEditor() {
		Utils.isElementLoaded(driver, WorkflowButton);
		WorkflowButton.click();
		return new ProcessWorkflowEditor();
	}
	
	public ProcessPermissionEditor navToPermissionEditor() {
		Utils.isElementLoaded(driver, PermissionButton);
		PermissionButton.click();
		return new ProcessPermissionEditor();
	}
	
	public NewItemPage publish() throws InterruptedException {
		Thread.sleep(2000);
		Utils.isElementLoaded(driver, PublishButton);
		PublishButton.click();
		takeScreenshot();
		return new NewItemPage();
		
	}
}
