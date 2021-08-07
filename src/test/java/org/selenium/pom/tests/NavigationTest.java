package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).load().getMyHeader().navigateToStoreUsingMenu();
        storePage.isLoaded();
        Assert.assertEquals(storePage.getTitle(),"Store");
    }
    @Test
    public void NavigateFromHomeToMenSectionUsingMainMenu(){
        MensPage mensPage = new HomePage(getDriver()).load().getMyHeader().navigateToMenSectionUsingMenu();
        mensPage.isLoaded();
        Assert.assertEquals(mensPage.getTitle(),"Men");
    }
    @Test
    public void NavigateFromHomeToWomenSectionUsingMainMenu(){
        WomenPage womenPage = new HomePage(getDriver()).load().getMyHeader().navigateToWomenSectionUsingMenu();
        womenPage.isLoaded();
        Assert.assertEquals(womenPage.getTitle(),"Women");
    }
    @Test
    public void NavigateFromHomeToAccessoriesSectionUsingMainMenu(){
        AccessoriesPage accessoriesPage = new HomePage(getDriver()).load().getMyHeader().navigateToAccessoriesSectionUsingMenu();
        accessoriesPage.isLoaded();
        Assert.assertEquals(accessoriesPage.getTitle(),"Accessories");
    }
    @Test
    public void NavigateFromHomeToAccountSectionUsingMainMenu(){
        AccountPage accountPage = new HomePage(getDriver()).load().getMyHeader().navigateToAccountSectionUsingMenu();
        accountPage.isLoaded();
        Assert.assertEquals(accountPage.getTitle(),"Account");
    }
    @Test
    public void NavigateFromHomeToAboutSectionUsingMainMenu(){
        AboutUsPage aboutUsPage = new HomePage(getDriver()).load().getMyHeader().navigateToAboutSectionUsingMenu();
        aboutUsPage.isLoaded();
        Assert.assertEquals(aboutUsPage.getTitle(),"About Us");
    }
    @Test
    public void NavigateFromHomeToContactUsSectionUsingMainMenu(){
        ContactUsPage contactUsPage = new HomePage(getDriver()).load().getMyHeader().navigateToContactUsSectionUsingMenu();
        contactUsPage.isLoaded();
        Assert.assertEquals(contactUsPage.getTitle(),"Contact Us");
    }
}
