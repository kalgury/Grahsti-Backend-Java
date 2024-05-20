package com.example.grahstibackend.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.grahstibackend.entities.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, UUID> {
    Iterable<Expense> findAllByGroupId(UUID groupId);
}
