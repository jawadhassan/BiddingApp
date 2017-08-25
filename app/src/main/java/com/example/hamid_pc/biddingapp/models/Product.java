package com.example.hamid_pc.biddingapp.models;


public class Product {
    private String productUid;
    private String productTitle;
    private String productDescription;
    private String photoUrl;


    public Product(String productUid, String productTitle, String productDescription, String photoUrl) {
        this.productUid = productUid;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.photoUrl = photoUrl;
    }


    public String getProductUid() {
        return productUid;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
