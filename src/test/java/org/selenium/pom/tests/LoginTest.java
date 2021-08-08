package org.selenium.pom.tests;

import org.checkerframework.checker.units.qual.C;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.ShippingAddress;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckOut() throws IOException, InterruptedException {
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
        Thread.sleep(5000);
        injectCookiesToBrowser(cartapi.getCookies());
        checkoutpage.load();
        Thread.sleep(5000);
        checkoutpage.doLogin(user.getUsername(),user.getPassword());
        Thread.sleep(5000);
        Assert.assertTrue(checkoutpage.getProductName().contains(product.getName()));
        }

        @Test(dataProvider = "getShippingAddressData",dataProviderClass = MyDataProvider.class)
        public void addToCartAddressChangeCheck(ShippingAddress shippingAddress) throws IOException, InterruptedException {
            CartApi cartApi = new CartApi();
            Product product = new Product(1205);
            cartApi.addToCart(product.getId(), 2);
            CartPage cartPage = new CartPage(getDriver()).load();
            injectCookiesToBrowser(cartApi.getCookies());
            cartPage.load().isLoaded();
            cartPage.selectChangeShippingAddress().setShippingAddress(shippingAddress).updateShippingAddress();
            Assert.assertTrue(cartPage.getUpdatedShippingAddress().contains(shippingAddress.getPostCode()));
            System.out.println("ENTERED POSTAL CODE : "+shippingAddress.getPostCode());
            System.out.println("UPDATED SHIPPING ADDRESS : "+cartPage.getUpdatedShippingAddress());
        }
    }

