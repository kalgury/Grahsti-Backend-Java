package com.example.grahstibackend.dtos;

import com.example.grahstibackend.entities.Expense;

public class ExpensesListDto extends Expense {
    private String createdByUsername;

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

      public ExpensesListDto(Expense expense, String name) {
        this.id = expense.getId();
        this.userId = expense.getUserId();
        this.groupId = expense.getGroupId();
        this.amount = expense.getAmount();
        this.currency = expense.getCurrency();
        this.category = expense.getCategory();
        this.sub_category = expense.getSub_category();
        this.title = expense.getTitle();
        this.description = expense.getDescription();
        this.status = expense.getStatus();
        this.settlementStatus = expense.getSettlementStatus();
        this.createdByUsername = name;
        this.createdAt= expense.getCreatedAt();
        this.updatedAt= expense.getUpdatedAt();

    }

}
