package com.kuldeepkumar290497.testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public static Logger logger;
    public Properties p;

    public BaseClass(){
        logger = LogManager.getLogger(this.getClass()); //log4j for logs purpose.
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException
    {
        //Loading properties file
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);


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

    @AfterClass(alwaysRun = true)
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

    public String captureScreen(String tname) {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }
}
