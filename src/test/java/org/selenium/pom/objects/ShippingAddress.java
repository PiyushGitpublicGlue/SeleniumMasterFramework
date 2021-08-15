package org.selenium.pom.objects;

public class ShippingAddress {


    private String city;
    private String postCode;
    private String country;
    private String state;
    private double salesTax;

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }



    public ShippingAddress(){

    }


    public String getCountry() {
        return country;
    }

    public ShippingAddress setCountry(String country) {

        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public ShippingAddress setState(String state) {

        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingAddress setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }








}
