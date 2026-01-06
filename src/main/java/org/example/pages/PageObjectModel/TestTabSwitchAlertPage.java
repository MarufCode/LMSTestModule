package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.driver.DriverManagerTL.getDriver;

public class TestTabSwitchAlertPage extends CommonToAllPage {

    // Locators
//    private By confirmMessage = By.xpath("//strong[contains(text(),'Congratulations!')]");


    // Waits for tab-switch warning alert and returns its text

    public String getTabSwitchWarningText() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText().trim();
    }

     // Accepts the warning alert
    public void acceptTabSwitchAlert() {
        getDriver().switchTo().alert().accept();
    }

     // Checks if alert is present (safe)
    public boolean isWarningAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
