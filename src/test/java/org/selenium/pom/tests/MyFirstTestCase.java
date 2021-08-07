package org.selenium.pom.tests;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.pages.orderRecived;
import org.selenium.pom.utils.JacksonsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

        @Test
        public void guestCheckoutUsingDirectBankTransfer() throws IOException {
            String searchFor="Blue";
            //String searchFor="Dark";
            BillingAddress billingAddress = JacksonsUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
            Product product = new Product(1215);
            //Product product = new Product(1211);
            StorePage storePage = new HomePage(getDriver()).load().getMyHeader().navigateToStoreUsingMenu().search(searchFor);
            Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");
            storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
            CartPage cartPage = storePage.getProductThumbnail().clickOnViewCartBtn();
            Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
            CheckoutPage checkoutPage = cartPage.checkout()
            .setBillingAddress(billingAddress).selectDirectBankTransferBtn();
            orderRecived orderrecived =  checkoutPage.clickOnOrderBtn();
            Assert.assertEquals(orderrecived.verifyOrder(),"Thank you. Your order has been received.");

        }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        //String searchFor="Blue";
        String searchFor="Dark";
        BillingAddress billingAddress = JacksonsUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        UserLogin userlogin = new UserLogin(1);
        //Product product = new Product(1215);
        Product product = new Product(1211);
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.getMyHeader().navigateToStoreUsingMenu();
        storePage.isLoaded();
        String titleAfterSearch = storePage.search(searchFor).getTitle();
        Assert.assertEquals(titleAfterSearch,"Search results: “"+searchFor+"”");
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickOnViewCartBtn();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
        CheckoutPage checkoutPage = cartPage.checkout().doLogin(userlogin.getUsername(),userlogin.getPassword())
                .setBillingAddress(billingAddress).selectDirectBankTransferBtn();
        orderRecived orderrecived =  checkoutPage.clickOnOrderBtn();
        Assert.assertEquals(orderrecived.verifyOrder(),"Thank you. Your order has been received.");

    }

    @Test(dataProvider = "getAddress",dataProviderClass = MyDataProvider.class)
    public void loginAndCheckoutUsingDirectBankTransferWithDifferentAddress(BillingAddress billingAddress) throws IOException {
        //String searchFor="Blue";
        String searchFor="Dark";
        //BillingAddress billingAddress = JacksonsUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        UserLogin userlogin = new UserLogin(1);
        //Product product = new Product(1215);
        Product product = new Product(1211);
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.getMyHeader().navigateToStoreUsingMenu();
        storePage.isLoaded();
        String titleAfterSearch = storePage.search(searchFor).getTitle();
        Assert.assertEquals(titleAfterSearch,"Search results: “"+searchFor+"”");
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickOnViewCartBtn();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductNameFromCart(),product.getName());
        CheckoutPage checkoutPage = cartPage.checkout().doLogin(userlogin.getUsername(),userlogin.getPassword())
                .setBillingAddress(billingAddress).selectDirectBankTransferBtn();
        orderRecived orderrecived =  checkoutPage.clickOnOrderBtn();
        Assert.assertEquals(orderrecived.verifyOrder(),"Thank you. Your order has been received.");

    }
}
