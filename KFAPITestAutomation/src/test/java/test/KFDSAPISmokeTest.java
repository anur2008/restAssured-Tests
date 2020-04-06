package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.HashMap;

import org.kissflow.qa.dataset.DatasetHomePage;
import org.kissflow.qa.process.NewItemActivityPage;
import org.kissflow.qa.process.ProcessFormEditor;
import org.kissflow.qa.process.ProcessHomePage;
import org.kissflow.qa.shared.HomePage;
import org.kissflow.qa.shared.LoginPage;
import org.kissflow.qa.utils.KissflowPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class KFDSAPISmokeTest extends KissflowPage {
	final String apiKeyAdmin = prop.getProperty("apiKey");
	String acctId = prop.getProperty("account_id");
	public String dsId = null;

	@BeforeTest
	public void setUp() {
		super.initialization();

	}

	@Test(priority = 1)
	public void completeDSTest() throws Exception {

		String Admin = getJsonData("ProcessSmokeTest.json", "Admin");

		String FlowType = getJsonData("DatasetSmokeTest.json", "flowType");
		String FlowName = getJsonData("DatasetSmokeTest.json", "flowName");
		String UploadFieldType = getJsonData("DatasetSmokeTest.json", "DSimportData.FieldType");

		String uploadFieldValue = getJsonData("DatasetSmokeTest.json", "DSimportData.FieldValue");
		String uploadFieldName = getJsonData("DatasetSmokeTest.json", "DSimportData.FieldName");
		String TextFieldType = getJsonData("ProcessSmokeTest.json", "TextFieldData.FieldType");
		String TextFieldName = getJsonData("ProcessSmokeTest.json", "TextFieldData.FieldName");
		String TextFieldDefault = getJsonData("ProcessSmokeTest.json", "TextFieldData.Default");
		String TextFieldDefaultParam = getJsonData("ProcessSmokeTest.json", "TextFieldData.DefaultParam");
		String TextFieldRequired = getJsonData("ProcessSmokeTest.json", "TextFieldData.Required");
		String TextFieldRequiredParam = getJsonData("ProcessSmokeTest.json", "TextFieldData.RequiredParam");

		String NumberFieldType = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldType");
		String NumberFieldName = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldName");
		String NumberFieldValue = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldValue");

		String AttachmentFieldType = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldType");
		String AttachmentFieldName = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldName");
		String AttachmentFieldValue = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldValue");

		String ImageFieldType = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldType");
		String ImageFieldName = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldName");
		String ImageFieldValue = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldValue");

		LoginPage lg = new LoginPage();

		HomePage homePage = lg.login(Admin);
		String hmTitle = homePage.getHomePageTitle();
		Assert.assertEquals(hmTitle, "Kissflow");
		ProcessHomePage pg = homePage.createFlowNew(FlowType, FlowName);
		String DSPgElement = pg.getDsName();
		Assert.assertEquals(DSPgElement, FlowName);
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put(TextFieldDefault, TextFieldDefaultParam);
		hm1.put(TextFieldRequired, TextFieldRequiredParam);
		ProcessFormEditor pgfe = new ProcessFormEditor();
		System.out.println("title:" + driver.getCurrentUrl());
		String title1 = pg.driver.getCurrentUrl();
		System.out.println("title1:" + title1);
		String[] dsId1 = title1.split("/");
		dsId = dsId1[6];
		System.out.println("dsId:" + dsId);
		pgfe.createDSTableColumn();
		pgfe.createFieldForChild(TextFieldType, TextFieldName, hm1);
		pgfe.createDSTableColumn();
		HashMap<String, String> hm3 = new HashMap<String, String>();
		pgfe.createFieldForChild(NumberFieldType, NumberFieldName, hm3);
		pgfe.createDSTableColumn();
		HashMap<String, String> hm8 = new HashMap<String, String>();
		pgfe.createFieldForChild(ImageFieldType, ImageFieldName, hm8);
		pgfe.createDSTableColumn();
		HashMap<String, String> hm15 = new HashMap<String, String>();
		pgfe.createFieldForChild(AttachmentFieldType, AttachmentFieldName, hm15);
		pgfe.addNewRowDS();
		pgfe.importCsvData(uploadFieldValue, uploadFieldName);

	}

	@Test(priority = 2)
	public void modifyDSRecords() {
		String rowIdModify = getDSRecords();

		JsonObject newObject = new JsonObject();
		newObject.addProperty("ThisIsText", "Master");
		newObject.addProperty("_id", rowIdModify);
		String jsonInput = "[" + newObject.toString() + "]";
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Modify DS records************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(jsonInput).when().post("/dataset/1/" + acctId + "/" + dsId + "/batch");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("modifyDsRecords.json"));
	}

	public String getDSRecords() {

		
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************get DS records************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.get("/dataset/1/" + acctId + "/" + dsId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getDsRecords.json"));
		String rowId = response.jsonPath().get("Data[0]._id");
		System.out.println("rowId:" + rowId);
		return rowId;
	}

	@Test(priority = 3)

	public void addAttachment() {
		final String rowIdAttach = getDSRecords();

		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Add Attachment************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/SBI.pages")).when()
				.post("/dataset/1/" + acctId + "/" + dsId + "/" + rowIdAttach + "/ThisIsAttach" + "/attachment");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addAttachmentDS.json"));
	}

	@Test(priority = 4)
	public void addImage() {
		final String rowIdImg = getDSRecords();

		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************AddImage************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/tree.jpg")).when()
				.post("/dataset/1/" + acctId + "/" + dsId + "/" + rowIdImg + "/ThisIsImage" + "/image");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addImageDS.json"));
	}
	
	@Test(priority = 5)
	public void listAllFlowsType() {
		//https://orangescape4.tst.zingworks.com/flow/1/AcZAAN7lsPYw/dataset
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************listAllFlowsType************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("/flow/1/" + acctId + "/dataset");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("listAllflowsType.json"));
	}
	@Test(priority = 6)
	public void listAllFlows() {
		//https://orangescape4.tst.zingworks.com/flow/1/AcZAAN7lsPYw/dataset
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************listAllFlows************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("/flow/1/" + acctId+"/explore");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("listAllFlows.json"));
	}
	

	@AfterTest

	public void DelDS() {

		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Del DS records************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.delete("/flow/1/" + acctId + "/dataset/" + dsId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		driver.quit();
	}

}
