/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

import java.util.HashMap;
public abstract class Product implements GiftProduct {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String message = null;
    private TaxType taxType;
    private boolean isGift = false;
    private static HashMap<String,Product> allProduct = new HashMap<>();
    public Product(String name, String description, int quantity, double price,TaxType taxType) {

        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.taxType = taxType;
        allProduct.put(name,this);
    }
    public Product(String name, String description, int quantity, double price,TaxType taxType,boolean isGift) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.taxType = taxType;
        this.isGift = isGift;
        allProduct.put(name,this);
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

    public static HashMap<String,Product> getAllProduct() {
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
    @Override
    public boolean isGift(){
        return this.isGift;
    }
    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
