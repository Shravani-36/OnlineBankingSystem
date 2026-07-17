package com.onlinebank.backend.controller;

import com.onlinebank.backend.entity.Transaction;
import com.onlinebank.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{userId}")
    public List<Transaction> getTransactions(@PathVariable Long userId) {

        return transactionService.getTransactions(userId);

    }
}