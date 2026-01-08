package org.example.tests.TestQuiz;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTimerVisibilityAfterSubmission extends CommonToAllTest {

    @Test(groups = {"regression"}, description = "Verify test timer is NOT visible after test submission")
    @Owner("Maruf")
    public void verifyTimerIsNotVisibleAfterSubmission() {

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

        // ---------- Validation on Confirmation Page ----------
        TestConfirmationPage confirmationPage = new TestConfirmationPage();
        boolean isTimerVisible = confirmationPage.isTestTimerVisible();
        System.out.println("Is timer visible after submission: " + isTimerVisible);

        // BUG VALIDATION
        Assert.assertFalse(isTimerVisible, "BUG: Test timer is still visible after submission");
        quizPage.closeConfirmationPopup();
    }
}
