package com.example.hamid_pc.biddingapp.models;


public class Bid {

    private int mBidAmount;
    private String mAuctionId;
    private String mProductId;
    private String mBidderId;
    private String mBidderEmail;


    public Bid() {
    }

    public Bid(int bidAmount, String auctionId, String productId, String bidderId, String bidderEmail) {
        mBidAmount = bidAmount;
        mAuctionId = auctionId;
        mProductId = productId;
        mBidderId = bidderId;
        mBidderEmail = bidderEmail;
    }

    public int getBidAmount() {
        return mBidAmount;
    }

    public void setBidAmount(int bidAmount) {
        mBidAmount = bidAmount;
    }

    public String getAuctionId() {
        return mAuctionId;
    }

    public void setAuctionId(String auctionId) {
        mAuctionId = auctionId;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String productId) {
        mProductId = productId;
    }

    public String getBidderId() {
        return mBidderId;
    }

    public void setBidderId(String bidderId) {
        mBidderId = bidderId;
    }

    public String getBidderEmail() {
        return mBidderEmail;
    }

    public void setBidderEmail(String bidderEmail) {
        mBidderEmail = bidderEmail;
    }
}
