package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserDashboardPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class UserDashboardRunner extends Setup {
    LoginPage loginPage;
    UserDashboardPage userDashboardPage = new UserDashboardPage(driver);
    @BeforeTest (description = "last registered user Login", groups = "smoke")

    public void doLogin() throws IOException, ParseException {

        loginPage=new LoginPage(driver);
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email= (String) userObj.get("email");
        String password= (String) userObj.get("password");

        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin(email,password);
        }

    }
    @Test(priority = 1, description = "Add a cost/expenditure of the last user")
    public void addCost(){
        LoginPage loginPage = new LoginPage(driver);
        userDashboardPage = new UserDashboardPage(driver);
        userDashboardPage.btnAddCost.click();
        userDashboardPage.doAddCost("Laptop","2", "35000", "07/06/2025", "Awesome product");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        loginPage.doLogOut();
    }
}
