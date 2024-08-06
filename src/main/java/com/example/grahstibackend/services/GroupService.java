package com.example.grahstibackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.grahstibackend.dtos.CreateGroupDto;
import com.example.grahstibackend.dtos.GroupMembersList;
import com.example.grahstibackend.entities.Group;
import com.example.grahstibackend.entities.GroupMember;
import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.repositories.GroupMembersRepository;
import com.example.grahstibackend.repositories.GroupRepository;
import com.example.grahstibackend.repositories.UserRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMembersRepository groupMembersRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, GroupMembersRepository groupMembersRepository,
            UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMembersRepository = groupMembersRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Group> getGroupListing(UUID userId) {
        Iterable<GroupMember> userGroupMemberships = groupMembersRepository.findUsersGroupMembershipByUserId(userId);
        List<UUID> groupIds = new ArrayList<UUID>();
        for (GroupMember groupMember : userGroupMemberships) {
            groupIds.add(groupMember.getGroupId());
        }
        return groupRepository.findAllGroupsByIdIn(groupIds, Sort.by("createdAt").ascending());
    }

    public Group createNewGroup(CreateGroupDto body) {
        Group groupDoc = new Group()
                .setTitle(body.getTitle())
                .setTotalBudget(body.getTotalBudget())
                .setCreatedBy(body.getCreatedBy())
                .setType(body.getType());
        Group group = groupRepository.save(groupDoc);
        GroupMember adminGroupMemeber = new GroupMember()
                .setAdmin(true).setGroupId(group.getId()).setUserId(body.getCreatedBy());
        groupMembersRepository.save(adminGroupMemeber);
        return group;
    }

    public Group fetchGroupById(UUID groupId) {
        Group groupDetails = groupRepository.findById(groupId).orElseThrow();
        return groupDetails;
    }

    public void joinGroup(UUID groupId, UUID userId) {
        // check if already joined
        GroupMember hasAlredyJoined = groupMembersRepository.findMemberByGroupIdAndUserId(groupId, userId).orElse(null);
        if (hasAlredyJoined != null) {
            return;
        }
        GroupMember newMember = new GroupMember().setUserId(userId).setGroupId(groupId);
        groupMembersRepository.save(newMember);
        return;
    }

    public Iterable<GroupMembersList> getGroupMembersListing(UUID groupId) {
        Iterable<GroupMember> membersList = groupMembersRepository.findAllMembersByGroupId(groupId);

        return StreamSupport.stream(membersList.spliterator(), false)
                .map(member -> {
                    UUID userId = member.getUserId();
                    User user = userRepository.findById(userId).orElse(null);
                    String username = user.getFullName();
                    return new GroupMembersList(member, username);
                })
                .collect(Collectors.toList());
    }

    public GroupMember getGroupMemberDetails(UUID groupId, UUID userId) {
        GroupMember memberDetails = groupMembersRepository.findMemberByGroupIdAndUserId(groupId, userId).orElse(null);
        if (memberDetails == null) {
            throw new RuntimeException("User Exist By mobile number");
        }
        return memberDetails;
    }
}
