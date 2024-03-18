package phonestockmanager;


import javax.swing.JOptionPane;
import java.io.*;

public class PhoneStockManager {

    private final String FILE_PATH = "phone_stock.txt";
    private int numPhones; // Current number of phones

    public PhoneStockManager() {
        numPhones = 0;
        loadData(new Phone[100]); // Load data from file when the system is initialized
    }

    // Main method to run the program
    public static void main(String[] args) {
        PhoneStockManager manager = new PhoneStockManager();
        Phone[] phones = new Phone[100]; // Array to hold phone objects

        int choice; // Variable to store user's choice

        do {
            choice = Menu.showMenu(); // Show menu and get user's choice

            switch (choice) {
                case 0:
                    // Add a new phone
                    String brand = JOptionPane.showInputDialog("Enter phone brand:");
                    String model = JOptionPane.showInputDialog("Enter phone model:");
                    double price = Double.parseDouble(JOptionPane.showInputDialog("Enter phone price:"));
                    int stockQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter phone stock quantity:"));
                    manager.addPhone(phones, brand, model, price, stockQuantity);
                    break;
                case 1:
                    // Print all phones
                    manager.printAllPhones(phones);
                    break;
                case 2:
                    // Search for a phone
                    String searchBrand = JOptionPane.showInputDialog("Enter phone brand to search:");
                    manager.searchPhone(phones, searchBrand);
                    break;
                case 3:
                    // Update phone details
                    manager.updatePhoneDetails(phones);
                    break;
                case 4:
                    // Sort phones by price
                    manager.sortPhonesByPrice(phones);
                    break;
                case 5:
                    // Save phone details to file
                    manager.savePhonesToFile(phones);
                    break;
                case 6:
                    // Exit
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please select again.");
            }
        } while (choice != 6); // Repeat the loop until the user chooses to exit
    }

    // Method to add a new phone to the system
    public void addPhone(Phone[] phones, String brand, String model, double price, int stockQuantity) {
        if (numPhones < 100) {
            phones[numPhones] = new Phone(brand, model, price, stockQuantity);
            numPhones++;
            saveData(phones); // Save phone data to file
            JOptionPane.showMessageDialog(null, "Phone added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Phone stock is full.");
        }
    }

    // Method to save phone data to file
    private void saveData(Phone[] phones) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < numPhones; i++) {
                writer.println(phones[i].getBrand() + "," + phones[i].getModel() + "," + phones[i].getPrice() + ","
                        + phones[i].getStockQuantity());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Failed to save data to file.");
        }
    }

    // Method to load phone data from file
    private void loadData(Phone[] phones) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (numPhones < 100) {
                    phones[numPhones] = new Phone(parts[0], parts[1], Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]));
                    numPhones++;
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Failed to load data from file.");
        }
    }

    // Method to print details of all phones
    public void printAllPhones(Phone[] phones) {
        if (numPhones == 0) {
            JOptionPane.showMessageDialog(null, "No phones to display.");
            return;
        }
        StringBuilder message = new StringBuilder("List of Phones:\n");
        for (int i = 0; i < numPhones; i++) {
            // Append phone details to the message
            message.append("Brand: ").append(phones[i].getBrand()).append(", Model: ").append(phones[i].getModel())
                    .append(", Price: $").append(phones[i].getPrice()).append(", Stock Quantity: ")
                    .append(phones[i].getStockQuantity()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    // Method to search for a phone by brand
    public void searchPhone(Phone[] phones, String brand) {
        StringBuilder message = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < numPhones; i++) {
            if (phones[i].getBrand().equalsIgnoreCase(brand)) {
                // Append phone details to the message if found
                message.append("Phone found:\n");
                message.append("Brand: ").append(phones[i].getBrand()).append(", Model: ").append(phones[i].getModel())
                        .append(", Price: $").append(phones[i].getPrice()).append(", Stock Quantity: ")
                        .append(phones[i].getStockQuantity()).append("\n");
                found = true;
                break;
            }
        }
        if (!found) {
            message.append("Phone with brand ").append(brand).append(" not found.");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    // Method to update details of a phone
    private void updatePhoneDetails(Phone[] phones) {
        String brand = JOptionPane.showInputDialog("Enter the brand of the phone to update details:");
        if (brand == null) {
            return; // User canceled
        }

        boolean found = false;
        double price = 0.0;
        int stockQuantity = 0;

        for (int i = 0; i < numPhones; i++) {
            if (phones[i].getBrand().equalsIgnoreCase(brand)) {
                found = true;
                try {
                    price = Double.parseDouble(JOptionPane.showInputDialog("Enter the new price:"));
                    stockQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the new stock quantity:"));
                    if (stockQuantity < 0 || price < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid price or quantity! Please enter positive values.");
                        return;
                    }
                    // Update phone details
                    phones[i].setPrice(price);
                    phones[i].setStockQuantity(stockQuantity);
                    JOptionPane.showMessageDialog(null, "Phone details updated successfully!");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid price or quantity format!");
                }
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "No phone found with brand: " + brand, "Update Phone Details",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Method to sort phones by price
    public void sortPhonesByPrice(Phone[] phones) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < numPhones - 1; i++) {
                if (phones[i].getPrice() > phones[i + 1].getPrice()) {
                    // Swap phones if they are not in ascending order of price
                    Phone temp = phones[i];
                    phones[i] = phones[i + 1];
                    phones[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Phones sorted by price.");
    }

    // Method to save phone details to file
    public void savePhonesToFile(Phone[] phones) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < numPhones; i++) {
                // Write phone details to the file
                writer.write(phones[i].getBrand() + "," + phones[i].getModel() + "," + phones[i].getPrice() + ","
                        + phones[i].getStockQuantity());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Phone details saved to file.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving phone details to file.");
        }
    }
}
