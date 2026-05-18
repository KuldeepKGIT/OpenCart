package com.kuldeepkumar290497.testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
//    public static WebDriver driver;
private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Logger logger;
    public Properties p;

    public BaseClass(){
        logger = LogManager.getLogger(this.getClass()); //log4j for logs purpose.
    }
    // Getter for driver - use this everywhere instead of direct access
    public static WebDriver getDriver() {
        return driver.get();
    }
    // Setter for driver
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException
    {
        //Loading properties file
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);
        WebDriver webDriver = null;

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //OS
            if(os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN10);
            }
            else if (os.equalsIgnoreCase("Linux"))
            {
                capabilities.setPlatform((Platform.LINUX));

            } else if (os.equalsIgnoreCase("mac"))
            {
             capabilities.setPlatform((Platform.MAC));
            }
            else {
                System.out.println("No matching os found!");
                return;
            }
            //browser
            switch(br.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                case "firefox" : capabilities.setBrowserName("firefox"); break;
                default: System.out.println("No matching browser"); return;
            }

            webDriver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }


        if(p.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (br.toLowerCase()){
                case "edge":{
                    webDriver = new EdgeDriver();
                    break;
                }
                case "chrome":{
                    webDriver = new ChromeDriver();
                    break;
                }
                default:
                    System.out.println("No Matching browser found!");
            }
        }
// Set the driver for this thread
        setDriver(webDriver);
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(p.getProperty("appURL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if(getDriver() != null)
         getDriver().quit();
        driver.remove();
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

        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }
}
