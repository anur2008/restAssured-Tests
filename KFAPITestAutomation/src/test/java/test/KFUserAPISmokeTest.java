package test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.kissflow.qa.shared.HomePage;
import org.kissflow.qa.shared.LoginPage;
import org.kissflow.qa.utils.KissflowPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class KFUserAPISmokeTest extends KissflowPage {

	final String apiKeyAdmin = prop.getProperty("apiKey");
	String acctId = prop.getProperty("account_id");
	public String AcInput = null;
	public String grpId = null;

	public String createNewUser() {
		JsonObject userObject = new JsonObject();
		userObject.addProperty("FirstName", "Smoke");
		userObject.addProperty("LastName", "Tester");
		userObject.addProperty("Email", "SmokeTester@gmail.com");

		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Add User************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(userObject.toString()).when().post("/user/1/" + acctId + "/");
		response.then().log().all().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		String userId = response.jsonPath().get("_id");

		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUser.json"));
		return userId;
	}

	@Test(priority = 1)
	public void ActivateUser() {
		AcInput = createNewUser();

		String ActInput1 = "[\"" + AcInput + "\"]";
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Activate User************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(ActInput1).when().post("/user/1/" + acctId + "/activate");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("activateUser.json"));
		// delUser();
	}

	// @Test(priority = 2)
	public void getUserList() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************GetUser List************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.get("/user/1/" + acctId + "/1/5");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();

	}

	@Test(priority = 7)
	public void getUserDetails() {
		AcInput = createNewUser();
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************GetUser Details************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.get("/user/1/" + acctId + "/" + AcInput);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getUserDetails.json"));
		// delUser();

	}

	public String createGroup() throws IOException {
		AcInput = createNewUser();
		LinkedHashMap<String, String> grpuserObject = getJsonData1("LoginDetails1.json", "Groups");
		// String finalGrpUserObj=grpuserObject.replace("_id", AcInput);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Create group************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(grpuserObject).when().post("/group/1/" + acctId);
		response.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createGroup.json"));
		grpId = response.jsonPath().get("_id");

		return grpId;
	}

	@Test(priority = 6)
	public void listGrpByPage() throws IOException {
		AcInput = createNewUser();
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("**************listGrpByPage**********");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.get("/group/1/" + acctId + "/list?page_number=1&page_size=10");
		response.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("listByGrp.json"));
		// delUser();

	}

	// @Test(priority = 6)
	public void listGrpByPagePost() throws IOException {
		LinkedHashMap<String, String> grpuserList = getJsonData1("LoginDetails1.json", "ListGroup");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************listGrpByPagePost************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(grpuserList).when().post("/group/1/" + acctId + "/list?page_number=1&page_size=10");
		response.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		delUser();
	}

	@Test(priority = 4)
	public void getGroupDetails() throws IOException {
		grpId = createGroup();
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("**************getGroupDetails **********");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.get("/group/1/" + acctId + "/" + grpId);
		response.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getGroupDetails.json"));

	}

	@Test(priority = 5)
	public void getGroupDetailsByPage() throws IOException {
		AcInput = createNewUser();
		// grpId = createGroup();
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("**************getGroupDetailsByPage **********");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.get("/group/1/" + acctId + "/" + grpId + "/members?page_number=1&page_size=10");
		response.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getGroupDetailsByPage.json"));

	}

	@Test(priority = 8)
	public void delGroup() throws IOException {
		AcInput = createNewUser();
		// String delGrpId=createGroup();
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************delGroup************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).when()
				.delete("/group/1/" + acctId + "/" + grpId);
		response.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body()
				.extract().asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("delGroup.json"));
		// delUser();
	}

	@Test(priority = 3)
	public void BulkUpdateUser() {
		AcInput = createNewUser();
		JsonObject BulkUpdateDetails = new JsonObject();
		BulkUpdateDetails.addProperty("LastName", "TesterOne");
		BulkUpdateDetails.addProperty("_id", AcInput);
		String BulkUpdateInput = "[" + BulkUpdateDetails + "]";
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************BulkUpdate User************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(BulkUpdateInput).when().post("/user/1/" + acctId + "/batch");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("bulkUpdateUser.json"));

		// delUser();
	}

	@Test(priority = 9)
	public void DeactivateUser() {
		AcInput = createNewUser();

		String dActInput = "[\"" + AcInput + "\"]";

		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Deactivate User************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(dActInput).when().post("/user/1/" + acctId + "/deactivate");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("deactivateUser.json"));
		// delUser();
	}

	@AfterMethod

	public void delUser() {
		JsonObject DelDetails = new JsonObject();
		DelDetails.addProperty("_id", AcInput);
		DelDetails.addProperty("_is_deleted", true);
		String DelInput = "[" + DelDetails.toString() + "]";
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Del User************");
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON)
				.body(DelInput.toString()).when().post("/user/1/" + acctId + "/batch/");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
		// response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("delUser.json"));
	}

}
