package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.driver.DriverManagerTL.getDriver;

public class DashboardTestQuizNavigator extends CommonToAllPage {

    // Locators provided by you
    private By Dashboard_Popup = By.xpath("//h5[contains(text(), 'Hostel Admission')]");

    private By Modal_Dismiss = By.xpath("//a[@data-dismiss=\"modal\"]");

    private By giveTestButton = By.xpath("//input[@onclick=\"callAlert(4141,'Objective')\"]");

    private By Test_Quiz = By.xpath("//p[contains(text(),'Test/Quiz')]");

    // Safely closes dashboard popup (if present) and navigates to Test/Quiz section

    public void navigateToTestQuizSafely() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        // ---------- Close popup if present ----------
        try {
            WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_Popup));
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(Modal_Dismiss));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", closeBtn);
            wait.until(ExpectedConditions.invisibilityOf(popup));
            System.out.println("Dashboard popup closed");

        } catch (TimeoutException e) {
            System.out.println("Dashboard popup not present, continuing...");
        }

        // ---------- Scroll & click Test/Quiz ----------
        WebElement testQuiz = wait.until(ExpectedConditions.visibilityOfElementLocated(Test_Quiz));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", testQuiz);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", testQuiz);
        System.out.println("Navigated to Test/Quiz section");
    }

     // Scrolls and clicks Give Test button (optional use)
    public void clickGiveTestSafely() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        WebElement giveTest =
                wait.until(ExpectedConditions.visibilityOfElementLocated(giveTestButton));

        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", giveTest);

        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].click();", giveTest);
    }
}
