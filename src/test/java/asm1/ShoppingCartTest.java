/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ShoppingCartTest {
    @Test
    public void testAddItem() {
        // create some products
        Product p1 = new PhysicalProduct("book", "book", 10, 25.0, 2.0);
        Product p2 = new DigitalProduct("ebook", "ebook", 0, 10.0);
        
        // create a shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // add the products to the cart
        assertTrue(cart.addItem(p1.getName()));
       
        // adding a product with zero quantity should fail
        assertFalse(cart.addItem(p2.getName()));
        
        // adding an already added product should fail
        assertFalse(cart.addItem(p1.getName()));
    }
    
    @Test
    public void testRemoveItem() {
        // create some products
        Product p1 = new PhysicalProduct("book", "book", 10, 25.0, 2.0);
        // create a shopping cart
        ShoppingCart cart = new ShoppingCart();

        // add the products to the cart
        cart.addItem(p1.getName());

        // remove an existing product
        assertTrue(cart.removeItem(p1.getName()));
        
        // removing a non-existing product should fail
        assertFalse(cart.removeItem(p1.getName()));
    }
    
    @Test
    public void testCartAmount() {
        // create some products
        Product p1 = new PhysicalProduct("book", "book", 10, 25.0, 2.0);
        Product p2 = new DigitalProduct("ebook", "ebook", 2, 10.0);
        
        // create a shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // add the products to the cart
        cart.addItem(p1.getName());
        cart.addItem(p2.getName());
        // calculate the cart amount
        double expectedAmount = p1.getPrice() + p2.getPrice() + (((PhysicalProduct) p1).getWeight() * 0.1);
        double actualAmount = cart.cartAmount();
        assertTrue(expectedAmount == actualAmount);
    }
}
