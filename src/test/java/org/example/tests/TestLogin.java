package org.example.tests;

import io.qameta.allure.Owner;
import org.assertj.core.api.Assertions;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.DashboardPage;
import org.example.pages.PageObjectModel.GiveTestPage;
import org.example.pages.PageObjectModel.LogInPage;
import org.example.utils.PropertyReader;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.driver.DriverManagerTL.getDriver;

public class TestLogin extends CommonToAllTest {


    @Test
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
        dashboardPage.waitForSeconds(5);

        GiveTestPage giveTestPage = new GiveTestPage();
        String actualText = giveTestPage.getTestQuizDashboardText();
        Assert.assertEquals(actualText, PropertyReader.readKey("expected_test_quiz_text"));






    }




}
