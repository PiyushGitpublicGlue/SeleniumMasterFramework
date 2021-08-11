package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToMiniCartTest extends BaseTest {

    @Test(dataProvider="getFeaturedProducts",dataProviderClass = MyDataProvider.class)
    public void miniCartCheckFromHomePageByAddingFeaturedProducts(Product product) throws IOException, InterruptedException {

        HomePage homePage = new HomePage(getDriver()).load();
        homePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        homePage.getMyHeader().hoverOverMiniCart();
        Assert.assertEquals(product.getName(),homePage.getMyHeader().getProductTitleOnMiniCart(product.getName()));
        System.out.println("PRODUCT NAME ADDED TO CART : "+product.getName());
        System.out.println("PRODUCT TITLE FROM MINI CART : "+homePage.getMyHeader().getProductTitleOnMiniCart(product.getName()));

    }

    @Test
    public void miniCartCheckFromHomePageByAddingSingleProductOnHomePage() throws IOException, InterruptedException {

        HomePage homePage = new HomePage(getDriver()).load();
        Product product = new Product(1215);
        double productPrice=homePage.getProductThumbnail().clickAddToCartBtn(product.getName()).productPriceInInt(product.getName());
        System.out.println("PRODUCT PRICE : "+productPrice);
        homePage.getMyHeader().hoverOverMiniCart();
        Assert.assertEquals(product.getName(),homePage.getMyHeader().getProductTitleOnMiniCart(product.getName()));
        System.out.println("PRODUCT NAME ADDED TO CART : "+product.getName());
        System.out.println("PRODUCT TITLE FROM MINI CART : "+homePage.getMyHeader().getProductTitleOnMiniCart(product.getName()));

    }

}
