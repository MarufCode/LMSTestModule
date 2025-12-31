package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;

public class TestConfirmationPage extends CommonToAllPage {

    // Timer locator (provided by you)
    private By testTimer = By.xpath("//*[@id='CountDownTimer']/div/canvas");


     // Checks if test timer is visible on confirmation page
    public boolean isTestTimerVisible() {
        return isElementPresent(testTimer) && getElement(testTimer).isDisplayed();
    }
}
