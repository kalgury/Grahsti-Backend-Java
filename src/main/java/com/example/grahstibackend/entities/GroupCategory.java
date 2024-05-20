package com.example.grahstibackend.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "group_categories")
@Entity
public class GroupCategory extends BaseEntity {

    @Column(nullable = false)
    private UUID groupId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String icon;

    @Column(nullable = false)
    private UUID createdBy;

    public UUID getGroupId() {
        return groupId;
    }

    public GroupCategory setGroupId(UUID groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GroupCategory setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public GroupCategory setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public GroupCategory setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
        return this;
    }

   
}
