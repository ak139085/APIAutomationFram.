package userManagement;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;

public class jsonplaceholder {

	@Test(groups= {"Sanity", "Regression","E2E"})
	public void validateGetResponseBody() {
		// Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Send a GET request and validate the response body using 'then'
		given().when().get("/todos/1").then().assertThat().statusCode(200).body(not(isEmptyString()))
				.body("title", equalTo("delectus aut autem")).body("userId", equalTo(1));
	}

	@Test(groups= {"Regression","E2E"})
	public void validateResponseHasItems() {
		// Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Send a GET request and store the response in a variable
		Response response = given().when().get("/posts").then().extract().response();

		// Use Hamcrest to check that the response body contains specific items
		assertThat(response.jsonPath().getList("title"),
				hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
	}

	@Test(groups= {"Sanity","E2E"})
	public void validateResponseHasSize() {
		// Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Send a GET request and store the response in a variable
		Response response = given().when().get("/comments").then().extract().response();

		// Use Hamcrest to check that the response body has a specific size
		assertThat(response.jsonPath().getList(""), hasSize(500));
	}

	@Test(groups= {"Sanity", "Regression","E2E"})
	public void validateListContainsInOrder() {
		// Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Send a GET request and store the response in a variable
		Response response = given().when().get("/comments?postId=1").then().extract().response();

		// Use Hamcrest to check that the response body contains specific items in a
		// specific order
		List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com",
				"Nikita@garfield.biz", "Lew@alysha.tv", "Hayden@althea.biz");
		assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
	}

}
