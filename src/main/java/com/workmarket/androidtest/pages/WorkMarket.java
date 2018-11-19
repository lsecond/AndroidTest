package com.workmarket.androidtest.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class WorkMarket {

    private final AndroidDriver<AndroidElement> driver;

    public WorkMarket(AndroidDriver<AndroidElement> driver){
        this.driver=driver;
    }

    public LoginPage LoginPage() {return new LoginPage(driver);}

    public WelcomePage WelcomePage() {return new WelcomePage(driver);}
}
