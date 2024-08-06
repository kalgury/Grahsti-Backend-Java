package com.example.grahstibackend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Page<Expense> findAllByGroupId(UUID groupId, Pageable pageable);
}
