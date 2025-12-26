package org.example.pages.PageObjectModel;

import org.example.base.CommonToAllPage;
import org.example.utils.PropertyReader;
import org.openqa.selenium.By;

public class LogInPage extends CommonToAllPage {

    public LogInPage(){
        super();
    }



    // Page Locator
    By username  = By.id("userName");
    By password = By.id("userPwd");
    By LogIn = By.id("userLogin");
    By Title = By.xpath("//h3[contains(text(), 'Welcome to NMIMS University')]");
    By Modal_Dismiss = By.xpath("//a[@data-dismiss=\"modal\"]");
    By Modal = By.xpath("//h5[contains(text(), 'Hostel Admission')]");



    // Page Actions

    public void LogInWithValidCred(){
        presenceOfElement(Title);
        enterInput(username, PropertyReader.readKey("username"));
        enterInput(password, PropertyReader.readKey("password"));
        jsClick(LogIn);
        presenceOfElement(Modal);
        visibilityOfElement(Modal);
        jsClick(Modal_Dismiss);
    }

    public DashboardPage afterLoginWithValidCred(){
        return new DashboardPage();
    }



}
