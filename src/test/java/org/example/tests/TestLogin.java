package org.example.tests;

import io.qameta.allure.Owner;
import org.assertj.core.api.Assertions;
import org.example.basetest.CommonToAllTest;
import org.example.pages.PageObjectModel.DashboardPage;
import org.example.pages.PageObjectModel.LogInPage;
import org.example.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends CommonToAllTest {


    @Test
    @Owner("Maruf")
    public void testLogInPositive(){
        LogInPage logInPage = new LogInPage();
        logInPage.openLogInURL();
        logInPage.LogInWithValidCred();
        DashboardPage dashboardPage = logInPage.afterLoginWithValidCred();
        dashboardPage.LoggedInPopUp();

        String expected_dash_text = dashboardPage.LoggedInPopUp();
        Assert.assertEquals(expected_dash_text, PropertyReader.readKey("expected_dash_text"));
        Assertions.assertThat(expected_dash_text)
                .isNotNull()
                .isNotBlank();



    }



}
