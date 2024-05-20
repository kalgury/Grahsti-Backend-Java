package com.example.grahstibackend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.GroupMember;

@Repository
public interface GroupMembersRepository extends CrudRepository<GroupMember, UUID> {
    Optional<GroupMember> findMemberByGroupIdAndUserId(UUID groupId, UUID userId);
    Iterable<GroupMember> findAllMembersByGroupId(UUID groupId);
}