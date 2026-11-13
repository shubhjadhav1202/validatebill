package com.example.validatebill.service;

import com.example.validatebill.model.Bill;
import com.example.validatebill.model.Biller;
import com.example.validatebill.model.ValidateBillRequest;
import com.example.validatebill.model.ValidateBillResponse;
import com.example.validatebill.repository.BillRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidateBillService {

    private final BillRepository billRepository;

    public ValidateBillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public ValidateBillResponse validateBill(ValidateBillRequest request) {

        ValidateBillResponse response = new ValidateBillResponse();

        response.setRequestId(request.getRequestId());

        Bill bill = billRepository.findBill(
                request.getChannelId(),
                request.getBillerId(),
                request.getBillReference()
        );

        if (bill == null) {
            response.setSummaryIncluded(false);
            response.setStatusCode(7);
            response.setStatusDescription("Resource Not Found");
            return response;
        }

        Biller biller = new Biller();

        biller.setBillerId("1002");
        biller.setBillerShortName("REVDIV");
        biller.setBillerLongName("Revenue Division");

        response.setBiller(biller);
        response.setBill(bill);
        response.setSummaryIncluded(true);
        response.setStatusCode(0);
        response.setStatusDescription("Success");

        if (request.getRequestedAmount() != null) {

            double requestedAmount = request.getRequestedAmount();

            if (Double.compare(
                    requestedAmount,
                    bill.getBillPleasePay()
            ) != 0) {

                response.setSummaryIncluded(false);
                response.setStatusCode(8);
                response.setStatusDescription("Invalid Amount");
                return response;
            }
        }

        response.setSummaryIncluded(true);
        response.setStatusCode(0);
        response.setStatusDescription("Success");

        return response;
    }
}