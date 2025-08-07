package com.expensetracker.repository;

import com.expensetracker.model.Expense;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for expense data operations
 * Abstraction layer for data persistence
 */
public interface ExpenseRepository {
    
    /**
     * Save an expense to the database
     */
    Expense save(Expense expense);
    
    /**
     * Find expense by user ID and expense ID
     */
    Expense findByUserIdAndExpenseId(String userId, String expenseId);
    
    /**
     * Find all expenses for a user with specific status
     */
    List<Expense> findByUserIdAndStatus(String userId, String status, int page, int size);
    
    /**
     * Find expenses by user ID, category, and status
     */
    List<Expense> findByUserIdAndCategoryAndStatus(String userId, String category, String status);
    
    /**
     * Find expenses within a date range
     */
    List<Expense> findByUserIdAndDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Delete an expense (hard delete)
     */
    boolean delete(String userId, String expenseId);
}
