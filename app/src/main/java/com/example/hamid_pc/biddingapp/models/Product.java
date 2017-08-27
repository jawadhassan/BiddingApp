package com.example.hamid_pc.biddingapp.models;


public class Product {
    private String mProductUid;
    private String mProductTitle;
    private String mProductDescription;
    private String mPhotoUrl;
    private String mSellerId;
    private String mBuyerId;
    private Boolean mSold;


    public Product() {
    }


    public Product(String productUid, String productTitle, String productDescription, String photoUrl, String sellerId, String buyerId) {
        mProductUid = productUid;
        mProductTitle = productTitle;
        mProductDescription = productDescription;
        mPhotoUrl = photoUrl;
        mSellerId = sellerId;
        mBuyerId = buyerId;
        mSold = false;
    }

    public String getProductUid() {
        return mProductUid;
    }

    public void setProductUid(String productUid) {
        mProductUid = productUid;
    }

    public String getProductTitle() {
        return mProductTitle;
    }

    public void setProductTitle(String productTitle) {
        mProductTitle = productTitle;
    }

    public String getProductDescription() {
        return mProductDescription;
    }

    public void setProductDescription(String productDescription) {
        mProductDescription = productDescription;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public String getSellerId() {
        return mSellerId;
    }

    public void setSellerId(String sellerId) {
        mSellerId = sellerId;
    }

    public String getBuyerId() {
        return mBuyerId;
    }

    public void setBuyerId(String buyerId) {
        mBuyerId = buyerId;
    }

    public Boolean getSold() {
        return mSold;
    }

    public void setSold(Boolean sold) {
        mSold = sold;
    }
}
