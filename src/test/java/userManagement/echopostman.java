package userManagement;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;



import core.Statuscode;
import io.restassured.response.Response;
import pojo.Cityrequest;
import pojo.postrequestpojo;
import utils.jsonReader;
import java.io.File;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.*;

public class echopostman {
	@Test(groups = { "Sanity", "E2E" })
	public void validateResponseBodyGetBasicAuth() {

		Response resp = given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println(resp.body().asString());

	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void validateResponseBodyGetDigestAuth() {

		Response resp = given().auth().digest("postman", "password").when().get("https://postman-echo.com/digest-auth"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println(resp.body().asString());
	}

	@Test(groups = { "Sanity", "Regression", "E2E" })
	public void readdatafromjsonreaderfile() throws IOException, ParseException {
		Response resp = given().auth().basic(jsonReader.getTestData("username"), jsonReader.getTestData("password"))
				.when().get("https://postman-echo.com/basic-auth"); // RestAssured

		int actualStatusCode = resp.statusCode(); // RestAssured
		assertEquals(actualStatusCode, 200); // Testng
		System.out.println("readdatafromjsonreaderfile run successfully");
	}

	@Test
	public void Test() throws IOException, ParseException {
		jsonReader.getJsonArrayData("languages", 1);
		System.out.println(jsonReader.getJsonArrayData("languages", 1));
	}

	@Test
	public void Test1() throws IOException, ParseException {
		jsonReader.getJsonArrayData("technology", 1);
		System.out.println(jsonReader.getJsonArrayData("technology", 1));
	}

	@Test
	public void Test2() throws IOException, ParseException {
		JSONArray jsonArray = jsonReader.getJsonArray("contact");
		Iterator<String> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	@Test
	public void validatePostWithString() {

		Response response = given().header("Content-Type", "application/json")
				.body("{\"name\":\"morpheus\",\"job\":\"leader\"}").when().post("https://reqres.in/api/users");
		assertEquals(response.getStatusCode(), Statuscode.CREATED.code);
		System.out.println("validatePostWithString executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePutWithString() {

		Response response = given().header("Content-Type", "application/json")
				.body("{\"name\":\"Ankit\",\"job\":\"Manager\"}").when().put("https://reqres.in/api/users/2");
		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePutWithString executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePatchWithString() {

		Response response = given().header("Content-Type", "application/json").body("{\"name\":\"Simran\"}").when()
				.patch("https://reqres.in/api/users/2");
		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePatchWithString executed successfully");
		System.out.println(response.getBody().asString());
	}

	private static FileInputStream fileInputStream;

	private static FileInputStream fileInputStreamMethod(String requestBodyFileName) {
		try {
			fileInputStream = new FileInputStream(
					new File(System.getProperty("user.dir") + "/resources/TestData/" + requestBodyFileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return fileInputStream;
	}

	@Test
	public void validatePostWithjsonfile() throws IOException {

		Response response = null;
		try {
			response = given().header("Content-Type", "application/json")
					.body(IOUtils.toString(fileInputStreamMethod("postRequestBodyfile.json"))).when()
					.post("https://reqres.in/api/users");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(response.getStatusCode(), Statuscode.CREATED.code);
		System.out.println("validatePostWithjsonfile executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePatchWithjsonfile() {

		Response response = null;
		try {
			response = given().header("Content-Type", "application/json")
					.body(IOUtils.toString(fileInputStreamMethod("postRequestBodyfile.json"))).when()
					.patch("https://reqres.in/api/users/2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePatchWithString executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePutWithjsonfile() {

		Response response = null;
		try {
			response = given().header("Content-Type", "application/json")
					.body(IOUtils.toString(fileInputStreamMethod("postRequestBodyfile.json"))).when()
					.put("https://reqres.in/api/users/2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePutWithString executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePostWithpojo() {
		List<String> postlang = new ArrayList<>();
		postlang.add("Java");
		postlang.add("Python");
		postlang.add("Ruby");

		postrequestpojo postrequest = new postrequestpojo();
		postrequest.setName("morpheus");
		postrequest.setJob("leader");
		postrequest.setLanguages(postlang);

		Response response = given().header("Content-Type", "application/json").body(postrequest).when()
				.post("https://reqres.in/api/users");
		assertEquals(response.getStatusCode(), Statuscode.CREATED.code);
		System.out.println("validatePostWithpojo executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePutWithpojo() {

		postrequestpojo postrequest = new postrequestpojo();
		postrequest.setName("Ankit");
		postrequest.setJob("leader");
		Response response = given().header("Content-Type", "application/json").body(postrequest).when()
				.put("https://reqres.in/api/users/2");
		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePutWithpojo executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePatchWithpojo() {

		postrequestpojo postrequest = new postrequestpojo();
		postrequest.setName("Ankit");
		postrequest.setJob("judge");
		Response response = given().header("Content-Type", "application/json").body(postrequest).when()
				.patch("https://reqres.in/api/users/2");
		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePatchWithpojo executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePostWithcomplexpojo() {
		List<String> postlang = new ArrayList<>();

		postlang.add("Java");
		postlang.add("Python");
		postlang.add("Ruby");
		Cityrequest cityreq1 = new Cityrequest();
		cityreq1.setName("Varanasi");
		cityreq1.setTemperature("34");
		Cityrequest cityreq2 = new Cityrequest();
		cityreq2.setName("Kanpur");
		cityreq2.setTemperature("45");
		List<Cityrequest> cityreq = new ArrayList<>();
		cityreq.add(cityreq1);
		cityreq.add(cityreq2);

		postrequestpojo postrequest = new postrequestpojo();
		postrequest.setName("morpheus");
		postrequest.setJob("leader");
		postrequest.setLanguages(postlang);
		postrequest.setCityrequestbody(cityreq);

		Response response = given().header("Content-Type", "application/json").body(postrequest).when()
				.post("https://reqres.in/api/users");
		assertEquals(response.getStatusCode(), Statuscode.CREATED.code);
		System.out.println("validatePostWithcomplexpojo executed successfully");
		System.out.println(response.getBody().asString());
	}

	@Test
	public void validatePatchWithpojodeserialization() {
	
		String name = "Ankit";
		String job = "judge";

		postrequestpojo postrequest = new postrequestpojo();
		postrequest.setName(name);
		postrequest.setJob(job);
		Response response = given()
				.header("Content-Type", "application/json")
				.body(postrequest)
				.when()
				.patch("https://reqres.in/api/users/2");

		postrequestpojo postresponse = response.as(postrequestpojo.class);
		System.out.println(postresponse.getName());
		System.out.println(postresponse.getJob());
		assertEquals(postresponse.getName(),name);
		assertEquals(postresponse.getJob(),job);
		

		assertEquals(response.getStatusCode(), Statuscode.SUCCESS.code);
		System.out.println("validatePatchWithpojodeserilization executed successfully");
		System.out.println(response.getBody().asString());
	}

}
