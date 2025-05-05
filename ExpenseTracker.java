import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class for the Personal Expense Tracker using BST.
 * Provides console interface for CRUD operations with input validation and error handling.
 */
public class ExpenseTracker {
    private ExpenseBST expenseBST;
    private Scanner scanner;

    public ExpenseTracker() {
        expenseBST = new ExpenseBST();
        scanner = new Scanner(System.in);
        loadExpensesFromFile();
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.run();
    }

    private void run() {
        System.out.println("Welcome to the Personal Expense Tracker!");
        String command;
        while (true) {
            System.out.println("\nEnter a command (add, remove, update, display, exit):");
            command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "add":
                    addExpense();
                    break;
                case "remove":
                    removeExpense();
                    break;
                case "update":
                    updateExpense();
                    break;
                case "display":
                    displayExpenses();
                    break;
                case "exit":
                    saveExpensesToFile();
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void addExpense() {
        try {
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }
            System.out.print("Enter category: ");
            String category = scanner.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("Category cannot be empty.");
                return;
            }
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine().trim();
            if (!isValidDate(date)) {
                System.out.println("Invalid date format.");
                return;
            }
            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Description cannot be empty.");
                return;
            }
            Expense expense = new Expense(amount, category, date, description);
            expenseBST.insert(expense);
            System.out.println("Expense added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    private void removeExpense() {
        System.out.print("Enter description of the expense to remove: ");
        String description = scanner.nextLine().trim();
        Expense expense = expenseBST.search(description);
        if (expense == null) {
            System.out.println("Expense not found.");
        } else {
            expenseBST.delete(description);
            System.out.println("Expense removed successfully.");
        }
    }

    private void updateExpense() {
        System.out.print("Enter description of the expense to update: ");
        String description = scanner.nextLine().trim();
        Expense expense = expenseBST.search(description);
        if (expense == null) {
            System.out.println("Expense not found.");
            return;
        }
        try {
            System.out.print("Enter new amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }
            System.out.print("Enter new category: ");
            String category = scanner.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("Category cannot be empty.");
                return;
            }
            System.out.print("Enter new date (YYYY-MM-DD): ");
            String date = scanner.nextLine().trim();
            if (!isValidDate(date)) {
                System.out.println("Invalid date format.");
                return;
            }
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine().trim();
            if (newDescription.isEmpty()) {
                System.out.println("Description cannot be empty.");
                return;
            }
            // Remove old expense and insert updated expense
            expenseBST.delete(description);
            Expense updatedExpense = new Expense(amount, category, date, newDescription);
            expenseBST.insert(updatedExpense);
            System.out.println("Expense updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    private void displayExpenses() {
        System.out.println("Expenses:");
        expenseBST.inorderDisplay();
    }

    private boolean isValidDate(String date) {
        // Simple regex to validate date format YYYY-MM-DD
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private void saveExpensesToFile() {
        List<Expense> expenses = new ArrayList<>();
        collectExpenses(expenseBST, expenses);
        Persistence.saveExpenses(expenses);
    }

    private void collectExpenses(ExpenseBST bst, List<Expense> expenses) {
        collectExpensesRec(expenseBST.root, expenses);
    }

    private void collectExpensesRec(ExpenseNode node, List<Expense> expenses) {
        if (node != null) {
            collectExpensesRec(node.left, expenses);
            expenses.add(node.expense);
            collectExpensesRec(node.right, expenses);
        }
    }

    private void loadExpensesFromFile() {
        List<Expense> expenses = Persistence.loadExpenses();
        for (Expense expense : expenses) {
            expenseBST.insert(expense);
        }
    }
}
