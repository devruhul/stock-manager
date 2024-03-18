package phonestockmanager;

class Phone {
    private final String brand;
    private final String model;
    private double price;
    private int stockQuantity;

    public Phone(String brand, String model, double price, int stockQuantity) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
