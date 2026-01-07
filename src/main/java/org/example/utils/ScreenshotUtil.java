package org.example.utils;

import io.qameta.allure.Attachment;
import org.example.driver.DriverManagerTL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static void captureScreenshot(String testName) {

        if (DriverManagerTL.getDriver() == null) {
            System.out.println("Driver is null. Local screenshot not captured.");
            return;
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        Path screenshotPath = Paths.get(
                System.getProperty("user.dir"),
                "screenshots",
                testName + "_" + timestamp + ".png"
        );

        try {
            Files.createDirectories(screenshotPath.getParent());

            File src = ((TakesScreenshot) DriverManagerTL.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(src.toPath(), screenshotPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] captureScreenshotForAllure() {
        System.out.println(">>> ALLURE SCREENSHOT METHOD CALLED <<<");

        if (DriverManagerTL.getDriver() == null) {
            System.out.println(">>> DRIVER IS NULL <<<");
            return new byte[0];
        }
        byte[] bytes = ((TakesScreenshot) DriverManagerTL.getDriver()).getScreenshotAs(OutputType.BYTES);
        System.out.println(">>> SCREENSHOT SIZE = " + bytes.length);
        return bytes;
    }

}
