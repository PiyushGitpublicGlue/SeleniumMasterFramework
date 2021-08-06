package org.selenium.pom.tests;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.base.BaseTest;
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
}
