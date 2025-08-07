package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateExpense() throws Exception {
        ExpenseRequest request = new ExpenseRequest(25.50, "Food", "Lunch");
        Expense expense = new Expense("user123", 25.50, "Food", "Lunch");
        
        when(expenseService.createExpense(anyString(), any(ExpenseRequest.class)))
                .thenReturn(expense);

        mockMvc.perform(post("/api/v1/expenses")
                .header("User-Id", "user123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(25.50))
                .andExpect(jsonPath("$.category").value("Food"));
    }

    @Test
    public void testGetUserExpenses() throws Exception {
        List<Expense> expenses = Arrays.asList(
                new Expense("user123", 25.50, "Food", "Lunch"),
                new Expense("user123", 15.00, "Transport", "Bus fare")
        );
        
        when(expenseService.getUserExpenses(anyString(), anyInt(), anyInt()))
                .thenReturn(expenses);

        mockMvc.perform(get("/api/v1/expenses")
                .header("User-Id", "user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].category").value("Food"))
                .andExpect(jsonPath("$[1].category").value("Transport"));
    }

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/v1/expenses/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("expense-tracker-api"));
    }
}
