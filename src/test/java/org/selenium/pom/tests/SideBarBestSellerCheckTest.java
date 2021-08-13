package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.MensPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SideBarBestSellerCheckTest extends BaseTest {

    @Test
    public void bestSellerSideBarCheckOnStorePage() throws InterruptedException {
        StorePage storePage = new StorePage(getDriver()).load();
        Assert.assertEquals(storePage.getSideBarThumbnail().getBestSellerTitle(),"Our Best Sellers");
        List<String> product= storePage.getSideBarThumbnail().getAllBestSellerProductTitle();
        int count=0;
        for(String str:product){
            System.out.println("PRODUCT TITLE : "+str);
            count++;
        }
        System.out.println("TOTAL BEST SELLER PRODUCTS : "+count);

    }
    @Test
    public void bestSellerSideBarCheckOnMensPage(){
        MensPage mensPage = new MensPage(getDriver()).load();
        Assert.assertEquals(mensPage.getSideBarThumbnail().getBestSellerTitle(),"Our Best Sellers");
        List<String> product= mensPage.getSideBarThumbnail().getAllBestSellerProductTitle();
        int count=0;
        for(String str:product){
            System.out.println("PRODUCT TITLE : "+str);
            count++;
        }
        System.out.println("TOTAL BEST SELLER PRODUCTS : "+count);

    }
}
