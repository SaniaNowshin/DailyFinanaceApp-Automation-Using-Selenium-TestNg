package pages;

import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage{
    @FindBy(className = "css-zj8u5p")
    public WebElement btnEdit;

    @FindBy(className = "upload-input")
    public WebElement chooseImage;

    @FindBy(className = "css-1bnfzcw")
    public List<WebElement> btnUpdate;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void doProfileUpdate(){

        btnEdit.click();
        String relativePath = "\\src\\test\\resources\\Doll.jpg";
        String absolutePath = System.getProperty("user.dir") + relativePath;
        chooseImage.sendKeys(absolutePath);
    }
}