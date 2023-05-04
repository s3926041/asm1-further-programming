/**
 * @author Group 15
 */
package asm2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DataTest {
    @Test 
    void read(){
        Data.read();
        assertEquals(30,Product.getAllProduct().size());
        assertEquals(15, Coupon.getAllCoupons().size());
        assertEquals(5, ShoppingCart.getAllCart().size());
    }
}
