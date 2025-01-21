package Tests.Login;

import Config.Config;
import Pages.LoginPage.Login;
import Tests.TestBase;
import Utilities.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    String jsonFilePath = "C:\\Users\\Administrator\\Desktop\\Tickhelp\\Automation_selenuimFrameWork\\src\\resources\\testData\\LoginData.json";

    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        login.navigateToWebsite(url);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException, ParseException {
        JSONArray userLogins = JSONReader.getUserLogins(jsonFilePath);
        Object[][] data = new Object[userLogins.size()][2];

        // Loop through each user in the JSON file and extract username & password
        for (int i = 0; i < userLogins.size(); i++) {
            JSONObject user = (JSONObject) userLogins.get(i);
            data[i][0] = user.get("username");
            data[i][1] = user.get("password");
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        login.EnterName(username);
        login.EnterPass(password);
        login.ClickLogin();

    }
}
