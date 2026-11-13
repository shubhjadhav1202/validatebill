package com.example.validatebill.controller;

import com.example.validatebill.model.ValidateBillRequest;
import com.example.validatebill.model.ValidateBillResponse;
import com.example.validatebill.service.ValidateBillService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class ValidateBillController {

    private final ValidateBillService validateBillService;

    public ValidateBillController(
            ValidateBillService validateBillService) {

        this.validateBillService = validateBillService;
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateBillResponse> validateBill(
            @RequestBody ValidateBillRequest request) {

        ValidateBillResponse response =
                validateBillService.validateBill(request);

        return ResponseEntity.ok(response);
    }
}