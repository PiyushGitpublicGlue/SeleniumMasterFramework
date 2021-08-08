package org.selenium.pom.tests;

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

public class AddToCartShippingAddressTest extends BaseTest {


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

