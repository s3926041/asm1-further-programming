/**
 * @author Group 15
 */
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
        return this.price ; 
    }
    @Override
    public String getType(){
        return "price";
    }
}
