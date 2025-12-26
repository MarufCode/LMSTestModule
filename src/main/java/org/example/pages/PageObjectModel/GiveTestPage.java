package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;

public class GiveTestPage extends CommonToAllPage {


    // Locators

    By Dashboard_Text = By.xpath("//li[contains(text(),'Test/quiz')]");









    // Page Actions

    public String getTestQuizDashboardText(){
        return visibilityOfElement(Dashboard_Text).getText().trim();
    }






}
