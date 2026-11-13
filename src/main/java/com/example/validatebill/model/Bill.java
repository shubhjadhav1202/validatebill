package com.example.validatebill.model;
import java.util.List;

public class Bill {
    private String channelId;
    public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	private String billId;
    private String billUrl;
    private String billReference;
    private String billDescription;
    private String billIssueDate;
    private Double billNewAmount;
    private Double billPleasePay;
    private Double billOverdueAmount;
    private String billDueDate;
    private String billStatus;
    private String billPaymentStatus;
    private Double lastBillAmount;
    private Double lastBillPaymentAmount;
    private String billStartDate;
    private String billEndDate;
    public RevenueCode getRevenueCode() {
		return revenueCode;
	}

	public void setRevenueCode(RevenueCode revenueCode) {
		this.revenueCode = revenueCode;
	}

	public Boolean getAllowPartialPayment() {
		return allowPartialPayment;
	}

	public void setAllowPartialPayment(Boolean allowPartialPayment) {
		this.allowPartialPayment = allowPartialPayment;
	}

	public Boolean getAllowOverPayment() {
		return allowOverPayment;
	}

	public void setAllowOverPayment(Boolean allowOverPayment) {
		this.allowOverPayment = allowOverPayment;
	}

	public List<PaymentInstrument> getPaymentInstruments() {
		return paymentInstruments;
	}

	public void setPaymentInstruments(List<PaymentInstrument> paymentInstruments) {
		this.paymentInstruments = paymentInstruments;
	}

	private RevenueCode revenueCode;
    private Boolean allowPartialPayment;
    private Boolean allowOverPayment;
    private List<PaymentInstrument> paymentInstruments;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillUrl() {
        return billUrl;
    }

    public void setBillUrl(String billUrl) {
        this.billUrl = billUrl;
    }

    public String getBillReference() {
        return billReference;
    }

    public void setBillReference(String billReference) {
        this.billReference = billReference;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public String getBillIssueDate() {
        return billIssueDate;
    }

    public void setBillIssueDate(String billIssueDate) {
        this.billIssueDate = billIssueDate;
    }

    public Double getBillNewAmount() {
        return billNewAmount;
    }

    public void setBillNewAmount(Double billNewAmount) {
        this.billNewAmount = billNewAmount;
    }

    public Double getBillPleasePay() {
        return billPleasePay;
    }

    public void setBillPleasePay(Double billPleasePay) {
        this.billPleasePay = billPleasePay;
    }

    public Double getBillOverdueAmount() {
        return billOverdueAmount;
    }

    public void setBillOverdueAmount(Double billOverdueAmount) {
        this.billOverdueAmount = billOverdueAmount;
    }

    public String getBillDueDate() {
        return billDueDate;
    }

    public void setBillDueDate(String billDueDate) {
        this.billDueDate = billDueDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillPaymentStatus() {
        return billPaymentStatus;
    }

    public void setBillPaymentStatus(String billPaymentStatus) {
        this.billPaymentStatus = billPaymentStatus;
    }

    public Double getLastBillAmount() {
        return lastBillAmount;
    }

    public void setLastBillAmount(Double lastBillAmount) {
        this.lastBillAmount = lastBillAmount;
    }

    public Double getLastBillPaymentAmount() {
        return lastBillPaymentAmount;
    }

    public void setLastBillPaymentAmount(Double lastBillPaymentAmount) {
        this.lastBillPaymentAmount = lastBillPaymentAmount;
    }

    public String getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(String billStartDate) {
        this.billStartDate = billStartDate;
    }

    public String getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(String billEndDate) {
        this.billEndDate = billEndDate;
    }
}