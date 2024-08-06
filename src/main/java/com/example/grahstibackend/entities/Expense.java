package com.example.grahstibackend.entities;

import jakarta.persistence.*;

import java.util.UUID;

import com.example.grahstibackend.entities.enums.CategoryEnums;
import com.example.grahstibackend.entities.enums.SettlementStatusEnums;
import com.example.grahstibackend.entities.enums.StatusEnums;

@Table(name = "expenses")
@Entity
public class Expense extends BaseEntity {
    // to add relations later
    @Column(nullable = false)
    protected UUID userId;

    @Column(nullable = true)
    protected UUID groupId;

    @Column(nullable = false)
    protected float amount = 0;

    @Column(nullable = false)
    protected String currency = "INR";

    @Column(nullable = false)
    protected String category = "MISCELLANOUS";

    @Column(nullable = true)
    protected String sub_category;

    @Column(nullable = false)
    protected String title;

    @Column(nullable = true)
    protected String description;

    @Column(nullable = false)
    protected StatusEnums status = StatusEnums.ACTIVE;

    @Column(nullable = false)
    protected SettlementStatusEnums settlementStatus = SettlementStatusEnums.UNSETTLED;

    public UUID getUserId() {
        return userId;
    }

    public Expense setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public Expense setGroupId(UUID groupId) {
        this.groupId = groupId;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public Expense setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Expense setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Expense setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSub_category() {
        return sub_category;
    }

    public Expense setSub_category(String sub_category) {
        this.sub_category = sub_category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Expense setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Expense setDescription(String description) {
        this.description = description;
        return this;
    }

    public StatusEnums getStatus() {
        return status;
    }

    public Expense setStatus(StatusEnums status) {
        this.status = status;
        return this;
    }

    public SettlementStatusEnums getSettlementStatus() {
        return settlementStatus;
    }

    public Expense setSettlementStatus(SettlementStatusEnums settlementStatus) {
        this.settlementStatus = settlementStatus;
        return this;
    }

}
