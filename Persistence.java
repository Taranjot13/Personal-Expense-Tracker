import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistence class to save and load expenses to/from a file.
 */
public class Persistence {
    private static final String FILE_NAME = "expenses.dat";

    public static void saveExpenses(List<Expense> expenses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(expenses);
            System.out.println("Expenses saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            expenses = (List<Expense>) ois.readObject();
            System.out.println("Expenses loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved expenses found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}
