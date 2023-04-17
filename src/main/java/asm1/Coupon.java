package asm1;

public abstract class Coupon {
    private Product tiedProduct;
    private String stringValue;

    public Coupon(Product tiedProduct, String stringValue){
        this.stringValue = stringValue;
        this.tiedProduct = tiedProduct;
    }
    public Product getTiedProduct() {
        return tiedProduct;
    }
    public abstract double discount();
}
