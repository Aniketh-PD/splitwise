package com.example.splitwise.services;

import com.example.splitwise.dtos.AddExpenseRequest;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.ExpenseRepository;
import com.example.splitwise.repositories.ExpenseUserRepository;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseUserRepository expenseUserRepository;

    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository, ExpenseUserRepository expenseUserRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.expenseUserRepository = expenseUserRepository;
    }





    public Expense createExpense(AddExpenseRequest request){
        Map<Long,Double> paidBy = request.getPaidBy();
        List<ExpenseUser> paidByList = new ArrayList<>();

        paidBy.forEach((user,amt) -> {
           User paidUser = userRepository.findById(user).get();
           ExpenseUser paidExpenseUser = expenseUserRepository.save(new ExpenseUser(paidUser,amt));
           paidByList.add(paidExpenseUser);

        });

        Map<Long,Double> owedBy = request.getOwedBy();
        List<ExpenseUser> owedByList = new ArrayList<>();
        owedBy.forEach((user,amt) -> {
            User owedUser = userRepository.findById(user).get();
            ExpenseUser ownedExpenseUser = expenseUserRepository.save(new ExpenseUser(owedUser,amt));
            owedByList.add(ownedExpenseUser);
        });

        Expense expense = Expense.builder()
                                .name(request.getName())
                                .type(request.getExpenseType())
                                .totalAmount(request.getAmount())
                                .paidBy(paidByList)
                                .owedBy(owedByList)
                                .build();

        return expenseRepository.save(expense);
    }
}
