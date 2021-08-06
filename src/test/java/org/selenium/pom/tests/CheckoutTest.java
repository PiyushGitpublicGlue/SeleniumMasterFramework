package org.selenium.pom.tests;

import org.checkerframework.checker.units.qual.C;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.orderRecived;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonsUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferBtn();
        orderRecived orderrecived =  checkoutPage.clickOnOrderBtn();
        Assert.assertEquals(orderrecived.verifyOrder(),"Thank you. Your order has been received.");
    }

    @Test
    public void LoginAndCheckOutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonsUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        SignUpApi signup = new SignUpApi();
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        UserLogin user = new UserLogin()
                .setUsername(username)
                .setPassword("demopwd")
                .setEmail(username+"@test.com");
        signup.register(user);

        CartApi cartapi = new CartApi(signup.getCookies());
        Product product = new Product(1211);
        cartapi.addToCart(product.getId(),1);

        CheckoutPage checkoutpage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(signup.getCookies());
        checkoutpage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferBtn();
        orderRecived orderrecived =  checkoutpage.clickOnOrderBtn();
        Assert.assertEquals(orderrecived.verifyOrder(),"Thank you. Your order has been received.");
    }
}
