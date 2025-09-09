package com.kuldeepkumar290497.testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    public void setup(){
        logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        return RandomStringUtils.random(5,true,false);
    }

    public String randomNumeric(){
        return RandomStringUtils.random(10,false,true);
    }

    public String randomAlphaNumeric(){
        String str = RandomStringUtils.random(3,true, false);
        String num = RandomStringUtils.random(3,false, true);
        return str+"@"+num;
    }
}
