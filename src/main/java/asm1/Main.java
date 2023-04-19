
/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;

import java.util.*;

public class Main {
    public static ShoppingCart cart = null;

    public static ShoppingCart getCart() {
        return cart;
    }

    public static void setCart(ShoppingCart cart) {
        Main.cart = cart;
    }

    public static void main(String[] args) {
        DataManipulate.read();
        for(String i : Product.getAllProduct().keySet()){
            System.out.println(i);
        }
        
    }

}
