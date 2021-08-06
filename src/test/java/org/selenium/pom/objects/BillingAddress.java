package org.selenium.pom.objects;

public class BillingAddress {

    private String fistName;
    private String lastName;
    private String companyName;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
    private String phoneNumber;
    private String email;
    private String country;
    private String state;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public BillingAddress(){

    }

    public BillingAddress(String fistName,String lastName,String company,
                          String address1,String address2,String city,
                          String postalCode,String phoneNumber,String email){

        this.fistName=fistName;
        this.lastName=lastName;
        this.companyName=company;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.postCode=postalCode;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }

    public String getFistName() {
        return fistName;
    }

    public BillingAddress setFistName(String fistName) {
        this.fistName = fistName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BillingAddress setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public BillingAddress setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public BillingAddress setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public BillingAddress setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BillingAddress setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }




}
