package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {
	
	@Before("@PublishStatus")
	public void beforePublishStatus() throws IOException {
		StepDefs s=new StepDefs();
		s.user_has_a_token();
		s.user_has_a_payload_image_file();
		s.user_sends_something_request_using_something_api("post", "UploadMedia");
		s.media_id_is_returned();
	}

}
