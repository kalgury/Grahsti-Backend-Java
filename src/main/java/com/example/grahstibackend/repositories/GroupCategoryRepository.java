package com.example.grahstibackend.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.GroupCategory;

@Repository
public interface GroupCategoryRepository extends CrudRepository<GroupCategory, UUID> {
    Iterable<GroupCategory> findAllByGroupId(UUID groupId);
}
