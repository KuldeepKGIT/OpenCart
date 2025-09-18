package com.kuldeepkumar290497.testCases;

import com.kuldeepkumar290497.pageObjects.HomePage;
import com.kuldeepkumar290497.pageObjects.LoginPage;
import com.kuldeepkumar290497.pageObjects.MyAccountPage;
import com.kuldeepkumar290497.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002LoginAccountTest extends BaseClass {

    @Test
    public void loginAccount(){

        logger.info("***** Starting the Login account Test *****");
        logger.debug("capturing application debug logs....");
        try {

            HomePage hp = new HomePage(driver);
            hp.ClickMyAccount();
            logger.info("Clicked on MyAccount Link...");
            hp.ClickLogin();
            logger.info("Clicked on Login account link....");

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Entering valid email and password..");
            loginPage.setEmail(p.getProperty("email"));
            loginPage.setPassword(p.getProperty("password"));
            logger.info("clicked on login button..");
            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountDisplayed();
            Assert.assertTrue(targetPage, "Login failed");
        } catch (Exception e) {
            logger.error("Exception occurred in loginAccount test", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Test execution TC002LoginAccountTest finished *****");


    }

}
