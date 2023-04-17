/**
 * @author <Nguyen Thanh Hung - s3926041>
 */
package asm1;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testCreatProduct() {
        // information
        String digitalBook = "book\n" + // name
                "a book\n" + // description
                "10\n" + // quantity
                "25\n" + "n\n"; // price
        ByteArrayInputStream in = new ByteArrayInputStream(digitalBook.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        Main.createProduct(scanner);
        // create data
        Product digitalProduct = new DigitalProduct("book", "a book", 10, 25);
        assertEquals(digitalProduct, Product.getAllProduct().get("book"));

        // information
        String physicalBook = "pbook\n" + // name
                "a pbook\n" + // description
                "20\n" + // quantity
                "35\n" + // price
                "y\n" + // choice
                "2"; // weight
        in = new ByteArrayInputStream(physicalBook.getBytes());
        System.setIn(in);
        Scanner scanner2 = new Scanner(System.in);
        Main.createProduct(scanner2);
        // create data
        Product physicalProduct = new PhysicalProduct("pbook", "a pbook", 25, 35, 2);
        assertEquals(physicalProduct, Product.getAllProduct().get("pbook"));
    }

    @Test
    void testEditProduct() {
        Product product = new DigitalProduct("Test Product", "Test Description", 10, 9.99);
        // User input for the test
        String input = "Test Product\n" + // product name to edit
                "1\n" + // change description option
                "New Description\n"; // new description
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Call the method and check the result
        Main.editProduct(scanner);
        assertEquals("New Description", product.getDescription());
    }

    @Test
    void testAllOtherMethods() {
        Product product = new DigitalProduct("Test Product", "Test Description", 10, 9.99);
        Product product2 = new PhysicalProduct("Test2", "Test2", 2, 10, 3);
        Product product3 = new PhysicalProduct("Test3", "Test3", 2, 10, 32);

        Main.createCart();

        String input = "Test Product"; // new description
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        // add product with main method
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        Main.addProduct(scanner);
        assertTrue(Main.cart.getProductNames().contains(product.getName()));

        // add product
        Main.cart.addItem("Test2");
        assertTrue(Main.cart.getProductNames().contains(product2.getName()));

        // create new cart
        Main.createCart();
        Main.cart.addItem(product3.getName());

        // create new cart
        Main.createCart();
        Main.cart.addItem(product.getName());

        // sorted set by weight
        // cart1 : product1 + product2 = 3 weight
        // cart2: product3 = 32 weight
        // cart3: product1 = 0 weight
        double prev = 0;
        boolean check = true;
        ShoppingCart.sortList();
        for (ShoppingCart s : ShoppingCart.getAllCart()) {
            if (s.getTotalWeight() < prev) {
                check = false;
                break;
            }
            prev = s.getTotalWeight();
        }
        assertTrue(check);
        assertTrue(ShoppingCart.getAllCart().size() == 3);

        product.setPrice(2232); 
        assertEquals(Product.getAllProduct().get(product.getName()).getPrice(), 2232);

        // remove product
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        Main.removeProduct(scanner);
        assertTrue(!Main.cart.getProductNames().contains(product2.getName()));
    }
}