package asm1;

public class GiftItem extends ProductItem{
    private String message;
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
