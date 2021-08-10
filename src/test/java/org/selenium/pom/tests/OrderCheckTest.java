package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.orderRecived;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OrderCheckTest extends BaseTest {

    @Test
    public void loginDuringCheckOutPlaceOrderAndCheckOrderDetails() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonsUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        SignUpApi signup = new SignUpApi();
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        UserLogin user = new UserLogin()
                .setUsername(username)
                .setPassword("demopwd")
                .setEmail(username+"@test.com");
        signup.register(user);

        CartApi cartapi = new CartApi();
        Product product = new Product(1211);
        cartapi.addToCart(product.getId(),1);

        CheckoutPage checkoutpage = new CheckoutPage(getDriver()).load();
        //Thread.sleep(5000);
        injectCookiesToBrowser(cartapi.getCookies());
        checkoutpage.load();
        //Thread.sleep(5000);
        checkoutpage.doLogin(user.getUsername(),user.getPassword());
        //Thread.sleep(5000);
        Assert.assertTrue(checkoutpage.getProductName().contains(product.getName()));
        checkoutpage.setBillingAddress(billingAddress).selectDirectBankTransferBtn();
        orderRecived orderrecived =  checkoutpage.clickOnOrderBtn();
        Assert.assertEquals(orderrecived.verifyOrder(),"Thank you. Your order has been received.");
        String orderNumber = orderrecived.getOrderNumber();
        System.out.println("ORDER NUMBER : "+orderrecived.getOrderNumber());
        orderrecived.getMyHeader().navigateToAccountSectionUsingMenu().getMyOrderSection();
        AccountPage accountPage = new AccountPage(getDriver());
        Assert.assertTrue(accountPage.viewOrderNumber().contains(orderNumber));
        System.out.println("ORDER NUMBER ON ACCOUNTS SECTION : "+accountPage.viewOrderNumber());

        }

    }

