package com.workmarket.androidtest.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class MainPage extends AbstractPage{

    @AndroidFindBy(id = "whats_new_line_title")
    private List<MobileElement> headers;


    public MainPage(AndroidDriver<AndroidElement> driver) {
        super(driver);

    }

    public boolean verifyLoginSuccess() {
        log.info("Verify correct header displayed");
        sleep(3000);
        for(MobileElement header: headers){
            if(header.getText().contains("FIND WORK NEAR YOU")){
                return true;
            }
        }
        log.severe("Unable to find 'FIND WORK NEAR YOU' title ");
        return false;
    }
}


