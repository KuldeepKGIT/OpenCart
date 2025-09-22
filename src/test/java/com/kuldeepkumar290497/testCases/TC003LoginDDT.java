package com.kuldeepkumar290497.testCases;

import com.kuldeepkumar290497.pageObjects.HomePage;
import com.kuldeepkumar290497.pageObjects.LoginPage;
import com.kuldeepkumar290497.pageObjects.MyAccountPage;
import com.kuldeepkumar290497.testBase.BaseClass;
import com.kuldeepkumar290497.utilities.UtilExcel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC003LoginDDT extends BaseClass {

    @Test(dataProvider="dp")
    public void verifyLoginDDT(String email, String password, String exp){

        logger.info("**** Starting TC003LoginDDT ****");

        //HomePage
        try {
            HomePage homePage = new HomePage(driver);
            homePage.ClickMyAccount();
            homePage.ClickLogin();

            // LoginPage

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);



            boolean targetPage = myAccountPage.isMyAccountDisplayed();
            if(exp.equalsIgnoreCase("Valid")){
                if(targetPage){
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                }
                else {
                    Assert.fail();
                }
            }
            if(exp.equalsIgnoreCase("Invalid")){
                if(targetPage){
                    myAccountPage.clickLogout();
                    Assert.fail();
                }
                else {
                    Assert.assertTrue(true);
                }

            }

        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
        logger.info("**** Finished TC_003_LoginDDT *****");
    }
    @DataProvider(name = "dp")
    public Object[][] getData(){
        // READ THE DATA FROM THE EXCEL FILE
        // GIVE THEM IN THE 2D ARRAY
        return UtilExcel.getTestDataFromExcel("sheet1");
    }


}
