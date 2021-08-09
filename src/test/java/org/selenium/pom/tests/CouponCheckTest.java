package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Coupons;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.ShippingAddress;
import org.selenium.pom.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CouponCheckTest extends BaseTest {


        @Test(dataProvider = "getCouponsData",dataProviderClass = MyDataProvider.class)
        public void couponsCheckOnAddToCartPage(Coupons coupons) throws IOException, InterruptedException {
            CartApi cartApi = new CartApi();
            Product product = new Product(1205);
            cartApi.addToCart(product.getId(), 2);
            CartPage cartPage = new CartPage(getDriver()).load();
            injectCookiesToBrowser(cartApi.getCookies());
            cartPage.load().isLoaded();
            System.out.println("TOTAL OF PRODUCT ON CART PAGE : " + cartPage.getTotal());
            cartPage.setCoupons(coupons).getCouponCodeApplyBtn();
            System.out.println("VERIFY COUPON APPLY SUCCESS MESSAGE : " +cartPage.getSuccessMsgAfterCouponApply());
            System.out.println("COUPON APPLY  : " +coupons.getCoupon());
            System.out.println("TOTAL DISCOUNTED AMOUNT AFTER COUPON APPLY : " +cartPage.getCouponAmount(coupons.getCoupon()));
            System.out.println("TOTAL OF PRODUCT ON CART PAGE AFTER COUPON APPLY : " + cartPage.getTotal());

        }
    }

