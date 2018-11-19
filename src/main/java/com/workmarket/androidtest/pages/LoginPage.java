package com.workmarket.androidtest.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

    @AndroidFindBy(id = "email")
    private MobileElement emailAddressField;

    @AndroidFindBy(id = "password")
    private MobileElement passwordField;

    @AndroidFindBy(id = "sign_in_up_button")
    private MobileElement signInButton;


    public LoginPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean inputEmail(String email) {
        log.info(String.format("Input email: %s", email));
        return sendKeys(emailAddressField, email);
    }

    public boolean inputPassword(String password) {
        log.info(String.format("Input password: %s", password));
        return sendKeys(passwordField, password);
    }

    public boolean tapOnSignInButton() {
        log.info("Tap on sign in button");
        return tapMobileElement(signInButton);
    }

}
