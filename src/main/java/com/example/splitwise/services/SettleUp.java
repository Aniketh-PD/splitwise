package com.example.splitwise.services;


import com.example.splitwise.models.Expense;

import com.example.splitwise.models.Group;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.stratergy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleUp {


    public SettleUp(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    private final GroupRepository groupRepository;


    public List<Expense> settleUpGroup(Long groupId){
        /*
        * fetch the group based on group id
        */
        Group group =  groupRepository.findById(groupId).get();
        List<Expense> groupExpenses =  group.getExpenses();
        return SettleUpStrategy.settleUp(groupExpenses);
    }
}
