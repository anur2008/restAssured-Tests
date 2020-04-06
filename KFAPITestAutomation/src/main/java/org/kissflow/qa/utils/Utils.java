package org.kissflow.qa.utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {


	public static void isElementLoaded(WebDriver driver, WebElement elementToBeLoaded) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	}

	public static void isElementLoadedSec(WebDriver driver, WebElement elementToBeLoaded) {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	}
	public static void isElementLoadedClickable(WebDriver driver, WebElement elementToBeLoaded) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
	}
	
}
