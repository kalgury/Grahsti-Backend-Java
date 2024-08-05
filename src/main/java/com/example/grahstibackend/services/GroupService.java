package com.example.grahstibackend.services;

import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.grahstibackend.dtos.CreateGroupDto;
import com.example.grahstibackend.entities.Group;
import com.example.grahstibackend.entities.GroupMember;
import com.example.grahstibackend.repositories.GroupMembersRepository;
import com.example.grahstibackend.repositories.GroupRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMembersRepository groupMembersRepository;

    public GroupService(GroupRepository groupRepository,GroupMembersRepository groupMembersRepository) {
        this.groupRepository = groupRepository;
        this.groupMembersRepository = groupMembersRepository;
    }

    public Iterable<Group> getGroupListing(UUID userId) {
        return groupRepository.findAll(Sort.by("createdAt").ascending());
    }

    public Group createNewGroup(CreateGroupDto body) {
        System.out.println("---------------------"+body.getCreatedBy());
        Group user = new Group()
                .setTitle(body.getTitle())
                .setTotalBudget(body.getTotalBudget())
                .setCreatedBy(body.getCreatedBy())
                .setType(body.getType());

        return groupRepository.save(user);
    }

    public Group fetchGroupById(UUID groupId){
        Group groupDetails = groupRepository.findById(groupId).orElseThrow();
        return groupDetails;
    }

    public void joinGroup(UUID groupId, UUID userId){
        // check if already joined 
        GroupMember hasAlredyJoined = groupMembersRepository.findMemberByGroupIdAndUserId(groupId, userId).orElse(null);
        if(hasAlredyJoined != null){
            return;
        }

        GroupMember newMember = new GroupMember().setUserId(userId).setGroupId(groupId);
        groupMembersRepository.save(newMember);
        return;
    }

    public Iterable<GroupMember> getGroupMembersListing(UUID groupId) {
        return groupMembersRepository.findAllMembersByGroupId(groupId);
    }
    public GroupMember getGroupMemberDetails(UUID groupId,UUID userId) {
        GroupMember memberDetails = groupMembersRepository.findMemberByGroupIdAndUserId(groupId,userId).orElse(null);
        if(memberDetails == null){
            throw new RuntimeException("User Exist By mobile number");
        }
        return memberDetails;
    }
}
