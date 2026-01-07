package org.example.tests.TestQuiz;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.*;
import org.example.utils.AttemptStoreUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testAttemptCountValidation_TC extends CommonToAllTest {

    @Test(groups = {"regression"}, description = "Verify test attempt count is NOT decremented after submission (Defect validation)")
    @Owner("Maruf")
    public void verifyAttemptCountNotDecrementedAfterSubmission() {


        LogInPage logInPage = new LogInPage();
        logInPage.openLogInURL();
        logInPage.LogInWithValidCred();

        DashboardTestQuizNavigator navigator = new DashboardTestQuizNavigator();
        navigator.navigateToTestQuizSafely();

        GiveTestPage giveTestPage = new GiveTestPage();
        giveTestPage.clickLastTableItem();
        giveTestPage.waitForSeconds(2);
        giveTestPage.clickOkButtonToStartTest();

        TestQuizPage quizPage = new TestQuizPage();
        quizPage.answerAllQuestionsAndFinish(10);
        quizPage.waitForSeconds(2);

        // ---------- Read remaining attempts ----------
        TestAttemptPage attemptPage = new TestAttemptPage();
        int currentAttempts = attemptPage.getRemainingAttempts();

        System.out.println("Remaining attempts after submission: " + currentAttempts);

        Integer previousAttempts = AttemptStoreUtil.getPreviousAttempt();

        if (previousAttempts != null) {
            Assert.assertEquals(currentAttempts, previousAttempts - 1, "BUG: Attempt count did not decrement correctly");
        } else {
            System.out.println("First run detected, skipping decrement assertion");
        }

        // ---------- Save for next run ----------
        AttemptStoreUtil.saveCurrentAttempt(currentAttempts);
        quizPage.closeConfirmationPopup();
    }
}
