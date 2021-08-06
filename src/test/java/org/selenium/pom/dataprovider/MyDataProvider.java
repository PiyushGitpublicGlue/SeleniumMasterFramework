package org.selenium.pom.dataprovider;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonsUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getfeaturedproducts", parallel = false)
    public Object[] getFeatureProduct() throws IOException {

        return JacksonsUtils.deserializeJson("products.json", Product[].class);
    }

    @DataProvider(name = "getStoreproducts", parallel = false)
    public Object[] getSToreProduct() throws IOException {

        return JacksonsUtils.deserializeJson("productsOnStorePage.json", Product[].class);
    }

    @DataProvider(name = "getAddress", parallel = false)
    public Object[] getBillingAddress() throws IOException {

        return JacksonsUtils.deserializeJson("allBillingAddress.json", BillingAddress[].class);
    }

}
