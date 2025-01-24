package com.obilet.utils;

import android.content.Context;
import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

public class DriverManager {
    private static UiDevice device;
    private static final long LAUNCH_TIMEOUT = 5000;

    public static UiDevice getDevice() {
        if (device == null) {
            initializeDevice();
        }
        return device;
    }

    private static void initializeDevice() {
        try {
            // Initialize UiDevice instance
            device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

            // Start from the home screen
            device.pressHome();

            // Wait for launcher
            String launcherPackage = device.getLauncherPackageName();
            device.wait(Until.hasObject(androidx.test.uiautomator.By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

            // Launch the app
            Context context = ApplicationProvider.getApplicationContext();
            Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.obilet.androidside");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
            context.startActivity(intent);

            // Wait for the app to appear
            device.wait(Until.hasObject(androidx.test.uiautomator.By.pkg("com.obilet.androidside").depth(0)), LAUNCH_TIMEOUT);

            System.out.println("Device başarıyla başlatıldı");
            
        } catch (Exception e) {
            throw new RuntimeException("Device başlatılırken beklenmeyen bir hata oluştu: " + e.getMessage());
        }
    }

    public static void quitDriver() {
        try {
            if (device != null) {
                device.pressHome();
                device = null;
                System.out.println("Device başarıyla kapatıldı");
            }
        } catch (Exception e) {
            System.err.println("Device kapatılırken hata oluştu: " + e.getMessage());
        }
    }
}
