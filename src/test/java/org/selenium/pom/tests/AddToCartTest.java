package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonsUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1211);
        CartPage cartpage = new StorePage(getDriver()).load()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
                cartpage.isLoaded();
        Assert.assertEquals(cartpage.getProductNameFromCart(),product.getName());
    }
    @Test(dataProvider = "getfeaturedproducts",dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProduct(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }

    @Test(dataProvider = "getStoreproducts",dataProviderClass = MyDataProvider.class)
    public void addToCartProductFromStorePage(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getMyHeader()
                .nativateToStoreUsingMenu()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }


}
