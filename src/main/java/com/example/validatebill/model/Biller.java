package com.example.validatebill.model;

public class Biller {

    private String billerId;
    private String billerShortName;
    private String billerLongName;

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getBillerShortName() {
        return billerShortName;
    }

    public void setBillerShortName(String billerShortName) {
        this.billerShortName = billerShortName;
    }

    public String getBillerLongName() {
        return billerLongName;
    }

    public void setBillerLongName(String billerLongName) {
        this.billerLongName = billerLongName;
    }
}