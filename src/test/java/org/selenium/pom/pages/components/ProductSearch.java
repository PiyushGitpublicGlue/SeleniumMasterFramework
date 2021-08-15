package org.selenium.pom.pages.components;

import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductSearch extends BasePage {

    private By searchBox = By.id("woocommerce-product-search-field-0");
    private By searchBnt = By.cssSelector("button[value='Search']");
    private By totalProductAfterSearch = By.cssSelector(".woocommerce-loop-product__title");
    private By productOnPageTitle = By.cssSelector(".woocommerce-result-count");
    private By nextPage = By.xpath("//a[contains(text(),'→')]");
    private By getPriceOfDiscountProductOnPage = By.xpath("//*[@class='astra-shop-summary-wrap']//span[@class='price']/ins/span/bdi");
    private By getPriceOfProductOnPage = By.xpath("//*[@class='astra-shop-summary-wrap']//span[@class='price']/span/bdi");



    public ProductSearch(WebDriver driver) {
        super(driver);
    }


    public void search(String Text){
        enterInSearchBox(Text).clickInSearchBtn();
    }

    public ProductSearch enterInSearchBox(String Text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(Text);
        return this;
    }

    public ProductSearch clickInSearchBtn(){

        wait.until(ExpectedConditions.elementToBeClickable(searchBnt)).click();
        return this;
    }

    public List<WebElement> getTotalProductAfterSearch(){
        return driver.findElements(totalProductAfterSearch);
    }

    public int searchedProductCount(){

        return getTotalProductAfterSearch().size();

    }

    public String getTotalProductsOnPageTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productOnPageTitle)).getText();
    }

    public List<WebElement> getPriceOfDiscountProductOnPage(){
        if(driver.findElements(getPriceOfDiscountProductOnPage).size()==0){
            return Collections.EMPTY_LIST;
        }
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getPriceOfDiscountProductOnPage));

    }

    public List<WebElement> getProductPriceOnProductOnPage(){
        if(driver.findElements(getPriceOfProductOnPage).size()==0){
            return Collections.EMPTY_LIST;
        }
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getPriceOfProductOnPage));
    }

    public List<WebElement> getProductPriceWebElementList(){
        List<WebElement> priceList=getPriceOfDiscountProductOnPage();
        List<WebElement> priceListDiscounted=getProductPriceOnProductOnPage();
        List<WebElement> priceListNew = new ArrayList<WebElement>();
        priceListNew.addAll(priceList);
        priceListNew.addAll(priceListDiscounted);
        return priceListNew;
    }



    public int searchedProductList(){
        List<String> productList = null;
        int count=0;
        List<WebElement> productListWebElements = driver.findElements(totalProductAfterSearch);
        for (WebElement e:productListWebElements
             ) {
            System.out.println(e.getText());
            count++;
        }
        return count;

    }

    public int productOnPage(){

        int count=0;
        List<WebElement> productListWebElements = driver.findElements(totalProductAfterSearch);
        System.out.println("TOTAL PRODUCTS ON PAGE : "+getTotalProductsOnPageTitle());
        for (WebElement e:productListWebElements) {
            System.out.println(e.getText());
            count++;
        }
        if(driver.findElements(nextPage).size()>=1)
        {
            driver.findElement(nextPage).click();
            List<WebElement> productListWebElementsNext = driver.findElements(totalProductAfterSearch);
            for (WebElement e:productListWebElementsNext) {
                System.out.println("PRODUCT NAME ON PAGE : "+e.getText());
                count++;
            }
        }
        System.out.println("TOTAL PRODUCT COUNT : "+count);
        return count;

    }

    public void productPriceWithDiscountAndNonDiscountFilterCheck() {

        List<WebElement> priceInListString = getPriceOfDiscountProductOnPage();
        String priceInStringNew = null;
        double productPrice;
        for (WebElement e : priceInListString) {
            priceInStringNew = e.getText();
            priceInStringNew = priceInStringNew.replaceAll("\\$", "");
            productPrice = Double.parseDouble(priceInStringNew);
            System.out.println("DISCOUNTED PRODUCT PRICE IN DOUBLE : " + productPrice);
        }

        List<WebElement> priceInNotDiscountedListString = getProductPriceOnProductOnPage();
        String priceInNotDiscountStringNew = null;
        double productPriceNotDiscount;
        for (WebElement e : priceInNotDiscountedListString) {
            priceInNotDiscountStringNew = e.getText();
            priceInNotDiscountStringNew = priceInNotDiscountStringNew.replaceAll("\\$", "");
            productPriceNotDiscount = Double.parseDouble(priceInNotDiscountStringNew);
            System.out.println("NOT DISCOUNTED PRODUCT PRICE IN DOUBLE : " + productPriceNotDiscount);
        }
    }

    public List<Double> getProductPriceInDouble() {
        List<Double> priceListInDouble = new ArrayList<Double>();
        double productPrice;
        for (WebElement e : getProductPriceWebElementList()) {
            productPrice = Double.parseDouble(e.getText().replaceAll("\\$",""));
            //System.out.println("PRODUCT PRICE IN DOUBLE : " + productPrice);
            priceListInDouble.add(productPrice);
        }
        if (driver.findElements(nextPage).size() >= 1) {
            driver.findElement(nextPage).click();
            for (WebElement e : getProductPriceWebElementList()) {
                productPrice = Double.parseDouble(e.getText().replaceAll("\\$",""));
                //System.out.println("PRODUCT PRICE IN DOUBLE : " + productPrice);
                priceListInDouble.add(productPrice);

            }

        }return priceListInDouble;
    }

        public List<Double> allProductPriceFilterCheck() {

            List<Double> priceListInDouble = new ArrayList<Double>();
            String priceInStringNew = null;
            double productPrice;
            for (WebElement e : getProductPriceWebElementList()) {
                priceInStringNew = e.getText();
                priceInStringNew = priceInStringNew.replaceAll("\\$", "");
                productPrice = Double.parseDouble(priceInStringNew);
                System.out.println("PRODUCT PRICE IN DOUBLE : " + productPrice);
                priceListInDouble.add(productPrice);
            }
            if(driver.findElements(nextPage).size()>=1)
            {
                driver.findElement(nextPage).click();

            }
            return priceListInDouble;
        }



    }


