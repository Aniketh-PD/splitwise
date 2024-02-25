package com.example.splitwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "splitwise_group")
public class Group extends BaseModel{

    private String name;
    private boolean isSimplifiedDebtOn;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "group")
    List<Expense> expenses;
}
