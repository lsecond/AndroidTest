package com.workmarket.androidtest.tests;

import com.workmarket.androidtest.pages.LoginPage;
import com.workmarket.androidtest.pages.MainPage;
import com.workmarket.androidtest.pages.WelcomePage;
import org.junit.Assert;
import org.junit.Test;

import static com.workmarket.androidtest.tests.AbstractTest.UserEnum.VALID_USER;

public class demoTest extends AbstractTest {

    WelcomePage welcomePage = new WelcomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    MainPage mainPage = new MainPage(driver);


    @Test
    public void validLoginAndLogOutTest() {

        Assert.assertTrue("Unable to tap on have an account button",  welcomePage.tapOnHaveAnAccount());
        Assert.assertTrue("Unable to input email",  loginPage.inputEmail(VALID_USER.email));
        Assert.assertTrue("Unable to input password",  loginPage.inputPassword(VALID_USER.password));
        Assert.assertTrue("Unable to tap on login button",  loginPage.tapOnSignInButton());
        Assert.assertTrue("Unable to verify on main page",  mainPage.verifyLoginSuccess());


    }
}
