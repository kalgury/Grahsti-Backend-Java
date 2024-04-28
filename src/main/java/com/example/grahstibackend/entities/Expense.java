package com.example.grahstibackend.entities;

import jakarta.persistence.*;

import java.util.UUID;

import com.example.grahstibackend.entities.enums.CategoryEnums;
import com.example.grahstibackend.entities.enums.StatusEnums;

@Table(name = "expenses")
@Entity
public class Expense extends BaseEntity {
    // to add relations later
    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = true)
    private UUID groupId;

    @Column(nullable = false)
    private float amount = 0;

    @Column(nullable = false)
    private String currency = "INR";

    @Column(nullable = false)
    private CategoryEnums category = CategoryEnums.MISCELLANOUS;

    @Column(nullable = true)
    private String sub_category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private StatusEnums status = StatusEnums.ACTIVE;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CategoryEnums getCategory() {
        return category;
    }

    public void setCategory(CategoryEnums category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnums getStatus() {
        return status;
    }

    public void setStatus(StatusEnums status) {
        this.status = status;
    }

}
