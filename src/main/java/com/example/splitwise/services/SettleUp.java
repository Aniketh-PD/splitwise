package com.example.splitwise.services;


import com.example.splitwise.models.Expense;

import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.stratergy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettleUp {



    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SettleUp(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }


    public List<Expense> settleUpGroup(Long groupId){
        /*
        * fetch the group based on group id
        */
        Group group =  groupRepository.findById(groupId).get();
        List<Expense> groupExpenses =  group.getExpenses();
        return SettleUpStrategy.settleUp(groupExpenses);
    }
    public List<Expense> settleUpUserInaGroup(Long groupId ,Long userId){
        List<Expense> settledUpGroupList = settleUpGroup(groupId);
        User user = userRepository.findById(userId).get();
        List<Expense> result = new ArrayList<>();
        for(Expense expense: settledUpGroupList){
            if(expense.getOwedBy().stream().anyMatch(expenseUser -> expenseUser.getUser().equals(user))
                || expense.getPaidBy().stream().anyMatch(expenseUser -> expenseUser.getUser().equals(user))){
                result.add(expense);
            }
        }
        return result;
    }
}
