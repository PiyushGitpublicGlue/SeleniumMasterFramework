package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.testng.annotations.Test;

public class FooterTest extends BaseTest {


    @Test
    public void footerLinksCheck(){
        HomePage homePage=new HomePage(getDriver()).load();
        homePage.getMyFooter().footerLinksCheck();
    }

    @Test
    public void footerQuickLinks(){
        HomePage homePage=new HomePage(getDriver()).load();
        homePage.getMyFooter().footerQuickLinks();
    }

    @Test
    public void footerHerLinks(){
        HomePage homePage=new HomePage(getDriver()).load();
        homePage.getMyFooter().footerHerLinks();
    }

    @Test
    public void footerHimLinks(){
        HomePage homePage=new HomePage(getDriver()).load();
        homePage.getMyFooter().footerHimLinks();
    }

    @Test
    public void footerAppsLinks(){
        HomePage homePage=new HomePage(getDriver()).load();
        homePage.getMyFooter().footerAppsLinks();
    }
}
