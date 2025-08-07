package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST API Controller for Expense Management
 * 
 * Provides RESTful endpoints for CRUD operations
 * Includes error handling and validation
 */
@RestController
@RequestMapping("/api/v1/expenses")
@CrossOrigin(origins = "*") // For frontend integration
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    /**
     * Create a new expense
     */
    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody ExpenseRequest request,
            @RequestHeader("User-Id") String userId) {
        
        try {
            Expense expense = expenseService.createExpense(userId, request);
            ExpenseResponse response = new ExpenseResponse(expense);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get all expenses for a user
     */
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getUserExpenses(
            @RequestHeader("User-Id") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        try {
            List<Expense> expenses = expenseService.getUserExpenses(userId, page, size);
            List<ExpenseResponse> responses = expenses.stream()
                    .map(ExpenseResponse::new)
                    .toList();
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get expense by ID
     */
    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpense(
            @RequestHeader("User-Id") String userId,
            @PathVariable String expenseId) {
        
        try {
            Expense expense = expenseService.getExpense(userId, expenseId);
            if (expense != null) {
                return ResponseEntity.ok(new ExpenseResponse(expense));
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Update an existing expense
     */
    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @RequestHeader("User-Id") String userId,
            @PathVariable String expenseId,
            @Valid @RequestBody ExpenseRequest request) {
        
        try {
            Expense expense = expenseService.updateExpense(userId, expenseId, request);
            if (expense != null) {
                return ResponseEntity.ok(new ExpenseResponse(expense));
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Delete an expense (soft delete)
     */
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(
            @RequestHeader("User-Id") String userId,
            @PathVariable String expenseId) {
        
        try {
            boolean deleted = expenseService.deleteExpense(userId, expenseId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get expense analytics for a user
     */
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getExpenseAnalytics(
            @RequestHeader("User-Id") String userId,
            @RequestParam(required = false) String period) {
        
        try {
            Map<String, Object> analytics = expenseService.getExpenseAnalytics(userId, period);
            return ResponseEntity.ok(analytics);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "expense-tracker-api",
            "timestamp", java.time.Instant.now().toString()
        ));
    }
}
