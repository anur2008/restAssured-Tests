package org.kissflow.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.JsonPath;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class KissflowPage {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	
	// public RequestSpecification REQUEST;
	public KissflowPage() {


		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/org/kissflow/qa/utils/config.properties");

			prop.load(ip);
			// Rest Assured config

			RestAssured.baseURI = prop.getProperty("base.uri");
			//RestAssured.sessionId = prop.getProperty("apiKey");
			RestAssured.rootPath = prop.getProperty("account_id");
			//RestAssured.rootPath=prop.getProperty("yahooApiKey");
			

			 final String apiKeyUser=prop.getProperty("yahooApiKey");

			System.out.println("Uri:" + RestAssured.baseURI);
			//System.out.println("apiKey" + RestAssured.sessionId);
			System.out.println("account_id" + RestAssured.rootPath);

			// RestAssured.port = Integer.valueOf(props.getProperty("api.port"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	public static void requestSpec() {
		RequestSpecification REQUEST1=RestAssured.given().contentType(ContentType.JSON);
		
	}
	


	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/ChromeDriver/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			//chromeOptions.addArguments("headless","disable-gpu","--test-type","--ignore-certificate-errors");
			chromeOptions.addArguments("window-size=1200,1100");
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/ChromeDriver/geckodriver");
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	@Attachment(value = "Screenshot for current step", type = "image/png")
	public byte[] takeScreenshot() {
		// Take a screenshot as byte array and return
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public String getJsonData(String JsonFileName, String getbyName) throws IOException {
		File jsonfile;
		jsonfile = new File(System.getProperty("user.dir") + "/JsonInputFiles/" + JsonFileName);
		String exactData = JsonPath.read(jsonfile, getbyName);
		return exactData;
	}
	public LinkedHashMap<String, String>  getJsonData1(String JsonFileName, String getbyName) throws IOException {
		File jsonfile;
		jsonfile = new File(System.getProperty("user.dir") + "/JsonInputFiles/" + JsonFileName);
		LinkedHashMap<String, String>exactData = JsonPath.read(jsonfile, getbyName);
		return exactData;
	}

	public String currentDate() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String datetime = ft.format(dNow);
		String ProcessName = "Automation" + datetime;
		System.out.println(datetime);
		return ProcessName;
	}
	/*public void jsonSchemaSettings()
	{
	JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
			  .setValidationConfiguration(
			   ValidationConfiguration.newBuilder()
			    .setDefaultVersion(SchemaVersion.DRAFTV3)
			      .freeze()).freeze();
	
			JsonSchemaValidator.settings = settings()
			  .with().jsonSchemaFactory(factory)
			      .and().with().checkedValidation(false);
	}*/
}

