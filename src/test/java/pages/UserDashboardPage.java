package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UserDashboardPage {
    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;

    @FindBy(id = "itemName")
    public WebElement txtItemName;

    @FindBy(css = "[type=button]")
    public List<WebElement> btnQuantity;

    @FindBy(id = "amount")
    public WebElement txtItemAmount;

    @FindBy(id = "purchaseDate")
    public WebElement txtPurchaseDate;

    @FindBy(id = "month")
    public WebElement purchaseMonth;

    @FindBy(id = "remarks")
    public WebElement txtItemRemarks;

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;

    public UserDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doAddCost(String itemName, String quantity, String amount, String purchase_date, String remark) {
        txtItemName.sendKeys(itemName);
        int quantityIndex = Integer.parseInt(quantity);
        if (quantityIndex == 1 || quantityIndex == 2) {
            btnQuantity.get(quantityIndex).click();
        }

        txtItemAmount.sendKeys(amount);
        txtPurchaseDate.sendKeys(purchase_date);

        Select select = new Select(purchaseMonth);
        select.selectByVisibleText("July");
        txtItemRemarks.sendKeys(remark);
        btnSubmit.click();
    }
}
