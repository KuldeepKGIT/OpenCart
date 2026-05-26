package com.kuldeepkumar290497.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegisterAccount;

    @FindBy(xpath="//a[text()='Login']")
    WebElement linkLogin;

    @FindBy(xpath = "//input[@name='search']")
    WebElement searchProduct;

    @FindBy(xpath = "//span[@class='input-group-btn']/button")
    WebElement searchBtn;

    public void ClickMyAccount(){
        lnkMyAccount.click();
    }

    public void ClickRegister(){
        lnkRegisterAccount.click();
    }
    public void ClickLogin(){
        linkLogin.click();
    }

    public void enterProductName(String productName){
        searchProduct.sendKeys(productName);
    }

    public void clickSearch(){
        searchBtn.click();
    }
}
