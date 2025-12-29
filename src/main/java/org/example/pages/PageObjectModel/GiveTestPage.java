package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;

public class GiveTestPage extends CommonToAllPage {


    // Locators

    By Dashboard_Text = By.xpath("//li[contains(text(),'Test/quiz')]");

    By TestTable = By.xpath("//table[@id=\"viewTestTable\"]");

    By GiveTest = By.xpath("//*[@id=\"viewTestTable\"]/tbody/tr[20]/td[8]/input");

    By Confirm_Button = By.xpath("//button[contains(text(), 'Yes')]");









    // Page Actions

    public String getTestQuizDashboardText(){
        return visibilityOfElement(Dashboard_Text).getText().trim();
    }

    public void clickLastTableItem(){
        scrollTableAndClickLastRow(TestTable, GiveTest);
    }

    public void clickOkButtonToStartTest(){
        clickElement(Confirm_Button);
    }






}
