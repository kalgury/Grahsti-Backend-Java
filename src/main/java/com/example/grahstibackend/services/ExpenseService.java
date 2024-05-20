package com.example.grahstibackend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.grahstibackend.dtos.AddExpenseDto;
import com.example.grahstibackend.entities.Expense;
import com.example.grahstibackend.entities.enums.SettlementStatusEnums;
import com.example.grahstibackend.repositories.ExpenseRepository;

@Service
public class ExpenseService {
      private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Iterable<Expense> groupExpenseListing(UUID groupId) {
        return expenseRepository.findAllByGroupId(groupId);
    }

    public Expense addNewGroupCategory(AddExpenseDto data){
        Expense newExpense = new Expense()
        .setUserId(data.getUserId())
        .setGroupId(data.getGroupId())
        .setTitle(data.getTitle())
        .setAmount(data.getAmount())
        .setDescription(data.getDescription())
        .setCategory(data.getCategory());

        return expenseRepository.save(newExpense);
    }

    public Expense settleExpense(UUID expenseId, Boolean isSettled){
        Expense expense= expenseRepository.findById(expenseId).orElseThrow();
        expense.setSettlementStatus(isSettled?SettlementStatusEnums.SETTLED:SettlementStatusEnums.UNSETTLED);
        return expenseRepository.save(expense);
    }
}
