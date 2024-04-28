package com.example.grahstibackend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, UUID> {
    // Optional<Group[]> findByUserId(UUID id);
}