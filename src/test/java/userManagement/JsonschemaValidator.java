package userManagement;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonschemaValidator {
	
	@Test(groups= {"Sanity","E2E"})
	public void jsonschemavalidation() {
		File schema=new File("resources/TestData/Expectedschema.json");
		

		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchema(schema));
		System.out.println("Successfully executed jsonschemavalidation");

	}
	

}
