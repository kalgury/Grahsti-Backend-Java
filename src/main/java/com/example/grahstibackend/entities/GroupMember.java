package com.example.grahstibackend.entities;

import jakarta.persistence.*;

import java.util.UUID;

import com.example.grahstibackend.entities.enums.StatusEnums;

@Table(name = "group_members")
@Entity
public class GroupMember extends BaseEntity {
    // to add relations later
    @Column(nullable = false)
    protected UUID userId;
    @Column(nullable = false)
    protected UUID groupId;
    @Column(nullable = false)
    protected boolean isAdmin = false;

    @Column(nullable = false)
    protected Integer contributionSplitPercentage = 0;

    @Column(nullable = false)
    protected StatusEnums status = StatusEnums.ACTIVE;

    public StatusEnums getStatus() {
        return status;
    }

    public GroupMember setStatus(StatusEnums status) {
        this.status = status;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public GroupMember setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public GroupMember setGroupId(UUID groupId) {
        this.groupId = groupId;
        return this;
    }

    // public boolean isAdmin() {
    //     return isAdmin;
    // }
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public GroupMember setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public Integer getContributionSplitPercentage() {
        return contributionSplitPercentage;
    }

    public GroupMember setContributionSplitPercentage(Integer contributionSplitPercentage) {
        this.contributionSplitPercentage = contributionSplitPercentage;
        return this;
    }

}
