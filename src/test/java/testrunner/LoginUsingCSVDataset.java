package testrunner;

import config.LoginDataset;
import config.Setup;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginUsingCSVDataset extends Setup {
    @Test(dataProvider = "LoginCSVData", dataProviderClass = LoginDataset.class)
    public void doLogin(String email, String password){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin(email,password);
        loginPage.doLogOut();
    }
}