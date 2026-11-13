package com.example.validatebill.repository;

import com.example.validatebill.model.Bill;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import com.example.validatebill.model.PaymentInstrument;
import com.example.validatebill.model.RevenueCode;
import java.util.Arrays;
@Repository
public class BillRepository {

    private final Map<String, Bill> bills = new HashMap<>();

    public BillRepository() {

        Bill bill = new Bill();

        bill.setBillId("887711101");
        bill.setBillUrl("https://www.biller.com.bn/bills/billid=8100992749");
        bill.setChannelId("10032036");
        bill.setBillReference("05-011851");
        bill.setBillDescription("Tax Invoice");
        bill.setBillIssueDate("2012-08-01");
        bill.setBillNewAmount(9125.0);
        bill.setBillPleasePay(11539.0);
        bill.setBillOverdueAmount(2414.0);
        bill.setBillDueDate("2012-08-30");
        bill.setBillStatus("ACTIVE");
        bill.setBillPaymentStatus("UNPAID");
        bill.setLastBillAmount(7414.0);
        bill.setLastBillPaymentAmount(5000.0);
        bill.setBillStartDate("2012-08-01");
        bill.setBillEndDate("2012-08-31");
        RevenueCode revenueCode = new RevenueCode();

        revenueCode.setProfitCenter("PC1001");
        revenueCode.setCostCenter("CC1001");
        revenueCode.setGlAccountNo("GL100001");
        revenueCode.setCommitmentItem("CI1001");
        revenueCode.setFundCenter("FC1001");
        revenueCode.setFunctionalArea("FA1001");
        revenueCode.setPsNetworkWbs("WBS1001");

        bill.setRevenueCode(revenueCode);

        bill.setAllowPartialPayment(true);
        bill.setAllowOverPayment(false);

        PaymentInstrument cash = new PaymentInstrument();
        cash.setPaymentInstrumentType("CASH");
        cash.setOnUs(true);

        PaymentInstrument card = new PaymentInstrument();
        card.setPaymentInstrumentType("CARD");
        card.setOnUs(true);

        bill.setPaymentInstruments(Arrays.asList(cash, card));


        bills.put(
            createKey("10032036", "1003", "05-011851"),
            bill
        );
    }

    private String createKey(
            String channelId,
            String billerId,
            String billReference) {

        return channelId + "|" + billerId + "|" + billReference;
    }

    public Bill findBill(
            String channelId,
            String billerId,
            String billReference) {

        return bills.get(
            createKey(channelId, billerId, billReference)
        );
    }
}