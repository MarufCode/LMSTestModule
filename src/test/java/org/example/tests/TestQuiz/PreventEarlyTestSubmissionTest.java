package org.example.tests.TestQuiz;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.*;
import org.example.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(org.example.listeners.TestListener.class)
public class PreventEarlyTestSubmissionTest extends CommonToAllTest {

    @Test(description = "Verify user cannot submit test when middle questions are unanswered")
    @Owner("Maruf")
    public void verifySubmissionBlockedIfMiddleQuestionsUnanswered() {

        LogInPage logInPage = new LogInPage();
        logInPage.openLogInURL();
        logInPage.LogInWithValidCred();

        DashboardPage dashboardPage = logInPage.afterLoginWithValidCred();
        Assert.assertEquals(
                dashboardPage.getDashboardPopupText(),
                PropertyReader.readKey("expected_dash_text"));

        dashboardPage.closeDashboardPopup();
        dashboardPage.waitForSeconds(1);

        dashboardPage.scrollDownToClickTest();
        dashboardPage.clickToTestQuizTab();

        GiveTestPage giveTestPage = new GiveTestPage();
        giveTestPage.clickLastTableItem();
        giveTestPage.waitForSeconds(2);

        Assert.assertEquals(giveTestPage.getTestQuizDashboardText(), PropertyReader.readKey("expected_test_quiz_text"));

        giveTestPage.clickOkButtonToStartTest();
        giveTestPage.waitForSeconds(2);

        TestQuizPage quizPage = new TestQuizPage();

        quizPage.answerQuestions(5);

        quizPage.moveToNextQuestionOnly(5);

        quizPage.attemptFinalSubmit();
        quizPage.waitForSeconds(2);

        boolean successDisplayed;
        try {
            quizPage.isTestSubmittedSuccessfully();
            successDisplayed = true;
        } catch (Exception e) {
            successDisplayed = false;
        }

        Assert.assertFalse(successDisplayed, "Test should NOT be submitted when some questions are unanswered");

        quizPage.answerAllQuestionsAndFinish(10);

        String confirmationText = quizPage.isTestSubmittedSuccessfully();
        Assert.assertEquals(confirmationText, PropertyReader.readKey("Confirm_Message"));

        quizPage.closeConfirmationPopup();
    }
}
