package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonsUtils;

import java.io.IOException;

public class Product {

    private int id;
    private String name;
    private boolean featured;

    public boolean isFeaturedProduct() {
        return featured;
    }

    public void setFeaturedProduct(boolean featuredProduct) {
        this.featured = featuredProduct;
    }



    public Product(){

    }

    public Product(int id) throws IOException {
        Product products[] = JacksonsUtils.deserializeJson("products.json",Product[].class);
        for(Product product:products){
            if(product.getId()==id){
                this.id=id;
                this.name=product.getName();
                this.featured=isFeaturedProduct();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
