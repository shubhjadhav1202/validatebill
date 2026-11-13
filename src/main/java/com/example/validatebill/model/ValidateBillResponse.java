package com.example.validatebill.model;

public class ValidateBillResponse {

    private String requestId;
    private Integer statusCode;
    private String statusDescription;
    private Biller biller;
    private Bill bill;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Biller getBiller() {
        return biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    
    private boolean summaryIncluded;

    public boolean isSummaryIncluded() {
        return summaryIncluded;
    }

    public void setSummaryIncluded(boolean summaryIncluded) {
        this.summaryIncluded = summaryIncluded;
    }
}