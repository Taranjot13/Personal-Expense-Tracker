package com.expensetracker.exception;

/**
 * Custom exception for expense-related business logic errors
 */
public class ExpenseNotFoundException extends RuntimeException {
    
    public ExpenseNotFoundException(String message) {
        super(message);
    }
    
    public ExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
