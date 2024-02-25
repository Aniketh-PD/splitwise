package com.example.splitwise.controllers;

import com.example.splitwise.dtos.AddExpenseRequest;
import com.example.splitwise.services.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

   private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @PostMapping(path = "/add")
    public @ResponseBody Long addExpense(@RequestBody AddExpenseRequest expenseRequest){
        return expenseService.createExpense(expenseRequest).getId();
    }
}
