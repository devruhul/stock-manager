package phonestockmanager;

import javax.swing.*;
import java.io.*;

public class PhoneStockManager {

    public static void main(String[] args) {
        // File path to store phone details
        String FILE_PATH = "phone_stock.txt";

        // Create an array of Phone objects with 100 elements
        Phone[] phoneStock = new Phone[100];

        // Load phone details from file (if exists)
        loadPhoneDetailsFromFile(phoneStock, FILE_PATH);

        // Menu loop
        int choice;
        do {

            // Parse user input
            choice = Menu.showMenu(); // Show the menu and get user choice

            switch (choice) {
                case 0:
                    addNewPhone(phoneStock); // Add a new phone
                    break;
                case 1:
                    printAllPhones(phoneStock); // Print all phones
                    break;
                case 2:
                    searchForPhone(phoneStock); // Search for a phone
                    break;
                case 3:
                    sortPhoneStockByPrice(phoneStock); // Sort phone stock by price
                    JOptionPane.showMessageDialog(null, "Phone stock sorted by price.");
                    break;
                case 4:
                    updatePhoneDetails(phoneStock); // Update phone details
                    break;
                case 5:
                    savePhoneDetailsToFile(phoneStock, FILE_PATH); // Save phone details to file
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Exiting the program.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid option.");
            }
        } while (choice != 6);
    }

    // Method to add a new phone to the array
    public static void addNewPhone(Phone[] phoneStock) {
        // Input data for the new phone
        String brand = JOptionPane.showInputDialog("Enter the brand of the phone:");
        String model = JOptionPane.showInputDialog("Enter the model of the phone:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the phone:"));
        int stockQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the stock quantity:"));

        // Find the first available index in the array
        int index = findNextAvailableIndex(phoneStock);

        // Create and add the new phone object to the array
        phoneStock[index] = new Phone(brand, model, price, stockQuantity);

        JOptionPane.showMessageDialog(null, "Phone added successfully.");
    }

    // Method to print details of all phones in the array
    public static void printAllPhones(Phone[] phoneStock) {
        // Check if the array is empty
        boolean isEmpty = true;
        for (Phone phone : phoneStock) {
            if (phone != null) {
                isEmpty = false;
                break;
            }
        }

        if (isEmpty) {
            JOptionPane.showMessageDialog(null, "No phones in the stock.");
            return;
        }

        // Construct a message with details of all phones
        StringBuilder message = new StringBuilder("Phone Stock:\n");
        for (Phone phone : phoneStock) {
            if (phone != null) {
                message.append("\nBrand: ").append(phone.getBrand())
                        .append(" Model: ").append(phone.getModel())
                        .append(" Price: $").append(phone.getPrice())
                        .append(" Stock Quantity: ").append(phone.getStockQuantity())
                        .append("\n");
            }
        }
        // Display the scrollable JTextArea
        JOptionPane.showMessageDialog(null, message.toString());
    }

    // Method to search for a phone by brand
    public static void searchForPhone(Phone[] phoneStock) {
        // Input data for searching
        String brandToSearch = JOptionPane.showInputDialog("Enter the brand of the phone to search:");

        // Search for phones with the specified brand in the array
        boolean found = false;
        StringBuilder message = new StringBuilder("Phones found with brand '" + brandToSearch + "':\n");
        for (Phone phone : phoneStock) {
            if (phone != null && phone.getBrand().equalsIgnoreCase(brandToSearch)) {
                found = true;
                message.append("\n").append("Model: ").append(phone.getModel())
                        .append(", Price: $").append(phone.getPrice())
                        .append(", Stock Quantity: ").append(phone.getStockQuantity());
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, message.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No phones found with brand '" + brandToSearch + "'.");
        }
    }

    // Method to find the next available index in the array
    public static int findNextAvailableIndex(Phone[] phoneStock) {
        for (int i = 0; i < phoneStock.length; i++) {
            if (phoneStock[i] == null) {
                return i;
            }
        }
        return -1; // No available index found
    }

    // Method to load phone details from a file
    public static void loadPhoneDetailsFromFile(Phone[] phoneStock, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String brand = parts[0];
                String model = parts[1];
                double price = Double.parseDouble(parts[2]);
                int stockQuantity = Integer.parseInt(parts[3]);
                phoneStock[index++] = new Phone(brand, model, price, stockQuantity);
            }
        } catch (IOException e) {
            // Ignore if the file doesn't exist or cannot be read
        }
    }

    // Method to save phone details to a file
    public static void savePhoneDetailsToFile(Phone[] phoneStock, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Phone phone : phoneStock) {
                if (phone != null) {
                    // Write phone details to the file
                    writer.println(phone.getBrand() + "," + phone.getModel() + ","
                            + phone.getPrice() + "," + phone.getStockQuantity());
                }
            }
            JOptionPane.showMessageDialog(null, "Phone details saved to file successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while saving phone details to file.");
            e.printStackTrace();
        }
    }

    // Method to sort phone stock by price using Bubble Sort
    public static void sortPhoneStockByPrice(Phone[] phoneStock) {
        int n = phoneStock.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (phoneStock[j] != null && phoneStock[j + 1] != null
                        && phoneStock[j].getPrice() > phoneStock[j + 1].getPrice()) {
                    // Swap phone objects
                    Phone temp = phoneStock[j];
                    phoneStock[j] = phoneStock[j + 1];
                    phoneStock[j + 1] = temp;
                }
            }
        }
    }

    // Method to update phone details
    public static void updatePhoneDetails(Phone[] phoneStock) {
        // Input data for searching
        String brandToSearch = JOptionPane.showInputDialog("Enter the brand of the phone to update:");

        // Search for phones with the specified brand in the array
        boolean found = false;
        for (Phone phone : phoneStock) {
            if (phone != null && phone.getBrand().equalsIgnoreCase(brandToSearch)) {
                found = true;

                // Display current details of the phone
                JOptionPane.showMessageDialog(null, "Current details:\n"
                        + "Brand: " + phone.getBrand() + "\n"
                        + "Model: " + phone.getModel() + "\n"
                        + "Price: " + phone.getPrice() + "\n"
                        + "Stock Quantity: " + phone.getStockQuantity());

                // Input updated details
                String model = JOptionPane.showInputDialog("Enter the updated model:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the updated price:"));
                int stockQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the updated stock quantity:"));

                // Update phone details
                phone.setModel(model);
                phone.setPrice(price);
                phone.setStockQuantity(stockQuantity);

                JOptionPane.showMessageDialog(null, "Phone details updated successfully.");
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No phones found with brand '" + brandToSearch + "'.");
        }
    }
}
