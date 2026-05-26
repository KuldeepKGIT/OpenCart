package com.kuldeepkumar290497.testCases.search;

import com.kuldeepkumar290497.pageObjects.HomePage;
import com.kuldeepkumar290497.pageObjects.SearchResultPage;
import com.kuldeepkumar290497.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001SearchProductTest extends BaseClass {

    @Test(groups="Master")
    public void verifySearchFunctionality(){
        logger.info("---executing test to  Validate searching with an existing Product Name---");
        logger.debug("this is a debug log message");

                HomePage hp = new HomePage(getDriver());
                hp.enterProductName("mac");
                hp.clickSearch();

                SearchResultPage sp = new SearchResultPage(getDriver());
                Assert.assertTrue(sp.isProductExist("macBook"),"product is not found!");


        logger.info("Execution is finished!");


    }
}
