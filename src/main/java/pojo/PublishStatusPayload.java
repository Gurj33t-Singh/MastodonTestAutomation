package pojo;

import java.util.ArrayList;
import java.util.List;

public class PublishStatusPayload {
	private String status;
	private List<String> media_ids;

	public List<String> getMedia_ids() {
		return media_ids;
	}

	public void setMedia_ids(List<String> mediaID) {
		this.media_ids = mediaID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
