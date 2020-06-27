/* 
 * This script utilizes java libraries RestAssured, TestNG, and Hamcrest
 * which are configured respectively in the Maven POM file. 
 * It tests two respective GET api requests 'random' and 'ten.'
 */
package getChimeTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;

public class GetJokeFactory {
	
	String api1="https://official-joke-api.appspot.com/jokes/programming/random";
	String api2="https://official-joke-api.appspot.com/jokes/programming/ten";

	//First case tests if returned joke is of type 'programming'
	@Test
	public void testJokeRandom() {
		//Requesting the 'random' GET api call 
		//Asserting key 'type' has value equal to 'programming' in json response
		get(api1).then().assertThat().body("type", hasItem("programming"));
		
	}
	
	//Second case derives total jokes returned 
	//Determines if all jokes are of type 'programming.'
	@Test
	public void testJokeTen() {
		
		//Requesting 'ten' GET api call
		//extracting key 'type' from json response
		ArrayList<String> jokes = get(api2).then().extract().path("type");
		
		int count1 = 0;
		int count2 = 0;
		
		//traversing jokes string array
		//counting the occurances of type 'programming' 
		for(String j:jokes) {
			count1++;
			if(j.contains("programming")) {
				count2++;
			}
		
		}
		//conditional statement to determine total joke count
		//all joke are of type 'programming'
		if((count1 == 10) && (count1 == count2)) {
		  System.out.println("The number of jokes returned is: "+count1);
		  System.out.println("All jokes are of type 'programming'");
		}else {
		  System.out.println("The number of jokes returned is: "+count1);
		  System.out.println("Not all jokes returned are of type 'programming'");
		}
		Assert.assertEquals(count1, 10);
		Assert.assertEquals(count1, count2);
	}

}
