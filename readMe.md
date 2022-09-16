
# Customer Rewards

This application calculates the rewards for the customer based on the transaction he done in the last 3 months.

## Prerequisites

<ol>
	<li> jdk/jre 8 or more</li>
</ol>

#####  Running application
  <em> java -jar rewards.jar</em>
   
#####  Testing
Run the class, <em> "CustomerControllerIntegrationTest"</em> for integration testing.


#### EndPoint Testing

To test the application from postman, use the following url as a sample:

http://localhost:8080/rewards/12345/rewardPoints

For the sample url, it will return the rewards for the customer with id, 12345 on monthly basis for 3 consecutive months.
 



