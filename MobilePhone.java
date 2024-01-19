
import java.io.Serializable;

enum MobileType implements Cloneable {
    Android,
    IOS,
    Windows
}

public class MobilePhone implements Cloneable, Serializable {

    private String model;
    private MobileType type;
    private int memorySize;
    private double price;

    public MobilePhone(String model, MobileType type, int memorySize, double price) {
        this.model = model;
        this.type = type;
        this.memorySize = memorySize;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public MobileType getType() {
        return type;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void print() {
        System.out.print(" Model: " + model + " Type: " + type + " MemorySize: " + memorySize + " Price: ");
        System.out.printf("%.2f", price);
    }

    public String toString() {
        return " Model:" + model + " Type: " + type + " MemorySize: " + memorySize + " Price: " + price;
    }

    public void priceRise(double rise) {
        price *= (1 + rise);
    }

    //---------------------------------------------Lab_04--------------------------------------------------
    public MobilePhone(MobilePhone mobilePhone) {
        this.model = mobilePhone.model;
        this.type = mobilePhone.type;
        this.memorySize = mobilePhone.memorySize;
        this.price = mobilePhone.price;
    }

    public MobilePhone clone() throws CloneNotSupportedException {
        return (MobilePhone) super.clone();
    }

    //----------------------------------------Lab-6-------------------------------------------------------
    public String toDelimitedString() {
        return model + "," + type + "," + memorySize + "," + price;
    }
}
