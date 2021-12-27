package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static PrintStream Logs;
	
	public RequestSpecification myRequestSpecifications(String bearerToken) throws IOException {
		Logs=new PrintStream(new FileOutputStream("Logs.txt"));
		RequestSpecification Req= new RequestSpecBuilder()
										.setBaseUri(getPropertyFile().getProperty("BaseURI"))
										.addHeader("Authorization", bearerToken)
										.addHeader("Content-Type", "application/json")
										.addFilter(RequestLoggingFilter.logRequestTo(Logs))
										.addFilter(ResponseLoggingFilter.logResponseTo(Logs))
										.build();
		return Req;
	}
	
	public RequestSpecification multipartRequestSpecifications(String bearerToken) throws IOException {
		Logs=new PrintStream(new FileOutputStream("Logs.txt"));
		RequestSpecification Req= new RequestSpecBuilder()
										.setBaseUri(getPropertyFile().getProperty("BaseURI"))
										.addHeader("Authorization", bearerToken)
										.addHeader("Content-Type", "multipart/form-data")
										.addFilter(RequestLoggingFilter.logRequestTo(Logs))
										.addFilter(ResponseLoggingFilter.logResponseTo(Logs))
										.build();
		return Req;
	}
	
	public static Properties getPropertyFile() throws IOException {
		Properties prop=new Properties();
		FileInputStream GlobalFile=new FileInputStream("/media/gurjeet/HDD/eclipse-workspace/Mastodon/src/test/java/resources/Global.properties");
		prop.load(GlobalFile);
		return prop;
		//BaseURI= prop.getProperty("BaseURI");
	}

	public Object getJsonVal(Response RawResponse, String key) {
		JsonPath js=new JsonPath(RawResponse.asString());
		return js.get(key);
	}
}
