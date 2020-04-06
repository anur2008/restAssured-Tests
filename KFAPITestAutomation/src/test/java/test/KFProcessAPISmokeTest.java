package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kissflow.qa.process.NewItemActivityPage;
import org.kissflow.qa.process.NewItemPage;
import org.kissflow.qa.process.ProcessEditorPage;
import org.kissflow.qa.process.ProcessFormEditor;
import org.kissflow.qa.process.ProcessHomePage;
import org.kissflow.qa.process.ProcessPermissionEditor;
import org.kissflow.qa.process.ProcessReportPage;
import org.kissflow.qa.process.ProcessWorkflowEditor;
import org.kissflow.qa.shared.AdminPage;
import org.kissflow.qa.shared.HomePage;
import org.kissflow.qa.shared.LoginPage;
import org.kissflow.qa.utils.KissflowPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.FilterableRequestSpecification;

public class KFProcessAPISmokeTest extends KissflowPage {

	// RequestSpecification REQUEST = RestAssured.with();

	final String apiKeyUser = prop.getProperty("yahooApiKey");
	final String apikeyUser1 = prop.getProperty("outlookApiKey");
	final String apiKeyAdmin = prop.getProperty("apiKey");
	// String apiKey = RestAssured.sessionId;
	// String apiKeyUser = RestAssured.rootPath;
	String acctId = prop.getProperty("account_id");

	public String processId = null;
	public String processInstId = null;
	String ActvtyInstId = null;

	@BeforeTest
	public void setUp() {
		super.initialization();

	}

	@Test(priority = 1)
	public void completeProcessTest() throws Exception {

		String Admin = getJsonData("ProcessSmokeTest.json", "Admin");
		String timeZoneInd = getJsonData("ProcessSmokeTest.json", "timeZone1");
		String timeZonePacific = getJsonData("ProcessSmokeTest.json", "timeZone2");
		String LocaleInd = getJsonData("ProcessSmokeTest.json", "Locale");
		String FlowType = getJsonData("ProcessSmokeTest.json", "flowType");
		String FlowName = getJsonData("ProcessSmokeTest.json", "flowName");
		String TextFieldType = getJsonData("ProcessSmokeTest.json", "TextFieldData.FieldType");
		String TextFieldName = getJsonData("ProcessSmokeTest.json", "TextFieldData.FieldName");
		String TextFieldDefault = getJsonData("ProcessSmokeTest.json", "TextFieldData.Default");
		String TextFieldDefaultParam = getJsonData("ProcessSmokeTest.json", "TextFieldData.DefaultParam");
		String TextFieldRequired = getJsonData("ProcessSmokeTest.json", "TextFieldData.Required");
		String TextFieldRequiredParam = getJsonData("ProcessSmokeTest.json", "TextFieldData.RequiredParam");
		String TextAreaFieldType = getJsonData("ProcessSmokeTest.json", "TextAreaFieldData.FieldType");
		String TextAreaFieldName = getJsonData("ProcessSmokeTest.json", "TextAreaFieldData.FieldName");
		String TextAreaFieldValue = getJsonData("ProcessSmokeTest.json", "TextAreaFieldData.FieldValue");

		String NumberFieldType = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldType");
		String NumberFieldName = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldName");
		String NumberFieldValue = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldValue");

		String CurrencyFieldType = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.FieldType");
		String CurrencyFieldName = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.FieldName");
		String CurrencyFieldValue = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.FieldValue");
		String ExpCurrencyFieldValue = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.ExpectedFieldValue");

		String DateFieldType = getJsonData("ProcessSmokeTest.json", "DateFieldData.FieldType");
		String DateFieldName = getJsonData("ProcessSmokeTest.json", "DateFieldData.FieldName");
		String DateFieldValue = getJsonData("ProcessSmokeTest.json", "DateFieldData.FieldValue");
		String ExpDateFieldValue = getJsonData("ProcessSmokeTest.json", "DateFieldData.ExpectedFieldValue");

		String DateAndTimeFieldType = getJsonData("ProcessSmokeTest.json", "DateAndTimeFieldData.FieldType");
		String DateAndTimeFieldName = getJsonData("ProcessSmokeTest.json", "DateAndTimeFieldData.FieldName");
		String DateAndTimeFieldValue = getJsonData("ProcessSmokeTest.json", "DateAndTimeFieldData.FieldValue");
		String ExpDateAndTimeFieldValue = getJsonData("ProcessSmokeTest.json",
				"DateAndTimeFieldData.ExpectedFieldValue");

		String YesNoFieldType = getJsonData("ProcessSmokeTest.json", "YesNoFieldData.FieldType");
		String YesNoFieldName = getJsonData("ProcessSmokeTest.json", "YesNoFieldData.FieldName");
		String YesNoFieldValue = getJsonData("ProcessSmokeTest.json", "YesNoFieldData.FieldValue");
		String ImageFieldType = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldType");
		String ImageFieldName = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldName");
		String ImageFieldValue = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldValue");

		String RatingFieldType = getJsonData("ProcessSmokeTest.json", "RatingFieldData.FieldType");
		String RatingFieldName = getJsonData("ProcessSmokeTest.json", "RatingFieldData.FieldName");
		String RatingFieldValue = getJsonData("ProcessSmokeTest.json", "RatingFieldData.FieldValue");

		String DropdownFieldType = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.FieldType");
		String DropdownFieldName = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.FieldName");
		String DropdownListType = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.listType");
		String DropdownListName = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.ListName");
		String DropdownFieldValue = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.FieldValue");

		String CheckboxFieldType = getJsonData("ProcessSmokeTest.json", "CheckboxFieldData.FieldType");
		String CheckboxFieldName = getJsonData("ProcessSmokeTest.json", "CheckboxFieldData.FieldName");
		String CheckboxFieldValue = getJsonData("ProcessSmokeTest.json", "CheckboxFieldData.FieldValue");

		String SliderFieldType = getJsonData("ProcessSmokeTest.json", "SliderFieldData.FieldType");
		String SliderFieldName = getJsonData("ProcessSmokeTest.json", "SliderFieldData.FieldName");
		String SliderMin = getJsonData("ProcessSmokeTest.json", "SliderFieldData.Min");
		String SliderMinValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.MinValue");
		String SliderMax = getJsonData("ProcessSmokeTest.json", "SliderFieldData.Max");
		String SliderMaxValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.MaxValue");
		String SliderInterval = getJsonData("ProcessSmokeTest.json", "SliderFieldData.IntervalSize");
		String SliderIntervalValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.IntervalValue");
		String SliderFieldValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.FieldValue");

		String UserFieldType = getJsonData("ProcessSmokeTest.json", "UserFieldData.FieldType");
		String UserFieldName = getJsonData("ProcessSmokeTest.json", "UserFieldData.FieldName");
		String UserFieldValue = getJsonData("ProcessSmokeTest.json", "UserFieldData.FieldValue");

		String ChecklistFieldType = getJsonData("ProcessSmokeTest.json", "ChecklistFieldData.FieldType");
		String ChecklistFieldName = getJsonData("ProcessSmokeTest.json", "ChecklistFieldData.FieldName");
		String ChecklistFieldValue = getJsonData("ProcessSmokeTest.json", "ChecklistFieldData.FieldValue");

		String AttachmentFieldType = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldType");
		String AttachmentFieldName = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldName");
		String AttachmentFieldValue = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldValue");

		String SignFieldType = getJsonData("ProcessSmokeTest.json", "SignFieldData.FieldType");
		String SignFieldName = getJsonData("ProcessSmokeTest.json", "SignFieldData.FieldName");

		String LookupFieldType = getJsonData("ProcessSmokeTest.json", "LookupFieldData.FieldType");
		String LookupFieldName = getJsonData("ProcessSmokeTest.json", "LookupFieldData.FieldName");

		String LookedupFieldParam = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpParam");
		String LookedupFieldSource = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpSource");

		String LookedupField = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpField");
		String LookedupFieldValue = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpFieldValue");

		String RemoteLookupFieldType = getJsonData("ProcessSmokeTest.json", "RemoteLookupFieldData.FieldType");
		String RemoteLookupFieldName = getJsonData("ProcessSmokeTest.json", "RemoteLookupFieldData.FieldName");

		String RemoteLookedupFieldParam = getJsonData("ProcessSmokeTest.json",
				"RemoteLookupFieldData.RemoteLookedUpParam");
		String RemoteLookedupFieldSource = getJsonData("ProcessSmokeTest.json",
				"RemoteLookupFieldData.RemoteLookedUpSource");

		String RemoteLookedupField = getJsonData("ProcessSmokeTest.json", "RemoteLookupFieldData.RemoteLookedUpField");
		String RemoteLookedupFieldValue = getJsonData("ProcessSmokeTest.json",
				"RemoteLookupFieldData.ReLookedUpFieldValue");

		String WorkflowName1 = getJsonData("ProcessSmokeTest.json", "WorkflowData.WorkflowName1");
		String WorkflowName2 = getJsonData("ProcessSmokeTest.json", "WorkflowData.WorkflowName2");

		String Approver1 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Approver1");
		String Approver2 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Approver2");

		String Formula1 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Formula1");
		String Formula2 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Formula2");
		String ReadonlyPermission = getJsonData("ProcessSmokeTest.json", "Permission.ReadOnly");

		String Sendbackmess = getJsonData("ProcessSmokeTest.json", "SendingBackMess");
		String ReassignMess = getJsonData("ProcessSmokeTest.json", "ReassigningMess");
		String RejectMess = getJsonData("ProcessSmokeTest.json", "RejectMess");
		String WithdrawMess = getJsonData("ProcessSmokeTest.json", "WithdrawMess");

		LoginPage lg = new LoginPage();
		NewItemActivityPage newItemNewPg = new NewItemActivityPage();
		ProcessReportPage prp = new ProcessReportPage();
		HomePage homePage = lg.login(Admin);
		String hmTitle = homePage.getHomePageTitle();
		Assert.assertEquals(hmTitle, "Kissflow");
		AdminPage adpg = homePage.clickOnAdminLink();
		adpg.timeZoneChange(timeZoneInd);
		adpg.localeChange(LocaleInd);
		adpg.saveLocaleAndTimezoneChange();
		ProcessHomePage pg = homePage.createFlowNew(FlowType, FlowName);
		ProcessEditorPage pge = new ProcessEditorPage();
		String ProcessPgElement = pg.getProcessName();
		Assert.assertEquals(ProcessPgElement, FlowName);
		ProcessFormEditor pgfe = pge.navToFormEditor();
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put(TextFieldDefault, TextFieldDefaultParam);
		hm1.put(TextFieldRequired, TextFieldRequiredParam);
		pgfe.createField(TextFieldType, TextFieldName, hm1);
		pgfe.clearUntitledField();

		HashMap<String, String> hm2 = new HashMap<String, String>();
		pgfe.createField(TextAreaFieldType, TextAreaFieldName, hm2);

		HashMap<String, String> hm3 = new HashMap<String, String>();
		pgfe.createField(NumberFieldType, NumberFieldName, hm3);

		HashMap<String, String> hm4 = new HashMap<String, String>();
		pgfe.createField(CurrencyFieldType, CurrencyFieldName, hm4);

		HashMap<String, String> hm5 = new HashMap<String, String>();
		pgfe.createField(DateFieldType, DateFieldName, hm5);

		HashMap<String, String> hm6 = new HashMap<String, String>();
		pgfe.createField(DateAndTimeFieldType, DateAndTimeFieldName, hm6);

		HashMap<String, String> hm7 = new HashMap<String, String>();
		pgfe.createField(YesNoFieldType, YesNoFieldName, hm7);

		HashMap<String, String> hm8 = new HashMap<String, String>();
		pgfe.createField(ImageFieldType, ImageFieldName, hm8);

		HashMap<String, String> hm9 = new HashMap<String, String>();
		pgfe.createField(RatingFieldType, RatingFieldName, hm9);

		HashMap<String, String> hm10 = new HashMap<String, String>();
		hm10.put(DropdownListType, DropdownListName);
		pgfe.createField(DropdownFieldType, DropdownFieldName, hm10);

		HashMap<String, String> hm11 = new HashMap<String, String>();
		hm11.put(DropdownListType, DropdownListName);
		pgfe.createField(CheckboxFieldType, CheckboxFieldName, hm11);

		HashMap<String, String> hm12 = new HashMap<String, String>();
		hm12.put(SliderMin, SliderMinValue);
		hm12.put(SliderMax, SliderMaxValue);
		hm12.put(SliderInterval, SliderIntervalValue);
		//pgfe.createField(SliderFieldType, SliderFieldName, hm12);

		HashMap<String, String> hm13 = new HashMap<String, String>();
		pgfe.createField(UserFieldType, UserFieldName, hm13);

		HashMap<String, String> hm14 = new HashMap<String, String>();
		hm14.put(DropdownListType, DropdownListName);
		pgfe.createField(ChecklistFieldType, ChecklistFieldName, hm14);

		HashMap<String, String> hm15 = new HashMap<String, String>();
		pgfe.createField(AttachmentFieldType, AttachmentFieldName, hm15);

		HashMap<String, String> hm16 = new HashMap<String, String>();
		pgfe.createField(SignFieldType, SignFieldName, hm16);

		HashMap<String, String> hm17 = new HashMap<String, String>();
		hm17.put(LookedupFieldParam, LookedupFieldSource);
		hm17.put(LookedupField, LookedupFieldValue);
		pgfe.createField(LookupFieldType, LookupFieldName, hm17);

		HashMap<String, String> hm18 = new HashMap<String, String>();
		hm18.put(RemoteLookedupFieldParam, RemoteLookedupFieldSource);
		hm18.put(RemoteLookedupField, RemoteLookedupFieldValue);
		pgfe.createField(RemoteLookupFieldType, RemoteLookupFieldName, hm18);
		
		pgfe.createChildTable();
		pgfe.createChildTableColumn();

		
		pgfe.createFieldForChild(AttachmentFieldType, AttachmentFieldName, hm15);
		pgfe.createChildTableColumn();
		
		pgfe.createFieldForChild(ImageFieldType, ImageFieldName, hm8);

		

		ProcessWorkflowEditor pgwe = pge.navToWorkflowEditor();
		pgwe.createWorkflow(WorkflowName1, Approver1, Approver2, Formula1);
		pgwe.createWorkflow(WorkflowName2, Approver1, Approver2, Formula2);
		ProcessPermissionEditor pgpe = pge.navToPermissionEditor();
		pgpe.setPermission(TextFieldName, ReadonlyPermission);
		NewItemPage netItempg = pge.publish();
		pg.newItemClickOfProcess(FlowName);
		netItempg.enterValueToTetAreaField(TextAreaFieldValue, TextAreaFieldName);
		String title1 = pg.driver.getCurrentUrl();
		System.out.println("title1:" + title1);
		String[] processId1 = title1.split("/");
		processId = processId1[6];
		processInstId = processId1[8];
		ActvtyInstId = processId1[9];
		System.out.println("Processid:" + processId);
		System.out.println("prcessInstId:" + processInstId);
		System.out.println("ActvtyInstId:" + ActvtyInstId);

		netItempg.enterValueToField(NumberFieldValue, NumberFieldName);
		netItempg.enterValueToField(CurrencyFieldValue, CurrencyFieldName);
		netItempg.enterValueToField(DateFieldValue, DateFieldName);
		netItempg.enterValueToDateTimeField(DateAndTimeFieldValue, DateAndTimeFieldName);
		netItempg.selectYesNoValueToField(YesNoFieldValue, YesNoFieldName);
		netItempg.uploadImage(ImageFieldValue, ImageFieldName);
		netItempg.enterValueToRatingField(RatingFieldValue, RatingFieldName);
		netItempg.enterValueToCheckboxAndDropdownField(DropdownFieldValue, DropdownFieldName);
		netItempg.enterValueToCheckboxAndDropdownField(CheckboxFieldValue, CheckboxFieldName);
		//netItempg.setValueForSliderField(SliderFieldValue, SliderFieldName);
		netItempg.selectUserFromUserField(UserFieldValue, UserFieldName);
		netItempg.selectValueFromChecklistField(ChecklistFieldValue, ChecklistFieldName);
		netItempg.uploadAttachment(AttachmentFieldValue, AttachmentFieldName);
		netItempg.signToSignatureField(SignFieldName);
		netItempg.selectValueForLookUpField(LookedupFieldSource, LookupFieldName);
		netItempg.getdataForRemoteLookUpField(RemoteLookupFieldName);
		netItempg.addNewRownTable();

		ProcessHomePage pg1 = netItempg.Submit();
		homePage.clickOnAdminLink1();
		adpg.timeZoneChange(timeZonePacific);
		adpg.saveLocaleAndTimezoneChange();
		homePage.getBacktoProcess(FlowName);
		pg1.openMyItemProcess(FlowName);
		newItemNewPg.checkCreatedFieldValue(TextFieldName, TextFieldDefaultParam);
		newItemNewPg.checkCreatedFieldValue(TextAreaFieldName, TextAreaFieldValue);
		newItemNewPg.checkCreatedFieldValue(NumberFieldName, NumberFieldValue);
		newItemNewPg.checkCreatedFieldContainsValue(CurrencyFieldName, ExpCurrencyFieldValue);
		// newItemNewPg.checkCreatedFieldValue(DateFieldName, ExpDateFieldValue);
		// newItemNewPg.overAndFindCurrentFieldValue(DateAndTimeFieldName,
		// ExpDateAndTimeFieldValue);
		newItemNewPg.checkCreatedFieldValue(YesNoFieldName, YesNoFieldValue);
		newItemNewPg.checkUploadedImage(ImageFieldName);
		newItemNewPg.checkCreatedFieldValue(DropdownFieldName, DropdownFieldValue);
		newItemNewPg.checkCreatedFieldValue(CheckboxFieldName, CheckboxFieldValue);
		//newItemNewPg.overAndFindCurrentFieldValue(SliderFieldName, SliderFieldValue);
		newItemNewPg.checkCreatedFieldValue(UserFieldName, UserFieldValue);
		// newItemNewPg.verifyApprovername(Approver1);
		newItemNewPg.closeNewItemActivityOfProcess(FlowName);
		pg.shareProcess();

	}

	@Test(priority = 10)
	public String[] CreateNewProcessItem() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Create New Process item************");
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).log().all().contentType(ContentType.JSON).body("{}")
				.when().post("/process/1/" + acctId + "/" + processId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString().contains("ModelId:" + processId);

		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createNewProcessItem.json"));
		JsonPath jp = new JsonPath(response.asString());
		String respprcessId = jp.get("ModelId").toString();
		String prcessInstId = jp.get("_id").toString();
		assertThat(respprcessId, equalTo(processId));
		String actvtyInstId = jp.get("_activity_instance_id").toString();
		String[] processDetails = new String[] { prcessInstId, actvtyInstId };
		return processDetails;

	}

	@Test(priority = 2)
	public void getProcessItemList() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************getProcessItemList()************");
		Response response = REQUEST.replaceHeader("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("process/1/" + acctId + "/admin/" + processId + "/item/p1/5");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getProcessItemList.json"));
		JsonPath jp = new JsonPath(response.asString());
		String prcessId = jp.get("_id").toString();
		String prcessInstId = jp.getString("Data[0]._id").toString();
		System.out.println("ProcessId of the created flowValue:" + prcessId);
		assertThat(prcessId, equalTo(processId));
		assertThat(processInstId, equalTo(prcessInstId));

	}

	@Test(priority = 3)
	public void getProcessItemDetails() {
		System.out.println("************getProcessItemDetails************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("process/1/" + acctId + "/admin/" + processId + "/" + processInstId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().body();
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getProcessItemDetails.json"));
		JsonPath jp = new JsonPath(response.asString());

	}

	@Test(priority = 11)
	public void getMyItemList() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when()
				.get("process/1/" + acctId + "/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getMyItemList.json"));

	}

	//@Test(priority = 16)
	public String getMyTaskList() {
		System.out.println("************getMytaskList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apikeyUser1).when()
				.get("process/1/" + acctId + "/" + processId + "/pending?page_number=1&page_size=5");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getMyTaskList.json"));
		JsonPath jp = new JsonPath(response.asString());
		String rActvtyInstId = jp.getString("Data[0]._activity_instance_id").toString();
		return rActvtyInstId;

	}

	@Test(priority = 13)
	public void getProgressDetails() {
		String[] newProcesInfo = CreateNewProcessItem();
		String newProcessInstId = newProcesInfo[0];
		System.out.println("************progress************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when()
				.get("process/1/" + acctId + "/" + processId + "/" + newProcessInstId + "/progress");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getProgress.json"));

	}

	@Test(priority = 14)
	public void getItemDetailsOfWFstep() {
		System.out.println("************Wf step************");
		String[] newProcesInfo = CreateNewProcessItem();
		String newProcessInstId = newProcesInfo[0];
		String actvtyInstId = newProcesInfo[1];
		System.out.println("actvtyInstId:" + actvtyInstId);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();

		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when()
				.get("process/1/" + acctId + "/" + processId + "/" + newProcessInstId + "/" + actvtyInstId);
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getWFStepDetails.json"));

	}

	@Test(priority = 17)
	public void rejectItem() {
		String[] RejectProcesInfo = submitItem();
		String RejectProcessInstId = RejectProcesInfo[0];
		String RejactvtyInstId = getMyTaskList();
		System.out.println("RejectProcessInstId:" + RejectProcessInstId);
		System.out.println("RactvtyInstId:" + RejactvtyInstId);
		HashMap<String, String> jsonAsMap1 = new HashMap<String, String>();
		jsonAsMap1.put("Note", "Rejection of an item for Testing");
		System.out.println("map:" + jsonAsMap1);
		System.out.println("************reject an item************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apikeyUser1).log().all().when().contentType(ContentType.JSON)
				.body(jsonAsMap1).post("process/1/" + acctId + "/" + processId + "/" + RejectProcessInstId + "/"
						+ RejactvtyInstId + "/reject");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().log().body().assertThat().statusCode(200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("rejectItem.json"));

	}

	@Test(priority = 12)
	public String[] submitItem() {
		String[] newProcesInfo = CreateNewProcessItem();
		String newProcessInstId = newProcesInfo[0];
		String actvtyInstId = newProcesInfo[1];
		System.out.println("************Submit an itemt************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();

		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when().post(
				"process/1/" + acctId + "/" + processId + "/" + newProcessInstId + "/" + actvtyInstId + "/submit");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("submitItem.json"));
		JsonPath jp = new JsonPath(response.asString());
		String[] RejectProcessDetails = new String[] { newProcessInstId, actvtyInstId, };
		System.out.println("newProcessInstId:" + newProcessInstId);
		System.out.println("actvtyInstId:" + actvtyInstId);

		return RejectProcessDetails;
	}
	

	@Test(priority = 16)
	public void addAttachmentasUserToFormField() {
		String[] newProcesInfo = CreateNewProcessItem();
		String newProcessInstId = newProcesInfo[0];
		String actvtyInstId = newProcesInfo[1];
		System.out.println("************addAttachmentUserToFormField************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when().contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/SBI.pages")).log().all().post("process/1/" + acctId
						+ "/" + processId + "/" + newProcessInstId + "/" + actvtyInstId + "/ThisIsAttach/attachment");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addAttachmentFrmUser.json"));
		JsonPath jp = new JsonPath(response.asString());

	}

	@Test(priority = 18)
	public void addImgasUserToFormField() {
		String[] newProcesInfo = CreateNewProcessItem();
		String newProcessInstId = newProcesInfo[0];
		String actvtyInstId = newProcesInfo[1];
		System.out.println("************addImgasUserToFormField************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();

		Response response = REQUEST.header("X-Api-Key", apiKeyUser).log().all().when()
				.contentType("multipart/form-data").multiPart("file", new File("src/test/resources/sun.jpg"))
				.accept("application/json").post("process/1/" + acctId + "/" + processId + "/" + newProcessInstId + "/"
						+ actvtyInstId + "/ThisIsImage/image");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addImgFrmUser.json"));
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"))
		JsonPath jp = new JsonPath(response.asString());

		// assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");

	}

	//@Test(priority = 6)
	public String getTableID() throws IOException {

		System.out.println("************getTableID************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).expect().defaultParser(Parser.JSON).when()
				.get("flow/1/" + acctId + "/" + "process" + "/" + processId + "/draft");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();

		

//		RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
		ObjectMapper objectMapper = new ObjectMapper();
		// SmokeProcess119 Sp=response.as(SmokeProcess119.class);
//		String fType=jr.getFlowType();
//		System.out.println("fType"+fType);
//	List<String> tableId=jr.getModelModel();
//		String td=tableId.get(0).toString();

		
		// JsonNode root = objectMapper.readTree(body);

		// LinkedHashMap<Integer,String>processNode = response.path(processId);
		LinkedHashMap<String, List<String>> hMap = response.path(processId);
		System.out.println("processNode:" + hMap);
		List<List<String>> l = new ArrayList<List<String>>(hMap.values());
		List<String> tableId = l.get(6);

//		Set<Integer> keySet = processNode.keySet();
//		Integer[] keyArray = keySet.toArray( new Integer[ keySet.size() ] );
//Integer key = keyArray[1];
//        
		// get value from the LinkedHashMap for the key
		System.out.println("Value at index 1: " + l.get(6));

		// int tId1=processNode.getOrDefault(key, defaultValue)
		String tableId1 = tableId.toString();

		System.out.println("tableId1:" + tableId1);
		// List<String> tableId=processNode.indexOf(3);
		// JsonResponse jr = processNode.as(JsonResponse.class);

		// JsonNode tableNode = processNode.path(".Model::Model");
		// String id=tableNode.asText();
		// System.out.println("id:"+tableNode.path(0).asText());

		// Map<String, Object> mapObject = objectMapper.readValue(body, new
		// TypeReference<Map<String, Object>>() { });
		// System.out.println("mapObj:"+mapObject);

		// List<String> list = (List<String>) mapObject.get(processId);
		// System.out.println("List:"+list);

//String tableId=list.get(4);
//System.out.println("tableId"+td);

		// JsonNode jsonNode = objectMapper.readTree(body);
		// String fieldName=processId+".Model::Model[0]";
		// .processId
		// System.out.println("FldNam:"+fieldName);
		// String tableId = jsonNode.get(processId).asText();
		// JsonPath jp = new JsonPath(response.asString());
		// JsonNode jsonNode = jp.get(processId);
		// System.out.println("jsonNode:"+jsonNode);

		// List<String> table = jp.get(processId+".Model::Model[0]");

		// String tableId=body.getClass().getName();

		return tableId1;
	}

	// @Test(priority=7)
	public String getrowID() throws IOException {
		String tableInfo = getTableID();
		System.out.println("tableInfo:" + tableInfo);
		System.out.println("************getRowID************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("flow/1/" + acctId + "/process/" + processId + "/" + processInstId + "/" + ActvtyInstId);
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		JsonPath jp = new JsonPath(response.asString());
//			String tableId=jp.getString("Table").toString();
		String rowId = jp.getString("Table::Model" + tableInfo + "[0]._id").toString();
		System.out.println("rowId:" + rowId);
		return rowId;
	}

	 @Test(priority = 8)
	public void addAttachmentToTable() throws IOException {
		String[] newProcesInfo = CreateNewProcessItem();
		String newProcessInstId = newProcesInfo[0];
		String actvtyInstId = newProcesInfo[1];
		String newTableId = getTableID();
		String newRowId = getrowID();

		System.out.println("************addAttachtoTable************");
		System.out.println("Tableid:" + newTableId);
		System.out.println("rowId:" + newRowId);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.contentType("multipart/form-data").multiPart("file", new File("src/test/resources/SBI.pages"))
				.accept("application/json").post("process/1/" + acctId + "/" + processId + "/" + newProcessInstId + "/"
						+ actvtyInstId + "/" + newTableId + "/" + newRowId + "/ThisIsAttach/attachment");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().log().body().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"))
		JsonPath jp = new JsonPath(response.asString());

		// assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	@Test(priority = 19)
	public void deleteProcessItem() {
		System.out.println("************deleteProcessItem************");
		// Response response = REQUEST.when().delete("/process/1/acctId/admin/" +
		// processId + "/" + processInstId);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).when()
				.delete("/process/1/" + acctId + "/admin/" + processId + "/" + processInstId);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("deleteProcessItem.json"));
		response.then().log().all().log().body().assertThat().statusCode(200).log().body().extract().asString()
				.equalsIgnoreCase("Success" + ":" + "Delete successfully");

	}

	@Test(priority = 4)
	public void updateProcessItemDetails() {
		System.out.println("************updateProcessItemDetails************");
		HashMap<String, String> jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("Name", "SmokeProcess");
		jsonAsMap.put("ThisIsTextArea", "This field is updated via API");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType(ContentType.JSON).body(jsonAsMap)
				.when().put("/process/1/" + acctId + "/admin/" + processId + "/" + processInstId);
		response.then().assertThat().statusCode(200).and().log().body().extract().asString().contains(processInstId);
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("updateProcessItemDetails.json"));

	}

	@Test(priority = 5)
	public void addAttachmenttoFormFieldAsAdmin() {
		System.out.println("************addAttachmenttoFormFieldAsAdmin************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/SBI.pages")).log().all().when().post("/process/1/"
						+ acctId + "/admin/" + processId + "/" + processInstId + "/ThisIsAttach/attachment");

		response.then().assertThat().statusCode(200).and().log().body().extract().asString().contains("Attach");
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addAttachmntFrmAdmin.json"));

	}

	@Test(priority = 9,dataProvider="fileName")
	public void addImageToFormFieldAsAdmin( String fileName) {
		System.out.println("************addImagetoFormFieldAsAdmin************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/"+fileName)).accept("application/json").log().all().when()
				.post("/process/1/" + acctId + "/admin/" + processId + "/" + processInstId + "/ThisIsImage/image");

		response.then().log().body().assertThat().statusCode(200).and().log().body().extract().asString()
				.contains(fileName);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addImgFrmAdmin.json"));

	}
	//https://orangescape4.tst.zingworks.com/search/1/AcZAAN7lsPYw/global/myitems/Draft/list

	@Test(priority = 19,dataProvider="Status")
	public void getStatusProcessItems(String Status) {
		System.out.println("************getStatusProcessItems************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType(ContentType.JSON)
				.when().get("/search/1/" + acctId + "/global/myitems/"+Status + "/list");
		/*Response response = REQUEST.header("X-Api-Key", "Ak06f526b7-8594-435e-aa2e-0f6bcb1862a7").contentType(ContentType.JSON)
				.log().all().when().get("/search/1/AcZAAN7lsPYw/global/myitems/"+Status + "/list");*/
		response.then().assertThat().statusCode(200).and().log().body().extract().asString();
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getStatusProcessItems.json"));

	}
	@Test(priority = 20)
	//https://orangescape4.tst.zingworks.com/search/1/AcZAAN7lsPYw/global/pendingitems?page_number=1&page_size=5
	public void getCurrentItemsAssigned() {
		System.out.println("************getCurrentItemsAssigned************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).contentType(ContentType.JSON)
				.when().get("/search/1/" + acctId + "/global/pendingitems?page_number=1&page_size=5");
		/*Response response = REQUEST.header("X-Api-Key", "Ak72d7fa07-c7f5-483f-bdad-770237de2f03").contentType(ContentType.JSON)
				.log().all().when().get("/search/1/AcZAAN7lsPYw/global/pendingitems?page_number=1&page_size=5");*/
		response.then().assertThat().statusCode(200).and().log().body().extract().asString();
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getCurrentItemsAssigned.json"));

	}

	@Test(priority = 20)
	public void archiveProcess() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.replaceHeader("X-Api-Key", apiKeyAdmin).log().all().when()
				.put("flow/1/" + acctId + "/process/" + processId + "/archive");
		response.then().and().log().body().assertThat().statusCode(200).and().log().body();
	}

	@Test(priority = 21)
	public void deleteProcess() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.replaceHeader("X-Api-Key", apiKeyAdmin).log().all().when()
				.delete("flow/1/" + acctId + "/process/" + processId);
		response.then().assertThat().statusCode(200).and().log().body();
	}
	@DataProvider(name="fileName")
	public Object getData()	
	{
		return new Object[] {"sun.jpg","tree.jpg"};
	}
	@DataProvider(name="Status")
	public Object getStatus()	
	{
		return new Object[] {"Draft","InProgress","Completed"};
	}
	@AfterTest
	public void tearDown() throws IOException, InterruptedException {
		driver.quit();
	}
}
