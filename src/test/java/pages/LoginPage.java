package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(id = "email")
    public WebElement txtEmail;

    @FindBy(id = "password")
    public WebElement txtPassword;

    @FindBy(css = "[type=submit]")
    WebElement btn;

    @FindBy(css = "[data-testid=AccountCircleIcon]")
    public WebElement btnProfileBtn;

    @FindBy(css = "[role=menuitem]")
    public List<WebElement> btnProfileMenuItems;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String email, String password) {
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btn.click();
    }
    public void doLogOut(){
          btnProfileBtn.click();
          btnProfileMenuItems.get(1).click();
    }
}
