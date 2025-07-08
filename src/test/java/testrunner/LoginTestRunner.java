package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description = "Admin Login With wrong Credentials")
    public void adminLoginWithWrongCredentials() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "1234");
        String headerActual = driver.findElement(By.tagName("p")).getText();
        String headerExpected = "Invalid email or password";
        Assert.assertTrue(headerActual.contains(headerExpected));
        clearCreds();
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Admin Login With Correct Credentials", groups = "smoke")
    public void adminLogin() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "admin123");
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));

        loginPage.doLogOut();
    }
    @Test(priority = 3, description = "User Login from json stored Data")
    public void userLogin() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = userObj.get("email").toString();
        String password = userObj.get("password").toString();

        loginPage = new LoginPage(driver);
        loginPage.doLogin(email,password);

        Assert.assertTrue(loginPage.btnProfileBtn.isDisplayed());

        loginPage.doLogOut();

    }

    public void clearCreds(){
        loginPage = new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
        loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);

        loginPage.txtPassword.sendKeys(Keys.CONTROL,"a");
        loginPage.txtPassword.sendKeys(Keys.BACK_SPACE);
    }
}
