package testrunner;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class ProfileTestRunner extends Setup {
    ProfilePage profilePage = new ProfilePage(driver);

    @Test(priority = 1, description = "Check user profile")
    public void dologin() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("sania166@gmail.com", "4567");
        Utils.getToken(driver);
    }

    @BeforeTest
    public void setToken() throws ParseException, IOException, InterruptedException {
        Utils.setAuth(driver);
    }

    @Test(priority = 2, description = "Upload Image and Update profile")
    public void seeProfile() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.btnProfileBtn.click();
        loginPage.btnProfileMenuItems.get(0).click();

        profilePage = new ProfilePage(driver);
        profilePage.doProfileUpdate();

        profilePage.btnUpdate.get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String actualResult1 = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        String expectedResult1 = "Image uploaded successfully!";
       // System.out.println(actualResult1);
        Assert.assertTrue(actualResult1.equals(expectedResult1));

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        profilePage.btnUpdate.get(1).click();
        wait2.until(ExpectedConditions.alertIsPresent());
        String actualResult2 = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        String expectedResult2 = "User updated successfully!";
        Assert.assertTrue(actualResult2.equals(expectedResult2));
    }
}