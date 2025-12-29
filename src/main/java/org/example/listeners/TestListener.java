package org.example.listeners;

import org.example.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        ScreenshotUtil.captureScreenshot(testName);

        ScreenshotUtil.captureScreenshotForAllure();

        System.out.println("Screenshot captured and attached to Allure");
    }
}
