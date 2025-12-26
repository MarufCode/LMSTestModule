package org.example.utils;

import org.example.driver.DriverManagerTL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String captureScreenshot(String testName) {

        if (DriverManagerTL.getDriver() == null) {
            System.out.println("Driver is null. Screenshot not captured.");
            return null;
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String screenshotPath = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) DriverManagerTL.getDriver())
                .getScreenshotAs(OutputType.FILE);

        File dest = new File(screenshotPath);

        try {
            Files.createDirectories(dest.getParentFile().toPath());
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
