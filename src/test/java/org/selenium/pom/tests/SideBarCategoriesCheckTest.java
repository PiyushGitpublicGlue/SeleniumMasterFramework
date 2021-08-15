package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Categories;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SideBarCategoriesCheckTest extends BaseTest {

    @Test
    public void sideBarCategoriesCheckFromStorePage(){
        StorePage storePage = new StorePage(getDriver()).load();
        System.out.println("TITLE OF CATEGORY : "+storePage.getSideBarThumbnail().getCategoriesTitle());
        storePage.getSideBarThumbnail().selectFromCategoryDropDwn("mens-jeans");
        //Assert.assertEquals(storePage.getTitle(),"mens-jeans");
        System.out.println("TITLE OF PAGE AFTER CATEGORY SELECTION : "+storePage.getTitle());
        storePage.getProductSearch().productOnPage();

    }

    @Test(dataProvider = "getCategories",dataProviderClass = MyDataProvider.class)
    public void sideBarCategoriesCheckFromWomenPage(Categories categories){
        WomenPage womenPage = new WomenPage(getDriver()).load();
        womenPage.isLoaded();
        System.out.println("WOMEN'S PAGE TITLE : "+womenPage.getTitle());
        System.out.println("TITLE OF CATEGORY : "+womenPage.getSideBarThumbnail().getCategoriesTitle());
        womenPage.getSideBarThumbnail().setCategories(categories);
        //Assert.assertEquals(storePage.getTitle(),"mens-jeans");
        System.out.println("TITLE OF PAGE AFTER CATEGORY SELECTION : "+womenPage.getTitle());
        womenPage.getProductSearch().productOnPage();

    }
}
