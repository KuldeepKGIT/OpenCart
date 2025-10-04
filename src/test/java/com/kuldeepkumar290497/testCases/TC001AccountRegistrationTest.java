package com.kuldeepkumar290497.testCases;

import com.kuldeepkumar290497.pageObjects.AccountRegistrationPage;
import com.kuldeepkumar290497.pageObjects.HomePage;
import com.kuldeepkumar290497.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001AccountRegistrationTest extends BaseClass {
    @Test(groups = {"Regression","Master"})
    public void verifyAccountRegistration(){

        logger.info("*****--Starting test Execution!--*****");
        logger.debug("This is a debug log message");

        try {

            HomePage homePage = new HomePage(driver);

            homePage.ClickMyAccount();
            logger.info("Click on MyAccount Link...");
            homePage.ClickRegister();
            logger.info("Click on Register Account Link...");

            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details...");
            regPage.setFirstName(randomString());
            regPage.setLastName(randomString());
            regPage.setEmail(randomString()+"@gmail.com");
            regPage.setTelephone(randomNumeric());
            String password = randomAlphaNumeric();

            regPage.setPassword(password);
            regPage.setConfirmPassword(password);

            regPage.setPrivacyPolicy();
            regPage.clickContinue();
            logger.info("Validating expected message..");

            String confMsg = regPage.getConfirmationMsg();
            if(confMsg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test Failed!...");
                logger.debug("Debug logs...");
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            Assert.fail();


        }
        finally {
            logger.info("**** Finished TC001AccountRegistrationTest ****");
        }
    }
}
