package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "splitwise_group")
public class Group extends BaseModel{

    private String name;
    private boolean isSimplifiedDebtOn;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;
    @OneToMany(mappedBy = "group")
    List<Expense> expenses;
}
