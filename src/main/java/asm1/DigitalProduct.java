package asm1;


public class DigitalProduct extends Product {
    public DigitalProduct(String name, String description, int quantity, double price) {
        super(name, description, quantity, price, taxType, canBeGift);
    }

    public String getType() {
        return "DIGITAL";
    }
}