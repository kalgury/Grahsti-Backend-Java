package com.example.grahstibackend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.GroupMember;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMember, UUID> {
    Optional<GroupMember> findMemberByGroupIdAndUserId(UUID groupId, UUID userId);
    Iterable<GroupMember> findAllMembersByGroupId(UUID groupId);
    Iterable<GroupMember> findUsersGroupMembershipByUserId(UUID userId);
}