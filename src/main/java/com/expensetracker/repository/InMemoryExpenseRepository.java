package com.expensetracker.repository;

import com.expensetracker.model.Expense;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-Memory repository implementation for local development
 * No AWS dependencies required
 */
@Repository
@Profile("local")
public class InMemoryExpenseRepository implements ExpenseRepository {
    
    private final Map<String, Map<String, Expense>> userExpenses = new ConcurrentHashMap<>();
    
    @Override
    public Expense save(Expense expense) {
        String userId = expense.getUserId();
        userExpenses.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
        userExpenses.get(userId).put(expense.getExpenseId(), expense);
        return expense;
    }
    
    @Override
    public Expense findByUserIdAndExpenseId(String userId, String expenseId) {
        Map<String, Expense> expenses = userExpenses.get(userId);
        return expenses != null ? expenses.get(expenseId) : null;
    }
    
    @Override
    public List<Expense> findByUserIdAndStatus(String userId, String status, int page, int size) {
        Map<String, Expense> expenses = userExpenses.get(userId);
        if (expenses == null) {
            return new ArrayList<>();
        }
        
        return expenses.values().stream()
                .filter(expense -> status.equals(expense.getStatus()))
                .sorted((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate()))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Expense> findByUserIdAndCategoryAndStatus(String userId, String category, String status) {
        Map<String, Expense> expenses = userExpenses.get(userId);
        if (expenses == null) {
            return new ArrayList<>();
        }
        
        return expenses.values().stream()
                .filter(expense -> category.equals(expense.getCategory()) && status.equals(expense.getStatus()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Expense> findByUserIdAndDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, Expense> expenses = userExpenses.get(userId);
        if (expenses == null) {
            return new ArrayList<>();
        }
        
        return expenses.values().stream()
                .filter(expense -> {
                    LocalDateTime createdDate = expense.getCreatedDate();
                    return createdDate.isAfter(startDate) && createdDate.isBefore(endDate);
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean delete(String userId, String expenseId) {
        Map<String, Expense> expenses = userExpenses.get(userId);
        if (expenses != null) {
            return expenses.remove(expenseId) != null;
        }
        return false;
    }
}
