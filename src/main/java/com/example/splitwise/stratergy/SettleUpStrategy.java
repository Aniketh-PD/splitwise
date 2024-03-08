package com.example.splitwise.stratergy;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.User;

import java.util.*;

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
        List<Expense> settledUpList = new ArrayList<>();
        PriorityQueue<ExpenseUser> paidbyHeap = new PriorityQueue<>(new PQComparator());
        PriorityQueue<ExpenseUser> owedByHeap = new PriorityQueue<>(new PQComparator());

        for(Map.Entry<User,Double> entry : userAmount.entrySet()){
            if(entry.getValue() > 0){
                paidbyHeap.add(new ExpenseUser(entry.getKey(), entry.getValue()));
            }else{
                owedByHeap.add(new ExpenseUser(entry.getKey(), -1 * entry.getValue()));
            }
        }
        /*
        * if cond owed user amt is getting paid by the owed user to the paid user hence paid user owes that much amt
        * */
        while(!paidbyHeap.isEmpty()){
            ExpenseUser paidUser = paidbyHeap.poll();
            ExpenseUser owedUser = owedByHeap.poll();
            if(paidUser.getAmount() > owedUser.getAmount()){
                settledUpList.add(Expense.builder()
                                        .owedBy(List.of(new ExpenseUser(paidUser.getUser(),owedUser.getAmount())))
                                        .paidBy(List.of(owedUser))
                                        .build());

                paidbyHeap.add(new ExpenseUser(paidUser.getUser(),paidUser.getAmount()- owedUser.getAmount()));

            }else{
                settledUpList.add(Expense.builder()
                                    .owedBy(List.of(paidUser))
                                    .paidBy(List.of(new ExpenseUser(owedUser.getUser(),paidUser.getAmount())))
                                    .build());
                owedByHeap.add(new ExpenseUser(owedUser.getUser(), owedUser.getAmount()- paidUser.getAmount()));
            }
        }

        return settledUpList;
    }
    static class  PQComparator implements  Comparator<ExpenseUser>{
        @Override
        public int compare(ExpenseUser u1, ExpenseUser u2) {
            return (int)(u2.getAmount() - u1.getAmount());
        }
    }
}
