package org.example.utils;

import org.openqa.selenium.WindowType;

import static org.example.driver.DriverManagerTL.getDriver;

public class TabSwitchUtil {

    public static void switchTabAndComeBack() {

        String originalWindow = getDriver().getWindowHandle();

        // Open new tab
        getDriver().switchTo().newWindow(WindowType.TAB);

        // Close it immediately
        getDriver().close();

        // Switch back
        getDriver().switchTo().window(originalWindow);
    }
}