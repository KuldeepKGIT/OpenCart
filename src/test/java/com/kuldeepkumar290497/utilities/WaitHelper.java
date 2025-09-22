package com.kuldeepkumar290497.utilities;

import com.kuldeepkumar290497.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper extends BasePage {
     WebDriver driver;

    public WaitHelper(WebDriver driver){
        super(driver);
this.driver = driver;}
    public WebElement visibilityOfElement(WebElement elementLocation) {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(elementLocation));
    }
}
