package com.workmarket.androidtest.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends AbstractPage{

    @AndroidFindBy(id = "welcome_activity_already_have_an_account")
    private MobileElement haveAnAccount;


    public WelcomePage(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean tapOnHaveAnAccount() {
        log.info("Tap On have a account");
        return tapMobileElement(haveAnAccount);
    }
}
