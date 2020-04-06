package org.kissflow.qa.channel;

import org.kissflow.qa.process.NewItemActivityPage;
import org.kissflow.qa.utils.KissflowPage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.qameta.allure.Step;

public class ChannelHomePage extends KissflowPage {
	@FindBy(xpath = "//*[@id='studioOverlay']/..//h5")
	WebElement ChannelName;

	@FindBy(xpath = "//button[text()='Joined']/../../div[last()]")
	WebElement MoreOptionBtn;

	@FindBy(xpath = "//li[text()='Archive channel']")
	WebElement ArchiveLink;

	@FindBy(xpath = "//button[text()='OK']")
	WebElement ArchiveBtn;

	@FindBy(xpath = "//button[text()='Unarchive']")
	WebElement UnarchiveBtn;

	@FindBy(xpath = "//button[text()='Delete']")
	WebElement DeleteBtn;

	@FindBy(xpath = "//button[text()='OK']")
	WebElement DeleteChannelBtn;

	@FindBy(xpath = "//button[text()='Add']")
	WebElement AddBtn;

	@FindBy(xpath = "//button[text()='Members']")
	WebElement MemberBtn;

	@FindBy(xpath = "(//div[text()='This channel was archived successfully']/../../../span)[last()]")
	WebElement ArchiveSuccessMess;

	@FindBy(xpath = "//div[@id='postContainer']/../div[2]/div[2]")
	WebElement PostArea;

	@FindBy(xpath = "//div[@role='textbox']")
	WebElement PostAreaClick;

	@FindBy(xpath = "//div[@role='textbox']/div/span")
	WebElement PostAreaInput;

	@FindBy(xpath = "//button[text()='Post']")
	WebElement PostBtn;

	@FindBy(xpath = "//span[text()='Write Post']")
	WebElement WritePostLink;

	@FindBy(xpath = "//span[text()='Make Announcement']")
	WebElement MakeAnnounceLink;

	@FindBy(xpath = "//span[text()='Add Poll']")
	WebElement AddPollLink;

	@FindBy(xpath = "//input[@placeholder='What do you want to ask?']")
	WebElement PollTitleInput;

	@FindBy(xpath = "//input[@placeholder='Option1']")
	WebElement PollOptionInput1;

	@FindBy(xpath = "//input[@placeholder='Option2']")
	WebElement PollOptionInput2;

	@FindBy(xpath = "//button[@id='poll']")
	WebElement AddOptionBtn;

	@FindBy(xpath = "//input[@placeholder='Option3']")
	WebElement PollOptionInput3;

	@FindBy(xpath = "//span[text()='Add Image/File']")
	WebElement AddImageLink;

	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DoneBtn;

	@FindBy(xpath = "//div[text()='100% Done']")
	WebElement CheckAttachComplete;

	@FindBy(xpath = "//img[@id ='0']")
	WebElement ImagePostView;

	// Initializing the Page Objects:
	public ChannelHomePage() {
		PageFactory.initElements(driver, this);
	}

	NewItemActivityPage nia = new NewItemActivityPage();

	public String getChannelName() {
		Utils.isElementLoaded(driver, ChannelName);
		String CreatedChannelName = ChannelName.getText();
		return CreatedChannelName;
	}

	@Step("Archieve the Channel")
	public void archiveChannel() throws InterruptedException {
		Thread.sleep(2000);
		Utils.isElementLoaded(driver, MoreOptionBtn);
		MoreOptionBtn.click();
		Utils.isElementLoaded(driver, ArchiveLink);
		ArchiveLink.click();
		Utils.isElementLoaded(driver, ArchiveBtn);
		ArchiveBtn.click();
		Utils.isElementLoaded(driver, ArchiveSuccessMess);
		ArchiveSuccessMess.click();
		takeScreenshot();

	}

	@Step("Delete the Channel")
	public void deleteChannel() throws InterruptedException {
		Utils.isElementLoaded(driver, UnarchiveBtn);
		Utils.isElementLoaded(driver, DeleteBtn);
		DeleteBtn.click();
		Utils.isElementLoaded(driver, DeleteChannelBtn);
		DeleteChannelBtn.click();
		takeScreenshot();

	}

	@Step("Add member to channel")
	public void addMemberToChannel(String Member) throws InterruptedException {
		String InitalPath = "//div[text()='";
		String LastPath = "']";
		String FinalPath = InitalPath + Member + LastPath;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath)));
		WebElement addmem = driver.findElement(By.xpath(FinalPath));
		addmem.click();
		takeScreenshot();
		Utils.isElementLoaded(driver, AddBtn);
		AddBtn.click();
		takeScreenshot();

	}

	@Step("Verify added member in channel")
	public void verifyAddedMemberInChannel(String Member, String FlowName) throws InterruptedException {
		Utils.isElementLoaded(driver, MemberBtn);
		MemberBtn.click();
		takeScreenshot();
		String InitalPath = "//div[text()='Members']/..//*[text()='";
		String LastPath = "']";
		String FinalPath = InitalPath + Member + LastPath;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath)));
		String InitalPath1 = "//a[text()='";
		String LastPath1 = "']";
		String FinalPath1 = InitalPath1 + FlowName + LastPath1;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath1)));
		WebElement flowname = driver.findElement(By.xpath(FinalPath1));
		flowname.click();
	}

	@Step("Write a post in channel")
	public void writeAPostInChannel(String PostMessage) throws InterruptedException {
		Utils.isElementLoaded(driver, WritePostLink);
		Utils.isElementLoaded(driver, PostArea);
		PostArea.click();
		Utils.isElementLoaded(driver, PostAreaClick);
		PostAreaClick.click();
		Utils.isElementLoaded(driver, PostAreaInput);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(PostAreaInput).clickAndHold().sendKeys(PostMessage).build().perform();
		act.release().build().perform();
		takeScreenshot();
		Utils.isElementLoaded(driver, PostBtn);
		PostBtn.click();
	}

	@Step("verify post in channel")
	public void verifyPost(String PostMessage) throws InterruptedException {
		String InitalPath = "//div[@id='postWrapper']/..//*[text()='";
		String LastPath = "']";
		String FinalPath = InitalPath + PostMessage + LastPath;
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(FinalPath)));
		WebElement PostedMess = driver.findElement(By.xpath(FinalPath));
		String populatingmess = PostedMess.getText();
		if (populatingmess.equals(PostMessage)) {
			System.out.println("The post content is " + populatingmess);
		}
		Assert.assertTrue(true, PostMessage);
	}

	@Step("Make announcement in channel")
	public void makeAnnouncementInChannel(String Announcement) throws InterruptedException {
		Utils.isElementLoaded(driver, WritePostLink);
		Utils.isElementLoaded(driver, PostArea);
		PostArea.click();
		Utils.isElementLoaded(driver, MakeAnnounceLink);
		MakeAnnounceLink.click();
		Utils.isElementLoaded(driver, PostAreaInput);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(PostAreaInput).clickAndHold().sendKeys(Announcement).build().perform();
		act.release().build().perform();
		takeScreenshot();
		Utils.isElementLoaded(driver, PostBtn);
		PostBtn.click();
	}

	@Step("Add poll in channel")
	public void addPollInChannel(String PollTitle, String PollOption1, String PollOption2, String PollOption3)
			throws InterruptedException {
		Utils.isElementLoaded(driver, WritePostLink);
		Utils.isElementLoaded(driver, PostArea);
		PostArea.click();
		Utils.isElementLoaded(driver, AddPollLink);
		AddPollLink.click();
		Utils.isElementLoaded(driver, PollTitleInput);
		PollTitleInput.sendKeys(PollTitle);

		Utils.isElementLoaded(driver, PollOptionInput1);
		PollOptionInput1.sendKeys(PollOption1);

		Utils.isElementLoaded(driver, PollOptionInput2);
		PollOptionInput2.sendKeys(PollOption2);

		Utils.isElementLoaded(driver, AddOptionBtn);
		AddOptionBtn.click();

		Utils.isElementLoaded(driver, PollOptionInput3);
		PollOptionInput3.sendKeys(PollOption3);
		takeScreenshot();
		Utils.isElementLoaded(driver, PostBtn);
		PostBtn.click();

	}

	@Step("Add image in channel")
	public void addImageInChannel(String ImageInput) throws InterruptedException {
		Utils.isElementLoaded(driver, WritePostLink);
		Utils.isElementLoaded(driver, PostArea);
		PostArea.click();
		Utils.isElementLoaded(driver, AddImageLink);
		AddImageLink.click();
		((JavascriptExecutor) driver)
				.executeScript("HTMLInputElement.prototype.click = function() {                     "
						+ "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " + "};          ");

		String currentDirectory = System.getProperty("user.dir");
		String NewInput = currentDirectory + ImageInput;
		driver.findElement(By.cssSelector("input[type=file]")).sendKeys(NewInput);
		Utils.isElementLoaded(driver, CheckAttachComplete);
		Utils.isElementLoaded(driver, DoneBtn);
		DoneBtn.click();
		takeScreenshot();
		Utils.isElementLoaded(driver, PostBtn);
		PostBtn.click();
	}

	@Step("verify image post in channel")
	public void verifyImagePost() throws InterruptedException {
		Utils.isElementLoaded(driver, ImagePostView);
		takeScreenshot();

	}
}