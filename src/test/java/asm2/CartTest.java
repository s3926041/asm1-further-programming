package asm2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CartTest {
    @Test
    void createCart() {
        Data.read();
        ShoppingCart cart = new ShoppingCart();
        assertEquals(cart.getCartOrder(), 6);
    }

    @Test
    void addItem() {
        Data.read();
        ShoppingCart cart = new ShoppingCart();

        Product p = Product.getAllProduct().get("Running shorts");
        int pQuantity = p.getQuantity();
        cart.addItem(p.getName(), 10);

        assertEquals(pQuantity,p.getQuantity()+10);

        assertEquals(cart.getCart().get(p.getName()).getProduct(),p);
    }
    @Test
    void removeItem() {
        Data.read();
        ShoppingCart cart = new ShoppingCart();
        Product p = Product.getAllProduct().get("Running shorts");
        cart.addItem(p.getName(), 10);

        cart.removeItem(p.getName(), 11); //false

        cart.removeItem(p.getName(), 8);
        assertEquals(cart.getCart().get(p.getName()).getQuantity(),2);

        cart.removeItem(p.getName(), 2);
        assertEquals(cart.getCart().size(),0);

    }
    @Test
    void addCoupon() {
        Data.read();
        ShoppingCart cart = new ShoppingCart();
        Product p = Product.getAllProduct().get("Running shorts");

        cart.addItem(p.getName(), 10);

        cart.addCoupon(Coupon.getAllCoupons().get(0)); //Running shorts coupon
        cart.addCoupon(Coupon.getAllCoupons().get(1)); //Water bottle coupon 

        assertEquals(p,cart.getCoupon().getTiedProduct());
    }
    @Test
    void removeCoupon() {
        Data.read();
        ShoppingCart cart = new ShoppingCart();
        Product p = Product.getAllProduct().get("Running shorts");

        cart.addItem(p.getName(), 10);

        cart.addCoupon(Coupon.getAllCoupons().get(0)); //Running shorts coupon
        cart.addCoupon(Coupon.getAllCoupons().get(1)); //Water bottle coupon

        cart.removeCoupon();
        assertEquals(null,cart.getCoupon());

    }
}
