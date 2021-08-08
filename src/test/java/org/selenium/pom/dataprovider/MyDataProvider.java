package org.selenium.pom.dataprovider;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.ShippingAddress;
import org.selenium.pom.utils.JacksonsUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = false)
    public Object[] getFeatureProduct() throws IOException {

        return JacksonsUtils.deserializeJson("productsOnHomePageFeatureSection.json", Product[].class);
    }

    @DataProvider(name = "getStoreProducts", parallel = false)
    public Object[] getSToreProduct() throws IOException {

        return JacksonsUtils.deserializeJson("productsOnStorePage.json", Product[].class);
    }

    @DataProvider(name = "getMensProducts", parallel = false)
    public Object[] getMensProducts() throws IOException {

        return JacksonsUtils.deserializeJson("ProductsOnMenPage.json", Product[].class);
    }

    @DataProvider(name = "getWomenProducts", parallel = false)
    public Object[] getWomenProducts() throws IOException {

        return JacksonsUtils.deserializeJson("ProductsOnWomenPage.json", Product[].class);
    }

    @DataProvider(name = "getAccessoriesProducts", parallel = false)
    public Object[] getAccessoriesProducts() throws IOException {

        return JacksonsUtils.deserializeJson("ProductsOnAccessoriesPage.json", Product[].class);
    }

    @DataProvider(name = "getAddress", parallel = false)
    public Object[] getBillingAddress() throws IOException {

        return JacksonsUtils.deserializeJson("allBillingAddress.json", BillingAddress[].class);
    }

    @DataProvider(name = "getShippingAddressData", parallel = false)
    public Object[] getShippingAddress() throws IOException {

        return JacksonsUtils.deserializeJson("shippingAddress.json", ShippingAddress[].class);
    }



}
