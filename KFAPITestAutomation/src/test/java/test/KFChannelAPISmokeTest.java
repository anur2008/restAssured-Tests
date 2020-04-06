package test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.kissflow.qa.channel.ChannelHomePage;
import org.kissflow.qa.shared.HomePage;
import org.kissflow.qa.shared.LoginPage;
import org.kissflow.qa.utils.KissflowPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class KFChannelAPISmokeTest extends KissflowPage {
	final String apiKeyUser = prop.getProperty("yahooApiKey");
	final String apikeyUser1 = prop.getProperty("outlookApiKey");
	final String apiKeyAdmin = prop.getProperty("apiKey");
	String acctId = prop.getProperty("account_id");
	String channelId = null;

	@BeforeTest
	public void setUp() {
		super.initialization();
	}

	@Test(priority = 1)
	@Description("This is end to end test for Channel")
	public void completeChannelTest() throws Exception {
		String Admin = getJsonData("ChannelSmokeTest.json", "Admin");
		String FlowType = getJsonData("ChannelSmokeTest.json", "flowType");
		String FlowName = getJsonData("ChannelSmokeTest.json", "flowName");
		String MemberQAOutlook = getJsonData("ChannelSmokeTest.json", "Member2");
		String PostMessage = getJsonData("ChannelSmokeTest.json", "PostMessage");
		String Announcement = getJsonData("ChannelSmokeTest.json", "Announcement");
		String PollTitle = getJsonData("ChannelSmokeTest.json", "Poll.PollTitle");
		String PollOption1 = getJsonData("ChannelSmokeTest.json", "Poll.PollOption1");
		String PollOption2 = getJsonData("ChannelSmokeTest.json", "Poll.PollOption2");
		String PollOption3 = getJsonData("ChannelSmokeTest.json", "Poll.PollOption3");
		String ImageFieldValue = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldValue");

		LoginPage lg = new LoginPage();
		HomePage homePage = lg.login(Admin);
		String hmTitle = homePage.getHomePageTitle();
		Assert.assertEquals(hmTitle, "Kissflow");
		homePage.createFlowNew(FlowType, FlowName);
		ChannelHomePage chanhp = new ChannelHomePage();

		chanhp.addMemberToChannel(MemberQAOutlook);

		chanhp.verifyAddedMemberInChannel(MemberQAOutlook, FlowName);
		String title1 = chanhp.driver.getCurrentUrl();
		System.out.println("title1:" + title1);
		String[] channelId1 = title1.split("/");
		channelId = channelId1[6];
		System.out.println("ChannelId:" + channelId);
		chanhp.writeAPostInChannel(PostMessage);
		chanhp.verifyPost(PostMessage);
		chanhp.makeAnnouncementInChannel(Announcement);
		chanhp.verifyPost(Announcement);
		chanhp.addPollInChannel(PollTitle, PollOption1, PollOption2, PollOption3);
		chanhp.verifyPost(PollTitle);
		chanhp.addImageInChannel(ImageFieldValue);
		chanhp.verifyImagePost();

	}

	//@Test(priority = 2)
	public void listAllAnnouncements() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************List all announcements************");
		

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("/channel/1/" + acctId + "/" + channelId + "/announcement");
		response.then().assertThat().statusCode(200).and().log().body();
		boolean isAnnouncement = response.jsonPath().get("[0].IsAnnouncement");
		System.out.println("isAnnouncement:" + isAnnouncement);
		Assert.assertEquals(isAnnouncement, true);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("listAllAnnouncements.json"));
	}

	@Test(priority = 3)
	public String createPost() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************createPost************");
		

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).body(
				"{\"Content\":[{\"type\":\"paragraph\",\"data\":{},\"nodes\":[{\"type\":\"text\",\"text\":\"this is for testing\"}]}]}")
				.when().post("/channel/1/" + acctId + "/" + channelId + "/post");
		response.then().assertThat().statusCode(200).and().log().body();
		String postId = response.jsonPath().get("_id");
		System.out.println("postId:" + postId);

		String channelId1 = response.jsonPath().get("ChannelId");
		System.out.println("channelId1:" + channelId1);
		Assert.assertEquals(channelId1, channelId);
		//response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createPost.json"));
		return postId;
	}

	//@Test(priority = 4)
	public void createAnnouncement() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************createAnnouncement************");

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType(ContentType.JSON).body(
				"{\"Content\":[{\"type\":\"paragraph\",\"data\":{},\"nodes\":[{\"type\":\"text\",\"text\":\"This is an announcement created via API\"}]}],\"IsAnnouncement\":true,\"ScheduledAt\":{\"StartDate\":\"2020-03-17T01:00:00-07:00 America/Los_Angeles\",\"EndDate\":\"2020-03-18T01:15:00-07:00 America/Los_Angeles\"},\"IsComment\":false}")
				.when().post("/channel/1/" + acctId + "/" + channelId + "/post");
		response.then().assertThat().statusCode(200).and().log().body();
		String announcementId = response.jsonPath().get("_id");
		System.out.println("announcementId:" + announcementId);
		boolean isAnnounce = response.jsonPath().get("IsAnnouncement");
		System.out.println("isAnnounce:" + isAnnounce);

		String channelId1 = response.jsonPath().get("ChannelId");
		System.out.println("channelId1:" + channelId1);
		Assert.assertEquals(channelId1, channelId);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createAnnouncement.json"));
	}

	//@Test(priority = 5)
	public void addAttachToPost() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************addAttachToPost************");
		String postIdAttach = createPost();

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/star.jpg")).when()
				.post("/channel/1/" + acctId + "/" + channelId + "/" + postIdAttach + "/attachment");
		response.then().assertThat().statusCode(200).and().log().body();
		String bodyAsString = response.asString();
		Assert.assertEquals(bodyAsString.contains("Success"), true, "Response Body contains Success");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addAttachToPost.json"));

	}

	//@Test(priority = 6)
	public void getChannelPostDetails() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************getChannelPostDetails************");

		String postIdGet = createPost();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("/channel/1/" + acctId + "/" + channelId + "/post/" + postIdGet);
		response.then().assertThat().statusCode(200).and().log().body();
		String postIdOne = response.jsonPath().get("_id");
		System.out.println("postIdOne:" + postIdOne);
		Assert.assertEquals(postIdOne, postIdGet);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getChannelPostDetails.json"));
	}
	@Test(priority = 6)
	public void AddComment() throws IOException {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************getChannelPostDetails************");
		//https://orangescape4.tst.zingworks.com/comment/1/AcZAAN7lsPYw/channel/Anu_Channel/post/Pogz4ye2BhDN
        //String postIdGet = createPost();
       // {"Content":[{"type":"paragraph","data":{},"nodes":[{"type":"text","text":"Test"}]}],"AtMention":{}}
        LinkedHashMap<String, String> commentInput=getJsonData1	("ChannelSmokeTest.json", "Comment");
      //String commentInput1=commentInput;
        //System.out.println("JsonComments:"+commentInput);
		/*Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().body(commentInput).when()
				.post("/comment/1/" + acctId + "/channel/"+ channelId + "/post/" + postIdGet);*/
        Response response = REQUEST.header("X-Api-Key", "Ak06f526b7-8594-435e-aa2e-0f6bcb1862a7").log().all().contentType(ContentType.JSON).body(commentInput).when()
				.post("/comment/1/AcZAAN7lsPYw/channel/Anu_Channel/post/PojYHgFD0hQr");
		
		response.then().log().all().assertThat().statusCode(200).and().log().body();
//		String postIdOne = response.jsonPath().get("_id");
//		System.out.println("postIdOne:" + postIdOne);
//		Assert.assertEquals(postIdOne, postIdGet);
		//response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getChannelPostDetails.json"));
	}
	
	//@Test(priority = 6)
	/*public void getCommentResp() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************getChannelPostDetails************");
		https://orangescape4.tst.zingworks.com/comment/1/AcZAAN7lsPYw/Channel/Anu-Channel/Post/PoHwh0tvYtSU/CoBkQAXOYr1p/reply
		String postIdGet = createPost();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("/channel/1/" + acctId + "/" + channelId + "/post/" + postInstId+"/"+commentId+"/reply" ;
		response.then().assertThat().statusCode(200).and().log().body();
		String postIdOne = response.jsonPath().get("_id");
		System.out.println("postIdOne:" + postIdOne);
		Assert.assertEquals(postIdOne, postIdGet);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getChannelPostDetails.json"));*/
	//}

	//@Test(priority = 7)
	public void DelPost() {

		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************DelPost************");

		String postIdDel = createPost();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.delete("/channel/1/" + acctId + "/" + channelId + "/post/" + postIdDel);
		response.then().assertThat().statusCode(200).and().log().body();
		String DelbodyAsString = response.asString();
		Assert.assertEquals(DelbodyAsString.contains("Success"), true, "Response Body contains Success");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("delPost.json"));
	}

	@Test(priority = 8)
	public void archiveChannel() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Archive channel************");
		

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.put("/flow/1/" + acctId + "/channel/" + channelId + "/archive");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
	}

	@Test(priority = 9)
	public void DelChannel() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Delete Channel************");
		

		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.delete("/flow/1/" + acctId + "/channel/" + channelId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString();
	}

	
	@AfterTest
	public void tearDown() throws IOException, InterruptedException {
		driver.quit();

	}
}
