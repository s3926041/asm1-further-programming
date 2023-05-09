/**
 * @author Group 15
 */
package asm2;

public class PerCentCoupon extends Coupon {
    private int percent;
    public PerCentCoupon(Product tiedProduct, String stringValue,int percent){
        super(tiedProduct,stringValue);
        this.percent = percent;
    }
    public int getPercent() {
        return percent;
    }
    @Override
    public double discount(){
        return super.getTiedProduct().getPrice() * this.percent/ 100; 
    }
    @Override
    public String getType(){
        return "percent";
    }
}
