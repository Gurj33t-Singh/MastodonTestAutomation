package stepDefinitions;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.PublishStatusPayload;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;

public class StepDefs extends Utils{

	
	
	static RequestSpecification ReqSpec;
	static String StatusId;
	static String m;
	static Response RawResponse;
	TestDataBuild TDBody=new TestDataBuild();
	String BearerToken;
	RequestSpecification MediaReqSpec;
	
	
	
	@Given("User has a token")
	public void user_has_a_token() throws IOException {
	    // Write code for oAuth token fetch 
		BearerToken= getPropertyFile().getProperty("BearerToken");
	}
	
	@And("User has a payload content {string}")
	public void user_has_a_payload_content_something(String content) throws IOException {
	    // Get a payload and generate request						
		ReqSpec=given()
				.spec(myRequestSpecifications(BearerToken))
				.log()
				.all()
				.body(TDBody.MediaPayload(content, m));
	}
	
	@When("User sends {string} request using {string} api")
	public void user_sends_something_request_using_something_api(String method, String ApiResource) {
	    // Set request http method and API for WHEN
		ApiResources API=ApiResources.valueOf(ApiResource);
		
		if(method.equalsIgnoreCase("POST")) {
			
			if(ApiResource.equalsIgnoreCase("PublishStatus")) {
				RawResponse=ReqSpec.when().post(API.getResource()).then().log().all().extract().response();
			}
			
			if(ApiResource.equalsIgnoreCase("UploadMedia")) {
				RawResponse=MediaReqSpec.when().post(API.getResource()).then().log().all().extract().response();
			}
			
		}
		if(method.equalsIgnoreCase("DELETE")) {
			RawResponse=ReqSpec.pathParam("ID", StatusId).when().delete(API.getResource()).then().log().all().extract().response();
		}
		
	}
	
	@Then("Status code returned is {int}")
	public void status_code_returned_is(Integer int1) {
	    // Verify the status code in THEN 
		
		assertEquals(RawResponse.getStatusCode(), 200);
	}
	
	@And("Status content is {string}")
	public void status_content_is(String string) {
	    // Verify the status content from JSON parse
		String content=getJsonVal(RawResponse, "content").toString();
	}
		
	@Given("User has status id")
	public void user_has_status_id() {
	    // Write code here that turns the phrase above into concrete actions
		StatusId=getJsonVal(RawResponse, "id").toString();
	    System.out.println(StatusId);
	}
	
	//UploadMedia feature
	@Given("User has a payload Image file")
	public void user_has_a_payload_image_file() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		MediaReqSpec=given()
				.spec(multipartRequestSpecifications(BearerToken))
				.multiPart("file", new File(getPropertyFile().getProperty("TweetImage")))
				.log()
				.all();
	    
	}

	
	@Then("Media id is returned")
	public void media_id_is_returned() {
	    // Write code here that turns the phrase above into concrete actions
		m=getJsonVal(RawResponse, "id").toString();
		System.out.println("Media ID returned is: "+m);
	}
	
}
