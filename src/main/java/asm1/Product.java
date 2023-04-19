/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

import java.util.HashMap;

public abstract class Product {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String message = null;
    private TaxType taxType;
    private boolean isGift = false;
    private static HashMap<String, Product> allProduct = new HashMap<>();

    public static void setAllProduct(HashMap<String, Product> allProduct) {
        Product.allProduct = allProduct;
    }

    public Product(String name, String description, int quantity, double price, TaxType taxType) {
        this.taxType = taxType;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        allProduct.put(name, this);
    }

    public Product(String name, String description, int quantity, double price, TaxType taxType, boolean isGift) {
        this.taxType = taxType;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.isGift = isGift;
        allProduct.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public static HashMap<String, Product> getAllProduct() {
        return allProduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " - " + name;
    }

    public boolean isGift() {
        return this.isGift;
    }

    public void setMessage(String msg) {
        if (isGift)
            this.message = msg;
    }

    public String getMessage() {
        if (isGift)
            return this.message;
        return "";
    }
}
