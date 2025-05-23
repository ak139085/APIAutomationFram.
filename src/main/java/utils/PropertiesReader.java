package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	public static String readproperty() {

		File propfile = new File(System.getProperty("user.dir") + "\\config.properties");
		FileReader filereader = null;
		Properties properties = new Properties();
		try {
			filereader = new FileReader(propfile);
			properties.load(filereader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = properties.getProperty("server");
		return value;

	}

}
