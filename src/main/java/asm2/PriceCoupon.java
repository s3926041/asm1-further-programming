package asm2;

public class PriceCoupon extends Coupon {
    private  double price;
    public PriceCoupon(Product tiedProduct, String stringValue,double price){
        super(tiedProduct,stringValue);
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public double discount(){
        return super.getTiedProduct().getPrice() - this.price; 
    }
}
