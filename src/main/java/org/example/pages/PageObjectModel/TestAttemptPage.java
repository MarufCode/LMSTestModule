package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;

public class TestAttemptPage extends CommonToAllPage {

    // Locator provided by you
    private By attemptCountText =
            By.xpath("//span[normalize-space()='Reattempt:']/parent::p");

    /**
     * Extracts remaining attempts from text like:
     * "Reattempt:\nAllowed 51 times"
     */
    public int getRemainingAttempts() {

        String text = visibilityOfElement(attemptCountText).getText().trim();

        // Extract number safely
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}
