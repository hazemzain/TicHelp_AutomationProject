package Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    // Method to read JSON data from a given file path
    public static JSONObject readJsonFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(filePath);
        return (JSONObject) jsonParser.parse(fileReader);
    }

    // Method to retrieve user login credentials dynamically
    public static JSONArray getUserLogins(String filePath) throws IOException, ParseException {
        JSONObject jsonObject = readJsonFile(filePath);
        return (JSONArray) jsonObject.get("user_Logins");
    }

    // Method to retrieve filter test data
    public static JSONArray getFilterData(String filePath) throws IOException, ParseException {
        JSONObject jsonObject = readJsonFile(filePath);
        return (JSONArray) jsonObject.get("filters");
    }
    public static JSONArray getdueData(String filePath) throws IOException, ParseException {
        JSONObject jsonObject = readJsonFile(filePath);
        return (JSONArray) jsonObject.get("due");
    }
}
