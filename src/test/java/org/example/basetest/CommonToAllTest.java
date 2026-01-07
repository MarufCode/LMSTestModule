package org.example.basetest;

import org.example.driver.DriverManagerTL;
import org.example.utils.ScreenshotUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonToAllTest {


    @BeforeMethod(alwaysRun = true)
    public void setup(){
        DriverManagerTL.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureScreenshotForAllure(); // Allure
            ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName()); // Local
        }
        DriverManagerTL.down();
    }



}
