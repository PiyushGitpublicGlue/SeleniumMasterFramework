package org.selenium.pom.tests;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.components.ProductSearch;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch(){
        String searchFor="Blue";
        StorePage storepage = new StorePage(getDriver()).load()
                .search(searchFor);
        storepage.isSearchLoaded();
        Assert.assertEquals(storepage.getTitle(),"Search results: “"+searchFor+"”");

    }

    @Test
    public void searchProductCount(){
        String searchFor="Blue";
        StorePage storepage = new StorePage(getDriver()).load()
                .search(searchFor);
        storepage.isSearchLoaded();
        Assert.assertEquals(storepage.getTitle(),"Search results: “"+searchFor+"”");
        ProductSearch productSearch = new ProductSearch(getDriver());
        Assert.assertEquals(productSearch.searchedProductCount(),6);
    }

    @Test
    public void searchProductListWithBlue(){
        String searchFor="Blue";
        StorePage storepage = new StorePage(getDriver()).load()
                .search(searchFor);
        storepage.isSearchLoaded();
        Assert.assertEquals(storepage.getTitle(),"Search results: “"+searchFor+"”");
        ProductSearch productSearch = new ProductSearch(getDriver());
        Assert.assertEquals(productSearch.searchedProductCount(),6);
        productSearch.searchedProductList();
    }

    @Test
    public void searchProductListWithDark(){
        String searchFor="Dark";
        StorePage storepage = new StorePage(getDriver()).load()
                .search(searchFor);
        storepage.isSearchLoaded();
        Assert.assertEquals(storepage.getTitle(),"Search results: “"+searchFor+"”");
        ProductSearch productSearch = new ProductSearch(getDriver());
        Assert.assertEquals(productSearch.searchedProductCount(),2);
        productSearch.searchedProductList();
    }

    @Test
    public void searchAllProductsOnStorePage(){
        StorePage storepage = new StorePage(getDriver()).load();
        Assert.assertEquals(storepage.getTitle(),"Store");
        storepage.searchAllProductsOnStorePage();

    }
}
