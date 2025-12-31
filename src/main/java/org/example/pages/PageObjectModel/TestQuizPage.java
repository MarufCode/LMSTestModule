package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.driver.DriverManagerTL.getDriver;

public class TestQuizPage extends CommonToAllPage {


    // Locators


    private final Random random = new Random();

    private By questionOptions = By.xpath("//input[( @type='radio' or @type='checkbox' ) and contains(@name,'testQuestions')]");


    private By saveAndNextBtn = By.xpath("//div[contains(@class,'nextWrap')]//button[contains(@class,'btn-next')]");
    private By questionText = By.xpath("//h6[contains(text(),'Questions')]");
    private By testSubmit = By.xpath("//button[@id=\"testSubmitFinal\"]");
    private By saveProgressBtn = By.xpath("//*[@id=\"nextQuest\"][1]");
    private By finalSubmitBtn = By.xpath("//button[contains(text(),'Submit')][1]");
    private By confirmMessage = By.xpath("//strong[contains(text(),'Congratulations!')]");
    private By closeButton = By.xpath("//a[contains(text(),'Close')]");


    // PAGE ACTIONS

    public void answerAllQuestionsAndFinish(int totalQuestions) {

        for (int i = 1; i <= totalQuestions; i++) {
            selectRandomOption();
            clickSaveProgress();
            waitForNextState();
        }

        // ✅ Final submission (SAFE)
        attemptFinalSubmit();
    }

    private void clickSaveProgress() {

        // ✅ If submit button is visible, do NOT try to click Save
        if (!getDriver().findElements(testSubmit).isEmpty()
                && getDriver().findElement(testSubmit).isDisplayed()) {
            System.out.println("Submit visible, skipping Save button");
            return;
        }

        List<WebElement> buttons = getDriver().findElements(saveProgressBtn);

        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) {
                ((JavascriptExecutor) getDriver())
                        .executeScript("arguments[0].click();", btn);
                return;
            }
        }

        throw new RuntimeException("Save button not found to click");
    }

    private void waitForOptionsToRefresh() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(driver -> {List<WebElement> options = driver.findElements(questionOptions);

            for (WebElement opt : options) {
                if (opt.isDisplayed() && opt.isEnabled()) {
                    return true;
                }
            }
            return false;
        });
    }

    private void waitForNextState() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(driver -> {
            // Next question loaded
            for (WebElement opt : driver.findElements(questionOptions)) {
                if (opt.isDisplayed()) {
                    return true;
                }
            }
            // OR final submit page loaded
            if (!driver.findElements(testSubmit).isEmpty()
                    && driver.findElement(testSubmit).isDisplayed()) {
                return true;
            }
            return false;
        });
    }

    private void selectRandomOption() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        // 1️⃣ If submit button is visible, do NOT try to answer
        if (!getDriver().findElements(testSubmit).isEmpty()
                && getDriver().findElement(testSubmit).isDisplayed()) {
            System.out.println("Submit button visible, no more questions to answer");
            return;
        }

        // 2️⃣ Try to fetch visible options (WITHOUT infinite wait)
        List<WebElement> allOptions = getDriver().findElements(questionOptions);
        List<WebElement> visibleOptions = new ArrayList<>();

        for (WebElement option : allOptions) {
            if (option.isDisplayed() && option.isEnabled()) {
                visibleOptions.add(option);
            }
        }

        // 3️⃣ If no options & submit not visible → wait briefly and retry once
        if (visibleOptions.isEmpty()) {
            wait.until(driver ->
                    !driver.findElements(questionOptions).isEmpty()
                            || (!driver.findElements(testSubmit).isEmpty()
                            && driver.findElement(testSubmit).isDisplayed())
            );
            return;
        }

        // 4️⃣ Select random option
        WebElement randomOption =
                visibleOptions.get(new Random().nextInt(visibleOptions.size()));

        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].click();", randomOption);
    }



    private void clickSaveAndNext() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

        WebElement visibleButton = wait.until(driver -> {
            List<WebElement> buttons = driver.findElements(saveAndNextBtn);
            for (WebElement btn : buttons) {
                if (btn.isDisplayed()) {
                    return btn;
                }
            }
            return null;
        });
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].click();", visibleButton);
    }


    public String isTestSubmittedSuccessfully() {
        return visibilityOfElement(confirmMessage).getText();
    }

    public void closeConfirmationPopup() {
        if (isElementPresent(closeButton)) {
            clickElement(closeButton);
        }
    }

    public void answerQuestions(int numberOfQuestions) {
        for (int i = 1; i <= numberOfQuestions; i++) {
            selectRandomOption();
            clickSaveProgress();
            waitForNextState();
        }
    }

    public void moveToNextQuestionOnly(int steps) {
        for (int i = 1; i <= steps; i++) {
            clickSaveProgress();
            waitForNextState();
        }
    }

    public void attemptFinalSubmit() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(testSubmit));
        submitBtn.click();

        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(finalSubmitBtn));
        confirmBtn.click();
    }


}
