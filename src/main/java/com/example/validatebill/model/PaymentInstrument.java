package com.example.validatebill.model;

public class PaymentInstrument {

private String paymentInstrumentType;
private Boolean onUs;

public String getPaymentInstrumentType() {
    return paymentInstrumentType;
}

public void setPaymentInstrumentType(String paymentInstrumentType) {
    this.paymentInstrumentType = paymentInstrumentType;
}

public Boolean getOnUs() {
    return onUs;
}

public void setOnUs(Boolean onUs) {
    this.onUs = onUs;
}


}
