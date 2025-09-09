package com.kuldeepkumar290497.testCases;

import com.kuldeepkumar290497.pageObjects.AccountRegistrationPage;
import com.kuldeepkumar290497.pageObjects.HomePage;
import com.kuldeepkumar290497.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC001AccountRegistrationTest extends BaseClass {
    @Test
    public void verifyAccountRegistration(){

        logger.info("*****--Starting test Execution!--*****");

        driver.get("https://tutorialsninja.com/demo/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        HomePage homePage = new HomePage(driver);
        homePage.ClickMyAccount();
        homePage.ClickRegister();

        AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
        regPage.setFirstName(randomString());
        regPage.setLastName(randomString());
        regPage.setEmail(randomString()+"@gmail.com");
        regPage.setTelephone(randomNumeric());
        String password = randomAlphaNumeric();
        regPage.setPassword(password);
        regPage.setConfirmPassword(password);
        regPage.setPrivacyPolicy();
        regPage.clickContinue();
        String confMsg = regPage.getConfirmationMsg();
        Assert.assertEquals(confMsg,"Your Account Has Been Created!");

    }
}
