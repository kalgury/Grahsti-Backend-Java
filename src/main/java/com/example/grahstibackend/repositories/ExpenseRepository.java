package com.example.grahstibackend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Iterable<Expense> findAllByGroupId(UUID groupId, Sort sort);
}
