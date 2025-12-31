package org.example.tests.E2E;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testAttemptCountValidation_TC extends CommonToAllTest {

    @Test(description = "Verify test attempt count is NOT decremented after submission (Defect validation)")
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

        TestAttemptPage attemptPage = new TestAttemptPage();
        int remainingAttempts = attemptPage.getRemainingAttempts();

        System.out.println("Remaining attempts after submission: " + remainingAttempts);

        Assert.assertTrue(remainingAttempts > 0, "Attempt count SHOULD have been decremented, but user can still reattempt");

        quizPage.closeConfirmationPopup();
    }
}
