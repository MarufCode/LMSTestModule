package org.example.basetest;

import org.example.driver.DriverManagerTL;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonToAllTest {


    @BeforeMethod(alwaysRun = true)
    public void setup(){
        DriverManagerTL.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverManagerTL.down();
    }

}
