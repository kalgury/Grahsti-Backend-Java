package com.example.grahstibackend.entities;

import jakarta.persistence.*;

import com.example.grahstibackend.entities.enums.GroupTypeEnums;

@Table(name = "groups")
@Entity
public class Group extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupTypeEnums type = GroupTypeEnums.PERCENTAGESPLIT;

    @Column(nullable = true)
    private Integer totalBudget = 0;

    public Integer getTotalBudget() {
        return totalBudget;
    }

    public Group setTotalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                // ", fullName='" + fullName + '\'' +
                // ", email='" + email + '\'' +
                // ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Group setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GroupTypeEnums getType() {
        return type;
    }

    public Group setType(GroupTypeEnums type) {
        this.type = type;
        return this;
    }

}
