package userManagement;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

import static io.restassured.RestAssured.*;

public class ergast {

	@Test(groups= {"Sanity","E2E"})
	public void validateResponseBodyGetPathParam() {

		Response resp = given().pathParam("raceSeason", "2017").when()
				.get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng

	}

	@Test(groups= {"Regression","E2E"})
	public void printresponsebodyinconsole() {

		Response resp = given().pathParam("raceSeason", "2017").when()
				.get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println(resp.body().asString());

	}

}
