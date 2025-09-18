package com.kuldeepkumar290497.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutLink;

    public boolean isMyAccountDisplayed(){
        try {
            return msgMyAccount.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public void clickLogout(){
        logoutLink.click();
    }
}
