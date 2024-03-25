// Define the package to which this class belongs
package phonestockmanager;

// Import JOptionPane class for displaying GUI-based menu
import javax.swing.JOptionPane;

// Define the Menu class
public class Menu {

  // Method to display the menu and get user's choice
  public static int showMenu() {
    // Define menu options as an array of strings
    String[] options = {
        "Add a new phone", // Option to add a new phone
        "Print all phones", // Option to print all phones
        "Search for a phone", // Option to search for a phone
        "Sort phones by price", // Option to sort phones by price
        "Update phone details", // Option to update phone details
        "Save phone details to file", // Option to save phone details to file
        "Exit" // Option to exit the program
    };

    // Display the menu using JOptionPane and get user's choice
    return JOptionPane.showOptionDialog(null,
        "Phone Stock Management System", // Title of the menu
        "Menu", // Title of the window
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        null,
        options, // Menu options
        options[0]); // Default selected option
  }
}
