package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.ShippingAddress;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ShippingAddressApi {

        private Cookies cookies;
        public Cookies getCookies(){
            return cookies;
        }

        private String fetchRegisterNonceValueUsingGroovy(){
            Response response = getAccount();
            return response.htmlPath().getString("**.findAll{it.@name=='woocommerce-shipping-calculator-nonce'}.@value");
        }

        private String fetchRegisterNonceValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-shipping-calculator-nonce");
        return element.attr("value");
    }

        private Response getAccount(){
            Cookies cookies=new Cookies();
            Response response = given().baseUri(ConfigLoader.getInstance().getBaseUrl()).cookies(cookies)
                    //.log().all()
                    .when().get("/cart").then().log().all().extract().response();

            if(response.getStatusCode() !=200){
                throw new RuntimeException("Failed to fetch cart Page, :"+response.statusCode());
            }

            return response;
        }

    public Response ShippingAddressUpdate(ShippingAddress shippingAddress){
        Cookies cookies=new Cookies();
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,String> formParams = new HashMap<>();
        formParams.put("Country",shippingAddress.getCountry());
        formParams.put("State",shippingAddress.getState());
        formParams.put("City",shippingAddress.getCity());
        formParams.put("PostalCode",shippingAddress.getPostCode());
        formParams.put("woocommerce-shipping-calculator-nonce",fetchRegisterNonceValueUsingGroovy());
        formParams.put("_wp_http_referer:","/cart/");
        formParams.put("calc_shipping","x");
        Response response = given().baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                //.log().all()
                .when().post("/cart").then()
                //.log().all()
                .extract().response();

        if(response.getStatusCode() !=200){
            throw new RuntimeException("Failed to update Shipping Address On Cart Page, :"+response.statusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
