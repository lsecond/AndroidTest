package com.workmarket.androidtest.tests.android;

import com.workmarket.androidtest.tests.ShellCommand;

import java.io.File;

public class AdbHelper {
    public static final String ADB="adb";
    private String deviceId;

    public AdbHelper(String deviceId) {
        if(deviceId.isEmpty()){
            throw new RuntimeException("DeviceId is not set");
        }
        this.deviceId=deviceId;
    }

    public void pushFile(File filePath, String destination){
        //Should use $ANDROID_HOME
        executeCommand(String.format("push %s %s%s", filePath.getAbsolutePath(), getExternalStorage(), destination));
    }


    public String getExternalStorage(){
        //Should use $ANDROID_HOME
        return executeCommand("shell printenv EXTERNAL_STORAGE");
    }

    public boolean openURI(String uri){
        //Should use $ANDROID_HOME
        final String adbCommand = "shell am start -a android.intent.action.VIEW -d \"%s\"";
        if(!executeCommand(String.format(adbCommand, uri)).isEmpty()){
            sleep(3000);
            return true;
        }
        return false;
    }

    public String executeCommand(String command){
        return ShellCommand.execute(String.format("%s -s %s %s", ADB, deviceId, command));
    }

    public String executeCommandQuietlyWithNewlines(String command){
        return ShellCommand.executeQuietlyWithNewlines(String.format("%s -s %s %s", ADB, deviceId, command));
    }

    public static void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
        }
    }
}
