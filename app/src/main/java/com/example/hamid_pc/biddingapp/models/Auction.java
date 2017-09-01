package com.example.hamid_pc.biddingapp.models;


public class Auction {

    private String mAuctionId;
    private String mProductId;
    private String mSellerId;
    private String mBuyerId;
    private int mMinBid;
    private int mBidClosingAmount;
    private long mBidStartDate;
    private long mBidEndDate;


    public Auction() {
    }

    public Auction(String auctionId, String productId, String sellerId, String buyerId,
                   int minBid, int bidClosingAmount, long bidStartDate, long bidEndDate) {
        mAuctionId = auctionId;
        mProductId = productId;
        mSellerId = sellerId;
        mBuyerId = buyerId;
        mMinBid = minBid;
        mBidClosingAmount = bidClosingAmount;
        mBidStartDate = bidStartDate;
        mBidEndDate = bidEndDate;
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

    public int getMinBid() {
        return mMinBid;
    }

    public void setMinBid(int minBid) {
        mMinBid = minBid;
    }

    public int getBidClosingAmount() {
        return mBidClosingAmount;
    }

    public void setBidClosingAmount(int bidClosingAmount) {
        mBidClosingAmount = bidClosingAmount;
    }

    public long getBidStartDate() {
        return mBidStartDate;
    }

    public void setBidStartDate(long bidStartDate) {
        mBidStartDate = bidStartDate;
    }

    public long getBidEndDate() {
        return mBidEndDate;
    }

    public void setBidEndDate(long bidEndDate) {
        mBidEndDate = bidEndDate;
    }
}
