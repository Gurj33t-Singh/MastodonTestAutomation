Feature: Publish and Delete status

@PublishStatus @ImageStatus
Scenario Outline: User is able to publish a new status
Given User has a token
And User has a payload content "<Content>"
When User sends "post" request using "PublishStatus" api
Then Status code returned is 200 
And Status content is "<Content>"

Examples:
|Content		|
|My first post	|

@DeleteStatus
Scenario: User is able to delete status
Given User has a token
Given User has status id
When User sends "delete" request using "DeleteStatus" api
Then Status code returned is 200 