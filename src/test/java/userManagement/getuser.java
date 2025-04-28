package userManagement;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.Statuscode;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.PropertiesReader;
import utils.SoftAssertionutilSingletonpattern;
import utils.jsonReader;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class getuser {

	@Test(groups = { "Sanity", "E2E" })
	public void getuserdata() {

		given().when().get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200);

	}

	@BeforeClass
	public void setup() {
		// Set base URI for the API
		RestAssured.baseURI = "https://reqres.in/api";
	}

	@Test(groups = { "Regression", "E2E" })
	public void testGetUsersWithQueryParameters() {
		Response response = given().queryParam("page", 2).when().get("/users").then().statusCode(200).extract()
				.response();

		// Assert that the response contains 6 users
		response.then().body("data", hasSize(6));

		// Assert that the first user in the list has the correct values
		response.then().body("data[0].id", equalTo(7));// use "equalTo" or "is" both are working
		response.then().body("data[0].email", is("michael.lawson@reqres.in"));
		response.then().body("data[0].first_name", is("Michael"));
		response.then().body("data[0].last_name", is("Lawson"));
		response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void validateStatusCodeGetUser() {

		System.out.println("*****************");
		Response resp = given().queryParam("page", 2).when().get("https://reqres.in/api/users"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void testGetUsersWithMultipleQueryParams() {

		given().queryParam("page", 2).queryParam("per_page", 3).queryParam("rtqsdr", 4).when()
				.get("https://reqres.in/api/users").then().statusCode(200).extract().response();
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void testCreateUserWithFormParam() {
		Response response = given().contentType("application/x-www-form-urlencoded").formParam("name", "John Doe")
				.formParam("job", "Developer").when().post("https://reqres.in/api/users").then().statusCode(201)
				.extract().response();

		// Assert that the response contains the correct name and job values
		response.then().body("name", is("John Doe"));
		response.then().body("job", is("Developer"));
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void testGetUserListWithHeader() {
		given().header("Content-Type", "application/json").when().get("https://reqres.in/api/users?page=2").then()
				.statusCode(200);
		System.out.println("testGetUserListWithHeader Executed Successfully");
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void testWithTwoHeaders() {
		given().header("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
				.header("Content-Type", "application/json").when().get("https://reqres.in/api/users?page=2").then()
				.statusCode(200);
		System.out.println("testWithTwoHeaders Executed Successfully");
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void testGetUserListusingMap() {
		// Set base URI for the API
		RestAssured.baseURI = "https://reqres.in/api";

		// Create a Map to hold headers
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer <your_token_here>");

		// Send a GET request with headers
		given().headers(headers).when().get("/users?page=2").then().statusCode(200);
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void testFetchHeadersfromresponseandvalidate() {
		Response response = given().when().get("https://reqres.in/api/users?page=2").then().extract().response();

		Headers headers = response.getHeaders();

		for (Header h : headers) {
			if (h.getName().contains("Server")) {
				System.out.println(h.getName() + " : " + h.getValue());
				assertEquals(h.getValue(), "cloudflare");
				System.out.println("testFetchHeaders Executed Successfully");
			}
		}
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void verifyStatusCodeDelete() {

		Response resp = given().delete("https://reqres.in/api/users/2");
		assertEquals(resp.getStatusCode(), Statuscode.NO_CONTENT.code);
		System.out.println(Statuscode.NO_CONTENT.msg);

	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void readdatafromjsonreaderfile() throws IOException, ParseException {
		Response resp = given().auth().basic(jsonReader.getTestData("username"), jsonReader.getTestData("password"))
				.when().get("https://postman-echo.com/basic-auth"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println("readdatafromjsonreaderfile run successfully");
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void readdatafrompropertiesfile() {
		String serveraddress = PropertiesReader.readproperty();

		System.out.println(serveraddress);
		Response resp = given().queryParam("page", 2).when().get(serveraddress + "users"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println("readdatafrompropertiesreaderfile run successfully");
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void validateFromPropertiesandTestData() throws IOException, ParseException {
		String serveraddress = PropertiesReader.readproperty();
		String endpoint = jsonReader.getTestData("endpoint");
		String URL = serveraddress + endpoint;
		System.out.println("URL  is : " + URL);
		Response resp = given().queryParam("page", 2).when().get(URL);
		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println("validateFromProperties_TestData executed successfully" + URL);

	}

	@Test
	public void validateWithSoftAssertUtil() {
		RestAssured.baseURI = "https://reqres.in/api";
		Response response = given().queryParam("page", 2).when().get("/users").then().statusCode(200).extract()
				.response();

		SoftAssertionutilSingletonpattern.assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code,
				"Status code is not 200");
		SoftAssertionutilSingletonpattern.assertAll();
		System.out.println("validateWithSoftAssertUtil executed successfully");
	}

	@Test(groups= {"Sanity","E2E"})
	public void getuserdata1() {

		String data=given().when().get("https://jsonplaceholder.typicode.com/todos/1").body().asString();
		
		//System.out.println(data.body().asString());
		
		
		JsonPath path =new JsonPath(data);
		
		System.out.println(path.getString("title"));

	}

}
