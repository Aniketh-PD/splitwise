package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends BaseModel{
    private String name;
    private double totalAmount;

    @Enumerated(value = EnumType.STRING)
    private ExpenseType type;
    @ManyToOne
    private Group group;

    //who all paid
    @OneToMany
    List<ExpenseUser> paidBy;

    //who all owed
    @OneToMany
    List<ExpenseUser> owedBy;
}
