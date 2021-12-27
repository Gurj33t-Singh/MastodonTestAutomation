Feature: Upload media 

@UploadMedia 
Scenario: User is able to upload media for post 
Given User has a token
Given User has a payload Image file
When User sends "post" request using "UploadMedia" api
Then Status code returned is 200 
And Media id is returned 