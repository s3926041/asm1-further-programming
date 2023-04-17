package asm1;

public class PriceCoupon extends Coupon {
    private  double value;
    public PriceCoupon(Product tiedProduct, String stringValue,double value){
        super(tiedProduct,stringValue);
        this.value = value;
    }
    public double getValue() {
        return value;
    }
    @Override
    public double discount(){
        return super.getTiedProduct().getPrice() - this.value; 
    }
}
