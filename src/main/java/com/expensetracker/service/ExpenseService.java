package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business logic service for expense management
 * Includes caching and analytics capabilities
 */
@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    /**
     * Create a new expense
     */
    @CacheEvict(value = "userExpenses", key = "#userId")
    public Expense createExpense(String userId, ExpenseRequest request) {
        Expense expense = new Expense(
            userId,
            request.getAmount(),
            request.getCategory(),
            request.getDescription()
        );
        
        return expenseRepository.save(expense);
    }
    
    /**
     * Get all expenses for a user with pagination
     */
    @Cacheable(value = "userExpenses", key = "#userId + '-' + #page + '-' + #size")
    public List<Expense> getUserExpenses(String userId, int page, int size) {
        return expenseRepository.findByUserIdAndStatus(userId, "ACTIVE", page, size);
    }
    
    /**
     * Get a specific expense by ID
     */
    @Cacheable(value = "expense", key = "#userId + '-' + #expenseId")
    public Expense getExpense(String userId, String expenseId) {
        return expenseRepository.findByUserIdAndExpenseId(userId, expenseId);
    }
    
    /**
     * Update an existing expense
     */
    @CacheEvict(value = {"expense", "userExpenses"}, key = "#userId")
    public Expense updateExpense(String userId, String expenseId, ExpenseRequest request) {
        Expense expense = expenseRepository.findByUserIdAndExpenseId(userId, expenseId);
        
        if (expense != null && "ACTIVE".equals(expense.getStatus())) {
            expense.setAmount(request.getAmount());
            expense.setCategory(request.getCategory());
            expense.setDescription(request.getDescription());
            expense.setUpdatedDate(LocalDateTime.now());
            
            return expenseRepository.save(expense);
        }
        
        return null;
    }
    
    /**
     * Soft delete an expense
     */
    @CacheEvict(value = {"expense", "userExpenses"}, key = "#userId")
    public boolean deleteExpense(String userId, String expenseId) {
        Expense expense = expenseRepository.findByUserIdAndExpenseId(userId, expenseId);
        
        if (expense != null && "ACTIVE".equals(expense.getStatus())) {
            expense.setStatus("DELETED");
            expense.setUpdatedDate(LocalDateTime.now());
            expenseRepository.save(expense);
            return true;
        }
        
        return false;
    }
    
    /**
     * Get expense analytics for a user
     */
    @Cacheable(value = "analytics", key = "#userId + '-' + #period")
    public Map<String, Object> getExpenseAnalytics(String userId, String period) {
        List<Expense> expenses = expenseRepository.findByUserIdAndStatus(userId, "ACTIVE", 0, 1000);
        
        // Total amount
        double totalAmount = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        
        // Category breakdown
        Map<String, Double> categoryBreakdown = expenses.stream()
                .collect(Collectors.groupingBy(
                    Expense::getCategory,
                    Collectors.summingDouble(Expense::getAmount)
                ));
        
        // Monthly trend (simplified)
        Map<String, Double> monthlyTrend = expenses.stream()
                .collect(Collectors.groupingBy(
                    expense -> expense.getCreatedDate().getMonth().toString(),
                    Collectors.summingDouble(Expense::getAmount)
                ));
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalAmount", totalAmount);
        analytics.put("totalExpenses", expenses.size());
        analytics.put("categoryBreakdown", categoryBreakdown);
        analytics.put("monthlyTrend", monthlyTrend);
        analytics.put("averageExpense", expenses.isEmpty() ? 0 : totalAmount / expenses.size());
        
        return analytics;
    }
    
    /**
     * Get expenses by category
     */
    public List<Expense> getExpensesByCategory(String userId, String category) {
        return expenseRepository.findByUserIdAndCategoryAndStatus(userId, category, "ACTIVE");
    }
    
    /**
     * Get expenses within date range
     */
    public List<Expense> getExpensesByDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return expenseRepository.findByUserIdAndDateRange(userId, startDate, endDate);
    }
}
