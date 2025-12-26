package org.example.base;

import org.example.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.driver.DriverManagerTL.getDriver;

public class CommonToAllPage {


    public void openLogInURL() {
        getDriver().get(PropertyReader.readKey("url"));
    }

    public void clickElement(By by){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void jsClick(By by){
        WebElement element = presenceOfElement(by);
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", element);
    }


    public void enterInput(By by, String key) {
        WebElement element = visibilityOfElement(by);
        element.clear();
        element.sendKeys(key);
    }

    public WebElement presenceOfElement(By elementLocation) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocation));
    }


    public WebElement visibilityOfElement(By elementLocation) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }



    public WebElement getElement(By key){
        return getDriver().findElement(key);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(By by) {
        WebElement element = visibilityOfElement(by);
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({behavior:'instant', block:'center'});", element);
    }


    public void scrollDownByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public boolean isElementPresent(By by) {
        return getDriver().findElements(by).size() > 0;
    }

    public String getText(By by) {
        return visibilityOfElement(by).getText().trim();
    }


    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<WebElement> getElements(By by) {
        return getDriver().findElements(by);
    }

    public void scrollAndPause(By by) {
        scrollToElement(by);
        waitForSeconds(1); // Let UI settle
    }










}
