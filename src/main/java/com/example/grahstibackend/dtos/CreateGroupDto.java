package com.example.grahstibackend.dtos;

import java.util.UUID;

import com.example.grahstibackend.entities.enums.GroupTypeEnums;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateGroupDto {
    @NotNull(message = "Group Title is required.")
    private String title;

    @Size(min = 0, max = 10000000, message = "Password must be between 8 and 20 characters.")
    private Integer totalBudget = 0;

    private GroupTypeEnums type = GroupTypeEnums.EQUALSPLIT;

    private UUID createdBy;
    

    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + title + '\'' +
                ", email='" + totalBudget + '\'' +
                ", password='" + totalBudget + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Integer getTotalBudget() {
        return totalBudget;
    }

    public GroupTypeEnums getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
    }

    public void setType(GroupTypeEnums type) {
        this.type = type;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }
}
