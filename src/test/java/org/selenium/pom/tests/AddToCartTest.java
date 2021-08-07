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
    @Test(dataProvider = "getFeaturedProducts",dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProduct(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }

    @Test(dataProvider = "getStoreProducts",dataProviderClass = MyDataProvider.class)
    public void addToCartProductFromStorePage(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getMyHeader()
                .navigateToStoreUsingMenu()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }
    @Test(dataProvider = "getMensProducts",dataProviderClass = MyDataProvider.class)
    public void addToCartProductFromMensPage(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getMyHeader()
                .navigateToMenSectionUsingMenu()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }
    @Test(dataProvider = "getWomenProducts",dataProviderClass = MyDataProvider.class)
    public void addToCartProductFromWomenPage(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getMyHeader()
                .navigateToWomenSectionUsingMenu()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }
    @Test(dataProvider = "getAccessoriesProducts",dataProviderClass = MyDataProvider.class)
    public void addToCartProductFromAccessoriesPage(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getMyHeader()
                .navigateToAccessoriesSectionUsingMenu()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickOnViewCartBtn();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
    }


}
