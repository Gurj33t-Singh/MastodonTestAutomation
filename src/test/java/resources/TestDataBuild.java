package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.PublishStatusPayload;

public class TestDataBuild {

/*	public PublishStatusPayload PublishPayload(String tweet, List<String> MediaID) {
		
		PublishStatusPayload PublishBody=new PublishStatusPayload();
		PublishBody.setStatus(tweet);
		PublishBody.setMedia_ids(MediaID);
		return PublishBody;
	} 
	*/
	
	public String MediaPayload(String content, String id) {
		return "{\n"
				+ "    \"status\": \""+content+"\",\n"
				+ "    \"media_ids\": [\""+id+"\"]\n"
				+ "}";
	}
	
}
