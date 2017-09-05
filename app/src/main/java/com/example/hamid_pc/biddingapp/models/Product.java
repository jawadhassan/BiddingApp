package com.example.hamid_pc.biddingapp.models;


public class Product {
    private String mProductUid;
    private String mProductTitle;
    private String mProductDescription;
    private String mPhotoUrl;
    private String mSellerId;
    private String mBuyerId;
    private int mMinBidAmount;
    private String mProductType;
    private String mOwnerId_ProductType_Sold;
    private Boolean mSold;
    private String mAuctionId;


    public Product() {
    }

    public Product(String productUid, String AuctionId, String productTitle, String productDescription,
                   String photoUrl, String sellerId, String buyerId, int minBidAmount,
                   String productType, String ownerId_ProductType_Sold, Boolean sold) {
        mProductUid = productUid;
        mProductTitle = productTitle;
        mProductDescription = productDescription;
        mPhotoUrl = photoUrl;
        mSellerId = sellerId;
        mBuyerId = buyerId;
        mMinBidAmount = minBidAmount;
        mProductType = productType;
        mOwnerId_ProductType_Sold = ownerId_ProductType_Sold;
        mSold = sold;
        mAuctionId = AuctionId;

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

    public int getMinBidAmount() {
        return mMinBidAmount;
    }

    public void setMinBidAmount(int minBidAmount) {
        mMinBidAmount = minBidAmount;
    }

    public String getProductType() {
        return mProductType;
    }

    public void setProductType(String productType) {
        mProductType = productType;
    }

    public String getOwnerId_ProductType_Sold() {
        return mOwnerId_ProductType_Sold;
    }

    public void setOwnerId_ProductType_Sold(String ownerId_ProductType_Sold) {
        mOwnerId_ProductType_Sold = ownerId_ProductType_Sold;
    }

    public String getAuctionId() {
        return mAuctionId;
    }

    public void setAuctionId(String auctionId) {
        mAuctionId = auctionId;
    }
}
