package org.example.tests.TestQuiz;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.*;
import org.example.utils.PropertyReader;
import org.example.utils.TabSwitchUtil;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.testng.AllureTestNg;

public class TestIncorrectAutoSubmitMessage extends CommonToAllTest {


    @Test(groups = {"regression"},description = "Verify correct auto-submission message is shown after exceeding tab switch limit")
    @Owner("Maruf")
    public void verifyCorrectAutoSubmitMessage(){
        LogInPage logInPage = new LogInPage();
        logInPage.openLogInURL();
        logInPage.LogInWithValidCred();

        DashboardTestQuizNavigator dashboardTestQuizNavigator = new DashboardTestQuizNavigator();
        dashboardTestQuizNavigator.navigateToTestQuizSafely();

        GiveTestPage giveTestPage = new GiveTestPage();
        giveTestPage.clickLastTableItem();
        giveTestPage.clickOkButtonToStartTest();

        TestTabSwitchAlertPage testTabSwitchAlertPage = new TestTabSwitchAlertPage();

        for (int attempt = 1; attempt<=3; attempt++){
            TabSwitchUtil.switchTabAndComeBack();
            Assert.assertTrue(testTabSwitchAlertPage.isWarningAlertPresent(), "Alert not displayed on tab switch page" + attempt);

            String alertText = testTabSwitchAlertPage.getTabSwitchWarningText();
            System.out.println("Alert text attempt " + attempt + ": " + alertText);
            testTabSwitchAlertPage.acceptTabSwitchAlert();

        }
        TestQuizPage quizPage = new TestQuizPage();
        String confirmText = quizPage.isTestSubmittedSuccessfully();
        System.out.println("Confirmation message is displayed" + confirmText);

        Assert.assertEquals(confirmText, PropertyReader.readKey("Auto_Submit_Message"));

    }



}
