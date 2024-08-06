package com.example.grahstibackend.dtos;

import com.example.grahstibackend.entities.GroupMember;

public class GroupMembersList extends GroupMember {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GroupMembersList(GroupMember member, String username) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.groupId = member.getGroupId();
        this.isAdmin = member.getIsAdmin();
        this.status = member.getStatus();
        this.contributionSplitPercentage = member.getContributionSplitPercentage();
        this.username = username;
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();

    }
}
