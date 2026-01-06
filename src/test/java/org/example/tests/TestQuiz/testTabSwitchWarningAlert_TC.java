package org.example.tests.TestQuiz;

import io.qameta.allure.Owner;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.*;
import org.example.utils.AttemptStoreUtil;
import org.example.utils.PropertyReader;
import org.example.utils.TabSwitchUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testTabSwitchWarningAlert_TC extends CommonToAllTest {

    @Test(groups = {"regression"}, description = "Verify warning alert appears on each tab switch and auto-submit after 3 attempts")
    @Owner("Maruf")
    public void verifyTabSwitchWarningAlert() {

        // ---------- Login ----------
        LogInPage logInPage = new LogInPage();
        logInPage.openLogInURL();
        logInPage.LogInWithValidCred();

        // ---------- Start Test ----------
        DashboardTestQuizNavigator navigator = new DashboardTestQuizNavigator();
        navigator.navigateToTestQuizSafely();

        TestQuizPage quizPage = new TestQuizPage();

        GiveTestPage giveTestPage = new GiveTestPage();
        giveTestPage.clickLastTableItem();
        giveTestPage.clickOkButtonToStartTest();

        TestTabSwitchAlertPage alertPage = new TestTabSwitchAlertPage();

        // ---------- Tab switch attempts ----------
        for (int attempt = 1; attempt <= 3; attempt++) {
            TabSwitchUtil.switchTabAndComeBack();
            Assert.assertTrue(alertPage.isWarningAlertPresent(), "Alert not displayed on tab switch " + attempt);

            String warningText = alertPage.getTabSwitchWarningText();
            System.out.println("Warning " + attempt + ": " + warningText);

            if (attempt < 3) {
                Assert.assertTrue(warningText.contains("Warning"), "Warning not shown on attempt " + attempt);
            } else {
                Assert.assertTrue(warningText.toLowerCase().contains("auto-submitted"), "Auto-submit message not shown on final attempt");
            }
            alertPage.acceptTabSwitchAlert();
            alertPage.waitForSeconds(3);
        }

        String confirmationText = quizPage.isTestSubmittedSuccessfully();
        Assert.assertEquals(confirmationText, PropertyReader.readKey("Confirm_Message"));
        quizPage.closeConfirmationPopup();

    }
}
