package asm2;

import java.util.ArrayList;

public abstract class Coupon {
    private static ArrayList<Coupon> allCoupons = new ArrayList<>();
    private Product tiedProduct;
    private String stringValue;

    public static void setAllCoupons(ArrayList<Coupon> allCoupons) {
        Coupon.allCoupons = allCoupons;
    }
    public static ArrayList<Coupon> getAllCoupons() {
        return allCoupons;
    }
    public String getStringValue() {
        return stringValue;
    }
    public Coupon(Product tiedProduct, String stringValue){
        this.stringValue = stringValue;
        this.tiedProduct = tiedProduct;
        allCoupons.add(this);
    }
    public Product getTiedProduct() {
        return tiedProduct;
    }
    public abstract double discount();
}
