package org.selenium.pom.api.actions;

import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String[] args){
        SignUpApi signup = new SignUpApi();
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        UserLogin user = new UserLogin()
                .setUsername(username)
                .setPassword("demopwd")
                .setEmail(username+"@test.com");
        signup.register(user);
        System.out.println("REGISTER COOKIES : "+signup.getCookies());
        CartApi cartapi = new CartApi(signup.getCookies());
        cartapi.addToCart(1211,1);
        System.out.println("CART COOKIES : "+cartapi.getCookies());
    }
}
