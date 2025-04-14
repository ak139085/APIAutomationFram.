package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonReader {

	public static String getTestData(String input) throws IOException, ParseException {
		String testdata;
		return testdata = (String) getJsonData().get(input);// input is the key

	}

	public static JSONObject getJsonData() throws IOException, ParseException {

		// pass the path of the testdata.json file
		File filename = new File("resources//TestData//testdata.json");
		// convert json file into string
		String json = FileUtils.readFileToString(filename, StandardCharsets.UTF_8);
		// parse the string into object
		Object obj = new JSONParser().parse(json);
		// give jsonobject o that I can return it to the function everytime it get called
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;

	}
	
	public static JSONArray getJsonArray(String key) throws IOException, ParseException {
		JSONObject jsonobject=getJsonData();
		JSONArray jsonArray = (JSONArray) jsonobject.get(key);
		return jsonArray;
	}
	
	public static Object getJsonArrayData(String key,int index) throws IOException, ParseException {
		JSONArray languages=getJsonArray(key);
		return languages.get(index);
	}

}
