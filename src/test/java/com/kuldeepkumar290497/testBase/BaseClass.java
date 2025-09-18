package com.kuldeepkumar290497.testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException
    {
        //Loading properties file
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass()); //log4j for logs purpose.
        switch (br.toLowerCase()){
            case "edge":{
                driver = new EdgeDriver();
                break;
            }
            case "chrome":{
                driver = new ChromeDriver();
                break;
            }
            default:
                System.out.println("No Matching browser found!");
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
