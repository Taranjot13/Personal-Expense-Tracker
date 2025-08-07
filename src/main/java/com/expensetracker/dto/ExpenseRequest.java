package com.expensetracker.dto;

import jakarta.validation.constraints.*;

/**
 * Request DTO for creating/updating expenses
 * Includes validation annotations for API security
 */
public class ExpenseRequest {
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Amount cannot exceed 999,999.99")
    private Double amount;
    
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;
    
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
    
    // Default constructor
    public ExpenseRequest() {}
    
    // Constructor with parameters
    public ExpenseRequest(Double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "ExpenseRequest{" +
                "amount=" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
