package com.workmarket.androidtest.tests;

import org.junit.Assert;

import static com.workmarket.androidtest.tests.AbstractTest.getApp;


public class PublicAction {

    private PublicAction() {}

    static public void login(AbstractTest.UserEnum user) {
        Assert.assertTrue("Unable to tap on have an account button",  getApp().WelcomePage().tapOnHaveAnAccount());
        Assert.assertTrue("Unable to input email",  getApp().LoginPage().inputEmail(user.email));
        Assert.assertTrue("Unable to input password",  getApp().LoginPage().inputPassword(user.password));
        Assert.assertTrue("Unable to tap on login button",  getApp().LoginPage().tapOnSignInButton());
    }
}
