package com.example.splitwise.controllers;


import com.example.splitwise.models.Expense;
import com.example.splitwise.services.SettleUp;
import com.example.splitwise.stratergy.SettleUpStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/settleUp")
public class SettleUpController {

    private final SettleUp settleUp;

    public SettleUpController(SettleUp settleUp) {
        this.settleUp = settleUp;
    }



    @GetMapping(path="/group/{groupId}")
    public List<Expense> settleUpGroup(@PathVariable(name = "groupId") Long groupId){
        return settleUp.settleUpGroup(groupId);
    }

    @GetMapping(path = "/{groupId}/user/{userId}")
    public List<Expense> settleUpGroupForUser(@PathVariable(name="groupId")Long groupId,@PathVariable(name="userId")Long userId){
        return settleUp.settleUpUserInaGroup(groupId,userId);
    }
}
