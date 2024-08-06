package com.example.grahstibackend.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.grahstibackend.dtos.AddExpenseDto;
import com.example.grahstibackend.dtos.ExpensesListDto;
import com.example.grahstibackend.entities.Expense;
import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.entities.enums.SettlementStatusEnums;
import com.example.grahstibackend.repositories.ExpenseRepository;
import com.example.grahstibackend.repositories.UserRepository;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Iterable<ExpensesListDto> groupExpenseListing(UUID groupId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1 , 50, Sort.by("createdAt").descending());
        Page<Expense> expenseList = expenseRepository.findAllByGroupId(groupId, pageable);

        Map<UUID, User> userMap = new HashMap<>(); // Use HashMap for memoization

        return StreamSupport.stream(expenseList.spliterator(), false)
                .map(expense -> {
                    UUID userId = expense.getUserId(); // Assuming userId is Long
                    User user = userMap.get(userId);
                    if (user == null) {
                        user = userRepository.findById(userId).orElse(null);
                        userMap.put(userId, user);
                    }
                    String createdByUsername = user.getFullName();
                    return new ExpensesListDto(expense, createdByUsername);
                })
                .collect(Collectors.toList());
    }

    public Expense addNewExpense(AddExpenseDto data) {
        Expense newExpense = new Expense()
                .setUserId(data.getUserId())
                .setGroupId(data.getGroupId())
                .setTitle(data.getTitle())
                .setAmount(data.getAmount())
                .setDescription(data.getDescription())
                .setSettlementStatus(
                        data.getIsSettled() ? SettlementStatusEnums.SETTLED : SettlementStatusEnums.UNSETTLED)
                .setCategory(data.getCategory());

        return expenseRepository.save(newExpense);
    }

    public Expense settleExpense(UUID expenseId, Boolean isSettled) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow();
        expense.setSettlementStatus(isSettled ? SettlementStatusEnums.SETTLED : SettlementStatusEnums.UNSETTLED);
        return expenseRepository.save(expense);
    }
}
