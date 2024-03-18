package phonestockmanager;

import javax.swing.JOptionPane;

public class Menu {
      public static int showMenu() {
        String[] options = { "Add a new phone", "Print all phones", "Search for a phone", "Update phone details",
                    "Sort phones by price", "Save phone details to file", "Exit" };
        return JOptionPane.showOptionDialog(null, "Menu", "Phone Stock Manager", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
}
