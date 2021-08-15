package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.annotations.Test;

import java.util.List;

public class SideBarPriceFilterCheckTest extends BaseTest {

    @Test
    public void priceFilterCheckOnStorePage(){
        int count = 0;
        StorePage storePage = new StorePage(getDriver()).load();
        storePage.isLoaded();
        System.out.println("PAGE TITLE : "+storePage.getTitle());
        System.out.println("PRICE FILTER TITLE : "+storePage.getSideBarThumbnail().getPriceFilterTitle());
        System.out.println("PRODUCT DISPLAYING BEFORE FILTER : "+storePage.getProductSearch().getTotalProductsOnPageTitle());
        System.out.println("RIGHT PRICE BEFORE MOVE FILTER : "+storePage.getSideBarThumbnail().getPriceOnRightSlider());
        System.out.println("LEFT PRICE BEFORE MOVE FILTER : "+storePage.getSideBarThumbnail().getPriceOnLeftSlider());
        storePage.getSideBarThumbnail().handlePriceSlider(4);
        System.out.println("PRODUCT DISPLAYING AFTER FILTER : "+storePage.getProductSearch().getTotalProductsOnPageTitle());
        System.out.println("LEFT PRICE AFTER MOVE FILTER : "+storePage.getSideBarThumbnail().getPriceOnLeftSlider());
        List<Double> priceListOfProductOnPage=storePage.getProductSearch().getProductPriceInDouble();
        double priceOnLeftSlider = storePage.getSideBarThumbnail().getPriceOnLeftSlider();
        double priceOnRightSlider = storePage.getSideBarThumbnail().getPriceOnRightSlider();
        for(double e:priceListOfProductOnPage){
            if(e>=priceOnLeftSlider&&e<=priceOnRightSlider){
                System.out.println("CHECKED PRODUCT PRICE IS IN RANGE : "+e+"  LEFT PRICE : "+priceOnLeftSlider+ "  RIGHT PRICE : "+priceOnRightSlider );
            }
            else{
                System.out.println("====>CHECKED PRODUCT PRICE IS NOT IN RANGE : "+e+"  LEFT PRICE : "+priceOnLeftSlider+ "  RIGHT PRICE : "+priceOnRightSlider );
            }
            count++;
        }

        System.out.println("TOTAL PRODUCTS CHECKED ON PAGE : "+count);


    }
}
