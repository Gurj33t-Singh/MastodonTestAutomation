package resources;

public enum ApiResources {
	PublishStatus("/api/v1/statuses"),
	DeleteStatus("/api/v1/statuses/{ID}"),
	UploadMedia("/api/v1/media");
	private String resource;

	ApiResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return this.resource;
	}
	
}
