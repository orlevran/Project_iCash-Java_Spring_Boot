package com.example.cash_register_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cash_register_service.model.Purchase;
import com.example.cash_register_service.model.DTO.PurchaseRequest;
import com.example.cash_register_service.service.PurchaseService;


// REST controller responsible for handling HTTP requests related to purchases.
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // Test endpoint to verify the controller is running and mapped correctly.
    @GetMapping("/test")
    public String test() {
        return "Controller is active";
    }

    /**
     * Handles the creation of a new purchase.
     *
     * Receives a JSON payload mapped to PurchaseRequest, delegates the creation
     * to PurchaseService#makePurchase(PurchaseRequest), and returns the persisted
     * Purchase object.
     *
     * param: request. the incoming purchase request from client
     * return: the saved Purchase entity
     * throws: IllegalArgumentException if request is invalid (handled globally via ControllerAdvice)
     * 
     * example:
     * POST /purchases/make with body:
     * {
     *   "supermarketId": "SMKT001",
     *   "items": ["milk", "bread"],
     *   "userId": null
     * }
     */
    @PostMapping("/make")
    public Purchase makePurchase(@RequestBody PurchaseRequest request) {
        return purchaseService.makePurchase(request);
    }
}
