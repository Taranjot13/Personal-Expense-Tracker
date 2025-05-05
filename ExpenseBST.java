/**
 * Binary Search Tree implementation for managing Expense objects.
 */
public class ExpenseBST {
    ExpenseNode root;

    public ExpenseBST() {
        this.root = null;
    }

    public void insert(Expense expense) {
        root = insertRec(root, expense);
    }

    private ExpenseNode insertRec(ExpenseNode root, Expense expense) {
        if (root == null) {
            root = new ExpenseNode(expense);
            return root;
        }
        int cmp = expense.getDescription().compareToIgnoreCase(root.expense.getDescription());
        if (cmp < 0) {
            root.left = insertRec(root.left, expense);
        } else if (cmp > 0) {
            root.right = insertRec(root.right, expense);
        } else {
            root.expense = expense;
        }
        return root;
    }

    public Expense search(String description) {
        ExpenseNode node = searchRec(root, description);
        return (node != null) ? node.expense : null;
    }

    private ExpenseNode searchRec(ExpenseNode root, String description) {
        if (root == null || description.equalsIgnoreCase(root.expense.getDescription())) {
            return root;
        }
        int cmp = description.compareToIgnoreCase(root.expense.getDescription());
        if (cmp < 0) {
            return searchRec(root.left, description);
        } else {
            return searchRec(root.right, description);
        }
    }

    public void delete(String description) {
        root = deleteRec(root, description);
    }

    private ExpenseNode deleteRec(ExpenseNode root, String description) {
        if (root == null) {
            return root;
        }
        int cmp = description.compareToIgnoreCase(root.expense.getDescription());
        if (cmp < 0) {
            root.left = deleteRec(root.left, description);
        } else if (cmp > 0) {
            root.right = deleteRec(root.right, description);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.expense = minValue(root.right);
            root.right = deleteRec(root.right, root.expense.getDescription());
        }
        return root;
    }

    private Expense minValue(ExpenseNode root) {
        Expense minv = root.expense;
        while (root.left != null) {
            root = root.left;
            minv = root.expense;
        }
        return minv;
    }
    public void inorderDisplay() {
        inorderRec(root);
    }

    private void inorderRec(ExpenseNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.expense);
            inorderRec(root.right);
        }
    }
}
