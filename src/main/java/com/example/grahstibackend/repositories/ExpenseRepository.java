package com.example.grahstibackend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.Expense;
import com.example.grahstibackend.entities.enums.StatusEnums;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Page<Expense> findAllByGroupIdAndStatus(UUID groupId, StatusEnums status,Pageable pageable);
}
