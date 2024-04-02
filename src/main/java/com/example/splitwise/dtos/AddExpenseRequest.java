package com.example.splitwise.dtos;

import com.example.splitwise.models.ExpenseType;
import lombok.Data;

import java.util.Map;

@Data
public class AddExpenseRequest {
    private String name;
    private double amount;

    private ExpenseType expenseType;

    private Map<Long,Double> paidBy;
    private Map<Long,Double> owedBy;
    private String groupName;
}
