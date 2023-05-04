/**
 * @author Group 15
 */
package asm2;

public class GiftItem extends ProductItem{
    private String message = null;
    public GiftItem(Product p,int quantity ){
        super(p,quantity);
    }
    public void setMessage(String msg) {
        this.message = msg;
    }
    public String getMessage() {
        return this.message;
    }
}
