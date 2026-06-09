import java.util.*;
import java.io.*;

public class Document {
    private StringBuffer textBuffer;

    public Document() {
        textBuffer = new StringBuffer();
    }

    // Add text from user
    public void addText() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter text to add: ");
        String str = in.nextLine();
        textBuffer.append(str);
    }

    // Clear all text
    public void clearText() {
        textBuffer.setLength(0);
    }

    // Reverse text
    public void reverseText() {
        textBuffer.reverse();
    }

    // Delete portion of text
    public void deleteText() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter start and end index to delete: ");
        int a = in.nextInt();
        int b = in.nextInt();

        if (a >= 0 && a < textBuffer.length() && a < b && b <= textBuffer.length()) {
            textBuffer.delete(a, b);
            System.out.println("Text deleted successfully.");
        } else {
            System.out.println("Error: Index out of bounds!");
        }
    }

    // Display current text
    public void displayText() {
        System.out.println("Current Content: " + textBuffer);
    }

    // ====================== FILE HANDLING METHODS ======================

    /**
     * Save current text to a file
     */
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(textBuffer.toString());
            System.out.println("Text saved successfully to: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Load text from a file
     */
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            textBuffer.setLength(0); // Clear current content
            String line;
            while ((line = reader.readLine()) != null) {
                textBuffer.append(line).append("\n");
            }
            System.out.println(" Text loaded successfully from: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Main method - Demonstration
    public static void main(String[] args) {
        Document doc = new Document();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simple Text Editor with File Handling ===\n");

        // Add text
        doc.addText();
        doc.displayText();

        // Reverse
        System.out.println("\nReversing text...");
        doc.reverseText();
        doc.displayText();

        // Save to file
        System.out.print("\nEnter filename to save (e.g., mytext.txt): ");
        String saveFile = sc.nextLine();
        doc.saveToFile(saveFile);

        // Clear and load from file
        System.out.println("\nClearing current text...");
        doc.clearText();
        doc.displayText();

        System.out.println("Loading from file...");
        doc.loadFromFile(saveFile);
        doc.displayText();

        // Optional: Delete operation
        System.out.print("\nDo you want to delete some text? (y/n): ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            doc.deleteText();
            doc.displayText();
        }
        System.out.print("\nEnter filename to save (e.g., mytext.txt): ");
        saveFile = sc.nextLine();
        doc.saveToFile(saveFile);

        System.out.println("Loading from file...");
        doc.loadFromFile(saveFile);
        doc.displayText();
        sc.close();
        System.out.println("\n=== Program Ended ===");
    }
}