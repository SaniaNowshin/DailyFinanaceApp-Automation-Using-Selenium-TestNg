package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.interactions.Actions;
import pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    RegistrationPage registrationPage;

    @Test(priority = 1, description = "User can do registration by filling all the fields")
    public void userRegistration() throws InterruptedException, IOException, ParseException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "sania" + Utils.generateRandom(1000,9999) + "@gmail.com";
        String password = "4567";
        String phoneNumber = "0151" + Utils.generateRandom(1000000, 9999999);
        String address = faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        registrationPage.doRegistration(userModel);

        doAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstName);
        userObj.put("lastName", lastName);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("phoneNumber", phoneNumber);
        userObj.put("address", address);

        Utils.saveUserInfo("./src/test/resources/users.json", userObj);

        Actions actions = new Actions(driver);
        actions.moveToElement(registrationPage.blankSpace).perform();
        Thread.sleep(5000);

    }

    @Test(priority = 2, description = "User can do registration by filling only the mandatory fields", groups = "smoke")
    public void userRegByMandatoryFields() throws InterruptedException, IOException, ParseException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String email = "sania" + Utils.generateRandom(1000, 9999) + "@gmail.com";
        String password = "4567";
        String phoneNumber = "0151" + Utils.generateRandom(1000000, 9999999);

        UserModel userModel = new UserModel();

        userModel.setFirstname(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);

        registrationPage.doRegistration(userModel);


        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstName);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("phoneNumber", phoneNumber);

        Utils.saveUserInfo("./src/test/resources/users.json", userObj);
        Actions actions = new Actions(driver);
        actions.moveToElement(registrationPage.blankSpace).perform();
        Thread.sleep(5000);
    }

    public void doAssertion() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Toastify__toast")));
        String successMessageActual = driver.findElement(By.className("Toastify__toast")).getText();
        String successMessageExpected = "registered successfully!";
        System.out.println(successMessageActual);
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }
}