package asm2;

import java.util.HashMap;

public abstract class Product {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private TaxType taxType;
    private static HashMap<String, Product> allProduct = new HashMap<>();
    private  boolean canBeGift = false;
    public static void setAllProduct(HashMap<String, Product> allProduct) {
        Product.allProduct = allProduct;
    }

    public Product(String name, String description, int quantity, double price, TaxType taxType,boolean canBeGift) {
        if(!allProduct.containsKey(name)){
            this.canBeGift = canBeGift;
            this.taxType = taxType;
            this.name = name;
            this.description = description;
            this.quantity = quantity;
            this.price = price;
            allProduct.put(name, this);
        }else
        System.out.println("Product existed");
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
    public void setCanBeGift(boolean canBeGift) {
        this.canBeGift = canBeGift;
    }
    public boolean canBeGift(){
        return canBeGift;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " - " + name;
    }

}
