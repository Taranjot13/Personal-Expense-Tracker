/**
 * Node class for the Expense Binary Search Tree.
 */
public class ExpenseNode {
    Expense expense;
    ExpenseNode left;
    ExpenseNode right;

    public ExpenseNode(Expense expense) {
        this.expense = expense;
        this.left = null;
        this.right = null;
    }
}
