
/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

public class Main {
    public static ShoppingCart cart = null;

    public static ShoppingCart getCart() {
        return cart;
    }

    public static void setCart(ShoppingCart cart) {
        Main.cart = cart;
    }

    public static void main(String[] args) {
        Data.read();
        
        // USER INTERACTION
 
        // Data.write();
    }

}
