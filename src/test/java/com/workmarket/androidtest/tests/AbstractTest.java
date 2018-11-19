package com.workmarket.androidtest.tests;

import com.workmarket.androidtest.pages.WorkMarket;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;


public abstract class AbstractTest {

    public enum UserEnum {

        VALID_USER("jiangliu321@hotmail.com", "1Dtjcjsh");


        public final String email;
        public final String password;

        UserEnum(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static final Logger log = Logger.getLogger(Class.class.getName());

   // protected static WebDriver driver;

    public static AndroidDriver<AndroidElement> driver;

    public static WorkMarket app;

    public static AppiumDriverLocalService service;

    public static WorkMarket getApp() {
        return app;
    }

    @BeforeClass
    public static void beforeClassAbstractTest() {

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            File app = new File("workmarket.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus6P7.1.1");
     //       capabilities.setCapability(MobileCapabilityType.UDID, "3375a9f30704");
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//
//            capabilities.setCapability("appPackage", "com.workmarket.android.native");
//            capabilities.setCapability("appActivity", "com.workmarket.android.WelcomeActivity");
            capabilities.setCapability("avd","Nexus6P7.1.1");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.*");


            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//            driver.closeApp();
//            driver.launchApp();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClassAbstractTests() {
        if (driver != null) {
            log.info("Close driver");

            driver.quit();
        }
        service.stop();

    }

    public static Collection<Object[]> parametersInjection(Object[] paramsToInject) {
        final Collection<Object[]> result = new ArrayList<>();
        for (Object e : paramsToInject) {
            result.add(new Object[]{e});
        }
        return result;
    }
}
