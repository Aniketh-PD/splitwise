package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Builder
@Getter
@Setter
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
