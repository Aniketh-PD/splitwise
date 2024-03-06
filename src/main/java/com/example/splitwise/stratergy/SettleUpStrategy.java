package com.example.splitwise.stratergy;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SettleUpStrategy {
    public static List<Expense> settleUp(List<Expense> expensesList){
        System.out.println(expensesList);
        Map<User,Double> userAmount = new HashMap<>();
        for(Expense expense:expensesList){
            for(ExpenseUser expenseUser :expense.getPaidBy()){
                userAmount.put(expenseUser.getUser(),
                        userAmount.getOrDefault(expenseUser.getUser(),
                                0.0) + expenseUser.getAmount());
            }
            for(ExpenseUser expenseUser :expense.getOwedBy()){
                userAmount.put(expenseUser.getUser(),
                        userAmount.getOrDefault(expenseUser.getUser(),
                                0.0) - expenseUser.getAmount());
            }
        }
        /*
        * from the ExpenseList create two heaps one Heap will contain
        * all the users which have paid and other heap will have all
        * the users who owed until the paidBy heap is not empty
        * we keep popping elements both heaps and check if the
        * */
        PriorityQueue<ExpenseUser> paidbyHeap = new PriorityQueue<>();
        PriorityQueue<ExpenseUser> owedByHeap = new PriorityQueue<>();

        return null;
    }
}
