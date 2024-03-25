// Define the package to which this class belongs
package phonestockmanager;

// Define the Phone class
public class Phone {

    // Declare private instance variables to store phone details
    private String brand; // Brand of the phone
    private String model; // Model of the phone
    private double price; // Price of the phone
    private int stockQuantity; // Stock quantity of the phone

    // Constructor to initialize the Phone object with given details
    public Phone(String brand, String model, double price, int stockQuantity) {
        this.brand = brand; // Initialize brand
        this.model = model; // Initialize model
        this.price = price; // Initialize price
        this.stockQuantity = stockQuantity; // Initialize stock quantity
    }

    // Getter method to retrieve the brand of the phone
    public String getBrand() {
        return brand;
    }

    // Getter method to retrieve the model of the phone
    public String getModel() {
        return model;
    }

    // Setter method to set the model of the phone
    public void setModel(String model) {
        this.model = model;
    }

    // Getter method to retrieve the price of the phone
    public double getPrice() {
        return price;
    }

    // Getter method to retrieve the stock quantity of the phone
    public int getStockQuantity() {
        return stockQuantity;
    }

    // Setter method to set the price of the phone
    public void setPrice(double price) {
        this.price = price;
    }

    // Setter method to set the stock quantity of the phone
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
