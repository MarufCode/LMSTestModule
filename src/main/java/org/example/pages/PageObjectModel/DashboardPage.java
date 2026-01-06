package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;

import static org.example.driver.DriverManagerTL.getDriver;

public class DashboardPage extends CommonToAllPage {


    // Page Locators
    By Dashboard_Popup = By.xpath("//h5[contains(text(), 'Hostel Admission')]");
    By Modal_Dismiss = By.xpath("//a[@data-dismiss=\"modal\"]");
    By giveTestButton = By.xpath("//input[@onclick=\"callAlert(4141,'Objective')\"]");
    By Test_Quiz = By.xpath("//p[contains(text(),'Test/Quiz')]");




    // Page Actions

    // Verify popup is visible
    public String getDashboardPopupText() {
        presenceOfElement(Dashboard_Popup);
        visibilityOfElement(Dashboard_Popup);
        waitForSeconds(1);
        return getElement(Dashboard_Popup).getText().trim();
    }

    // Close popup
    public void closeDashboardPopup() {
        if (isElementPresent(Dashboard_Popup)) {
            jsClick(Modal_Dismiss);
        }
    }

    // Scroll
    public void scrollDownToClickTest() {
        scrollAndPause(Test_Quiz);
    }

    // Click
    public void clickToTestQuizTab() {
        visibilityOfElement(Test_Quiz);
        presenceOfElement(Test_Quiz);
        clickElement(Test_Quiz);
    }

    // Navigate to Test/Quiz
    public void openTestQuizSection() {
        scrollToElement(Test_Quiz);
        jsClick(Test_Quiz);
    }

    // Start Quiz
    public void clickGiveTest() {
        scrollToElement(giveTestButton);
        jsClick(giveTestButton);
    }



















}
