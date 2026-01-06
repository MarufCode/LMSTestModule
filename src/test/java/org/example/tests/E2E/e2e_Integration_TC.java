package org.example.tests.E2E;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.DashboardPage;
import org.example.pages.PageObjectModel.GiveTestPage;
import org.example.pages.PageObjectModel.LogInPage;
import org.example.pages.PageObjectModel.TestQuizPage;
import org.example.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class e2e_Integration_TC extends CommonToAllTest {


    @Test(groups = {"pre-prod", "prod"}, description  = "Verify end-to-end integration scenario: User logs in, navigates to dashboard, starts Test/quiz, answers all questions " +
            "and successfully submits the test")
    @Owner("Maruf")
    public void testFullE2EIntegrationScenario() {

        LogInPage logInPage = new LogInPage();
        logInPage.openLogInURL();
        logInPage.LogInWithValidCred();

        DashboardPage dashboardPage = logInPage.afterLoginWithValidCred();
        String popupText = dashboardPage.getDashboardPopupText();
        Assert.assertEquals(popupText, PropertyReader.readKey("expected_dash_text"));

        dashboardPage.closeDashboardPopup();
        dashboardPage.waitForSeconds(1);

        dashboardPage.scrollDownToClickTest();
        dashboardPage.clickToTestQuizTab();

        GiveTestPage giveTestPage = new GiveTestPage();
        giveTestPage.clickLastTableItem();
        giveTestPage.waitForSeconds(2);
        String actualText = giveTestPage.getTestQuizDashboardText();
        Assert.assertEquals(actualText, PropertyReader.readKey("expected_test_quiz_text"));
        giveTestPage.clickOkButtonToStartTest();
        giveTestPage.waitForSeconds(2);

        TestQuizPage quizPage = new TestQuizPage();
        quizPage.answerAllQuestionsAndFinish(10);
        quizPage.waitForSeconds(2);
        String confirmationText = quizPage.isTestSubmittedSuccessfully();
        Assert.assertEquals(confirmationText, PropertyReader.readKey("Confirm_Message"));
        quizPage.waitForSeconds(3);
        quizPage.closeConfirmationPopup();








    }




}
