package com.example.hamid_pc.biddingapp.models;


public class Auctioneer {

    private String mAuctioneerId;
    private String mAuctioneerEmail;
    private String mAuctioneerName;

    public Auctioneer(String auctioneerId, String auctioneerEmail, String auctioneerName) {
        mAuctioneerId = auctioneerId;
        mAuctioneerEmail = auctioneerEmail;
        mAuctioneerName = auctioneerName;
    }


    public String getAuctioneerId() {
        return mAuctioneerId;
    }

    public void setAuctioneerId(String auctioneerId) {
        mAuctioneerId = auctioneerId;
    }

    public String getAuctioneerEmail() {
        return mAuctioneerEmail;
    }

    public void setAuctioneerEmail(String auctioneerEmail) {
        mAuctioneerEmail = auctioneerEmail;
    }

    public String getAuctioneerName() {
        return mAuctioneerName;
    }

    public void setAuctioneerName(String auctioneerName) {
        mAuctioneerName = auctioneerName;
    }
}

