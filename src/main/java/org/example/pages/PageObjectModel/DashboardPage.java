package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;

public class DashboardPage extends CommonToAllPage {


    // Page Locators
    By Dashboard_Popup = By.xpath("//h5[contains(text(), 'Hostel Admission')]");
    By Modal_Dismiss = By.xpath("//a[@data-dismiss=\"modal\"]");



    // Page Actions

    public String LoggedInPopUp(){
        presenceOfElement(Dashboard_Popup);
        visibilityOfElement(Dashboard_Popup);
        jsClick(Modal_Dismiss);
        return getElement(Dashboard_Popup).getText().trim();
    }










}
