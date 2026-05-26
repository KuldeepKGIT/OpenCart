package com.kuldeepkumar290497.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class SearchResultPage extends BasePage{

    public SearchResultPage(WebDriver driver){
        super(driver);
    }


    @FindBy(xpath = "//*[@class='product-thumb']/descendant::img")
    List<WebElement> products;

    public boolean isProductExist(String productName){
        boolean flag = false;
        for(WebElement product : products){
            if(Objects.equals(product.getAttribute("title"), productName)){
                flag = true;
                break;
            }
        }
        return flag;
    }

}
