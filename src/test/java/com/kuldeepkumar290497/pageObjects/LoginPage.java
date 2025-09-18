package com.kuldeepkumar290497.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }
    @FindBy(id = "input-email")
    WebElement inputEmail;

    @FindBy(id = "input-password")
    WebElement inputPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    public void setEmail(String email){
        inputEmail.sendKeys(email);
    }

    public void setPassword(String pass){
        inputPassword.sendKeys(pass);
    }
    public  void clickLogin(){
        btnLogin.click();
}
}
