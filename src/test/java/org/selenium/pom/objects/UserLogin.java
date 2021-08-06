package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonsUtils;

import java.io.IOException;

public class UserLogin {

    private int userID;
    private String username;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public UserLogin setEmail(String email) {
        this.email = email;
        return this;
    }



    public UserLogin(){}
    public UserLogin(int userID) throws IOException {
        UserLogin userlogin[] = JacksonsUtils.deserializeJson("userlogin1.json",UserLogin[].class);
        for(UserLogin ul:userlogin){
            if(ul.getUserID()==userID){
                this.userID=userID;
                this.username=ul.getUsername();
                this.password=ul.getPassword();
            }
        }
    }

    public UserLogin(String username,String password, String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public UserLogin setUsername(String username) {

        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLogin setPassword(String password) {

        this.password = password;
        return this;
    }

}
