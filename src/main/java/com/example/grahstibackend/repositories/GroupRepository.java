package com.example.grahstibackend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    Iterable<Group> findAllGroupsByIdIn(Iterable<UUID> ids, Sort sort);
}